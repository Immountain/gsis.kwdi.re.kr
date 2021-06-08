package wj.com.cms.wj.program.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.program.vo.WjProgramSubVO;

import java.util.List;

@Repository("WjProgramSubDAO")
public class WjProgramSubDAO extends EgovComAbstractDAO {

    public WjProgramSubVO selectProgramSub(WjProgramSubVO vo)throws Exception{
        return selectOne("WjProgramSubDAO.selectProgramSub",vo);
    }

    public Integer selectProgramSubTotalCount(WjProgramSubVO vo) throws Exception{
        return selectOne("WjProgramSubDAO.selectProgramSubTotalCount",vo);
    }

    public List<?> selectProgramSubList(WjProgramSubVO vo)throws Exception{
        return selectList("WjProgramSubDAO.selectProgramSubList",vo);
    }

    public void insertProgramSub(WjProgramSubVO vo)throws Exception{
         insert("WjProgramSubDAO.insertProgramSub",vo);
    }

    public void updateProgramSub(WjProgramSubVO vo)throws Exception{
        insert("WjProgramSubDAO.updateProgramSub",vo);
    }

    public void deleteProgramSub(WjProgramSubVO vo)throws Exception{
        insert("WjProgramSubDAO.deleteProgramSub", vo);
    }

}
