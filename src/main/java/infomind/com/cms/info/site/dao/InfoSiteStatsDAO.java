package infomind.com.cms.info.site.dao;


import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.site.vo.InfoSiteStatsVO;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoSiteStatsDAO")
public class InfoSiteStatsDAO extends EgovComAbstractDAO {

    /**
     * 메뉴별 통계
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> getSiteMenuStats(InfoSiteStatsVO vo) throws Exception {
        return selectList("InfoSiteStatsDAO.getSiteMenuStats", vo);
    }

    /**
     * 브라우저별 통
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> getSiteMenuBrowserStats(InfoSiteStatsVO vo) throws Exception {
        return selectList("InfoSiteStatsDAO.getSiteMenuBrowserStats", vo);
    }

    /**
     * 일짜별 통
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> getSiteMenuDayStats(InfoSiteStatsVO vo) throws Exception {
        return selectList("InfoSiteStatsDAO.getSiteMenuDayStats", vo);
    }

    /**
     * 월별통계
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> getSiteMenuMonthStats(InfoSiteStatsVO vo) throws Exception {
        return selectList("InfoSiteStatsDAO.getSiteMenuMonthStats", vo);
    }

    /**
     * 년별통계
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> getSiteMenuYearStats(InfoSiteStatsVO vo) throws Exception {
        return selectList("InfoSiteStatsDAO.getSiteMenuYearStats", vo);
    }


    /**
     * 년도멸 메뉴 통계
     * @param vo
     * @return
     * @throws Exception
     */
    public List<?> getSiteMenuTotYear(InfoSiteStatsVO vo) throws Exception {
        return selectList("InfoSiteStatsDAO.getSiteMenuTotYear", vo);
    }




}
