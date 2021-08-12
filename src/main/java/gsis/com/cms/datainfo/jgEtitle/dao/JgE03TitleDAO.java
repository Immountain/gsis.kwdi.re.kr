package gsis.com.cms.datainfo.jgEtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD06TiileDataVO;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE03TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgE03TitleDAO")
public class JgE03TitleDAO extends EgovComAbstractDAO {

    public List<JewE03TiileDataVO> selectList(JewE03TiileDataVO vo)throws Exception{
        return selectList("JgE03TitleDAO.selectList",vo);
    }

    public void insert(JewE03TiileDataVO vo)throws Exception{
        insert("JgE03TitleDAO.insert",vo);
    }


    public void delete(JewE03TiileDataVO vo)throws Exception{
        delete("JgE03TitleDAO.delete",vo);
    }




}
