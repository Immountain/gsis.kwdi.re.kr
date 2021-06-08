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
import wj.com.cms.wj.festivity.service.WjFestivityApplicationService;
import wj.com.cms.wj.festivity.vo.WjFestivityApplicationVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class WjFestivityApplicationController {

    @Resource(name="WjFestivityApplicationService")
    private WjFestivityApplicationService wjFestivityApplicationService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "LangPackService")
    private LangPackService langPackService;

    private String pagePath="wj/festivity/";

    @IncludedInfo(name="참가신청", listUrl = "/cms/wj/festivity/wjFestivityApplicationList.do", order = 1111 ,gid = 60)
    @RequestMapping(value ="/cms/wj/festivity/wjFestivityApplicationList.do")
    public String wjFestivityInfoList(@ModelAttribute("searchVO") WjFestivityApplicationVO searchVO,
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

        List<?> list = wjFestivityApplicationService.selectFestivityApplicationList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = wjFestivityApplicationService.selectFestivityApplicationTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminWjView(pagePath, "wjFestivityApplicationList", "ax5ui");
    }

    @RequestMapping(value="/cms/wj/festivity/wjFestivityApplicationRegistView.do")
    public String wjFestivityApplicationList(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO")CmsSearchVO userSearchVO,
                                             @ModelAttribute("resultVO") WjFestivityApplicationVO resultVO)throws Exception{

        resultVO.setListLang(langPackService.getWjFestivityApplicationLang(resultVO));
        model.addAttribute("resultVO", resultVO);

        return InfoViewUtils.adminWjView(pagePath, "wjFestivityApplicataonRegist", "ax5ui");
    }

    @RequestMapping(value="/cms/wj/festivity/wjFestivityApplicationRegist.do")
    @ResponseBody
    public ModelAndView wjFestivityApplicationRegist(@ModelAttribute("resultVO")WjFestivityApplicationVO resultVO)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="등록되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message",resultMsg);

        wjFestivityApplicationService.insertFestivityApplication(resultVO);

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/festivity/wjFestivityApplicationUpdtView.do")
    public String wjFestivityApplicationUpdtView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                                            @ModelAttribute("resultVO") WjFestivityApplicationVO wjFestivityApplicationVO) throws Exception {

        WjFestivityApplicationVO result = wjFestivityApplicationService.selectFestivityApplication(wjFestivityApplicationVO);

        result.setListLang(langPackService.getWjFestivityApplicationLang(wjFestivityApplicationVO));

        model.addAttribute("resultVO", result);

        return InfoViewUtils.adminWjView(pagePath,"wjFestivityApplicataonUpdt","axmodal");
    }

    //	참가신청설정수정 ajax
    @RequestMapping(value="/cms/wj/festivity/wjFestivityApplicationUpdt.do")
    @ResponseBody
    public ModelAndView wjFestivityApplicationUpdt(@ModelAttribute("resultVO") WjFestivityApplicationVO resultVO,
                                                      BindingResult bindingResult) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        String resultMsg="수정되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjFestivityApplicationService.updateFestivityApplication(resultVO);


        return modelAndView;
    }

}
