package gsis.com.cms.datainfo.jgDtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD04TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD05TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgD05TitleDAO")
public class JgD05TitleDAO extends EgovComAbstractDAO {

    public List<JewD05TiileDataVO> selectList(JewD05TiileDataVO vo)throws Exception{
        return selectList("JgD05TitleDAO.selectList",vo);
    }

    public void insert(JewD05TiileDataVO vo)throws Exception{
        insert("JgD05TitleDAO.insert",vo);
    }


    public void delete(JewD05TiileDataVO vo)throws Exception{
        delete("JgD05TitleDAO.delete",vo);
    }




}
