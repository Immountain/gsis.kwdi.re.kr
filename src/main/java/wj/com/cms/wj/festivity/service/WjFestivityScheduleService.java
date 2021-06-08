package wj.com.cms.wj.festivity.service;

import wj.com.cms.wj.festivity.vo.WjFestivityScheduleVO;

import java.util.List;

public interface WjFestivityScheduleService {
    public WjFestivityScheduleVO selectFestivitySchedule(WjFestivityScheduleVO vo) throws Exception;


    Integer selectFestivityScheduleTotalCount(WjFestivityScheduleVO vo) throws Exception;

    List<?> selectFestivityScheduleList(WjFestivityScheduleVO vo) throws Exception;

    void insertFestivitySchedule(WjFestivityScheduleVO vo) throws Exception;

    void updateFestivitySchedule(WjFestivityScheduleVO vo) throws Exception;

    void deleteFestivitySchedule(WjFestivityScheduleVO vo) throws Exception;
}
