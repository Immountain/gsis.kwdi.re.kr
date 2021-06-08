package infomind.com.cms.mnu.mcm.service;

import infomind.com.cms.mnu.mcm.vo.InfoMenuCreatVO;
import infomind.com.cms.mnu.mcm.vo.InfoMenuSiteMapVO;
import infomind.com.ext.vo.CmsSearchVO;

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
 *
 * </pre>
 */
public interface InfoMenuCreateManageService {

	/**
	 * ID 존재여부를 조회
	 * @param vo CmsSearchVO
	 * @return int
	 * @exception Exception
	 */
	int selectUsrByPk(CmsSearchVO vo) throws Exception;

	/**
	 * ID에 대한 권한코드를 조회
	 * @param vo CmsSearchVO
	 * @return List
	 * @exception Exception
	 */
	InfoMenuCreatVO selectAuthorByUsr(CmsSearchVO vo) throws Exception;


	/**
	 * 메뉴생성관리 목록을 조회
	 * @param vo CmsSearchVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectMenuCreatManagList(CmsSearchVO vo) throws Exception;

	/**
	 * 메뉴생성관리 총건수를 조회한다.
	 * @param vo CmsSearchVO
	 * @return int
	 * @exception Exception
	 */
	int selectMenuCreatManagTotCnt(CmsSearchVO vo) throws Exception;

	/**
	 * 메뉴생성 내역을 조회
	 * @param  vo InfoMenuCreatVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectMenuCreatList(InfoMenuCreatVO vo) throws Exception;


	/**
	 * 화면에 조회된 메뉴정보로 메뉴생성내역 데이터베이스에서 입력
	 * @param checkedScrtyForInsert String
	 * @param checkedMenuNoForInsert String
	 * @exception Exception
	 */
	void insertMenuCreatList(String checkedScrtyForInsert, String checkedMenuNoForInsert) throws Exception;

	/**
	 * 메뉴생성 사이트맵 내용 조회
	 * @param vo InfoMenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	List<?> selectMenuCreatSiteMapList(InfoMenuSiteMapVO vo) throws Exception;

	/**
	 * 사용자 권한별 사이트맵 내용 조회
	 * @param vo InfoMenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	 List<?> selectSiteMapByUser(InfoMenuSiteMapVO vo) throws Exception;

	 /**
	 * 사이트맵 등록
	 * @param vo InfoMenuSiteMapVO
	 * @param vHtmlValue String
	 * @return boolean
	 * @exception Exception
	 */
	 boolean creatSiteMap(InfoMenuSiteMapVO vo, String vHtmlValue) throws Exception;
}
