package gsis.com.cms.mdis.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.mdis.vo.JewMdisVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JewMdisDAO")
public class JewMdisDAO extends EgovComAbstractDAO {

    public JewMdisVO selectMdis(JewMdisVO vo)throws Exception{
        return selectOne("JewMdisDAO.selectMdis",vo);
    }

    public List<?> selectMdisList(JewMdisVO vo)throws Exception{
        return selectList("JewMdisDAO.selectMdisList", vo);
    }

    public void insertMdis(JewMdisVO vo)throws Exception{
         insert("JewMdisDAO.insertMdis",vo);
    }

    public void updateMdis(JewMdisVO vo)throws Exception{
        insert("JewMdisDAO.updateMdis",vo);
    }
}
