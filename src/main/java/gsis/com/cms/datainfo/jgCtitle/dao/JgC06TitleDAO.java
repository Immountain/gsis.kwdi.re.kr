package gsis.com.cms.datainfo.jgCtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC06TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgC06TitleDAO")
public class JgC06TitleDAO extends EgovComAbstractDAO {

    public List<JewC06TiileDataVO> selectList(JewC06TiileDataVO vo)throws Exception{
        return selectList("JgC06TitleDAO.selectList",vo);
    }

    public void insert(JewC06TiileDataVO vo)throws Exception{
        insert("JgC06TitleDAO.insert",vo);
    }


    public void delete(JewC06TiileDataVO vo)throws Exception{
        delete("JgC06TitleDAO.delete",vo);
    }




}
