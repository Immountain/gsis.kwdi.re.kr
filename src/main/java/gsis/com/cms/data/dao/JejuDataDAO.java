package gsis.com.cms.data.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.data.vo.JewThemaFileHisVO;
import gsis.com.cms.thema.vo.JewThemaGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JejuDataDAO")
public class JejuDataDAO extends EgovComAbstractDAO {

    public List<JewThemaFileHisVO> selectJewThemaFileHis(JewThemaFileHisVO vo)throws Exception{
        return selectList("JejuDataDAO.selectJewThemaFileHis",vo);
    }

    public void insertJewThemaFileHis(JewThemaFileHisVO vo)throws Exception{
        insert("JejuDataDAO.insertJewThemaFileHis",vo);
    }


    public JewThemaFileHisVO selectJewThemaFileHisView(JewThemaFileHisVO vo)throws Exception{
      return   selectOne("JejuDataDAO.selectJewThemaFileHisView",vo);
    }

    public void updateJewThemaFileHis(JewThemaFileHisVO vo)throws Exception{
        update("JejuDataDAO.updateJewThemaFileHis",vo);
    }


    public void getInsertJewUpdateKeep(JewThemaFileHisVO vo)throws Exception{
        insert("JejuDataDAO.insertJewUpdateKeep",vo);
    }


    public void getInsertJewThemaVisit(JewThemaFileHisVO vo)throws Exception{
        insert("JejuDataDAO.insertJewThemaVisit",vo);
    }


    public List<JewThemaFileHisVO> getSelectMainUpdateList(JewThemaFileHisVO vo)throws Exception{
        return selectList("JejuDataDAO.getSelectMainUpdateList",vo);
    }



}
