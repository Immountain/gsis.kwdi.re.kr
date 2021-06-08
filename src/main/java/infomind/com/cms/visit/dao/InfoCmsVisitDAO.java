package infomind.com.cms.visit.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.visit.vo.InfoCmsVisitVO;
import org.springframework.stereotype.Repository;

@Repository("InfoCmsVisitDAO")
public class InfoCmsVisitDAO extends EgovComAbstractDAO {

    public void getLogInfoCmsVisit(InfoCmsVisitVO vo) throws Exception {
        insert("InfoCmsVisitDAO.getLogInfoCmsVisit", vo);
    }
}
