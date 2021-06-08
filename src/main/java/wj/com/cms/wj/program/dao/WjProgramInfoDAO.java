package wj.com.cms.wj.program.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.program.vo.WjProgramInfoVO;

import java.util.List;

@Repository("WjProgramInfoDAO")
public class WjProgramInfoDAO extends EgovComAbstractDAO {
    
    public WjProgramInfoVO selectProgramInfo(WjProgramInfoVO vo) throws Exception {
        return selectOne("WjProgramInfoDAO.selectProgramInfo", vo);
    }

    public Integer selectProgramInfoTotalCount(WjProgramInfoVO vo) throws Exception {
        return selectOne("WjProgramInfoDAO.selectProgramInfoTotalCount", vo);
    }

    public List<?> selectProgramInfoList(WjProgramInfoVO vo) throws Exception {
        return selectList("WjProgramInfoDAO.selectProgramInfoList", vo);
    }

    public void insertProgramInfo(WjProgramInfoVO vo) throws Exception {
        insert("WjProgramInfoDAO.insertProgramInfo", vo);
    }

    public void updateProgramInfo(WjProgramInfoVO vo) throws Exception {
        insert("WjProgramInfoDAO.updateProgramInfo", vo);
    }

    public void deleteProgramInfo(WjProgramInfoVO vo) throws Exception{
        insert("WjProgramInfoDAO.deleteProgramInfo", vo);
    }
}
