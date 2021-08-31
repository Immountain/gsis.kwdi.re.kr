package gsis.com.cms.datainfo.jgCtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC04TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgC04TitleDAO")
public class JgC04TitleDAO extends EgovComAbstractDAO {

    public List<JewC04TiileDataVO> selectList(JewC04TiileDataVO vo)throws Exception{
        return selectList("JgC04TitleDAO.selectList",vo);
    }

    public void insert(JewC04TiileDataVO vo)throws Exception{
        insert("JgC04TitleDAO.insert",vo);
    }


    public void delete(JewC04TiileDataVO vo)throws Exception{
        delete("JgC04TitleDAO.delete",vo);
    }




}
