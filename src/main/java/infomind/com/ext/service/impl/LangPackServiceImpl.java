package infomind.com.ext.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import infomind.com.ext.dao.LangPackDAO;
import infomind.com.ext.service.LangPackService;
import infomind.com.ext.vo.LangPackVO;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.event.vo.WjEventInfoLangVO;
import wj.com.cms.wj.event.vo.WjEventInfoVO;
import wj.com.cms.wj.festivity.vo.*;
import wj.com.cms.wj.program.vo.WjProgramInfoLangVO;
import wj.com.cms.wj.program.vo.WjProgramInfoVO;
import wj.com.cms.wj.program.vo.WjProgramSubLangVO;
import wj.com.cms.wj.program.vo.WjProgramSubVO;
import wj.com.cms.wj.stats.vo.WjStatsReportLangVO;
import wj.com.cms.wj.stats.vo.WjStatsReportVO;
import wj.com.cms.wj.sub.vo.WjSubProgramLangVO;
import wj.com.cms.wj.sub.vo.WjSubProgramVO;

import javax.annotation.Resource;
import java.util.List;

@Service("LangPackService")
public class LangPackServiceImpl extends EgovAbstractServiceImpl implements LangPackService {


    @Resource(name="LangPackDAO")
    private LangPackDAO langPackDAO;


    @Override
    public List<LangPackVO> getSelectLangPackList() throws Exception {
        return langPackDAO.getSelectLangPackList();
    }

    @Override
    public List<WjFestivityLangVO> getSelectWjFestivityLang(WjFestivityInfoVO searchVO) throws Exception {
        return langPackDAO.getSelectWjFestivityLang(searchVO);
    }

    @Override
    public List<WjFestivityScheduleLangVO> getSelectWjFestivityScheduleLang(WjFestivityScheduleVO searchVO) throws Exception {
        return langPackDAO.getSelectWjFestivityScheduleLang(searchVO);
    }

    @Override
    public List<WjProgramInfoLangVO> getSelectWjProgramInfoLang(WjProgramInfoVO searchVO) throws Exception {
        return langPackDAO.getSelectWjProgramInfoLang(searchVO);
    }

    @Override
    public List<WjProgramSubLangVO> getSelectWjProgramSubLang(WjProgramSubVO searchVO) throws Exception {
        return langPackDAO.getSelectWjProgramSubLang(searchVO);
    }

    @Override
    public List<WjStatsReportLangVO> getSelectWjStatsReportLang(WjStatsReportVO searchVO) throws Exception {
        return langPackDAO.getSelectWjStatsReportLang(searchVO);
    }

    @Override
    public List<WjSubProgramLangVO> getWjSubProgramLang(WjSubProgramVO searchVO) throws Exception {
        return langPackDAO.getWjSubProgramLang(searchVO);
    }

    @Override
    public List<WjFestivityApplicationLangVO> getWjFestivityApplicationLang(WjFestivityApplicationVO searchVO) throws Exception {
        return langPackDAO.getWjFestivityApplicationLang(searchVO);
    }

    @Override
    public List<WjEventInfoLangVO> getWjEventLang(WjEventInfoVO searchVO) throws Exception {
        return langPackDAO.getWjEventLang(searchVO);
    }

    @Override
    public List<InfoLangCodeVO> getInfoLangCode(InfoLangCodeVO searchVO) throws Exception {
        return langPackDAO.getInfoLangCode(searchVO);
    }

    @Override
    public List<InfoLangCodeVO> getInfoActLangCode(InfoLangCodeVO searchVO) throws Exception {
        return langPackDAO.getInfoActLangCode(searchVO);
    }
}
