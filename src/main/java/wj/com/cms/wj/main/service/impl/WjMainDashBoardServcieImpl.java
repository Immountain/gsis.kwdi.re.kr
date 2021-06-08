package wj.com.cms.wj.main.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.main.dao.WjMainDashBoardDAO;
import wj.com.cms.wj.main.service.WjMainDashBoardServcie;
import wj.com.cms.wj.main.vo.WjMainDashBoardVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjMainDashBoardServcie")
public class WjMainDashBoardServcieImpl extends EgovAbstractServiceImpl implements WjMainDashBoardServcie {

    @Resource(name="WjMainDashBoardDAO")
    private WjMainDashBoardDAO wjMainDashBoardDAO;


    @Override
    public List<WjMainDashBoardVO> getWeekDaySiteMain(WjMainDashBoardVO vo) throws Exception {
        return wjMainDashBoardDAO.getWeekDaySiteMain(vo);
    }

    @Override
    public List<WjMainDashBoardVO> getPeopleCountSiteMain(WjMainDashBoardVO vo) throws Exception {
        return wjMainDashBoardDAO.getPeopleCountSiteMain(vo);
    }
}
