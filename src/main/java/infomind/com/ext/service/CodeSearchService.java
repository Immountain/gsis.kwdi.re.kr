package infomind.com.ext.service;

import infomind.com.ext.vo.CodeSearchVO;

import java.util.List;

public interface CodeSearchService {


    /**
     * 공통분류코드
     * @param searchVO
     * @return
     * @throws Exception
     */
     List<CodeSearchVO> selectComtccmmnclcodeList(CodeSearchVO searchVO) throws Exception;

    /**
     * 공통코드
     * @param searchVO
     * @return
     * @throws Exception
     */
     List<CodeSearchVO> selectComtccmmncodeList(CodeSearchVO searchVO) throws Exception ;
    /**
     * 공통상세 코드
     * @param searchVO
     * @return
     * @throws Exception
     */
     List<CodeSearchVO> selectComtccmmndetailcodeList(CodeSearchVO searchVO) throws Exception ;

    /**
     * 공통상세 코드 진행상태 관련
     * @param searchVO
     * @return
     * @throws Exception
     */
     List<CodeSearchVO> getComtccmmndetailcodePidList(CodeSearchVO searchVO) throws Exception ;

}
