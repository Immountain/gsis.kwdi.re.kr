package wj.com.cms.wj.event.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.event.vo.WjEventInfoLangVO;

import java.util.List;

@Repository("WjEventInfoLangDAO")
public class WjEventInfoLangDAO extends EgovComAbstractDAO {

    public WjEventInfoLangVO selectEventInfoLang(WjEventInfoLangVO vo)throws Exception{
        return selectOne("WjEventInfoLangDAO.selectEventInfoLang", vo);
    }

    public Integer selectEventInfoLangTotalCount(WjEventInfoLangVO vo)throws Exception{
        return selectOne("WjEventInfoLangDAO.selectEventInfoLangTotalCount",vo);
    }

    public List<?> selectEventInfoLangList(WjEventInfoLangVO vo)throws Exception{
        return selectList("WjEventInfoLangDAO.selectEventInfoLangList",vo);
    }

    public void insertEventInfoLang(WjEventInfoLangVO vo) throws Exception{
        insert("WjEventInfoLangDAO.insertEventInfoLang", vo);
    }

    public void updateEventInfoLang(WjEventInfoLangVO vo) throws Exception{
        insert("WjEventInfoLangDAO.updateEventInfoLang",vo);
    }

}
