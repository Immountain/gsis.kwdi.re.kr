package gsis.com.cms.datainfo.jgEtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE01TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgE01TitleDAO")
public class JgE01TitleDAO extends EgovComAbstractDAO {

    public List<JewE01TiileDataVO> selectList(JewE01TiileDataVO vo)throws Exception{
        return selectList("JgE01TitleDAO.selectList",vo);
    }

    public void insert(JewE01TiileDataVO vo)throws Exception{
        insert("JgE01TitleDAO.insert",vo);
    }


    public void delete(JewE01TiileDataVO vo)throws Exception{
        delete("JgE01TitleDAO.delete",vo);
    }




}
