package wj.com.cms.wj.event.web;
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
import wj.com.cms.wj.event.service.WjEventInfoService;
import wj.com.cms.wj.event.vo.WjEventInfoVO;
import wj.com.cms.wj.festivity.vo.WjFestivityScheduleVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class WjEventInfoController {

    @Resource(name="WjEventInfoService")
    private WjEventInfoService wjEventInfoService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "LangPackService")
    private LangPackService langPackService;

    private String pagePath="wj/event/";

    @IncludedInfo(name="이벤트", listUrl = "/cms/wj/event/wjEventInfoList.do", order= 1111, gid = 60)
    @RequestMapping(value="/cms/wj/event/wjEventInfoList.do")
    public String wjEventInfoList(@ModelAttribute("searchVO") WjEventInfoVO searchVO,
                                  ModelMap model)throws Exception{

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

        List<?> list = wjEventInfoService.selectEventInfoList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = wjEventInfoService.selectEventInfoTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminWjView(pagePath, "wjEventInfoList", "ax5ui");
    }

    @RequestMapping(value="/cms/wj/event/wjEventInfoRegistView.do")
    public String registView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") WjEventInfoVO resultVO) throws Exception {

        resultVO.setListLang(langPackService.getWjEventLang(resultVO));
        model.addAttribute("resultVO",resultVO);

        return InfoViewUtils.adminWjView(pagePath, "wjEventInfoRegist", "ax5ui");
    }

    @RequestMapping(value="/cms/wj/event/wjEventInfoRegist.do")
    @ResponseBody
    public ModelAndView wjEventInfoRegist (@ModelAttribute("resultVO")WjEventInfoVO resultVO)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="등록되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message",resultMsg);

        wjEventInfoService.insertEventInfo(resultVO);

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/event/wjEventInfoUpdtView.do")
    public String wjEventInfoUpdtView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") WjEventInfoVO resultVO) throws Exception {

        WjEventInfoVO result = wjEventInfoService.selectEventInfo(resultVO);
        result.setListLang(langPackService.getWjEventLang(resultVO));
        model.addAttribute("resultVO",result);

        return InfoViewUtils.adminWjView(pagePath, "wjEventInfoUpdt", "ax5ui");
    }

    @RequestMapping(value="/cms/wj/event/wjEventInfoUpdt.do")
    @ResponseBody
    public ModelAndView wjEventInfoUpdt(@ModelAttribute("resultVO")WjEventInfoVO resultVO)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="등록되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message",resultMsg);

        wjEventInfoService.updateEventInfo(resultVO);

        return modelAndView;
    }
}
