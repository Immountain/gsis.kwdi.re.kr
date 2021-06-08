package wj.com.cms.wj.festivity.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import wj.com.cms.wj.festivity.dao.WjFestivityApplicationLangDAO;
import wj.com.cms.wj.festivity.service.WjFestivityApplicationLangService;
import wj.com.cms.wj.festivity.vo.WjFestivityApplicationLangVO;

import javax.annotation.Resource;
import java.util.List;

public class WjFestivityApplicationLangServiceImpl extends EgovAbstractServiceImpl implements WjFestivityApplicationLangService {

    @Resource(name="WjFestivityApplicationLangDAO")
    private WjFestivityApplicationLangDAO wjFestivityApplicationLangDAO;

    @Override
    public WjFestivityApplicationLangVO selectFestivityApplicationLang(WjFestivityApplicationLangVO vo) throws Exception {
        return wjFestivityApplicationLangDAO.selectFestivityApplicationLang(vo);
    }

    @Override
    public Integer selectFestivityApplicationLangTotalCount(WjFestivityApplicationLangVO vo) throws Exception {
        return wjFestivityApplicationLangDAO.selectFestivityApplicationLangTotalCount(vo);
    }

    @Override
    public List<?> selectFestivityApplicationLangList(WjFestivityApplicationLangVO vo) throws Exception {
        return wjFestivityApplicationLangDAO.selectFestivityApplicationLangList(vo);
    }

    @Override
    public void insertFestivityApplicationLang(WjFestivityApplicationLangVO vo) throws Exception {
        wjFestivityApplicationLangDAO.insertFestivityApplicationLang(vo);
    }

    @Override
    public void updateFestivityApplicationLang(WjFestivityApplicationLangVO vo) throws Exception {
        wjFestivityApplicationLangDAO.updateFestivityApplicationLang(vo);
    }
}
