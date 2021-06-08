package wj.com.cms.wj.program.service;

import wj.com.cms.wj.program.vo.WjProgramSubVO;

import java.util.List;

public interface WjProgramSubService {

    WjProgramSubVO selectProgramSub(WjProgramSubVO vo) throws Exception;

    Integer selectProgramSubTotalCount(WjProgramSubVO vo) throws Exception;

    List<?> selectProgramSubList(WjProgramSubVO vo) throws Exception;

    void insertProgramSub(WjProgramSubVO vo) throws Exception;

    void updateProgramSub(WjProgramSubVO vo) throws Exception;

     void deleteProgramSub(WjProgramSubVO vo)throws Exception;

    }
