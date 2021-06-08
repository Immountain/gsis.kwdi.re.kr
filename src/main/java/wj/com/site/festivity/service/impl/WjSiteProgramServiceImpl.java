package wj.com.site.festivity.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.site.festivity.dao.WjSiteProgramDAO;
import wj.com.site.festivity.service.WjSiteProgramService;
import wj.com.site.festivity.vo.WjSiteProgramInfoVO;
import wj.com.site.festivity.vo.WjSiteProgramSubInfoVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjSiteProgramService")
public class WjSiteProgramServiceImpl extends EgovAbstractServiceImpl implements WjSiteProgramService {


    @Resource(name="WjSiteProgramDAO")
    private WjSiteProgramDAO wjSiteProgramDAO;

    @Override
    public WjSiteProgramInfoVO getSelectSiteWJProgramInfo(WjSiteProgramInfoVO vo) throws Exception {
        return wjSiteProgramDAO.getSelectSiteWJProgramInfo(vo);
    }

    @Override
    public List<WjSiteProgramInfoVO> getSelectSiteWJProgramList(WjSiteProgramInfoVO vo) throws Exception {
        return wjSiteProgramDAO.getSelectSiteWJProgramList(vo);
    }

    @Override
    public List<WjSiteProgramSubInfoVO> getSelectSiteWJProgramSubList(WjSiteProgramInfoVO vo) throws Exception {
        return wjSiteProgramDAO.getSelectSiteWJProgramSubList(vo);
    }
}
