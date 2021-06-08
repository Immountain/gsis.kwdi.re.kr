package infomind.com.cms.ccm.cde.service.impl;

import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.com.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.com.sym.ccm.cde.service.impl.CmmnDetailCodeManageDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.ccm.cde.dao.InfoCmmnDetailCodeManageDAO;
import infomind.com.cms.ccm.cde.service.InfoCcmCmmnDetailCodeManageService;
import infomind.com.cms.ccm.cde.vo.InfoCmmnDetailCodeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
*
* 공통상세코드에 대한 서비스 구현클래스를 정의한다
* @author 공통서비스 개발팀 이중호
* @since 2009.04.01
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2009.04.01  이중호          최초 생성
*
* </pre>
*/
@Service("InfoCcmCmmnDetailCodeManageService")
public class InfoCcmCmmnDetailCodeManageServiceImpl extends EgovAbstractServiceImpl implements InfoCcmCmmnDetailCodeManageService {
	
    @Resource(name="InfoCmmnDetailCodeManageDAO")
    private InfoCmmnDetailCodeManageDAO cmmnDetailCodeManageDAO;
    
	/**
	 * 공통상세코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectCmmnDetailCodeListTotCnt(InfoCmmnDetailCodeVO searchVO) throws Exception {
        return cmmnDetailCodeManageDAO.selectCmmnDetailCodeListTotCnt(searchVO);
	}
	
	/**
	 * 공통상세코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectCmmnDetailCodeList(InfoCmmnDetailCodeVO searchVO) throws Exception {
        return cmmnDetailCodeManageDAO.selectCmmnDetailCodeList(searchVO);
	}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @throws Exception 
	 */
	@Override
	public InfoCmmnDetailCodeVO selectCmmnDetailCodeDetail(InfoCmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		InfoCmmnDetailCodeVO ret = cmmnDetailCodeManageDAO.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
    	return ret;
	}

	/**
	 * 공통상세코드를 삭제한다.
	 * @throws Exception 
	 */
	@Override
	public void deleteCmmnDetailCode(InfoCmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		cmmnDetailCodeManageDAO.deleteCmmnDetailCode(cmmnDetailCodeVO);
		
	}

	/**
	 * 공통상세코드를 등록한다.
	 */
	@Override
	public void insertCmmnDetailCode(InfoCmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		cmmnDetailCodeManageDAO.insertCmmnDetailCode(cmmnDetailCodeVO);
		
	}

	/**
	 * 공통상세코드를 수정한다.
	 */
	@Override
	public void updateCmmnDetailCode(InfoCmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {
		cmmnDetailCodeManageDAO.updateCmmnDetailCode(cmmnDetailCodeVO);
		
	}
	
}
