package gsis.com.cms.datainfo.jgAtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA02TiileDataVO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgA02TitleDAO")
public class JgA02TitleDAO extends EgovComAbstractDAO {

    public List<JewA02TiileDataVO> selectList(JewA02TiileDataVO vo)throws Exception{
        return selectList("JgA02TitleDAO.selectList",vo);
    }

    public void insert(JewA02TiileDataVO vo)throws Exception{
        insert("JgA02TitleDAO.insert",vo);
    }


    public void delete(JewA02TiileDataVO vo)throws Exception{
        delete("JgA02TitleDAO.delete",vo);
    }




}
