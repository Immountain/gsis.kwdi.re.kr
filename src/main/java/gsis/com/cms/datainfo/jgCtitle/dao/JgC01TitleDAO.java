package gsis.com.cms.datainfo.jgCtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgC01TitleDAO")
public class JgC01TitleDAO extends EgovComAbstractDAO {

    public List<JewC01TiileDataVO> selectList(JewC01TiileDataVO vo)throws Exception{
        return selectList("JgC01TitleDAO.selectList",vo);
    }

    public void insert(JewC01TiileDataVO vo)throws Exception{
        insert("JgC01TitleDAO.insert",vo);
    }


    public void delete(JewC01TiileDataVO vo)throws Exception{
        delete("JgC01TitleDAO.delete",vo);
    }




}
