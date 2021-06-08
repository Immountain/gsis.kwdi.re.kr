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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.festivity.service.WjFestivityInfoService;
import wj.com.cms.wj.festivity.service.WjFestivityScheduleService;
import wj.com.cms.wj.festivity.vo.WjFestivityScheduleVO;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
public class WjFestivityScheduleController {


    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name="WjFestivityInfoService")
    private WjFestivityInfoService wjFestivityInfoService;
    @Resource(name = "WjFestivityScheduleService")
    private WjFestivityScheduleService wjFestivityScheduleService;

    private String pagePath = "wj/festivity/";

    @Resource(name = "LangPackService")
    private LangPackService langPackService;

    @IncludedInfo(name="대회일정", listUrl = "/cms/wj/festivity/wjFestivityScheduleList.do", order = 1111 ,gid = 60)
    @RequestMapping(value ="/cms/wj/festivity/wjFestivityScheduleList.do")
    public String wjFestivityScheduleList(@ModelAttribute("searchVO") WjFestivityScheduleVO searchVO,
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

        List<?> list = wjFestivityScheduleService.selectFestivityScheduleList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = wjFestivityScheduleService.selectFestivityScheduleTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);


        return InfoViewUtils.adminWjView(pagePath, "wjFestivityScheduleList", "ax5ui");

    }

    @RequestMapping(value="/cms/wj/festivity/RegistWjFestivityScheduleView.do")
    public String registView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                                     @ModelAttribute("resultVO") WjFestivityScheduleVO resultVO) throws Exception {

        resultVO.setListLang(langPackService.getSelectWjFestivityScheduleLang(resultVO));

        model.addAttribute("resultVO", resultVO);

        return InfoViewUtils.adminWjView(pagePath,"wjFestivityScheduleRegist","axmodal");
    }


    //	대회정보등록 ajax
    @RequestMapping(value="/cms/wj/festivity/RegistWjFestivityScheduleLang.do")
    @ResponseBody
    public ModelAndView RegistWjFestivityInfoLang(@ModelAttribute("resultVO") WjFestivityScheduleVO resultVO,
                                              BindingResult bindingResult) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        String resultMsg="등록되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjFestivityScheduleService.insertFestivitySchedule(resultVO);


        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/festivity/wjFestivityScheduleUpdateView.do")
    public String UpdateWjFestivityInfoView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                                            @ModelAttribute("resultVO") WjFestivityScheduleVO wjFestivityScheduleVO) throws Exception {

        WjFestivityScheduleVO result = wjFestivityScheduleService.selectFestivitySchedule(wjFestivityScheduleVO);

        result.setListLang(langPackService.getSelectWjFestivityScheduleLang(wjFestivityScheduleVO));

        model.addAttribute("resultVO", result);

        return InfoViewUtils.adminWjView(pagePath,"wjFestivityScheduleUpdt","axmodal");
    }

    //	대회정보수정 ajax
    @RequestMapping(value="/cms/wj/festivity/UpdateWjFestivityScheduleLang.do")
    @ResponseBody
    public ModelAndView UpdateWjFestivityScheduleLang(@ModelAttribute("resultVO") WjFestivityScheduleVO resultVO,
                                                  BindingResult bindingResult) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        String resultMsg="수정되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjFestivityScheduleService.updateFestivitySchedule(resultVO);


        return modelAndView;
    }

    //schedule delete
    @RequestMapping(value="/cms/wj/festivity/deleteWjFestivitySchedule.do")
    @ResponseBody
    public ModelAndView deleteWjFestivitySchedule(@ModelAttribute("resultVO") WjFestivityScheduleVO vo) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        wjFestivityScheduleService.deleteFestivitySchedule(vo);

        return modelAndView;
    }

}
