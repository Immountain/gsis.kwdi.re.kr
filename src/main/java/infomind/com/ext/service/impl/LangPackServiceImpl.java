package infomind.com.ext.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import infomind.com.ext.dao.LangPackDAO;
import infomind.com.ext.service.LangPackService;
import infomind.com.ext.vo.LangPackVO;
import org.springframework.stereotype.Service;


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
    public List<InfoLangCodeVO> getInfoLangCode(InfoLangCodeVO searchVO) throws Exception {
        return langPackDAO.getInfoLangCode(searchVO);
    }

    @Override
    public List<InfoLangCodeVO> getInfoActLangCode(InfoLangCodeVO searchVO) throws Exception {
        return langPackDAO.getInfoActLangCode(searchVO);
    }
}
