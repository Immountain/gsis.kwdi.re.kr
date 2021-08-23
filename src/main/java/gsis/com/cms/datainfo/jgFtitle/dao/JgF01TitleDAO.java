package gsis.com.cms.datainfo.jgFtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF01TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgF01TitleDAO")
public class JgF01TitleDAO extends EgovComAbstractDAO {

    public List<JewF01TiileDataVO> selectList(JewF01TiileDataVO vo)throws Exception{
        return selectList("JgF01TitleDAO.selectList",vo);
    }

    public void insert(JewF01TiileDataVO vo)throws Exception{
        insert("JgF01TitleDAO.insert",vo);
    }


    public void delete(JewF01TiileDataVO vo)throws Exception{
        delete("JgF01TitleDAO.delete",vo);
    }




}
