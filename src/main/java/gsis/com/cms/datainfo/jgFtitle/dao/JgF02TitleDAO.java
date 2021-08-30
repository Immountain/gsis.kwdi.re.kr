package gsis.com.cms.datainfo.jgFtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF01TiileDataVO;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF02TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgF02TitleDAO")
public class JgF02TitleDAO extends EgovComAbstractDAO {

    public List<JewF02TiileDataVO> selectList(JewF02TiileDataVO vo)throws Exception{
        return selectList("JgF02TitleDAO.selectList",vo);
    }

    public List<JewF02TiileDataVO> selectList2(JewF02TiileDataVO vo)throws Exception{
        return selectList("JgF02TitleDAO.selectList2",vo);
    }

    public void insert(JewF02TiileDataVO vo)throws Exception{
        insert("JgF02TitleDAO.insert",vo);
    }


    public void delete(JewF02TiileDataVO vo)throws Exception{
        delete("JgF02TitleDAO.delete",vo);
    }




}
