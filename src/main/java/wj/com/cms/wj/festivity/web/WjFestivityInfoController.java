package wj.com.cms.wj.festivity.web;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.ext.service.LangPackService;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.festivity.service.WjFestivityInfoService;
import wj.com.cms.wj.festivity.service.WjFestivityLangService;
import wj.com.cms.wj.festivity.vo.WjFestivityInfoVO;
import wj.com.cms.wj.festivity.vo.WjFestivityLangVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
public class WjFestivityInfoController {


    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


    @Resource(name = "WjFestivityInfoService")
    private WjFestivityInfoService wjFestivityInfoService;

    @Resource(name = "WjFestivityLangService")
    private WjFestivityLangService wjFestivityLangService;




    @Resource(name = "LangPackService")
    private LangPackService langPackService;




    private String pagePath = "wj/festivity/";


    @IncludedInfo(name="대회 정보", listUrl = "/cms/wj/festivity/wjFestivityInfoList.do", order = 1111 ,gid = 60)
    @RequestMapping(value ="/cms/wj/festivity/wjFestivityInfoList.do")
    public String wjFestivityInfoList(@ModelAttribute("searchVO") WjFestivityInfoVO searchVO,
                                      ModelMap model) throws Exception {

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

        List<?> list = wjFestivityInfoService.selectFestivityInfoList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = wjFestivityInfoService.selectFestivityInfoTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminWjView(pagePath, "wjFestivityInfoList", "ax5ui");

    }

    @RequestMapping(value="/cms/wj/festivity/RegistWjFestivityInfoView.do")
    public String registView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") WjFestivityInfoVO wjFestivityInfoVO) throws Exception {

        wjFestivityInfoVO.setListLang(langPackService.getSelectWjFestivityLang(wjFestivityInfoVO));

        model.addAttribute("resultVO", wjFestivityInfoVO);

        return InfoViewUtils.adminWjView(pagePath,"wjFestivityInfoRegist","axmodal");
    }



    //	대회정보등록 ajax
    @RequestMapping(value="/cms/wj/festivity/RegistWjFestivityInfo.do")
    @ResponseBody
    public ModelAndView RegistWjFestivityInfo(@ModelAttribute("resultVO") WjFestivityInfoVO resultVO,
          BindingResult bindingResult) throws Exception{



        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        String resultMsg="등록되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjFestivityInfoService.insertFestivityInfo(resultVO);


        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/festivity/UpdateWjFestivityInfoView.do")
    public String UpdateWjFestivityInfoView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") WjFestivityInfoVO wjFestivityInfoVO, WjFestivityLangVO wjFestivityLang) throws Exception {

        WjFestivityInfoVO result = wjFestivityInfoService.selectFestivityInfo(wjFestivityInfoVO);

        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminWjView(pagePath,"wjFestivityInfoUpdt","axmodal");
    }


    //	대회정보등록 ajax
    @RequestMapping(value="/cms/wj/festivity/UpdateWjFestivityInfo.do")
    @ResponseBody
    public ModelAndView UpdateWjFestivityInfo(@ModelAttribute("resultVO") WjFestivityInfoVO vo,
                          BindingResult bindingResult, ModelMap model) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="수정되었습니다";


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjFestivityInfoService.updateFestivityInfo(vo);


        return modelAndView;
    }

    //festivityInfo delete
    @RequestMapping(value="/cms/wj/festivity/deleteWjFestivityInfo.do")
    @ResponseBody
    public ModelAndView deleteWjFestivityInfo(@ModelAttribute("resultVO")WjFestivityInfoVO vo) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        wjFestivityInfoService.deleteFestivityInfo(vo);

        return modelAndView;
    }

    @RequestMapping(value ="/cms/wj/festivity/popWjFestivityInfoList.do")
    public String popWjFestivityInfoList(@ModelAttribute("searchVO") WjFestivityInfoVO searchVO,
                                      ModelMap model) throws Exception {

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

        List<?> list = wjFestivityInfoService.selectFestivityInfoList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = wjFestivityInfoService.selectFestivityInfoTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminWjView(pagePath, "popWjFestivityInfoList", "axmodal");

    }
 }
