package gsis.com.cms.datainfo.jgAtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA07TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgA07TitleDAO")
public class JgA07TitleDAO extends EgovComAbstractDAO {

    public List<JewA07TiileDataVO> selectList(JewA07TiileDataVO vo)throws Exception{
        return selectList("JgA07TitleDAO.selectList",vo);
    }

    public void insert(JewA07TiileDataVO vo)throws Exception{
        insert("JgA07TitleDAO.insert",vo);
    }


    public void delete(JewA07TiileDataVO vo)throws Exception{
        delete("JgA07TitleDAO.delete",vo);
    }




}
