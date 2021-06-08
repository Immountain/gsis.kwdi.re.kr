package wj.com.cms.wj.application.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import wj.com.cms.wj.application.dao.WjApplicationUserDAO;
import wj.com.cms.wj.application.service.WjApplicationUserService;
import wj.com.cms.wj.application.vo.WjApplicationUserVO;

import javax.annotation.Resource;
import java.util.List;

public class WjApplicationUserServiceImpl extends EgovAbstractServiceImpl implements WjApplicationUserService {

    @Resource(name="WjApplicationUserDAO")
    private WjApplicationUserDAO wjApplicationUserDAO;

    @Override
    public WjApplicationUserVO selectApplicationUser(WjApplicationUserVO vo) throws Exception {
        return wjApplicationUserDAO.selectApplicationUser(vo);
    }

    @Override
    public Integer selectApplicationUserTotalCount(WjApplicationUserVO vo) throws Exception {
        return wjApplicationUserDAO.selectApplicationUserTotalCount(vo);
    }

    @Override
    public List<?> selectApplicationUserList(WjApplicationUserVO vo) throws Exception {
        return wjApplicationUserDAO.selectApplicationUserList(vo);
    }

    @Override
    public void insertApplicationUser(WjApplicationUserVO vo) throws Exception {
        wjApplicationUserDAO.insertApplicationUser(vo);
    }

    @Override
    public void updateApplicationUser(WjApplicationUserVO vo) throws Exception {
        wjApplicationUserDAO.updateApplicationUser(vo);
    }
}
