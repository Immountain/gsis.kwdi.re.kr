package gsis.com.cms.datainfo.jgFtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF06TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgF06TitleDAO")
public class JgF06TitleDAO extends EgovComAbstractDAO {

    public List<JewF06TiileDataVO> selectList(JewF06TiileDataVO vo)throws Exception{
        return selectList("JgF06TitleDAO.selectList",vo);
    }

    public void insert(JewF06TiileDataVO vo)throws Exception{
        insert("JgF06TitleDAO.insert",vo);
    }


    public void delete(JewF06TiileDataVO vo)throws Exception{
        delete("JgF06TitleDAO.delete",vo);
    }




}
