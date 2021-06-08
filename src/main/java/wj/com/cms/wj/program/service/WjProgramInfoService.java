package wj.com.cms.wj.program.service;

import wj.com.cms.wj.program.vo.WjProgramInfoVO;

import java.util.List;

public interface WjProgramInfoService {
     WjProgramInfoVO selectProgramInfo(WjProgramInfoVO vo) throws Exception;


     Integer selectProgramInfoTotalCount(WjProgramInfoVO vo) throws Exception;

     List<?> selectProgramInfoList(WjProgramInfoVO vo) throws Exception;

     void insertProgramInfo(WjProgramInfoVO vo) throws Exception;

     void updateProgramInfo(WjProgramInfoVO vo) throws Exception;

     void deleteProgramInfo(WjProgramInfoVO vo) throws Exception;

    }
