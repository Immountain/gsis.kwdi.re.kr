package infomind.com.cms.info.layout.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.layout.service.InfoLayoutInfoService;
import infomind.com.cms.info.layout.vo.InfoLayoutInfoVO;
import infomind.com.cms.info.layout.vo.InfoLayoutInfoValidator;
import infomind.com.utils.page.InfoPageUtils;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class InfoLayoutInfoController {


    @Resource(name = "InfoLayoutInfoService")
    private InfoLayoutInfoService infoLayoutInfoService;


    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;



    private String pagePath ="info/layout/";


    @IncludedInfo(name="레이아웃 관리",listUrl="/cms/info/layout/selectLayoutInfoList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/layout/selectLayoutInfoList.do")
    public String selectLayoutInfoList(@ModelAttribute("searchVO") InfoLayoutInfoVO searchVO, ModelMap model) throws Exception {


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

        List<?> list_progrmmanage = infoLayoutInfoService.selectLayoutInfoList(searchVO);

        model.addAttribute("list_progrmmanage", list_progrmmanage);
        model.addAttribute("searchVO", searchVO);

        int totCnt = infoLayoutInfoService.selectLayoutInfoListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"InfoLayoutInfoList","sub");
    }

    @RequestMapping(value="/cms/info/layout/RegistlayoutInfoView.do")
    public String RegistlayoutInfoView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("infoLayoutInfoVO") InfoLayoutInfoVO infoLayoutInfoVO, BindingResult bindingResult, ModelMap model)throws Exception {

            System.out.println("RegistlayoutInfoView==>");
            model.addAttribute("layoutList", InfoConstants.PAGE_LAYOUT_LIST());






        return InfoViewUtils.adminTilesView(pagePath,"InfoLayoutInfoRegist","sub");
    }

    @RequestMapping("/cms/info/layout/insertLayoutInfo.do")
    public String insertPageInfo(@ModelAttribute("infoLayoutInfoVO") InfoLayoutInfoVO infoLayoutInfoVO,
                                 BindingResult bindingResult, ModelMap model) throws Exception {



        //체크
        InfoLayoutInfoValidator mValidator = new InfoLayoutInfoValidator();
        mValidator.validate(infoLayoutInfoVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoLayoutInfoRegist","sub");
        }

        if(infoLayoutInfoVO.getLayoutId() != null){
            InfoLayoutInfoVO result = infoLayoutInfoService.selecLayoutInfoDetail(infoLayoutInfoVO);
            if(result != null){
                model.addAttribute("message", "이미 등록된 레이아웃 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoLayoutInfoRegist","sub");
            }
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoLayoutInfoVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoLayoutInfoVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());


        infoLayoutInfoService.insertLayoutInfo(infoLayoutInfoVO);


        if("JSP".equals(infoLayoutInfoVO.getLayoutType())){
            //만들어준다...
            InfoPageUtils.createLayoutFile(infoLayoutInfoVO.getContentInfoDecode(),infoLayoutInfoVO.getLayoutId());
        }


        return "forward:/cms/info/layout/selectLayoutInfoList.do";


    }


    @RequestMapping("/cms/info/layout/UpdateLayoutInfoView.do")
    public String UpdateLayoutInfoView(@ModelAttribute("loginVO") LoginVO loginVO,
                                           @ModelAttribute("infoLayoutInfoVO") InfoLayoutInfoVO searchVO, ModelMap model)
            throws Exception {

        InfoLayoutInfoVO result = infoLayoutInfoService.selecLayoutInfoDetail(searchVO);
        model.addAttribute("infoLayoutInfoVO", result);


        InfoLayoutInfoVO contentInfo = infoLayoutInfoService.selecLayoutContentInfo(searchVO);
        model.addAttribute("contentInfo", contentInfo);


        model.addAttribute("layoutList", InfoConstants.PAGE_LAYOUT_LIST());

        return InfoViewUtils.adminTilesView(pagePath,"InfoLayouInfoUpdt","sub");
    }


    @RequestMapping("/cms/info/layout/updateLayoutInfo.do")
    public String updateLayoutInfo(@ModelAttribute("infoLayoutInfoVO") InfoLayoutInfoVO infoLayoutInfoVO,
                                 BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoLayoutInfoValidator mValidator = new InfoLayoutInfoValidator();
        mValidator.validate(infoLayoutInfoVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoLayouInfoUpdt","sub");
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoLayoutInfoVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoLayoutInfoService.updateLayoutInfo(infoLayoutInfoVO);

        if("JSP".equals(infoLayoutInfoVO.getLayoutType())){
            //만들어준다...
            InfoPageUtils.createLayoutFile(infoLayoutInfoVO.getContentInfoDecode(),infoLayoutInfoVO.getLayoutId());
        }
        return "forward:/cms/info/layout/selectLayoutInfoList.do";
    }

}
