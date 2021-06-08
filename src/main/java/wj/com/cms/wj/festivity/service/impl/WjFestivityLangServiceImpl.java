package wj.com.cms.wj.festivity.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.festivity.dao.WjFestivityLangDAO;
import wj.com.cms.wj.festivity.service.WjFestivityLangService;
import wj.com.cms.wj.festivity.vo.WjFestivityLangVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjFestivityLangService")
public class WjFestivityLangServiceImpl extends EgovAbstractServiceImpl implements WjFestivityLangService {

    @Resource(name="WjFestivityLangDAO")
    private WjFestivityLangDAO wjFestivityLangDAO;


    @Override
    public WjFestivityLangVO selectFestivityLang(WjFestivityLangVO vo) throws Exception {
        return wjFestivityLangDAO.selectFestivityLang(vo);
    }

    @Override
    public Integer selectFestivityLangTotalCount(WjFestivityLangVO vo) throws Exception {
        return wjFestivityLangDAO.selectFestivityLangTotalCount(vo);
    }

    @Override
    public List<?> selectFestivityLangList(WjFestivityLangVO vo) throws Exception {
        return wjFestivityLangDAO.selectFestivityLangList(vo);
    }

    @Override
    public void insertFestivityLang(WjFestivityLangVO vo) throws Exception {
        wjFestivityLangDAO.insertFestivityLang(vo);
    }

    @Override
    public void updateFestivityLang(WjFestivityLangVO vo) throws Exception {
        wjFestivityLangDAO.updateFestivityLang(vo);
    }
}
