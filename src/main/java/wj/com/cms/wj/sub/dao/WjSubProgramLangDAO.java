package wj.com.cms.wj.sub.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.sub.vo.WjSubProgramLangVO;

import java.util.List;

@Repository("WjSubProgramLangDAO")
public class WjSubProgramLangDAO extends EgovComAbstractDAO {

    public WjSubProgramLangVO selectSubProgramLang(WjSubProgramLangVO vo)throws Exception{
        return selectOne("WjSubProgramLangDAO.selectSubProgramLang",vo);
    }

    public Integer selectSubProgramLangTotalCount(WjSubProgramLangVO vo)throws Exception{
        return selectOne("WjSubProgramLangDAO.selectSubProgramLangTotalCount",vo);
    }

    public List<?> selectSubProgramLangList(WjSubProgramLangVO vo)throws Exception{
        return selectList("WjSubProgramLangDAO.selectSubProgramLangList",vo);
    }

    public void insertSubProgramLang(WjSubProgramLangVO vo)throws Exception{
         insert("WjSubProgramLangDAO.insertSubProgramLang",vo);
    }

    public void updateSubProgramLang(WjSubProgramLangVO vo)throws Exception{
        insert("WjSubProgramLangDAO.updateSubProgramLang",vo);
    }

    public void updateSubProgramContentLang(WjSubProgramLangVO vo)throws Exception{
        insert("WjSubProgramLangDAO.updateSubProgramContentLang",vo);
    }
}
