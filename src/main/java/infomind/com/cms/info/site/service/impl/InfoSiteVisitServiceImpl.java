package infomind.com.cms.info.site.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.site.dao.InfoSiteVisitDAO;
import infomind.com.cms.info.site.service.InfoSiteVisitService;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoSiteVisitService")
public class InfoSiteVisitServiceImpl extends EgovAbstractServiceImpl implements InfoSiteVisitService {

    @Resource(name="InfoSiteVisitDAO")
    private InfoSiteVisitDAO infoSiteVisitDAO;


    @Resource(name = "InfoSiteVisitIdGnrService")
    private EgovIdGnrService idgenService;


    @Override
    public InfoSiteVisitVO selectSiteVisit(InfoSiteVisitVO vo) throws Exception {
        return infoSiteVisitDAO.selectSiteVisit(vo);
    }

    @Override
    public Integer selectSiteVisitTotalCount(InfoSiteVisitVO vo) throws Exception {
        return infoSiteVisitDAO.selectSiteVisitTotalCount(vo);
    }

    @Override
    public List<?> selectSiteVisitList(InfoSiteVisitVO vo) throws Exception {
        return infoSiteVisitDAO.selectSiteVisitList(vo);
    }

    @Override
    public void insertSiteVisit(InfoSiteVisitVO vo) throws Exception {

        vo.setId(idgenService.getNextIntegerId());
        infoSiteVisitDAO.insertSiteVisit(vo);
    }
}
