package wj.com.cms.wj.stats.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.stats.dao.WjStatsReportLangDAO;
import wj.com.cms.wj.stats.service.WjStatsReportLangService;
import wj.com.cms.wj.stats.vo.WjStatsReportLangVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjStatsReportLangService")
public class WjStatsReportLangServiceImpl extends EgovAbstractServiceImpl implements WjStatsReportLangService {

    @Resource(name="WjStatsReportLangDAO")
    private WjStatsReportLangDAO wjStatsReportLangDAO;

    @Override
    public WjStatsReportLangVO selectStatsReportLang(WjStatsReportLangVO vo) throws Exception {
        return wjStatsReportLangDAO.selectStatsReportLang(vo);
    }

    @Override
    public Integer selectStatsReportLangTotalCount(WjStatsReportLangVO vo) throws Exception {
        return wjStatsReportLangDAO.selectStatsReportLangTotalCount(vo);
    }

    @Override
    public List<?> selectStatsReportLangList(WjStatsReportLangVO vo) throws Exception {
        return wjStatsReportLangDAO.selectStatsReportLangList(vo);
    }

    @Override
    public void insertStatsReportLang(WjStatsReportLangVO vo) throws Exception {
        wjStatsReportLangDAO.insertStatsReportLang(vo);
    }

    @Override
    public void updateStatsReportLang(WjStatsReportLangVO vo) throws Exception {
        wjStatsReportLangDAO.updateStatsReportLang(vo);
    }
}
