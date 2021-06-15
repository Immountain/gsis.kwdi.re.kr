package infomind.com.ext.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import infomind.com.ext.vo.LangPackVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("LangPackDAO")
public class LangPackDAO extends EgovComAbstractDAO {


    public List<LangPackVO> getSelectLangPackList() throws Exception {
        return (List<LangPackVO>) list("LangPackDAO.getSelectLangPackList", null);
    }
    public List<InfoLangCodeVO> getInfoLangCode(InfoLangCodeVO searchVO)throws Exception{
        return (List<InfoLangCodeVO>) list("LangPackDAO.getInfoLangCode",searchVO);
    }


    public List<InfoLangCodeVO> getInfoActLangCode(InfoLangCodeVO searchVO)throws Exception{
        return (List<InfoLangCodeVO>) list("LangPackDAO.getInfoActLangCode",searchVO);
    }



}
