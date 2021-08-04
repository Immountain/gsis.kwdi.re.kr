package gsis.com.cms.datainfo.jgDtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD03TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD04TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgD04TitleDAO")
public class JgD04TitleDAO extends EgovComAbstractDAO {

    public List<JewD04TiileDataVO> selectList(JewD04TiileDataVO vo)throws Exception{
        return selectList("JgD04TitleDAO.selectList",vo);
    }

    public void insert(JewD04TiileDataVO vo)throws Exception{
        insert("JgD04TitleDAO.insert",vo);
    }


    public void delete(JewD04TiileDataVO vo)throws Exception{
        delete("JgD04TitleDAO.delete",vo);
    }




}
