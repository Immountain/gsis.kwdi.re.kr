package gsis.com.cms.datainfo.jgCtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC02TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC03TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgC03TitleDAO")
public class JgC03TitleDAO extends EgovComAbstractDAO {

    public List<JewC03TiileDataVO> selectList(JewC03TiileDataVO vo)throws Exception{
        return selectList("JgC03TitleDAO.selectList",vo);
    }

    public void insert(JewC03TiileDataVO vo)throws Exception{
        insert("JgC03TitleDAO.insert",vo);
    }


    public void delete(JewC03TiileDataVO vo)throws Exception{
        delete("JgC03TitleDAO.delete",vo);
    }




}
