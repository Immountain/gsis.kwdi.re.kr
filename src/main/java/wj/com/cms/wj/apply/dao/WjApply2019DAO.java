package wj.com.cms.wj.apply.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.apply.vo.WjApply2019VO;

import java.util.List;

@Repository("WjApply2019DAO")
public class WjApply2019DAO extends EgovComAbstractDAO {

    public WjApply2019VO selectWjApply2019(WjApply2019VO vo) throws Exception {
        return selectOne("WjApply2019DAO.selectWjApply2019", vo);
    }

    public Integer selectWjApply2019TotalCount(WjApply2019VO vo) throws Exception {
        return selectOne("WjApply2019DAO.selectWjApply2019TotalCount", vo);
    }

    public List<?> selectWjApply2019List(WjApply2019VO vo) throws Exception {
        return selectList("WjApply2019DAO.selectWjApply2019List", vo);
    }

    public void insertWjApply2019(WjApply2019VO vo) throws Exception {
        insert("WjApply2019DAO.insertWjApply2019", vo);
    }

    public void updateWjApply2019(WjApply2019VO vo) throws Exception {
        insert("WjApply2019DAO.updateWjApply2019", vo);
    }

}
