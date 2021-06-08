package infomind.com.cms.info.site.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.site.dao.InfoSiteStatsDAO;
import infomind.com.cms.info.site.service.InfoSiteStatsService;
import infomind.com.cms.info.site.vo.InfoSiteStatsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoSiteStatsService")
public class InfoSiteStatsServiceImpl extends EgovAbstractServiceImpl implements InfoSiteStatsService {


    @Resource(name="InfoSiteStatsDAO")
    private InfoSiteStatsDAO infoSiteStatsDAO;


    @Override
    public List<?> getSiteMenuStats(InfoSiteStatsVO vo) throws Exception {
        return infoSiteStatsDAO.getSiteMenuStats(vo);
    }

    @Override
    public List<?> getSiteMenuBrowserStats(InfoSiteStatsVO vo) throws Exception {
        return infoSiteStatsDAO.getSiteMenuBrowserStats(vo);
    }

    @Override
    public List<?> getSiteMenuDayStats(InfoSiteStatsVO vo) throws Exception {
        return infoSiteStatsDAO.getSiteMenuDayStats(vo);
    }

    @Override
    public List<?> getSiteMenuMonthStats(InfoSiteStatsVO vo) throws Exception {
        return infoSiteStatsDAO.getSiteMenuMonthStats(vo);
    }

    @Override
    public List<?> getSiteMenuYearStats(InfoSiteStatsVO vo) throws Exception {
        return infoSiteStatsDAO.getSiteMenuYearStats(vo);
    }

    @Override
    public List<?> getSiteMenuTotYear(InfoSiteStatsVO vo) throws Exception {
        return infoSiteStatsDAO.getSiteMenuTotYear(vo);
    }
}
