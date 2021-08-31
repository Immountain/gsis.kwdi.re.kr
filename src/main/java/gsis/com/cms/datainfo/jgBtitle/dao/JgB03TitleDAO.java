package gsis.com.cms.datainfo.jgBtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB03TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgB03TitleDAO")
public class JgB03TitleDAO extends EgovComAbstractDAO {

    public List<JewB03TiileDataVO> selectList(JewB03TiileDataVO vo)throws Exception{
        return selectList("JgB03TitleDAO.selectList",vo);
    }

    public void insert(JewB03TiileDataVO vo)throws Exception{
        insert("JgB03TitleDAO.insert",vo);
    }


    public void delete(JewB03TiileDataVO vo)throws Exception{
        delete("JgB03TitleDAO.delete",vo);
    }




}
