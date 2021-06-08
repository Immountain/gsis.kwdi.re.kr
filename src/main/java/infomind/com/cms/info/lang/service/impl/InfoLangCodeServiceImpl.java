package infomind.com.cms.info.lang.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.lang.dao.InfoLangCodeDAO;
import infomind.com.cms.info.lang.service.InfoLangCodeService;
import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import javax.annotation.Resource;
import java.util.List;

@Service("InfoLangCodeService")
public class InfoLangCodeServiceImpl extends EgovAbstractServiceImpl implements InfoLangCodeService {

    @Resource(name="InfoLangCodeDAO")
    private InfoLangCodeDAO infoLangCodeDAO;

    @Override
    public InfoLangCodeVO selectLangCode(InfoLangCodeVO vo) throws Exception {
        return infoLangCodeDAO.selectLangCode(vo);
    }

    @Override
    public Integer selectLangCodeTotalCount(InfoLangCodeVO vo) throws Exception {
        return infoLangCodeDAO.selectLangCodeTotalCount(vo);
    }

    @Override
    public List<?> selectLangCodeList(InfoLangCodeVO vo) throws Exception {
        return infoLangCodeDAO.selectLangCodeList(vo);
    }

    @Override

    public void insertLangCode(InfoLangCodeVO vo) throws Exception {

        if(!CollectionUtils.isEmpty(vo.getListLang())) {
            for(InfoLangCodeVO langVO : vo.getListLang()) {
                langVO.setCodeId(vo.getCodeId());
                langVO.setCode(vo.getCode());
                langVO.setModId(vo.getModId());
                langVO.setRegId(vo.getRegId());
                updateLangCodeLang(langVO);
            }
        }
    }

    @Override
    public void updateLangCode(InfoLangCodeVO vo) throws Exception {

        if(!CollectionUtils.isEmpty(vo.getListLang())){
            for(InfoLangCodeVO langVO : vo.getListLang()){
                langVO.setCodeId(vo.getCodeId());
                langVO.setCode(vo.getCode());
                langVO.setModId(vo.getModId());
                langVO.setRegId(vo.getRegId());
                updateLangCodeLang(langVO);
            }
        }
        infoLangCodeDAO.updateLangCode(vo);
    }


    private void updateLangCodeLang(InfoLangCodeVO langVO) throws Exception {
        if(infoLangCodeDAO.selectLangCode(langVO) == null) {
            infoLangCodeDAO.insertLangCode(langVO);
        } else {
            infoLangCodeDAO.updateLangCode(langVO);
        }
    }
}
