package gsis.com.cms.datainfo.jgAtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA04TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgA04TitleDAO")
public class JgA04TitleDAO extends EgovComAbstractDAO {

    public List<JewA04TiileDataVO> selectList(JewA04TiileDataVO vo)throws Exception{
        return selectList("JgA04TitleDAO.selectList",vo);
    }

    public void insert(JewA04TiileDataVO vo)throws Exception{
        insert("JgA04TitleDAO.insert",vo);
    }


    public void delete(JewA04TiileDataVO vo)throws Exception{
        delete("JgA04TitleDAO.delete",vo);
    }




}
