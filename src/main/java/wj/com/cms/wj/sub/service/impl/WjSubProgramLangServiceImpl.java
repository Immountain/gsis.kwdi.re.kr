package wj.com.cms.wj.sub.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wj.com.cms.wj.sub.dao.WjSubProgramLangDAO;
import wj.com.cms.wj.sub.service.WjSubProgramLangService;
import wj.com.cms.wj.sub.vo.WjSubProgramLangVO;
import wj.com.cms.wj.sub.vo.WjSubProgramVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjSubProgramLangService")
public class WjSubProgramLangServiceImpl extends EgovAbstractServiceImpl implements WjSubProgramLangService {

    @Resource(name="WjSubProgramLangDAO")
    private WjSubProgramLangDAO wjSubProgramLangDAO;

    @Override
    public WjSubProgramLangVO selectSubProgramLang(WjSubProgramLangVO vo) throws Exception {
        return wjSubProgramLangDAO.selectSubProgramLang(vo);
    }

    @Override
    public Integer selectSubProgramLangTotalCount(WjSubProgramLangVO vo) throws Exception {
        return wjSubProgramLangDAO.selectSubProgramLangTotalCount(vo);
    }

    @Override
    public List<?> selectSubProgramLangList(WjSubProgramLangVO vo) throws Exception {
        return wjSubProgramLangDAO.selectSubProgramLangList(vo);
    }

    @Override
    public void insertSubProgramLang(WjSubProgramLangVO vo) throws Exception {
        wjSubProgramLangDAO.insertSubProgramLang(vo);
    }

    @Override
    public void updateSubProgramLang(WjSubProgramLangVO vo) throws Exception {
        wjSubProgramLangDAO.updateSubProgramLang(vo);
    }

    @Override
    public void updateSubProgramContentLang(WjSubProgramVO vo) throws Exception {
        if(!CollectionUtils.isEmpty(vo.getListLang())){
            for(WjSubProgramLangVO langVO : vo.getListLang()){
                langVO.setFestivityProgramSno(vo.getFestivityProgramSno());
                wjSubProgramLangDAO.updateSubProgramContentLang(langVO);
            }
        }
    }
}
