package wj.com.site.festivity.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.site.festivity.vo.WjSiteFestivityApplicationVO;
import wj.com.site.festivity.vo.WjSiteFestivityInfoVO;
import wj.com.site.festivity.vo.WjSiteFestivityScheduleVO;

import java.util.List;

@Repository("WjSiteFestivityDAO")
public class WjSiteFestivityDAO extends EgovComAbstractDAO {

    public String getLastFesitivityId() throws Exception {
        return selectOne("WjSiteFestivityDAO.getLastFesitivityId");
    }
    public String getLastFesitivityYear() throws Exception {
        return selectOne("WjSiteFestivityDAO.getLastFesitivityYear");
    }

    public WjSiteFestivityInfoVO getSelectSiteFestivityInfo(WjSiteFestivityInfoVO vo) throws Exception {
        return selectOne("WjSiteFestivityDAO.getSelectSiteFestivityInfo", vo);
    }

    public List<WjSiteFestivityScheduleVO> getSelectSiteWjFestivitySchedule(WjSiteFestivityInfoVO vo) throws Exception {
        return selectList("WjSiteFestivityDAO.getSelectSiteWjFestivitySchedule", vo);
    }


    public List<WjSiteFestivityInfoVO> getSelectSiteFestivityYearList(WjSiteFestivityInfoVO vo) throws Exception {
        return selectList("WjSiteFestivityDAO.getSelectSiteFestivityYearList", vo);
    }

    public List<WjSiteFestivityInfoVO> getSelectSiteFestivityInfoList(WjSiteFestivityInfoVO vo) throws Exception {
        return selectList("WjSiteFestivityDAO.getSelectSiteFestivityInfoList", vo);
    }



    public List<WjSiteFestivityApplicationVO> getSelectSiteWjSiteFestivityApplicationList(WjSiteFestivityInfoVO vo) throws Exception {
        return selectList("WjSiteFestivityDAO.getSelectSiteWjSiteFestivityApplicationList", vo);
    }




    public List<WjSiteFestivityInfoVO> getMainFesitivityList(WjSiteFestivityInfoVO vo) throws Exception {
        return selectList("WjSiteFestivityDAO.getMainFesitivityList", vo);
    }






}
