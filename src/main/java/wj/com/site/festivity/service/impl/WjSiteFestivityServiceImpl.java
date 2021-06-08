package wj.com.site.festivity.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.site.festivity.dao.WjSiteFestivityDAO;
import wj.com.site.festivity.service.WjSiteFestivityService;
import wj.com.site.festivity.vo.WjSiteFestivityApplicationVO;
import wj.com.site.festivity.vo.WjSiteFestivityInfoVO;
import wj.com.site.festivity.vo.WjSiteFestivityScheduleVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjSiteFestivityService")
public class WjSiteFestivityServiceImpl extends EgovAbstractServiceImpl implements WjSiteFestivityService {

    @Resource(name="WjSiteFestivityDAO")
    private WjSiteFestivityDAO wjSiteFestivityDAO;


    @Override
    public WjSiteFestivityInfoVO getSelectSiteFestivityInfo(WjSiteFestivityInfoVO vo) throws Exception {
        return wjSiteFestivityDAO.getSelectSiteFestivityInfo(vo);
    }

    @Override
    public List<WjSiteFestivityScheduleVO> getSelectSiteWjFestivitySchedule(WjSiteFestivityInfoVO vo) throws Exception {
        return wjSiteFestivityDAO.getSelectSiteWjFestivitySchedule(vo);
    }

    @Override
    public String getLastFesitivityId() throws Exception {
        return wjSiteFestivityDAO.getLastFesitivityId();
    }

    @Override
    public String getLastFesitivityYear() throws Exception {
        return wjSiteFestivityDAO.getLastFesitivityYear();
    }

    @Override
    public List<WjSiteFestivityInfoVO> getSelectSiteFestivityYearList(WjSiteFestivityInfoVO vo) throws Exception {
        return wjSiteFestivityDAO.getSelectSiteFestivityYearList(vo);
    }

    @Override
    public List<WjSiteFestivityInfoVO> getSelectSiteFestivityInfoList(WjSiteFestivityInfoVO vo) throws Exception {
        return wjSiteFestivityDAO.getSelectSiteFestivityInfoList(vo);
    }


    @Override
    public List<WjSiteFestivityApplicationVO> getSelectSiteWjSiteFestivityApplicationList(WjSiteFestivityInfoVO vo) throws Exception {
        return wjSiteFestivityDAO.getSelectSiteWjSiteFestivityApplicationList(vo);
    }

    @Override
    public List<WjSiteFestivityInfoVO> getMainFesitivityList(WjSiteFestivityInfoVO vo) throws Exception {
        return wjSiteFestivityDAO.getMainFesitivityList(vo);
    }


}
