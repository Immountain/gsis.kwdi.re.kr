package gsis.com.cms.datainfo.jgAtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA06TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgA06TitleDAO")
public class JgA06TitleDAO extends EgovComAbstractDAO {

    public List<JewA06TiileDataVO> selectList(JewA06TiileDataVO vo)throws Exception{
        return selectList("JgA06TitleDAO.selectList",vo);
    }

    public void insert(JewA06TiileDataVO vo)throws Exception{
        insert("JgA06TitleDAO.insert",vo);
    }


    public void delete(JewA06TiileDataVO vo)throws Exception{
        delete("JgA06TitleDAO.delete",vo);
    }




}
