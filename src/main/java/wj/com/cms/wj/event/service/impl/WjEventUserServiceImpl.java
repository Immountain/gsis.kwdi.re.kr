package wj.com.cms.wj.event.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.event.dao.WjEventUserDAO;
import wj.com.cms.wj.event.service.WjEventUserService;
import wj.com.cms.wj.event.vo.WjEventUserVO;

import javax.annotation.Resource;
import java.util.List;


@Service("WjEventUserService")
public class WjEventUserServiceImpl extends EgovAbstractServiceImpl implements WjEventUserService{

    @Resource(name="WjEventUserDAO")
    private WjEventUserDAO wjEventUserDAO;

    @Override
    public WjEventUserVO selectEventUser(WjEventUserVO vo) throws Exception {
        return wjEventUserDAO.selectEventUser(vo);
    }

    @Override
    public Integer selectEventUserTotalCount(WjEventUserVO vo) throws Exception {
        return wjEventUserDAO.selectEventUserTotalCount(vo);
    }

    @Override
    public List<?> selectEventUserList(WjEventUserVO vo) throws Exception {
        return wjEventUserDAO.selectEventUserList(vo);
    }

    @Override
    public void insertEventUser(WjEventUserVO vo) throws Exception {
        wjEventUserDAO.insertEventUser(vo);
    }

    @Override
    public void updateEventUser(WjEventUserVO vo) throws Exception {
        wjEventUserDAO.updateEventUser(vo);
    }

    @Override
    public void deleteEventUser(WjEventUserVO vo) throws Exception {
        wjEventUserDAO.deleteEventUser(vo);
    }
}
