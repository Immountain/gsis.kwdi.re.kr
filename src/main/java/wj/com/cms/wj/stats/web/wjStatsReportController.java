package wj.com.cms.wj.stats.web;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.stats.service.WjStatsReportService;
import wj.com.cms.wj.stats.vo.WjStatsReportVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class wjStatsReportController  {

    @Resource(name="WjStatsReportService")
    private WjStatsReportService wjStatsReportService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "LangPackService")
    private LangPackService langPackService;

    private String pagePath="wj/stats/";

    @IncludedInfo(name="제주도민현황", listUrl = "/cms/wj/stats/wjStatsReportList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/wj/stats/wjStatsReportList.do")
    public String wjStatsReportList(@ModelAttribute("searchVO") WjStatsReportVO searchVO, ModelMap model)
        throws Exception{

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

        List<?> list = wjStatsReportService.selectStatsReportList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = wjStatsReportService.selectStatsReportTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminWjView(pagePath, "wjStatsReportList", "ax5ui");
    }

    @RequestMapping(value="/cms/wj/stats/wjStatsReportRegistView.do")
    public String registView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") WjStatsReportVO resultVO) throws Exception {

        resultVO.setListLang(langPackService.getSelectWjStatsReportLang(resultVO));
        model.addAttribute("resultVO", resultVO);

        return InfoViewUtils.adminWjView(pagePath,"wjStatsReportRegist","axmodal");
    }

    @RequestMapping(value="/cms/wj/stats/wjStatsReportRegist.do")
    @ResponseBody
    public ModelAndView wjStatsReportRegist(@ModelAttribute("resultVO")WjStatsReportVO resultVO)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        wjStatsReportService.insertStatsReport(resultVO);

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/stats/wjStatsReportUpdtView.do")
    public String UpdateWjFestivityInfoView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                                            @ModelAttribute("resultVO") WjStatsReportVO wjStatsReportVO) throws Exception {

        WjStatsReportVO resultVO = wjStatsReportService.selectStatsReport(wjStatsReportVO);
        resultVO.setListLang(langPackService.getSelectWjStatsReportLang(wjStatsReportVO));

        model.addAttribute("resultVO", resultVO);

        return InfoViewUtils.adminWjView(pagePath,"wjStatsReportUpdt","axmodal");
    }

    @RequestMapping(value="/cms/wj/stats/wjStatsReportUpdt.do")
    @ResponseBody
    public ModelAndView wjStatsReportUpdt(@ModelAttribute("resultVO")WjStatsReportVO resultVO)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="수정되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjStatsReportService.updateStatsReport(resultVO);

        return modelAndView;
    }

    @RequestMapping(value ="/cms/wj/stats/wjStatsReportDelete.do")
    @ResponseBody
    public ModelAndView wjStatsReportDelete(@ModelAttribute("resultVO")WjStatsReportVO resultVO)throws Exception{

        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="삭제되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjStatsReportService.deleteStatsReport(resultVO);

        return modelAndView;
    }
}
