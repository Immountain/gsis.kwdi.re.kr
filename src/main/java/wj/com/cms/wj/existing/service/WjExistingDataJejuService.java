package wj.com.cms.wj.existing.service;

import wj.com.cms.wj.existing.vo.WjExistingDataJejuVO;

import java.util.List;

public interface WjExistingDataJejuService {

    WjExistingDataJejuVO selectWjExistingDataJeju(WjExistingDataJejuVO vo) throws Exception;

    Integer selectWjExistingDataJejuTotalCount(WjExistingDataJejuVO vo) throws Exception;

    List<?> selectWjExistingDataJejuList(WjExistingDataJejuVO vo) throws Exception;

    void insertWjExistingDataJeju(WjExistingDataJejuVO vo) throws Exception;

    void updateWjExistingDataJeju(WjExistingDataJejuVO vo) throws Exception;


}
