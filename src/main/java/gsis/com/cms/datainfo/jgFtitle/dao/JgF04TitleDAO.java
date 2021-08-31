package gsis.com.cms.datainfo.jgFtitle.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF04TiileDataVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JgF04TitleDAO")
public class JgF04TitleDAO extends EgovComAbstractDAO {

    public List<JewF04TiileDataVO> selectList(JewF04TiileDataVO vo)throws Exception{
        return selectList("JgF04TitleDAO.selectList",vo);
    }

    public void insert(JewF04TiileDataVO vo)throws Exception{
        insert("JgF04TitleDAO.insert",vo);
    }


    public void delete(JewF04TiileDataVO vo)throws Exception{
        delete("JgF04TitleDAO.delete",vo);
    }




}
