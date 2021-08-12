package gsis.com.cms.datainfo.jgCtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC07TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC08TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgC08TitleDAO")
public class JgC08TitleDAO extends EgovComAbstractDAO {

    public List<JewC08TiileDataVO> selectList(JewC08TiileDataVO vo)throws Exception{
        return selectList("JgC08TitleDAO.selectList",vo);
    }

    public void insert(JewC08TiileDataVO vo)throws Exception{
        insert("JgC08TitleDAO.insert",vo);
    }


    public void delete(JewC08TiileDataVO vo)throws Exception{
        delete("JgC08TitleDAO.delete",vo);
    }




}
