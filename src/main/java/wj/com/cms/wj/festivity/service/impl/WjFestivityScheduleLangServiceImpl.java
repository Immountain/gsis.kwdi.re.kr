package wj.com.cms.wj.festivity.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.festivity.dao.WjFestivityScheduleLangDAO;
import wj.com.cms.wj.festivity.service.WjFestivityScheduleLangService;
import wj.com.cms.wj.festivity.vo.WjFestivityScheduleLangVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjFestivityScheduleLangService")
public class WjFestivityScheduleLangServiceImpl extends EgovAbstractServiceImpl implements WjFestivityScheduleLangService {

    @Resource(name="WjFestivityScheduleLangDAO")
    private WjFestivityScheduleLangDAO wjFestivityScheduleLangDAO;

    @Override
    public WjFestivityScheduleLangVO selectFestivityScheduleLang(WjFestivityScheduleLangVO vo) throws Exception {
        return wjFestivityScheduleLangDAO.selectFestivityScheduleLang(vo);
    }

    @Override
    public Integer selectFestivityScheduleLangTotalCount(WjFestivityScheduleLangVO vo) throws Exception {
        return wjFestivityScheduleLangDAO.selectFestivityScheduleLangTotalCount(vo);
    }

    @Override
    public List<?> selectFestivityScheduleLangList(WjFestivityScheduleLangVO vo) throws Exception {
        return wjFestivityScheduleLangDAO.selectFestivityScheduleLangList(vo);
    }

    @Override
    public void insertFestivityScheduleLang(WjFestivityScheduleLangVO vo) throws Exception {
        wjFestivityScheduleLangDAO.insertFestivityScheduleLang(vo);
    }

    @Override
    public void updateFestivityScheduleLang(WjFestivityScheduleLangVO vo) throws Exception {
        wjFestivityScheduleLangDAO.updateFestivityScheduleLang(vo);
    }
}
