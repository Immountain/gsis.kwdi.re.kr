package wj.com.cms.wj.act.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.act.vo.WjActInfoVO;

import java.util.List;

@Repository("WjActInfoDAO")
public class WjActInfoDAO extends EgovComAbstractDAO {

    public WjActInfoVO selectActInfo(WjActInfoVO vo)throws Exception{
        return selectOne("WjActInfoDAO.selectActInfo",vo);
    }

    public Integer selectActInfoTotalCount(WjActInfoVO vo)throws Exception{
        return selectOne("WjActInfoDAO.selectActInfoTotalCount",vo);
    }

    public List<?> selectActInfoList(WjActInfoVO vo)throws Exception{
        return selectList("WjActInfoDAO.selectActInfoList",vo);
    }

    public void insertActInfo(WjActInfoVO vo)throws Exception{
        insert("WjActInfoDAO.insertActInfo",vo);
    }

    public void updateActInfo(WjActInfoVO vo)throws Exception{
        insert("WjActInfoDAO.insertActInfo",vo);
    }

    public void deleteUserId(String userId) {
        delete("WjActInfoDAO.deleteUserId", userId);
    }
}
