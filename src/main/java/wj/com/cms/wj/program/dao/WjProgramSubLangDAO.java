package wj.com.cms.wj.program.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.program.vo.WjProgramSubLangVO;

import java.util.List;

@Repository("WjProgramSubLangDAO")
public class WjProgramSubLangDAO extends EgovComAbstractDAO {

    public WjProgramSubLangVO selectProgramSubLang(WjProgramSubLangVO vo)throws Exception{
        return selectOne("WjProgramSubLangDAO.selectProgramSubLang",vo);
    }

    public Integer selectProgramSubLangTotalCount(WjProgramSubLangVO vo) throws Exception{
        return selectOne("WjProgramSubLangDAO.selectProgramSubLangTotalCount",vo);
    }

    public List<?> selectProgramSubLangList(WjProgramSubLangVO vo)throws Exception{
        return selectList("WjProgramSubLangDAO.selectProgramSubLangList",vo);
    }

    public void insertProgramSubLang(WjProgramSubLangVO vo)throws Exception{
         insert("WjProgramSubLangDAO.insertProgramSubLang",vo);
    }

    public void updateProgramSubLang(WjProgramSubLangVO vo)throws Exception{
        insert("WjProgramSubLangDAO.updateProgramSubLang",vo);
    }

}
