package gsis.com.site.jewmdis.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.site.jewmdis.dao.SiteJewMdisDAO;
import gsis.com.site.jewmdis.service.SiteJewMdisService;
import gsis.com.site.jewmdis.vo.SiteJewMdisVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("SiteJewMdisService")
public class SiteJewMdisServiceImpl extends EgovAbstractServiceImpl implements SiteJewMdisService {


    @Resource(name="SiteJewMdisDAO")
    private SiteJewMdisDAO siteJewMdisDAO;


    @Override
    public Integer getSelectJewMdisTotCnt(SiteJewMdisVO vo) throws Exception {
        return siteJewMdisDAO.getSelectJewMdisTotCnt(vo);
    }

    @Override
    public List<?> getSelectJewMdisList(SiteJewMdisVO vo) throws Exception {
        return siteJewMdisDAO.getSelectJewMdisList(vo);
    }

    @Override
    public SiteJewMdisVO getSelectJewMdisView(SiteJewMdisVO vo) throws Exception {
        return siteJewMdisDAO.getSelectJewMdisView(vo);
    }
}
