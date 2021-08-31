package gsis.com.cms.datainfo.jgBtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB04TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgB04TitleDAO")
public class JgB04TitleDAO extends EgovComAbstractDAO {

    public List<JewB04TiileDataVO> selectList(JewB04TiileDataVO vo)throws Exception{
        return selectList("JgB04TitleDAO.selectList",vo);
    }

    public void insert(JewB04TiileDataVO vo)throws Exception{
        insert("JgB04TitleDAO.insert",vo);
    }


    public void delete(JewB04TiileDataVO vo)throws Exception{
        delete("JgB04TitleDAO.delete",vo);
    }




}
