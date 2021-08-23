package gsis.com.cms.datainfo.jgDtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD01TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD02TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgD02TitleDAO")
public class JgD02TitleDAO extends EgovComAbstractDAO {

    public List<JewD02TiileDataVO> selectList(JewD02TiileDataVO vo)throws Exception{
        return selectList("JgD02TitleDAO.selectList",vo);
    }

    public void insert(JewD02TiileDataVO vo)throws Exception{
        insert("JgD02TitleDAO.insert",vo);
    }


    public void delete(JewD02TiileDataVO vo)throws Exception{
        delete("JgD02TitleDAO.delete",vo);
    }




}
