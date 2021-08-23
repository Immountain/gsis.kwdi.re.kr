package gsis.com.cms.datainfo.jgFtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF05TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgF05TitleDAO")
public class JgF05TitleDAO extends EgovComAbstractDAO {

    public List<JewF05TiileDataVO> selectList(JewF05TiileDataVO vo)throws Exception{
        return selectList("JgF05TitleDAO.selectList",vo);
    }

    public void insert(JewF05TiileDataVO vo)throws Exception{
        insert("JgF05TitleDAO.insert",vo);
    }


    public void delete(JewF05TiileDataVO vo)throws Exception{
        delete("JgF05TitleDAO.delete",vo);
    }




}
