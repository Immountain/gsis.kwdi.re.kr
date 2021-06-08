package wj.com.cms.wj.event.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.event.vo.WjEventUserVO;

import java.util.List;

@Repository("WjEventUserDAO")
public class WjEventUserDAO extends EgovComAbstractDAO {

    public WjEventUserVO selectEventUser(WjEventUserVO vo)throws Exception{
        return selectOne("WjEventUserDAO.selectEventUser",vo);
    }

    public Integer selectEventUserTotalCount(WjEventUserVO vo)throws Exception{
        return selectOne("WjEventUserDAO.selectEventUserTotalCount",vo);
    }

    public List<?> selectEventUserList(WjEventUserVO vo)throws Exception{
        return selectList("WjEventUserDAO.selectEventUserList",vo);
    }

    public void insertEventUser(WjEventUserVO vo)throws Exception{
         insert("WjEventUserDAO.insertEventUser",vo);
    }

    public void updateEventUser(WjEventUserVO vo)throws Exception{
        insert("WjEventUserDAO.updateEventUser",vo);
    }

    public void deleteEventUser(WjEventUserVO vo)throws Exception{
        insert("WjEventUserDAO.deleteEventUser",vo);
    }

}
