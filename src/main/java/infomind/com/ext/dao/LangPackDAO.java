package infomind.com.ext.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import infomind.com.ext.vo.LangPackVO;
import org.springframework.stereotype.Repository;
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

import java.util.List;

@Repository("LangPackDAO")
public class LangPackDAO extends EgovComAbstractDAO {


    public List<LangPackVO> getSelectLangPackList() throws Exception {
        return (List<LangPackVO>) list("LangPackDAO.getSelectLangPackList", null);
    }


    public List<WjFestivityLangVO> getSelectWjFestivityLang(WjFestivityInfoVO searchVO) throws Exception {
        return (List<WjFestivityLangVO>) list("LangPackDAO.getSelectWjFestivityLang", searchVO);
    }

    public List<WjFestivityScheduleLangVO> getSelectWjFestivityScheduleLang(WjFestivityScheduleVO searchVO) throws Exception {
        return (List<WjFestivityScheduleLangVO>) list("LangPackDAO.getSelectWjFestivityScheduleLang", searchVO);
    }



    public List<WjProgramInfoLangVO> getSelectWjProgramInfoLang(WjProgramInfoVO searchVO) throws Exception {
        return (List<WjProgramInfoLangVO>) list("LangPackDAO.getSelectWjProgramInfoLang", searchVO);
    }



    public List<WjProgramSubLangVO> getSelectWjProgramSubLang(WjProgramSubVO searchVO) throws Exception {
        return (List<WjProgramSubLangVO>) list("LangPackDAO.getSelectWjProgramSubLang", searchVO);
    }

    public List<WjStatsReportLangVO> getSelectWjStatsReportLang(WjStatsReportVO searchVO) throws Exception{
        return (List<WjStatsReportLangVO>) list("LangPackDAO.getSelectWjStatsReportLang",searchVO);
    }

    public List<WjSubProgramLangVO> getWjSubProgramLang(WjSubProgramVO searchVO)throws Exception{
        return (List<WjSubProgramLangVO>) list("LangPackDAO.getWjSubProgramLang",searchVO);
    }

    public List<WjFestivityApplicationLangVO> getWjFestivityApplicationLang(WjFestivityApplicationVO searchVO)throws Exception{
        return (List<WjFestivityApplicationLangVO>) list("LangPackDAO.getWjFestivityApplicationLang",searchVO);
    }

    public List<WjEventInfoLangVO> getWjEventLang(WjEventInfoVO searchVO)throws Exception{
        return (List<WjEventInfoLangVO>) list("LangPackDAO.getWjEventLang",searchVO);
    }


    public List<InfoLangCodeVO> getInfoLangCode(InfoLangCodeVO searchVO)throws Exception{
        return (List<InfoLangCodeVO>) list("LangPackDAO.getInfoLangCode",searchVO);
    }


    public List<InfoLangCodeVO> getInfoActLangCode(InfoLangCodeVO searchVO)throws Exception{
        return (List<InfoLangCodeVO>) list("LangPackDAO.getInfoActLangCode",searchVO);
    }



}
