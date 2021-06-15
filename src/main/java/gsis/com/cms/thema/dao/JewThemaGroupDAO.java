package gsis.com.cms.thema.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.thema.vo.JewThemaGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JewThemaGroupDAO")
public class JewThemaGroupDAO extends EgovComAbstractDAO {

    public JewThemaGroupVO selectThemaGroup(JewThemaGroupVO vo)throws Exception{
        return selectOne("JewThemaGroupDAO.selectThemaGroup",vo);
    }

    public List<?> selectThemaGroupList(JewThemaGroupVO vo)throws Exception{
        return selectList("JewThemaGroupDAO.selectThemaGroupList",vo);
    }

    public void insertThemaGroup(JewThemaGroupVO vo)throws Exception{
        insert("JewThemaGroupDAO.insertThemaGroup",vo);
    }

    public void updateThemaGroup(JewThemaGroupVO vo)throws Exception{
        insert("JewThemaGroupDAO.updateThemaGroup",vo);
    }

    public void deleteThemaGroup(JewThemaGroupVO vo)throws Exception{
        insert("JewThemaGroupDAO.deleteThemaGroup",vo);
    }

}
