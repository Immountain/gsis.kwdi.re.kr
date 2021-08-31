package gsis.com.cms.datainfo.jgCtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC05TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgC05TitleDAO")
public class JgC05TitleDAO extends EgovComAbstractDAO {

    public List<JewC05TiileDataVO> selectList(JewC05TiileDataVO vo)throws Exception{
        return selectList("JgC05TitleDAO.selectList",vo);
    }

    public void insert(JewC05TiileDataVO vo)throws Exception{
        insert("JgC05TitleDAO.insert",vo);
    }


    public void delete(JewC05TiileDataVO vo)throws Exception{
        delete("JgC05TitleDAO.delete",vo);
    }




}
