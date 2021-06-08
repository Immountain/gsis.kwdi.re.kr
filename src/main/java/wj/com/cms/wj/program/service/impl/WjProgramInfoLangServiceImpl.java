package wj.com.cms.wj.program.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wj.com.cms.wj.program.dao.WjProgramInfoLangDAO;
import wj.com.cms.wj.program.service.WjProgramInfoLangService;
import wj.com.cms.wj.program.vo.WjProgramInfoLangVO;
import wj.com.cms.wj.program.vo.WjProgramInfoVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjProgramInfoLangService")
public class WjProgramInfoLangServiceImpl extends EgovAbstractServiceImpl implements WjProgramInfoLangService {

    @Resource(name="WjProgramInfoLangDAO")
    private WjProgramInfoLangDAO wjProgramInfoLangDAO;

    @Override
    public WjProgramInfoLangVO selectProgramInfoLang(WjProgramInfoLangVO vo) throws Exception {
        return wjProgramInfoLangDAO.selectProgramInfoLang(vo);
    }

    @Override
    public Integer selectProgramInfoLangTotalCount(WjProgramInfoLangVO vo) throws Exception {
        return wjProgramInfoLangDAO.selectProgramInfoLangTotalCount(vo);
    }

    @Override
    public List<?> selectProgramInfoLangList(WjProgramInfoLangVO vo) throws Exception {
        return wjProgramInfoLangDAO.selectProgramInfoLangList(vo);
    }

    @Override
    public void insertProgramInfoLang(WjProgramInfoLangVO vo) throws Exception {
        wjProgramInfoLangDAO.insertProgramInfoLang(vo);
    }

    @Override
    public void updateProgramInfoLang(WjProgramInfoLangVO vo) throws Exception {
        wjProgramInfoLangDAO.updateProgramInfoLang(vo);
    }

    @Override
    public void updateProgramInfoContentLang(WjProgramInfoVO vo) throws Exception {

        if(!CollectionUtils.isEmpty(vo.getListLang())){
            for(WjProgramInfoLangVO langVO : vo.getListLang()){
                langVO.setProgramSno(vo.getProgramSno());
                wjProgramInfoLangDAO.updateProgramInfoContentLang(langVO);            }
        }
    }
}
