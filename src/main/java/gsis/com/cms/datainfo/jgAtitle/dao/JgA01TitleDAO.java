package gsis.com.cms.datainfo.jgAtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA01TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgA01TitleDAO")
public class JgA01TitleDAO extends EgovComAbstractDAO {

    public List<JewA01TiileDataVO> selectList(JewA01TiileDataVO vo)throws Exception{
        return selectList("JgA01TitleDAO.selectList",vo);
    }

    public void insert(JewA01TiileDataVO vo)throws Exception{
        insert("JgA01TitleDAO.insert",vo);
    }


    public void delete(JewA01TiileDataVO vo)throws Exception{
        delete("JgA01TitleDAO.delete",vo);
    }




}
