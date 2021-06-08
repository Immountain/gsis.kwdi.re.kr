package wj.com.cms.wj.sub.service;

import wj.com.cms.wj.sub.vo.WjSubProgramVO;

import java.util.List;

public interface WjSubProgramService {
    public WjSubProgramVO selectSubProgram(WjSubProgramVO vo) throws Exception;


    public Integer selectSubProgramTotalCount(WjSubProgramVO vo) throws Exception;

    public List<?> selectSubProgramList(WjSubProgramVO vo) throws Exception;

    public void insertSubProgram(WjSubProgramVO vo) throws Exception;

    public void updateSubProgram(WjSubProgramVO vo) throws Exception;
}
