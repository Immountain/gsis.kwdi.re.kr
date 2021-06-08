package infomind.com.cms.ccm.ccc.service;


import infomind.com.cms.ccm.ccc.vo.InfoCmmnClCode;


import java.util.List;

/**
 *
 * 공통분류코드에 관한 서비스 인터페이스 클래스를 정의한다
 * @author 인포마인드 개발팀 양진혁
 * @since 20020.10.07
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.10.07  양진혁          최초 생성
 *
 * </pre>
 */


public interface InfoCcmCmmnClCodeManageService {

	/**
	 * 공통분류코드 총 갯수를 조회한다.
	 * 
	 * @param searchVO
	 * @return int(공통분류코드 총 갯수)
	 */
	int selectCmmnClCodeListTotCnt(InfoCmmnClCode searchVO) throws Exception;

	/**
	 * 공통분류코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(공통분류코드 목록)
	 * @throws Exception
	 */
	List<?> selectCmmnClCodeList(InfoCmmnClCode searchVO) throws Exception;

	 /**
	  *  공통분류코드 상세항목을 조회한다.
	  * @param
	  * @return CmmnClCode(공통분류코드)
	  *  @throws Exception
	  */
	 InfoCmmnClCode selectCmmnClCodeDetail(InfoCmmnClCode cmmnClCodeVO) throws Exception;

	/**
	 * 공통분류코드를 등록한다.
	 * @param cmmnClCodeVO
	 * @throws Exception
	 */
	void insertCmmnClCode(InfoCmmnClCode cmmnClCodeVO) throws Exception;
	
	/**
	 * 공통분류코드를 삭제한다.
	 * @param cmmnClCodeVO
	 * @throws Exception
	 */
	void deleteCmmnClCode(InfoCmmnClCode cmmnClCodeVO) throws Exception;
	
	/**
	 * 공통분류코드를 수정한다.
	 * @param cmmnClCodeVO
	 * @throws Exception
	 */
	void updateCmmnClCode(InfoCmmnClCode cmmnClCodeVO) throws Exception;


	List<InfoCmmnClCode> selectCmmnClCodeAllList(InfoCmmnClCode searchVO) throws Exception ;

}
