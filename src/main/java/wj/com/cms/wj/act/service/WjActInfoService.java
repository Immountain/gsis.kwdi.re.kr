package wj.com.cms.wj.act.service;

import wj.com.cms.wj.act.vo.WjActInfoVO;

import java.util.List;

public interface WjActInfoService {

     WjActInfoVO selectActInfo(WjActInfoVO vo) throws Exception;

     Integer selectActInfoTotalCount(WjActInfoVO vo) throws Exception;

     List<?> selectActInfoList(WjActInfoVO vo) throws Exception;

     void insertActInfo(WjActInfoVO vo) throws Exception;

     void updateActInfo(WjActInfoVO vo) throws Exception;

}
