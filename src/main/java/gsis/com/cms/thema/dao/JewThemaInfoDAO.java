package gsis.com.cms.thema.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.thema.vo.JewThemaInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JewThemaInfoDAO")
public class JewThemaInfoDAO extends EgovComAbstractDAO {

    public JewThemaInfoVO selectThemaInfo(JewThemaInfoVO vo) throws Exception {
        return selectOne("JewThemaInfoDAO.selectThemaInfo", vo);
    }

    public Integer selectThemaInfoTotalCount(JewThemaInfoVO vo) throws Exception {
        return selectOne("JewThemaInfoDAO.selectThemaInfoTotalCount", vo);
    }

    public List<?> selectThemaInfoList(JewThemaInfoVO vo) throws Exception {
        return selectList("JewThemaInfoDAO.selectThemaInfoList", vo);
    }

    public void insertThemaInfo(JewThemaInfoVO vo) throws Exception {
        insert("JewThemaInfoDAO.insertThemaInfo", vo);
    }

    public void updateThemaInfo(JewThemaInfoVO vo) throws Exception {
        insert("JewThemaInfoDAO.updateThemaInfo", vo);
    }

    public void deleteThemaInfo(JewThemaInfoVO vo) throws Exception {
        insert("JewThemaInfoDAO.deleteThemaInfo", vo);
    }

    public List<?> selectSiteThemaInfoList(JewThemaInfoVO vo) throws Exception {
        return selectList("JewThemaInfoDAO.selectSiteThemaInfoList", vo);
    }


    public List<?> selectDashBoardThemaInfo(JewThemaInfoVO vo) throws Exception {
        return selectList("JewThemaInfoDAO.selectDashBoardThemaInfo", vo);
    }



}
