package wj.com.cms.wj.event.service;

import wj.com.cms.wj.event.vo.WjEventUserVO;

import java.util.List;

public interface WjEventUserService {

     WjEventUserVO selectEventUser(WjEventUserVO vo) throws Exception;

     Integer selectEventUserTotalCount(WjEventUserVO vo) throws Exception;

     List<?> selectEventUserList(WjEventUserVO vo) throws Exception;

     void insertEventUser(WjEventUserVO vo) throws Exception;

     void updateEventUser(WjEventUserVO vo) throws Exception;

     void deleteEventUser(WjEventUserVO vo) throws Exception;

}
