package gsis.com.cms.datainfo.jgFtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF03TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgF03TitleDAO")
public class JgF03TitleDAO extends EgovComAbstractDAO {

    public List<JewF03TiileDataVO> selectList(JewF03TiileDataVO vo)throws Exception{
        return selectList("JgF03TitleDAO.selectList",vo);
    }

    public void insert(JewF03TiileDataVO vo)throws Exception{
        insert("JgF03TitleDAO.insert",vo);
    }


    public void delete(JewF03TiileDataVO vo)throws Exception{
        delete("JgF03TitleDAO.delete",vo);
    }




}
