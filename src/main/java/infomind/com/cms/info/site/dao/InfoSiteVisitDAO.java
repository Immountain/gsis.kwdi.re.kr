package infomind.com.cms.info.site.dao;


import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoSiteVisitDAO")
public class InfoSiteVisitDAO extends EgovComAbstractDAO {


    public InfoSiteVisitVO selectSiteVisit(InfoSiteVisitVO vo) throws Exception {
        return selectOne("InfoSiteVisitDAO.selectSiteVisit", vo);
    }

    public Integer selectSiteVisitTotalCount(InfoSiteVisitVO vo) throws Exception {
        return selectOne("InfoSiteVisitDAO.selectSiteVisitTotalCount", vo);
    }

    public List<?> selectSiteVisitList(InfoSiteVisitVO vo) throws Exception {
        return selectList("InfoSiteVisitDAO.selectSiteVisitList", vo);
    }

    public void insertSiteVisit(InfoSiteVisitVO vo) throws Exception {
        insert("InfoSiteVisitDAO.insertSiteVisit", vo);
    }

}
