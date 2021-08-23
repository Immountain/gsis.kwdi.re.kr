package gsis.com.cms.datainfo.jgEtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE06TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgE06TitleDAO")
public class JgE06TitleDAO extends EgovComAbstractDAO {

    public List<JewE06TiileDataVO> selectList(JewE06TiileDataVO vo)throws Exception{
        return selectList("JgE06TitleDAO.selectList",vo);
    }

    public void insert(JewE06TiileDataVO vo)throws Exception{
        insert("JgE06TitleDAO.insert",vo);
    }


    public void delete(JewE06TiileDataVO vo)throws Exception{
        delete("JgE06TitleDAO.delete",vo);
    }




}
