package wj.com.site.festivity.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.site.festivity.dao.WjSiteSubProgramDAO;
import wj.com.site.festivity.service.WjSiteSubProgramService;
import wj.com.site.festivity.vo.WjSiteSubProgramVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjSiteSubProgramService")
public class WjSiteSubProgramServiceImpl extends EgovAbstractServiceImpl implements WjSiteSubProgramService {


    @Resource(name="WjSiteSubProgramDAO")
    private WjSiteSubProgramDAO wjSiteSubProgramDAO;


    @Override
    public WjSiteSubProgramVO getSelectSiteWjSubProgramInfo(WjSiteSubProgramVO vo) throws Exception {
        return wjSiteSubProgramDAO.getSelectSiteWjSubProgramInfo(vo);
    }

    @Override
    public List<WjSiteSubProgramVO> getSelectSiteWjSubProgramList(WjSiteSubProgramVO vo) throws Exception {
        return wjSiteSubProgramDAO.getSelectSiteWjSubProgramList(vo);
    }
}
