package gsis.com.cms.datainfo.jgDtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD05TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD06TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgD06TitleDAO")
public class JgD06TitleDAO extends EgovComAbstractDAO {

    public List<JewD06TiileDataVO> selectList(JewD06TiileDataVO vo)throws Exception{
        return selectList("JgD06TitleDAO.selectList",vo);
    }

    public void insert(JewD06TiileDataVO vo)throws Exception{
        insert("JgD06TitleDAO.insert",vo);
    }


    public void delete(JewD06TiileDataVO vo)throws Exception{
        delete("JgD06TitleDAO.delete",vo);
    }




}
