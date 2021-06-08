package wj.com.cms.wj.program.web;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cmm.web.BaseController;
import infomind.com.ext.service.LangPackService;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.festivity.service.WjFestivityInfoService;
import wj.com.cms.wj.festivity.vo.WjFestivityInfoVO;
import wj.com.cms.wj.program.service.WjProgramInfoLangService;
import wj.com.cms.wj.program.service.WjProgramInfoService;
import wj.com.cms.wj.program.vo.WjProgramInfoVO;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
public class WjProgramInfoController extends BaseController {

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name="WjFestivityInfoService")
    private WjFestivityInfoService wjFestivityInfoService;

    @Resource(name = "WjProgramInfoService")
    private WjProgramInfoService wjProgramInfoService;

    @Resource(name = "WjProgramInfoLangService")
    private WjProgramInfoLangService wjProgramInfoLangService;

    private String pagePath = "wj/program/";

    @Resource(name = "LangPackService")
    private LangPackService langPackService;


    @RequestMapping(value="/cms/wj/program/WjProgramInfoList.do")
    public String WjProgramInfoList(@ModelAttribute("searchVO")WjProgramInfoVO searchVO,WjFestivityInfoVO wjFestivityInfoVO, ModelMap model)throws Exception{

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

        wjFestivityInfoVO.setFirstIndex(0);

        List<?> list = wjProgramInfoService.selectProgramInfoList(searchVO);

        List<?>wjFestivityList =wjFestivityInfoService.selectFestivityInfoSearchList(wjFestivityInfoVO);


        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);
        model.addAttribute("wjFestivityList", wjFestivityList);

        int totalCnt = wjProgramInfoService.selectProgramInfoTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminWjView(pagePath,"wjProgramInfoList","ax5ui");

    }

    @RequestMapping(value="/cms/wj/program/RegistWjProgramInfoView.do")
    public String registView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") WjProgramInfoVO wjProgramInfoVO) throws Exception {

        wjProgramInfoVO.setListLang(langPackService.getSelectWjProgramInfoLang(wjProgramInfoVO));

        model.addAttribute("resultVO", wjProgramInfoVO);
        return InfoViewUtils.adminWjView(pagePath,"wjProgramInfoRegist","axmodal");
    }


    //	다국어 코드언어팩 ajax
    @RequestMapping(value="/cms/wj/program/RegistWjProgramInfo.do")
    @ResponseBody
    public ModelAndView RegistWjProgramInfo(@ModelAttribute("resultVO") WjProgramInfoVO resultVO
    ,BindingResult bindingResult) throws Exception{

        String resultMessage="";

        resultMessage = "등록되었습니다";

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMessage);

        wjProgramInfoService.insertProgramInfo(resultVO);

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/program/wjProgramInfoUpdateView.do")
    public String wjProgramInfoUpdateView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") WjProgramInfoVO wjProgramInfoVO) throws Exception {

        WjProgramInfoVO result = wjProgramInfoService.selectProgramInfo(wjProgramInfoVO);

        result.setListLang(langPackService.getSelectWjProgramInfoLang(wjProgramInfoVO));

        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminWjView(pagePath,"wjProgramInfoUpdt","axmodal");
    }

    @RequestMapping(value="/cms/wj/program/wjProgramInfoUpdate.do")
    @ResponseBody
    public ModelAndView wjProgramInfoUpdate(@ModelAttribute("resultVO")WjProgramInfoVO vo,
                                            BindingResult bindingResult,ModelMap model)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="수정되었습니다";


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjProgramInfoService.updateProgramInfo(vo);


        return modelAndView;
    }


    @RequestMapping(value="/cms/wj/program/DeleteWjProgramInfo.do")
    @ResponseBody
    public ModelAndView DeleteWjProgramInfo(@ModelAttribute("resultVO")WjProgramInfoVO vo)throws  Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        wjProgramInfoService.deleteProgramInfo(vo);

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/program/wjProgramInfoContentUpdateView.do")
    public String wjProgramInfoContentUpdateView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                                          @ModelAttribute("resultVO") WjProgramInfoVO wjProgramInfoVO) throws Exception {

        WjProgramInfoVO result = wjProgramInfoService.selectProgramInfo(wjProgramInfoVO);

        result.setListLang(langPackService.getSelectWjProgramInfoLang(wjProgramInfoVO));

        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminWjView(pagePath,"wjProgramInfoContentUpdt","axmodal");
    }

    @RequestMapping(value="/cms/wj/program/wjProgramInfoContentUpdate.do")
    @ResponseBody
    public ModelAndView wjProgramInfoContentUpdate(@ModelAttribute("resultVO")WjProgramInfoVO vo,
                                            BindingResult bindingResult,ModelMap model)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="수정되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjProgramInfoLangService.updateProgramInfoContentLang(vo);

        return modelAndView;
    }
}
