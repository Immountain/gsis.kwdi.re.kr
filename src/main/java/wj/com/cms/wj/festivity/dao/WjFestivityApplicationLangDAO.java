package wj.com.cms.wj.festivity.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.festivity.vo.WjFestivityApplicationLangVO;

import java.util.List;

@Repository("WjFestivityApplicationLangDAO")
public class WjFestivityApplicationLangDAO extends EgovComAbstractDAO {

    public WjFestivityApplicationLangVO selectFestivityApplicationLang(WjFestivityApplicationLangVO vo)throws Exception{
        return selectOne("WjFestivityApplicationLangDAO.selectFestivityApplicationLang",vo);
    }

    public Integer selectFestivityApplicationLangTotalCount(WjFestivityApplicationLangVO vo)throws Exception{
        return selectOne("WjFestivityApplicationLangDAO.selectFestivityApplicationLangTotalCount",vo);
    }

    public List<?> selectFestivityApplicationLangList(WjFestivityApplicationLangVO vo)throws Exception{
        return selectList("WjFestivityApplicationLangDAO.selectFestivityApplicationLangList",vo);
    }

    public void insertFestivityApplicationLang(WjFestivityApplicationLangVO vo)throws Exception{
        insert("WjFestivityApplicationLangDAO.insertFestivityApplicationLang",vo);
    }

    public void updateFestivityApplicationLang(WjFestivityApplicationLangVO vo)throws Exception{
        insert("WjFestivityApplicationLangDAO.updateFestivityApplicationLang",vo);
    }
}
