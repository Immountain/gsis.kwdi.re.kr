package wj.com.cms.wj.program.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.program.vo.WjProgramInfoLangVO;

import java.util.List;

@Repository("WjProgramInfoLangDAO")
public class WjProgramInfoLangDAO extends EgovComAbstractDAO {

    public WjProgramInfoLangVO selectProgramInfoLang(WjProgramInfoLangVO vo)throws Exception{
        return selectOne("WjProgramInfoLangDAO.selectProgramInfoLang",vo);
    }

    public Integer selectProgramInfoLangTotalCount(WjProgramInfoLangVO vo)throws Exception{
        return selectOne("WjProgramInfoLangDAO.selectProgramInfoLangTotalCount",vo);
    }

    public List<?> selectProgramInfoLangList(WjProgramInfoLangVO vo)throws Exception{
        return selectList("WjProgramInfoLangDAO.selectProgramInfoLangList",vo);
    }

    public void insertProgramInfoLang(WjProgramInfoLangVO vo)throws Exception{
        insert("WjProgramInfoLangDAO.insertProgramInfoLang",vo);
    }

    public void updateProgramInfoLang(WjProgramInfoLangVO vo)throws Exception{
        insert("WjProgramInfoLangDAO.updateProgramInfoLang",vo);
    }

    public void updateProgramInfoContentLang(WjProgramInfoLangVO vo)throws Exception{
        insert("WjProgramInfoLangDAO.updateProgramInfoContentLang",vo);
    }
}
