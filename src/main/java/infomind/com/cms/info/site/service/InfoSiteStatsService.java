package infomind.com.cms.info.site.service;

import infomind.com.cms.info.site.vo.InfoSiteStatsVO;

import java.util.List;

public interface InfoSiteStatsService {


    /**
     * 메뉴별 통계
     * @param vo
     * @return
     * @throws Exception
     */
     List<?> getSiteMenuStats(InfoSiteStatsVO vo) throws Exception ;

    /**
     * 브라우저별 통계
     * @param vo
     * @return
     * @throws Exception
     */
     List<?> getSiteMenuBrowserStats(InfoSiteStatsVO vo) throws Exception ;
    /**
     * 일짜별 통계
     * @param vo
     * @return
     * @throws Exception
     */
     List<?> getSiteMenuDayStats(InfoSiteStatsVO vo) throws Exception ;
    /**
     * 월별통계
     * @param vo
     * @return
     * @throws Exception
     */
     List<?> getSiteMenuMonthStats(InfoSiteStatsVO vo) throws Exception ;

    /**
     * 년별통계
     * @param vo
     * @return
     * @throws Exception
     */
     List<?> getSiteMenuYearStats(InfoSiteStatsVO vo) throws Exception ;


    /**
     * 년도멸 메뉴 통계
     * @param vo
     * @return
     * @throws Exception
     */
    List<?> getSiteMenuTotYear(InfoSiteStatsVO vo) throws Exception ;


}
