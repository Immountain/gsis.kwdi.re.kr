package wj.com.cms.wj.stats.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.stats.vo.WjStatsReportVO;

import java.util.List;

@Repository("WjStatsReportDAO")
public class WjStatsReportDAO extends EgovComAbstractDAO {

    public WjStatsReportVO selectStatsReport(WjStatsReportVO vo)throws Exception{
        return selectOne("WjStatsReportDAO.selectStatsReport",vo);
    }

    public Integer selectStatsReportTotalCount(WjStatsReportVO vo)throws Exception{
        return selectOne("WjStatsReportDAO.selectStatsReportTotalCount",vo);
    }

    public List<?> selectStatsReportList(WjStatsReportVO vo)throws Exception{
        return selectList("WjStatsReportDAO.selectStatsReportList",vo);
    }

    public void insertStatsReport(WjStatsReportVO vo)throws Exception{
        insert("WjStatsReportDAO.insertStatsReport",vo);
    }

    public void updateStatsReport(WjStatsReportVO vo) throws Exception{
        insert("WjStatsReportDAO.updateStatsReport",vo);
    }

    public void deleteStatsReport(WjStatsReportVO vo) throws Exception{
        insert("WjStatsReportDAO.deleteStatsReport",vo);
    }
}
