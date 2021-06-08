package wj.com.cms.wj.event.service;

import wj.com.cms.wj.event.vo.WjEventInfoVO;

import java.util.List;

public interface WjEventInfoService {

    WjEventInfoVO selectEventInfo(WjEventInfoVO vo) throws Exception;

    Integer selectEventInfoTotalCount(WjEventInfoVO vo) throws Exception;

    List<?> selectEventInfoList(WjEventInfoVO vo) throws Exception;

    void insertEventInfo(WjEventInfoVO vo) throws Exception;

    void updateEventInfo(WjEventInfoVO vo) throws Exception;

    void deleteEventInfo(WjEventInfoVO vo) throws Exception;
}
