package gsis.com.cms.datainfo.jgDtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD01TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD03TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgD03TitleDAO")
public class JgD03TitleDAO extends EgovComAbstractDAO {

    public List<JewD03TiileDataVO> selectList(JewD03TiileDataVO vo)throws Exception{
        return selectList("JgD03TitleDAO.selectList",vo);
    }

    public void insert(JewD03TiileDataVO vo)throws Exception{
        insert("JgD03TitleDAO.insert",vo);
    }


    public void delete(JewD03TiileDataVO vo)throws Exception{
        delete("JgD03TitleDAO.delete",vo);
    }




}
