package infomind.com.cms.info.site.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cmm.bean.InfoSiteCacheManager;
import infomind.com.cms.info.site.dao.InfoSiteDAO;
import infomind.com.cms.info.site.service.InfoSiteService;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoSiteService")
public class InfoSiteServiceImpl extends EgovAbstractServiceImpl implements InfoSiteService {

    @Resource(name = "InfoSiteDAO")
    private InfoSiteDAO infoSiteDAO;

    @Override
    public InfoSiteVO selectInfoSite(InfoSiteVO vo) throws Exception {
        return infoSiteDAO.selectInfoSite(vo);
    }

    @Override
    public Integer selectInfoSiteTotalCount(InfoSiteVO vo) throws Exception {
        return infoSiteDAO.selectInfoSiteTotalCount(vo);
    }

    @Override
    public List<?> selectSiteList(InfoSiteVO vo) throws Exception {
        return infoSiteDAO.selectSiteList(vo);
    }

    @Override
    public void insertSite(InfoSiteVO vo) throws Exception {
        infoSiteDAO.insertSite(vo);
    }

    @Override
    public void updateSite(InfoSiteVO vo) throws Exception {
        infoSiteDAO.updateSite(vo);
    }
}
