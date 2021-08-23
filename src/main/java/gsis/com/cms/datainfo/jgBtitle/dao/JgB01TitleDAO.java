package gsis.com.cms.datainfo.jgBtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB01TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgB01TitleDAO")
public class JgB01TitleDAO extends EgovComAbstractDAO {

    public List<JewB01TiileDataVO> selectList(JewB01TiileDataVO vo)throws Exception{
        return selectList("JgB01TitleDAO.selectList",vo);
    }

    public void insert(JewB01TiileDataVO vo)throws Exception{
        insert("JgB01TitleDAO.insert",vo);
    }


    public void delete(JewB01TiileDataVO vo)throws Exception{
        delete("JgB01TitleDAO.delete",vo);
    }




}
