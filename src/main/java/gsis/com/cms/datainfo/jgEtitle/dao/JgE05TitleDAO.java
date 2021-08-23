package gsis.com.cms.datainfo.jgEtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE05TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgE05TitleDAO")
public class JgE05TitleDAO extends EgovComAbstractDAO {

    public List<JewE05TiileDataVO> selectList(JewE05TiileDataVO vo)throws Exception{
        return selectList("JgE05TitleDAO.selectList",vo);
    }

    public void insert(JewE05TiileDataVO vo)throws Exception{
        insert("JgE05TitleDAO.insert",vo);
    }


    public void delete(JewE05TiileDataVO vo)throws Exception{
        delete("JgE05TitleDAO.delete",vo);
    }




}
