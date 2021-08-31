package gsis.com.cms.datainfo.jgAtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA08TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgA08TitleDAO")
public class JgA08TitleDAO extends EgovComAbstractDAO {

    public List<JewA08TiileDataVO> selectList(JewA08TiileDataVO vo)throws Exception{
        return selectList("JgA08TitleDAO.selectList",vo);
    }

    public void insert(JewA08TiileDataVO vo)throws Exception{
        insert("JgA08TitleDAO.insert",vo);
    }


    public void delete(JewA08TiileDataVO vo)throws Exception{
        delete("JgA08TitleDAO.delete",vo);
    }




}
