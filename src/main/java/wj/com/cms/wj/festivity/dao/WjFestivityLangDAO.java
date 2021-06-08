package wj.com.cms.wj.festivity.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.festivity.vo.WjFestivityLangVO;

import java.util.List;

@Repository("WjFestivityLangDAO")
public class WjFestivityLangDAO extends EgovComAbstractDAO {

    public WjFestivityLangVO selectFestivityLang(WjFestivityLangVO vo) throws Exception{
            return selectOne("WjFestivityLangDAO.selectFestivityLang", vo);
    }

    public Integer selectFestivityLangTotalCount(WjFestivityLangVO vo)throws Exception{
        return selectOne("WjFestivityLangDAO.selectFestivityLangTotalCount", vo);
    }

    public List<?> selectFestivityLangList(WjFestivityLangVO vo) throws Exception{
        return selectList("WjFestivityLangDAO.selectFestivityLangList",vo);
    }

    public void insertFestivityLang(WjFestivityLangVO vo)throws Exception{
        insert("WjFestivityLangDAO.insertFestivityLang",vo);
    }

    public void updateFestivityLang(WjFestivityLangVO vo)throws Exception{
        insert("WjFestivityLangDAO.updateFestivityLang",vo);
    }
}
