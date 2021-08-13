package infomind.com.cms.info.site.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import infomind.com.cmm.bean.InfoUserMenuCacheManager;
import infomind.com.cms.info.board.vo.InfoBoardVO;
import infomind.com.cms.info.layout.vo.InfoLayoutInfoVO;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import infomind.com.cms.info.site.service.InfoSiteStatsService;
import infomind.com.cms.info.site.vo.InfoSiteMenuGroupVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteStatsVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class InfoSiteStatsController {


    @Resource(name = "InfoSiteStatsService")
    private InfoSiteStatsService infoSiteStatsService;


    private String pagePath ="info/site/stats/";

    @IncludedInfo(name = "사이트 방문 통계", listUrl = "/cms/info/site/stats/view.do", order = 1111, gid = 60)
    @RequestMapping(value = "/cms/info/site/stats/view.*")
    public String getSiteStatsPage(@ModelAttribute("searchVO") InfoSiteMenuVO searchVO, ModelMap model) throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteStatsView","ax5ui");
    }

    @RequestMapping(value = "/cms/info/site/stats/getSiteMenuStats.do")
    @ResponseBody
    public Object getSiteMenuStats(InfoSiteStatsVO vo) throws Exception {
        //파이 차트
        return infoSiteStatsService.getSiteMenuStats(vo);
    }

    @RequestMapping(value = "/cms/info/site/stats/getSiteMenuBrowserStats.do")
    @ResponseBody
    public Object getSiteMenuBrowserStats(InfoSiteStatsVO vo) throws Exception {
        //파이 차트
        return infoSiteStatsService.getSiteMenuBrowserStats(vo);
    }


    @RequestMapping(value = "/cms/info/site/stats/getSiteMenuDayStats.do")
    @ResponseBody
    public Object getSiteMenuDayStats(InfoSiteStatsVO vo) throws Exception {
        //막대 차트
        return infoSiteStatsService.getSiteMenuDayStats(vo);
    }



    @RequestMapping(value = "/cms/info/site/stats/getSiteMenuMonthStats.do")
    @ResponseBody
    public Object getSiteMenuMonthStats(InfoSiteStatsVO vo) throws Exception {
        //막대 차트
        return infoSiteStatsService.getSiteMenuMonthStats(vo);
    }


    @RequestMapping(value = "/cms/info/site/stats/getSiteMenuYearStats.do")
    @ResponseBody
    public Object getSiteMenuYearStats(InfoSiteStatsVO vo) throws Exception {
        //파인 차트
        return infoSiteStatsService.getSiteMenuYearStats(vo);
    }


    @RequestMapping(value = "/cms/info/site/stats/getSiteMenuTotYear.do")
    @ResponseBody
    public Object getSiteMenuTotYear(InfoSiteStatsVO vo) throws Exception {
        //파인 차트
        return infoSiteStatsService.getSiteMenuTotYear(vo);
    }


    @RequestMapping(value = "/cms/info/site/stats/getLastWeekDaysVisitCount.do")
    @ResponseBody
    public Object getLastWeekDaysVisitCount(InfoSiteStatsVO vo) throws Exception {
        //파인 차트
        return infoSiteStatsService.getLastWeekDaysVisitCount(vo);
    }



}
