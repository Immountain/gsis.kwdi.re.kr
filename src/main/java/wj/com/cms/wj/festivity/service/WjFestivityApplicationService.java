package wj.com.cms.wj.festivity.service;

import wj.com.cms.wj.festivity.vo.WjFestivityApplicationVO;

import java.util.List;

public interface WjFestivityApplicationService {

     WjFestivityApplicationVO selectFestivityApplication(WjFestivityApplicationVO vo)throws Exception;

     Integer selectFestivityApplicationTotalCount(WjFestivityApplicationVO vo)throws Exception;

     List<?> selectFestivityApplicationList(WjFestivityApplicationVO vo)throws Exception;

     void insertFestivityApplication(WjFestivityApplicationVO vo)throws Exception;

     void updateFestivityApplication(WjFestivityApplicationVO vo) throws Exception;
}
