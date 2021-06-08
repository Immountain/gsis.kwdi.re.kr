package wj.com.cms.wj.application.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import wj.com.cms.wj.application.vo.WjApplicationUserVO;

import java.util.List;

public class WjApplicationUserDAO extends EgovComAbstractDAO {

    public WjApplicationUserVO selectApplicationUser(WjApplicationUserVO vo)throws Exception{
        return selectOne("WjApplicationUserDAO.selectApplicationUser",vo);
    }

    public Integer selectApplicationUserTotalCount(WjApplicationUserVO vo)throws Exception{
        return selectOne("WjApplicationUserDAO.selectApplicationUserTotalCount",vo);
    }

    public List<?> selectApplicationUserList(WjApplicationUserVO vo)throws Exception{
        return selectList("WjApplicationUserDAO.selectApplicationUserList",vo);
    }

    public void insertApplicationUser(WjApplicationUserVO vo)throws Exception{
        insert("WjApplicationUserDAO.insertApplicationUser", vo);
    }

    public void updateApplicationUser(WjApplicationUserVO vo) throws Exception{
        insert("WjApplicationUserDAO.updateApplicationUser", vo);
    }
}
