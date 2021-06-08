package wj.com.cms.wj.event.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.event.dao.WjEventInfoLangDAO;
import wj.com.cms.wj.event.service.WjEventInfoLangService;
import wj.com.cms.wj.event.vo.WjEventInfoLangVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjEventInfoLangService")
public class WjEventInfoLangServiceImpl extends EgovAbstractServiceImpl implements WjEventInfoLangService {

    @Resource(name="WjEventInfoLangDAO")
    private WjEventInfoLangDAO wjEventInfoLangDAO;

    @Override
    public WjEventInfoLangVO selectEventInfoLang(WjEventInfoLangVO vo) throws Exception {
        return wjEventInfoLangDAO.selectEventInfoLang(vo);
    }

    @Override
    public Integer selectEventInfoLangTotalCount(WjEventInfoLangVO vo) throws Exception {
        return wjEventInfoLangDAO.selectEventInfoLangTotalCount(vo);
    }

    @Override
    public List<?> selectEventInfoLangList(WjEventInfoLangVO vo) throws Exception {
        return wjEventInfoLangDAO.selectEventInfoLangList(vo);
    }

    @Override
    public void insertEventInfoLang(WjEventInfoLangVO vo) throws Exception {
        wjEventInfoLangDAO.insertEventInfoLang(vo);
    }

    @Override
    public void updateEventInfoLang(WjEventInfoLangVO vo) throws Exception {
        wjEventInfoLangDAO.updateEventInfoLang(vo);
    }
}
