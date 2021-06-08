package infomind.com.cms.ccm.cca.service;

import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import infomind.com.cms.ccm.cca.vo.InfoCmmnCodeVO;

import java.util.List;

/**
*
* 공통코드에 관한 서비스 인터페이스 클래스를 정의한다
* @author 인포마인드 개발팀 양진혁
* @since 2020.10.19
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2020.10.19  양진혁          최초 생성
*
* </pre>
*/

public interface InfoCcmCmmnCodeManageService {

	/**
	 * 공통분류코드 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 * @return int(공통분류코드 총 갯수)
	 * @throws Exception
	 */
	int selectCmmnCodeListTotCnt(InfoCmmnCodeVO searchVO) throws Exception;
	
	/**
	 * 공통코드 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @return List(공통분류코드 목록)
	 * @throws Exception
	 */
	List<?> selectCmmnCodeList(InfoCmmnCodeVO searchVO) throws Exception;

	/**
	 * 공통코드 상세항목을 조회한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통코드)
	 * @throws Exception
	 */
	InfoCmmnCodeVO selectCmmnCodeDetail(InfoCmmnCodeVO cmmnCodeVO) throws Exception;

	/**
	 * 공통코드를 수정한다.
	 * @param cmmnCodeVO
	 * @throws Exception
	 */
	void updateCmmnCode(InfoCmmnCodeVO cmmnCodeVO) throws Exception;

	/**
	 * 공통코드를 등록한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void insertCmmnCode(InfoCmmnCodeVO cmmnCode) throws Exception;

	/**
	 * 공통코드를 삭제한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void deleteCmmnCode(InfoCmmnCodeVO cmmnCode) throws Exception;

}
