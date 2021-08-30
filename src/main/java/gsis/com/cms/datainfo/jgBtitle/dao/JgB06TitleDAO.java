package gsis.com.cms.datainfo.jgBtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB01TiileDataVO;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB06TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgB06TitleDAO")
public class JgB06TitleDAO extends EgovComAbstractDAO {

    public List<JewB06TiileDataVO> selectList(JewB06TiileDataVO vo)throws Exception{
        return selectList("JgB06TitleDAO.selectList",vo);
    }

    public void insert(JewB06TiileDataVO vo)throws Exception{
        insert("JgB06TitleDAO.insert",vo);
    }


    public void delete(JewB06TiileDataVO vo)throws Exception{
        delete("JgB06TitleDAO.delete",vo);
    }




}
