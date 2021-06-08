package infomind.com.ext.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

import infomind.com.ext.vo.CodeSearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CodeSearchDAO")
public class CodeSearchDAO extends EgovComAbstractDAO {

    /**
     * 공통분류코드
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List<CodeSearchVO> selectComtccmmnclcodeList(CodeSearchVO searchVO) throws Exception {
        return (List<CodeSearchVO>) list("CodeSearchDAO.selectComtccmmnclcodeList", searchVO);
    }

    /**
     * 공통코드
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List<CodeSearchVO> selectComtccmmncodeList(CodeSearchVO searchVO) throws Exception {
        return (List<CodeSearchVO>) list("CodeSearchDAO.selectComtccmmncodeList", searchVO);
    }

    /**
     * 공통상세 코드
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List<CodeSearchVO> selectComtccmmndetailcodeList(CodeSearchVO searchVO) throws Exception {
        return (List<CodeSearchVO>) list("CodeSearchDAO.selectComtccmmndetailcodeList", searchVO);
    }


    /**
     * 공통상세 코드 진행상태 관련
     * @param searchVO
     * @return
     * @throws Exception
     */
    public List<CodeSearchVO> getComtccmmndetailcodePidList(CodeSearchVO searchVO) throws Exception {
        return (List<CodeSearchVO>) list("CodeSearchDAO.getComtccmmndetailcodePidList", searchVO);
    }
}
