package gsis.com.cms.datainfo.jgBtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA02TiileDataVO;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB05TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgB05TitleDAO")
public class JgB05TitleDAO extends EgovComAbstractDAO {

    public List<JewB05TiileDataVO> selectList(JewB05TiileDataVO vo)throws Exception{
        return selectList("JgB05TitleDAO.selectList",vo);
    }

    public void insert(JewB05TiileDataVO vo)throws Exception{
        insert("JgB05TitleDAO.insert",vo);
    }


    public void delete(JewB05TiileDataVO vo)throws Exception{
        delete("JgB05TitleDAO.delete",vo);
    }




}
