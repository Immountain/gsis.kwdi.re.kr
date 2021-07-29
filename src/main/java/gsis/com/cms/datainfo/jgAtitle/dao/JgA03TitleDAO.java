package gsis.com.cms.datainfo.jgAtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgA03TitleDAO")
public class JgA03TitleDAO extends EgovComAbstractDAO {

    public List<JewA03TiileDataVO> selectList(JewA03TiileDataVO vo)throws Exception{
        return selectList("JgA03TitleDAO.selectList",vo);
    }

    public void insert(JewA03TiileDataVO vo)throws Exception{
        insert("JgA03TitleDAO.insert",vo);
    }


    public void delete(JewA03TiileDataVO vo)throws Exception{
        delete("JgA03TitleDAO.delete",vo);
    }




}
