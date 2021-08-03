package gsis.com.cms.datainfo.jgDtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD01TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgD01TitleDAO")
public class JgD01TitleDAO extends EgovComAbstractDAO {

    public List<JewD01TiileDataVO> selectList(JewD01TiileDataVO vo)throws Exception{
        return selectList("JgD01TitleDAO.selectList",vo);
    }

    public void insert(JewD01TiileDataVO vo)throws Exception{
        insert("JgD01TitleDAO.insert",vo);
    }


    public void delete(JewD01TiileDataVO vo)throws Exception{
        delete("JgD01TitleDAO.delete",vo);
    }




}
