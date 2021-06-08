package infomind.com.cms.mnu.mpm.service;

import infomind.com.cms.mnu.mpm.vo.InfoMenuManageVO;
import infomind.com.ext.vo.CmsSearchVO;

import java.io.InputStream;
import java.util.List;

/**
 * 메뉴관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *   2011.07.01  서준식			자기 메뉴 정보를 상위메뉴 정보로 참조하는 메뉴정보가 있는지 조회하는
 *   							selectUpperMenuNoByPk() 메서드 추가
 *
 * </pre>
 */

public interface InfoMenuManageService {

	/**
	 * 메뉴 상세정보를 조회
	 * @param vo CmsSearchVO
	 * @return InfoMenuManageVO
	 * @exception Exception
	 */
	InfoMenuManageVO selectMenuManage(CmsSearchVO vo) throws Exception;

	/**
	 * 메뉴 목록을 조회
	 * @param vo CmsSearchVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectMenuManageList(CmsSearchVO vo) throws Exception;

	/**
	 * 메뉴목록 총건수를 조회한다.
	 * @param vo CmsSearchVO
	 * @return int
	 * @exception Exception
	 */
	int selectMenuManageListTotCnt(CmsSearchVO vo) throws Exception;

	/**
	 * 메뉴번호 존재 여부를 조회한다.
	 * @param vo CmsSearchVO
	 * @return int
	 * @exception Exception
	 */
	int selectMenuNoByPk(InfoMenuManageVO vo) throws Exception;

	int selectUpperMenuNoByPk(InfoMenuManageVO vo) throws Exception;

	/**
	 * 메뉴 정보를 등록
	 * @param vo InfoMenuManageVO
	 * @exception Exception
	 */
	void insertMenuManage(InfoMenuManageVO vo) throws Exception;

	/**
	 * 메뉴 정보를 수정
	 * @param vo InfoMenuManageVO
	 * @exception Exception
	 */
	void updateMenuManage(InfoMenuManageVO vo) throws Exception;

	/**
	 * 메뉴 정보를 삭제
	 * @param vo InfoMenuManageVO
	 * @exception Exception
	 */
	void deleteMenuManage(InfoMenuManageVO vo) throws Exception;

	/**
	 * 화면에 조회된 메뉴 목록 정보를 데이터베이스에서 삭제
	 * @param checkedMenuNoForDel String
	 * @exception Exception
	 */
	void deleteMenuManageList(String checkedMenuNoForDel) throws Exception;

	/*  메뉴 생성 관리  */

	/**
	 * 메뉴 목록을 조회
	 * @return List
	 * @exception Exception
	 */
	List<?> selectMenuList() throws Exception;

	/*### 메뉴관련 프로세스 ###*/
	/**
	 * MainMenu Head Menu 조회
	 * @param vo InfoMenuManageVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectMainMenuHead(InfoMenuManageVO vo) throws Exception;

	/**
	 * MainMenu Head Left 조회
	 * @param vo InfoMenuManageVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectMainMenuLeft(InfoMenuManageVO vo) throws Exception;

	/**
	 * MainMenu Head MenuURL 조회
	 * @param iMenuNo int
	 * @param sUniqId String
	 * @return String
	 * @exception Exception
	 */
	String selectLastMenuURL(int iMenuNo, String sUniqId) throws Exception;

	/* 일괄처리 프로세스   */

	/**
	 * 메뉴일괄초기화 프로세스 메뉴목록테이블, 프로그램 목록테이블 전체 삭제
	 * @return boolean
	 */
	boolean menuBndeAllDelete() throws Exception;

	/**
	 * 메뉴일괄등록 프로세스
	 * @param  vo InfoMenuManageVO
	 * @param  inputStream InputStream
	 * @exception Exception
	 */
	String menuBndeRegist(InfoMenuManageVO vo, InputStream inputStream) throws Exception;


	List<InfoMenuManageVO> selectMainMenuAll(InfoMenuManageVO vo) throws Exception;


	List<InfoMenuManageVO> binUserMenuAll(InfoMenuManageVO vo) throws Exception;

	/**
	 * 임시 추가
	 * @param menuNo
	 * @return
	 * @throws Exception
	 */
	InfoMenuManageVO getSelectMenuInfo(String menuTargetNo) throws Exception;


	public List<InfoMenuManageVO> selectCmsAllMenuList(InfoMenuManageVO vo) throws Exception;
}