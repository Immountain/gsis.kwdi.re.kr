package gsis.com.cms.datainfo.jgCtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC03TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC07TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgC07TitleDAO")
public class JgC07TitleDAO extends EgovComAbstractDAO {

    public List<JewC07TiileDataVO> selectList(JewC07TiileDataVO vo)throws Exception{
        return selectList("JgC07TitleDAO.selectList",vo);
    }

    public void insert(JewC07TiileDataVO vo)throws Exception{
        insert("JgC07TitleDAO.insert",vo);
    }


    public void delete(JewC07TiileDataVO vo)throws Exception{
        delete("JgC07TitleDAO.delete",vo);
    }




}
