package infomind.com.cms.sec.ram.service;

import infomind.com.cms.sec.ram.vo.InfoAuthorRoleManage;
import infomind.com.cms.sec.ram.vo.InfoAuthorRoleManageVO;

import java.util.List;

/**
 * 권한별 롤 관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */

public interface InfoAuthorRoleManageService {

	/**
	 * 권한 롤 관계정보를 조회
	 * @param infoAuthorRoleManageVO InfoAuthorRoleManageVO
	 * @exception Exception
	 */
	public InfoAuthorRoleManageVO selectInfoAuthorRole(InfoAuthorRoleManageVO infoAuthorRoleManageVO) throws Exception;

	/**
	 * 권한 롤 관계정보 목록 조회
	 * @param infoAuthorRoleManageVO InfoAuthorRoleManageVO
	 * @return List<AuthorRoleManageVO>
	 * @exception Exception
	 */
	public List<InfoAuthorRoleManageVO> selectInfoAuthorRoleList(InfoAuthorRoleManageVO infoAuthorRoleManageVO) throws Exception;
	
	/**
	 * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param infoAuthorRoleManage InfoAuthorRoleManage
	 * @exception Exception
	 */
	public void insertInfoAuthorRole(InfoAuthorRoleManage infoAuthorRoleManage) throws Exception;

	/**
	 * 수정된 권한 롤 관계정보를 데이터베이스에 반영
	 * @param infoAuthorRoleManage InfoAuthorRoleManage
	 * @exception Exception
	 */
	public void updateInfoAuthorRole(InfoAuthorRoleManage infoAuthorRoleManage) throws Exception;

	/**
	 * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param infoAuthorRoleManage InfoAuthorRoleManage
	 * @exception Exception
	 */
	public void deleteInfoAuthorRole(InfoAuthorRoleManage infoAuthorRoleManage) throws Exception;

    /**
	 * 목록조회 카운트를 반환한다
	 * @param infoAuthorRoleManageVO InfoAuthorRoleManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectInfoAuthorRoleListTotCnt(InfoAuthorRoleManageVO infoAuthorRoleManageVO) throws Exception;

}
