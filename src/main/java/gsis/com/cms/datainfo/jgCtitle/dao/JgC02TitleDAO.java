package gsis.com.cms.datainfo.jgCtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC02TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgC02TitleDAO")
public class JgC02TitleDAO extends EgovComAbstractDAO {

    public List<JewC02TiileDataVO> selectList(JewC02TiileDataVO vo)throws Exception{
        return selectList("JgC02TitleDAO.selectList",vo);
    }

    public void insert(JewC02TiileDataVO vo)throws Exception{
        insert("JgC02TitleDAO.insert",vo);
    }


    public void delete(JewC02TiileDataVO vo)throws Exception{
        delete("JgC02TitleDAO.delete",vo);
    }




}
