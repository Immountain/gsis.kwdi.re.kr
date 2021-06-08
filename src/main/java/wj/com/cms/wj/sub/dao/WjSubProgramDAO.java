package wj.com.cms.wj.sub.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.sub.vo.WjSubProgramVO;

import java.util.List;

@Repository("WjSubProgramDAO")
public class WjSubProgramDAO extends EgovComAbstractDAO {
    
    public WjSubProgramVO selectSubProgram(WjSubProgramVO vo) throws Exception {
        return selectOne("WjSubProgramDAO.selectSubProgram", vo);
    }

    public Integer selectSubProgramTotalCount(WjSubProgramVO vo) throws Exception {
        return selectOne("WjSubProgramDAO.selectSubProgramTotalCount", vo);
    }

    public List<?> selectSubProgramList(WjSubProgramVO vo) throws Exception {
        return selectList("WjSubProgramDAO.selectSubProgramList", vo);
    }

    public void insertSubProgram(WjSubProgramVO vo) throws Exception {
        insert("WjSubProgramDAO.insertSubProgram", vo);
    }

    public void updateSubProgram(WjSubProgramVO vo) throws Exception {
        insert("WjSubProgramDAO.updateSubProgram", vo);
    }
}
