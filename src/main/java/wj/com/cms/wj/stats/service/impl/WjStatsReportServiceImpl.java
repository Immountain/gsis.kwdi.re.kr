package wj.com.cms.wj.stats.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wj.com.cms.wj.stats.dao.WjStatsReportDAO;
import wj.com.cms.wj.stats.dao.WjStatsReportLangDAO;
import wj.com.cms.wj.stats.service.WjStatsReportService;
import wj.com.cms.wj.stats.vo.WjStatsReportLangVO;
import wj.com.cms.wj.stats.vo.WjStatsReportVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjStatsReportService")
public class WjStatsReportServiceImpl extends EgovAbstractServiceImpl implements WjStatsReportService {

    @Resource(name="WjStatsReportIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="WjStatsReportDAO")
    private WjStatsReportDAO wjStatsReportDAO;

    @Resource(name="WjStatsReportLangDAO")
    private WjStatsReportLangDAO wjStatsReportLangDAO;

    @Override
    public WjStatsReportVO selectStatsReport(WjStatsReportVO vo) throws Exception {
        return wjStatsReportDAO.selectStatsReport(vo);
    }

    @Override
    public Integer selectStatsReportTotalCount(WjStatsReportVO vo) throws Exception {
        return wjStatsReportDAO.selectStatsReportTotalCount(vo);
    }

    @Override
    public List<?> selectStatsReportList(WjStatsReportVO vo) throws Exception {
        return wjStatsReportDAO.selectStatsReportList(vo);
    }

    @Override
    public void insertStatsReport(WjStatsReportVO vo) throws Exception {

        String generateId = idgenService.getNextStringId();
        vo.setReportSno(generateId);


        if(!CollectionUtils.isEmpty(vo.getListLang())){

            for(WjStatsReportLangVO langVO : vo.getListLang()){
                langVO.setReportSno(generateId);
                langVO.setRegId(vo.getRegId());
                updateStatsReportLang(langVO);
            }

        }

        wjStatsReportDAO.insertStatsReport(vo);
    }

    @Override
    public void updateStatsReport(WjStatsReportVO vo) throws Exception {

        wjStatsReportDAO.updateStatsReport(vo);

        if(!CollectionUtils.isEmpty(vo.getListLang())){
            for(WjStatsReportLangVO langVO : vo.getListLang()) {
                langVO.setReportSno(vo.getReportSno());
                langVO.setModId(vo.getModId());
                updateStatsReportLang(langVO);
            }
        }
    }

    @Override
    public void deleteStatsReport(WjStatsReportVO vo) throws Exception {
        wjStatsReportDAO.deleteStatsReport(vo);
    }

    public void updateStatsReportLang(WjStatsReportLangVO vo) throws Exception{
        if(wjStatsReportLangDAO.selectStatsReportLang(vo) == null){
            wjStatsReportLangDAO.insertStatsReportLang(vo);
        } else{
            wjStatsReportLangDAO.updateStatsReportLang(vo);
        }
    }
}
