package infomind.com.ext.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.ext.dao.CodeSearchDAO;
import infomind.com.ext.service.CodeSearchService;
import infomind.com.ext.vo.CodeSearchVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("CodeSearchService")
public class CodeSearchServiceImpl extends EgovAbstractServiceImpl implements CodeSearchService {


    @Resource(name="CodeSearchDAO")
    private CodeSearchDAO codeSearchDAO;

    @Override
    public List<CodeSearchVO> selectComtccmmnclcodeList(CodeSearchVO searchVO) throws Exception {
        return codeSearchDAO.selectComtccmmnclcodeList(searchVO);
    }

    @Override
    public List<CodeSearchVO> selectComtccmmncodeList(CodeSearchVO searchVO) throws Exception {
        return codeSearchDAO.selectComtccmmncodeList(searchVO);
    }

    @Override
    public List<CodeSearchVO> selectComtccmmndetailcodeList(CodeSearchVO searchVO) throws Exception {
        return codeSearchDAO.selectComtccmmndetailcodeList(searchVO);
    }

    @Override
    public List<CodeSearchVO> getComtccmmndetailcodePidList(CodeSearchVO searchVO) throws Exception {
        return codeSearchDAO.getComtccmmndetailcodePidList(searchVO);
    }
}
