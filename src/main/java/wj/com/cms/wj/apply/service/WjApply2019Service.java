package wj.com.cms.wj.apply.service;

import wj.com.cms.wj.apply.vo.WjApply2019VO;

import java.util.List;

public interface WjApply2019Service {

    WjApply2019VO selectWjApply2019(WjApply2019VO vo) throws Exception;

    Integer selectWjApply2019TotalCount(WjApply2019VO vo) throws Exception;

    List<?> selectWjApply2019List(WjApply2019VO vo) throws Exception;

    void insertWjApply2019(WjApply2019VO vo) throws Exception;

    void updateWjApply2019(WjApply2019VO vo) throws Exception;


}
