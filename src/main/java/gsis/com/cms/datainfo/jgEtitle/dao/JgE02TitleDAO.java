package gsis.com.cms.datainfo.jgEtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE02TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgE02TitleDAO")
public class JgE02TitleDAO extends EgovComAbstractDAO {

    public List<JewE02TiileDataVO> selectList(JewE02TiileDataVO vo)throws Exception{
        return selectList("JgE02TitleDAO.selectList",vo);
    }

    public void insert(JewE02TiileDataVO vo)throws Exception{
        insert("JgE02TitleDAO.insert",vo);
    }


    public void delete(JewE02TiileDataVO vo)throws Exception{
        delete("JgE02TitleDAO.delete",vo);
    }




}
