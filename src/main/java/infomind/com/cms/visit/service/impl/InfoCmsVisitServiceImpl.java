package infomind.com.cms.visit.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.visit.dao.InfoCmsVisitDAO;
import infomind.com.cms.visit.service.InfoCmsVisitService;
import infomind.com.cms.visit.vo.InfoCmsVisitVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("InfoCmsVisitService")
public class InfoCmsVisitServiceImpl extends EgovAbstractServiceImpl implements InfoCmsVisitService {

    @Resource(name = "InfoCmsVisitSeqService")
    private EgovIdGnrService idgenService;

    @Resource(name = "InfoCmsVisitDAO")
    private InfoCmsVisitDAO infoCmsVisitDAO;

    @Override
    public void getLogInfoCmsVisit(InfoCmsVisitVO vo) throws Exception {
        String key =idgenService.getNextStringId();
        vo.setId(key);
        infoCmsVisitDAO.getLogInfoCmsVisit(vo);
    }
}
