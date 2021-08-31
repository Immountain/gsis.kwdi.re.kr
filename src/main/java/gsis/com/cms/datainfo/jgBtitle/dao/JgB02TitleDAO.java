package gsis.com.cms.datainfo.jgBtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB02TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgB02TitleDAO")
public class JgB02TitleDAO extends EgovComAbstractDAO {

    public List<JewB02TiileDataVO> selectList(JewB02TiileDataVO vo)throws Exception{
        return selectList("JgB02TitleDAO.selectList",vo);
    }

    public void insert(JewB02TiileDataVO vo)throws Exception{
        insert("JgB02TitleDAO.insert",vo);
    }


    public void delete(JewB02TiileDataVO vo)throws Exception{
        delete("JgB02TitleDAO.delete",vo);
    }




}
