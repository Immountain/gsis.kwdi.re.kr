package infomind.com.cms.ccm.cca.service.impl;

import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.com.sym.ccm.cca.service.impl.CmmnCodeManageDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.ccm.cca.dao.InfoCmmnCodeManageDAO;
import infomind.com.cms.ccm.cca.service.InfoCcmCmmnCodeManageService;
import infomind.com.cms.ccm.cca.vo.InfoCmmnCodeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
*
* 공통코드에 대한 서비스 구현클래스를 정의한다
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

@Service("InfoCcmCmmnCodeManageService")
public class InfoCcmCmmnCodeManageServiceImpl extends EgovAbstractServiceImpl implements InfoCcmCmmnCodeManageService {

    @Resource(name="InfoCmmnCodeManageDAO")
    private InfoCmmnCodeManageDAO cmmnCodeManageDAO;
    
	/**
	 * 공통코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectCmmnCodeListTotCnt(InfoCmmnCodeVO searchVO) throws Exception {
        return cmmnCodeManageDAO.selectCmmnCodeListTotCnt(searchVO);
	}

	/**
	 * 공통코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectCmmnCodeList(InfoCmmnCodeVO searchVO) throws Exception {
		return cmmnCodeManageDAO.selectCmmnCodeList(searchVO);
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 */
	@Override
	public InfoCmmnCodeVO selectCmmnCodeDetail(InfoCmmnCodeVO cmmnCodeVO) throws Exception{
		InfoCmmnCodeVO ret = cmmnCodeManageDAO.selectCmmnCodeDetail(cmmnCodeVO);
    	return ret;
	}

	/**
	 * 공통코드를 수정한다.
	 */
	@Override
	public void updateCmmnCode(InfoCmmnCodeVO cmmnCodeVO) throws Exception {
		cmmnCodeManageDAO.updateCmmnCode(cmmnCodeVO);
	}

	/**
	 * 공통코드를 등록한다.
	 */
	@Override
	public void insertCmmnCode(InfoCmmnCodeVO cmmnCode) throws Exception {
		cmmnCodeManageDAO.insertCmmnCode(cmmnCode);
		
	}

	/**
	 * 공통코드를 삭제한다.
	 */
	@Override
	public void deleteCmmnCode(InfoCmmnCodeVO cmmnCode) throws Exception {
		cmmnCodeManageDAO.deleteCmmnCode(cmmnCode);
	}

}
