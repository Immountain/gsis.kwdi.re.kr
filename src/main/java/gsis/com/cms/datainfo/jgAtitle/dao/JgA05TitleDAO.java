package gsis.com.cms.datainfo.jgAtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA05TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgA05TitleDAO")
public class JgA05TitleDAO extends EgovComAbstractDAO {

    public List<JewA05TiileDataVO> selectList(JewA05TiileDataVO vo)throws Exception{
        return selectList("JgA05TitleDAO.selectList",vo);
    }

    public void insert(JewA05TiileDataVO vo)throws Exception{
        insert("JgA05TitleDAO.insert",vo);
    }


    public void delete(JewA05TiileDataVO vo)throws Exception{
        delete("JgA05TitleDAO.delete",vo);
    }




}
