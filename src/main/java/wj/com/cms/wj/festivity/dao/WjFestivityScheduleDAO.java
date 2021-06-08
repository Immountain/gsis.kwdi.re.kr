package wj.com.cms.wj.festivity.dao;


import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.festivity.vo.WjFestivityScheduleVO;

import java.util.List;

@Repository("WjFestivityScheduleDAO")
public class WjFestivityScheduleDAO extends EgovComAbstractDAO {
    
    public WjFestivityScheduleVO selectFestivitySchedule(WjFestivityScheduleVO vo) throws Exception {
        return selectOne("WjFestivityScheduleDAO.selectFestivitySchedule", vo);
    }

    public Integer selectFestivityScheduleTotalCount(WjFestivityScheduleVO vo) throws Exception {
        return selectOne("WjFestivityScheduleDAO.selectFestivityScheduleTotalCount", vo);
    }

    public List<?> selectFestivityScheduleList(WjFestivityScheduleVO vo) throws Exception {
        return selectList("WjFestivityScheduleDAO.selectFestivityScheduleList", vo);
    }

    public void insertFestivitySchedule(WjFestivityScheduleVO vo) throws Exception {
        insert("WjFestivityScheduleDAO.insertFestivitySchedule", vo);
    }

    public void updateFestivitySchedule(WjFestivityScheduleVO vo) throws Exception {
        insert("WjFestivityScheduleDAO.updateFestivitySchedule", vo);
    }

    public void deleteFestivitySchedule(WjFestivityScheduleVO vo)throws Exception{
        insert("WjFestivityScheduleDAO.deleteFestivitySchedule", vo);
    }
}
