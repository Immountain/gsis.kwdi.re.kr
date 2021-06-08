package wj.com.cms.wj.festivity.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.festivity.vo.WjFestivityApplicationVO;

import java.util.List;

@Repository("WjFestivityApplicationDAO")
public class WjFestivityApplicationDAO extends EgovComAbstractDAO {

    public WjFestivityApplicationVO selectFestivityApplication(WjFestivityApplicationVO vo)throws Exception{
        return selectOne("WjFestivityApplicationDAO.selectFestivityApplication",vo);
    }

    public Integer selectFestivityApplicationTotalCount(WjFestivityApplicationVO vo)throws Exception{
        return selectOne("WjFestivityApplicationDAO.selectFestivityApplicationTotalCount",vo);
    }

    public List<?> selectFestivityApplicationList(WjFestivityApplicationVO vo)throws Exception{
        return selectList("WjFestivityApplicationDAO.selectFestivityApplicationList",vo);
    }

    public void insertFestivityApplication(WjFestivityApplicationVO vo)throws Exception{
        insert("WjFestivityApplicationDAO.insertFestivityApplication",vo);
    }

    public void updateFestivityApplication(WjFestivityApplicationVO vo) throws Exception{
        insert("WjFestivityApplicationDAO.updateFestivityApplication",vo);
    }
}
