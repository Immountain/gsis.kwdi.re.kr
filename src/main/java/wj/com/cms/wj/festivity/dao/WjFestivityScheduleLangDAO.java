package wj.com.cms.wj.festivity.dao;


import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.festivity.vo.WjFestivityScheduleLangVO;

import java.util.List;

@Repository("WjFestivityScheduleLangDAO")
public class WjFestivityScheduleLangDAO extends EgovComAbstractDAO {
    
    public WjFestivityScheduleLangVO selectFestivityScheduleLang(WjFestivityScheduleLangVO vo) throws Exception {
        return selectOne("WjFestivityScheduleLangDAO.selectFestivityScheduleLang", vo);
    }

    public Integer selectFestivityScheduleLangTotalCount(WjFestivityScheduleLangVO vo) throws Exception {
        return selectOne("WjFestivityScheduleLangDAO.selectFestivityScheduleLangTotalCount", vo);
    }

    public List<?> selectFestivityScheduleLangList(WjFestivityScheduleLangVO vo) throws Exception {
        return selectList("WjFestivityScheduleLangDAO.selectFestivityScheduleLangList", vo);
    }

    public void insertFestivityScheduleLang(WjFestivityScheduleLangVO vo) throws Exception {
        insert("WjFestivityScheduleLangDAO.insertFestivityScheduleLang", vo);
    }

    public void updateFestivityScheduleLang(WjFestivityScheduleLangVO vo) throws Exception {
        insert("WjFestivityScheduleLangDAO.updateFestivityScheduleLang", vo);
    }
}
