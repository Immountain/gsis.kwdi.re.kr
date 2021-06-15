package gsis.com.cms.stats.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.stats.vo.JewStatsCategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JewStatsCategoryDAO")
public class JewStatsCategoryDAO extends EgovComAbstractDAO {

    public JewStatsCategoryVO selectStatsCategory(JewStatsCategoryVO vo)throws Exception{
        return selectOne("JewStatsCategoryDAO.selectStatsCategory",vo);
    }

    public List<?> selectStatsCategoryList(JewStatsCategoryVO vo)throws Exception{
        return selectList("JewStatsCategoryDAO.selectStatsCategoryList",vo);
    }

    public List<?> selectStatsCategorySearchList(JewStatsCategoryVO vo)throws Exception{
        return selectList("JewStatsCategoryDAO.selectStatsCategorySearchList",vo);
    }

    public void insertStatsCategory(JewStatsCategoryVO vo)throws Exception{
        insert("JewStatsCategoryDAO.insertStatsCategory",vo);
    }

    public void updateStatsCategory(JewStatsCategoryVO vo)throws Exception{
        insert("JewStatsCategoryDAO.updateStatsCategory",vo);
    }

    public void deleteStatsCategory(JewStatsCategoryVO vo)throws Exception{
        insert("JewStatsCategoryDAO.deleteStatsCategory",vo);
    }
}
