package wj.com.cms.wj.festivity.service;


import wj.com.cms.wj.festivity.vo.WjFestivityInfoVO;

import java.util.List;

public interface WjFestivityInfoService {

     WjFestivityInfoVO selectFestivityInfo(WjFestivityInfoVO vo) throws Exception;

     Integer selectFestivityInfoTotalCount(WjFestivityInfoVO vo)throws Exception;

     List<?> selectFestivityInfoList(WjFestivityInfoVO vo) throws Exception;

     void insertFestivityInfo(WjFestivityInfoVO vo) throws Exception;

     void updateFestivityInfo(WjFestivityInfoVO vo) throws Exception;

     void deleteFestivityInfo(WjFestivityInfoVO vo) throws Exception;

     List<?> selectFestivityInfoSearchList(WjFestivityInfoVO vo)throws Exception;

     }
