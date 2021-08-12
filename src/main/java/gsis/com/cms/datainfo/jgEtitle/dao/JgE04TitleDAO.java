package gsis.com.cms.datainfo.jgEtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE03TiileDataVO;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE04TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgE04TitleDAO")
public class JgE04TitleDAO extends EgovComAbstractDAO {

    public List<JewE04TiileDataVO> selectList(JewE04TiileDataVO vo)throws Exception{
        return selectList("JgE04TitleDAO.selectList",vo);
    }

    public void insert(JewE04TiileDataVO vo)throws Exception{
        insert("JgE04TitleDAO.insert",vo);
    }


    public void delete(JewE04TiileDataVO vo)throws Exception{
        delete("JgE04TitleDAO.delete",vo);
    }




}
