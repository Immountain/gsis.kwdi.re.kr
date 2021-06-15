package infomind.com.cms.info.page.web;
import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cmm.InfoConstants;
import infomind.com.cms.ccm.cca.vo.InfoCmmnCodeVO;
import infomind.com.cms.ccm.ccc.vo.InfoCmmnClCode;
import infomind.com.cms.ccm.cde.vo.InfoCmmnDetailCodeVO;
import infomind.com.cms.info.page.service.InfoPageGroupService;
import infomind.com.cms.info.page.service.InfoPageInfoService;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import infomind.com.cms.info.page.vo.InfoPageInfoValidator;
import infomind.com.cms.sym.prm.vo.InfoProgrmManageVO;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.utils.page.InfoPageUtils;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class InfoPageInfoController {

    @Resource(name = "InfoPageInfoService")
    private InfoPageInfoService infoPageInfoService;


    @Resource(name = "InfoPageGroupService")
    private InfoPageGroupService infoPageGroupService;



    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;




    private String pagePath ="info/page/";

    @IncludedInfo(name="페이지 관리",listUrl="/cms/info/page/selectPageInfoList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/page/selectPageInfoList.do")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoPageInfoVO searchVO, ModelMap model) throws Exception {



        System.out.println("2=PAGE_DIR==>"+ InfoConstants.PAGE_DIR());

//        try {
//            System.out.println("PAGE_DIR==>"+ InfoConstants.PAGE_DIR());
//
//        }catch (Exception e){
//
//
//            System.out.println("PAGE_DIR==>"+ e);
//        }



        // 내역 조회
        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> list_progrmmanage = infoPageInfoService.selectPageInfoList(searchVO);

        model.addAttribute("list_progrmmanage", list_progrmmanage);
        model.addAttribute("searchVO", searchVO);

        int totCnt = infoPageInfoService.selectPageListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);



        List<?> list_group = infoPageGroupService.getSelectPageGroupAll(null);
        model.addAttribute("list_group", list_group);


        return InfoViewUtils.adminTilesView(pagePath,"InfoPageInfoList","ax5ui");
    }



    @RequestMapping(value="/cms/info/page/RegistPageInfoView.do")
    public String registPageInfoView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("infoPageInfoVO") InfoPageInfoVO infoPageInfoVO, BindingResult bindingResult, ModelMap model)throws Exception {



        List<?> list_group = infoPageGroupService.getSelectPageGroupAll(null);
        model.addAttribute("list_group", list_group);
        return InfoViewUtils.adminTilesView(pagePath,"InfoPageRegist","sub");
    }


    @RequestMapping("/cms/info/page/insertPageInfo.do")
    public String insertPageInfo(@ModelAttribute("infoPageInfoVO") InfoPageInfoVO infoPageInfoVO,
                                 BindingResult bindingResult, ModelMap model) throws Exception {



        //체크
        InfoPageInfoValidator mValidator = new InfoPageInfoValidator();
        mValidator.validate(infoPageInfoVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {

            List<?> list_group = infoPageGroupService.getSelectPageGroupAll(null);
            model.addAttribute("list_group", list_group);

            return InfoViewUtils.adminTilesView(pagePath,"InfoPageRegist","sub");
        }

        if(infoPageInfoVO.getPageId() != null){
            InfoPageInfoVO result = infoPageInfoService.selecPageInfoDetail(infoPageInfoVO);
            if(result != null){


                List<?> list_group = infoPageGroupService.getSelectPageGroupAll(null);
                model.addAttribute("list_group", list_group);

                model.addAttribute("message", "이미 등록된 페이지 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoPageRegist","sub");
            }
        }


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoPageInfoVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoPageInfoVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoPageInfoService.insertPageInfo(infoPageInfoVO);
        if("JSP".equals(infoPageInfoVO.getModType())){
            //만들어준다...
            InfoPageUtils.createPageFile(infoPageInfoVO.getContentInfoDecode(),infoPageInfoVO.getPageId(), infoPageInfoVO.getPageGroupId());
        }

        return "forward:/cms/info/page/selectPageInfoList.do";


    }


    @RequestMapping("/cms/info/page/UpdatePageInfoView.do")
    public String updateCmmnDetailCodeView(@ModelAttribute("loginVO") LoginVO loginVO,
                                           @ModelAttribute("infoPageInfoVO") InfoPageInfoVO searchVO, ModelMap model)
            throws Exception {

        InfoPageInfoVO result = infoPageInfoService.selecPageInfoDetail(searchVO);
        model.addAttribute("infoPageInfoVO", result);


        List<?> list_group = infoPageGroupService.getSelectPageGroupAll(null);
        model.addAttribute("list_group", list_group);



        return InfoViewUtils.adminTilesView(pagePath,"InfoPageUpdt","sub");
    }



    @RequestMapping("/cms/info/page/updatePageInfo.do")
    public String updatePageInfo(@ModelAttribute("infoPageInfoVO") InfoPageInfoVO infoPageInfoVO,
                                 BindingResult bindingResult, ModelMap model) throws Exception {
       //체크
        InfoPageInfoValidator mValidator = new InfoPageInfoValidator();
        mValidator.validate(infoPageInfoVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {

            List<?> list_group = infoPageGroupService.getSelectPageGroupAll(null);
            model.addAttribute("list_group", list_group);


            return InfoViewUtils.adminTilesView(pagePath,"InfoPageUpdt","sub");
        }

//        if(infoPageInfoVO.getPageId() != null){
//            InfoPageInfoVO result = infoPageInfoService.selecPageInfoDetail(infoPageInfoVO);
//            if(result != null){
//                model.addAttribute("message", "이미 등록된 페이지 아이디 입니다");
//                return InfoViewUtils.adminTilesView(pagePath,"InfoPageUpdt","sub");
//            }
//        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoPageInfoVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());



        infoPageInfoService.updatePageInfo(infoPageInfoVO);
        if("JSP".equals(infoPageInfoVO.getModType())){
            //만들어준다...
            InfoPageUtils.createPageFile(infoPageInfoVO.getContentInfoDecode(),infoPageInfoVO.getPageId(),infoPageInfoVO.getPageGroupId());
        }
        return "forward:/cms/info/page/selectPageInfoList.do";
    }


    @RequestMapping("/cms/info/page/samplePageInfo.do")
    public String samplePageInfo(@ModelAttribute("infoPageInfoVO") InfoPageInfoVO infoPageInfoVO,ModelMap model) throws Exception {


        String url ="";
         model.addAttribute("contentInfo",infoPageInfoVO.getContentInfoDecode());

       if("".equals(infoPageInfoVO.getLayoutId())||infoPageInfoVO.getLayoutId()==null){

           url ="page/default";
       }else{
           url ="page/default";
       }


        return url;
    }




    @RequestMapping(value="/cms/info/page/popInfoPageInfoList.do")
    public String popInfoPageInfoList(@ModelAttribute("searchVO") InfoPageInfoVO searchVO, ModelMap model) throws Exception {

        // 내역 조회
        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> list_progrmmanage = infoPageInfoService.selectPageInfoList(searchVO);

        model.addAttribute("list_progrmmanage", list_progrmmanage);
        model.addAttribute("searchVO", searchVO);

        int totCnt = infoPageInfoService.selectPageListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"popInfoPageInfoList","sub");
    }

    @RequestMapping(value="/cms/info/page/popInfoPageInfoHisList.do")
    public String popInfoPageInfoHisList(@ModelAttribute("searchVO") InfoPageInfoVO searchVO, ModelMap model) throws Exception {

        // 내역 조회
        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> list_progrmmanage = infoPageInfoService.selectPageInfoHisList(searchVO);

        model.addAttribute("list_progrmmanage", list_progrmmanage);
        model.addAttribute("searchVO", searchVO);

        int totCnt = infoPageInfoService.selectPageListHisTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"popInfoPageInfoHisList","axmodal");
    }

    @RequestMapping(value="/cms/info/page/popInfoPageInfoHisUpdtView.do")
    public String popInfoPageInfoHisUpdtView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                                            @ModelAttribute("resultVO") InfoPageInfoVO infoPageInfoVO) throws Exception {

        InfoPageInfoVO result = infoPageInfoService.selectPageInfoHis(infoPageInfoVO);

        model.addAttribute("resultVO", result);

        return InfoViewUtils.adminTilesView(pagePath,"popInfoPageInfoHisUpdt","axmodal");
    }

    @RequestMapping(value="/cms/info/page/popInfoPageInfoHisUpdt.do")
    @ResponseBody
    public ModelAndView popInfoPageInfoHisUpdt(@ModelAttribute("resultVO")InfoPageInfoVO vo)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="수정되었습니다";


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        infoPageInfoService.updatePageInfoHis(vo);

        return modelAndView;
    }

}