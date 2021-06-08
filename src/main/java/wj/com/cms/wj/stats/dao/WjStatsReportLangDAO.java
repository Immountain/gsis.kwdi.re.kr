package wj.com.cms.wj.stats.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.stats.vo.WjStatsReportLangVO;

import java.util.List;

@Repository("WjStatsReportLangDAO")
public class WjStatsReportLangDAO extends EgovComAbstractDAO {

    public WjStatsReportLangVO selectStatsReportLang(WjStatsReportLangVO vo)throws Exception{
        return selectOne("WjStatsReportLangDAO.selectStatsReportLang",vo);
    }

    public Integer selectStatsReportLangTotalCount(WjStatsReportLangVO vo)throws Exception{
        return selectOne("WjStatsReportLangDAO.selectStatsReportLangTotalCount",vo);
    }

    public List<?> selectStatsReportLangList(WjStatsReportLangVO vo) throws Exception{
        return selectList("WjStatsReportLangDAO.selectStatsReportLangList",vo);
    }

    public void insertStatsReportLang(WjStatsReportLangVO vo) throws Exception{
        insert("WjStatsReportLangDAO.insertStatsReportLang",vo);
    }

    public void updateStatsReportLang(WjStatsReportLangVO vo)throws Exception{
        insert("WjStatsReportLangDAO.updateStatsReportLang",vo);
    }
}
