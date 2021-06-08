package wj.com.cms.wj.main.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.main.vo.WjMainDashBoardVO;

import java.util.List;

@Repository("WjMainDashBoardDAO")
public class WjMainDashBoardDAO extends EgovComAbstractDAO {



    public List<WjMainDashBoardVO> getWeekDaySiteMain(WjMainDashBoardVO vo) throws Exception{
        return selectList("WjMainDashBoardDAO.getWeekDaySiteMain",vo);
    }

    public List<WjMainDashBoardVO> getPeopleCountSiteMain(WjMainDashBoardVO vo) throws Exception{
        return selectList("WjMainDashBoardDAO.getPeopleCountSiteMain",vo);
    }



}
