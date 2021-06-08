package infomind.com.cms.sec.rmt.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.sec.rmt.vo.InfoRoleManage;
import infomind.com.cms.sec.rmt.vo.InfoRoleManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 롤관리에 대한 DAO 클래스를 정의한다.
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
 *   2009.03.11  이문준          최초 생성
 *
 * </pre>
 */

@Repository("InfoRoleManageDAO")
public class InfoRoleManageDAO extends EgovComAbstractDAO {

	/**
	 * 등록된 롤 정보 조회
	 * @param infoRoleManageVO InfoRoleManageVO
	 * @return RoleManageVO
	 * @exception Exception
	 */
	public InfoRoleManageVO selectInfoRole(InfoRoleManageVO infoRoleManageVO) throws Exception {
		return (InfoRoleManageVO) selectOne("InfoRoleManageDAO.selectInfoRole", infoRoleManageVO);
	}

	/**
	 * 등록된 롤 정보 목록 조회
	 * @param infoRoleManageVO InfoRoleManageVO
	 * @return List<RoleManageVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<InfoRoleManageVO> selectInfoRoleList(InfoRoleManageVO infoRoleManageVO) throws Exception {
		return (List<InfoRoleManageVO>) list("InfoRoleManageDAO.selectInfoRoleList", infoRoleManageVO);
	}

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
	 * @param infoRoleManage InfoRoleManage
	 * @exception Exception
	 */
		public void insertInfoRole(InfoRoleManage infoRoleManage) throws Exception {
		update("InfoRoleManageDAO.insertInfoRole", infoRoleManage);
	}
	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
	 * @param infoRoleManage InfoRoleManage
	 * @exception Exception
	 */
	public void updateInfoRole(InfoRoleManage infoRoleManage) throws Exception {
		update("InfoRoleManageDAO.updateInfoRole", infoRoleManage);
	}
	/**
	 * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param infoRoleManage InfoRoleManage
	 * @exception Exception
	 */
	public void deleteInfoRole(InfoRoleManage infoRoleManage) throws Exception {
		delete("InfoRoleManageDAO.deleteInfoRole", infoRoleManage);
	}
	
    /**
	 * 롤목록 총 갯수를 조회한다.
	 * @param infoRoleManageVO InfoRoleManageVO
	 * @return int
	 * @exception Exception
	 */
    public int selectInfoRoleListTotCnt(InfoRoleManageVO infoRoleManageVO) throws Exception {
        return (Integer)selectOne("InfoRoleManageDAO.selectInfoAuthorListTotCnt", infoRoleManageVO);
    }

	/**
	 * 등록된 모든 롤 정보 목록 조회
	 * @param infoRoleManageVO InfoRoleManageVO
	 * @return List<InfoRoleManageVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<InfoRoleManageVO> selectInfoRoleAllList(InfoRoleManageVO infoRoleManageVO) throws Exception {
		return (List<InfoRoleManageVO>) list("InfoRoleManageDAO.selectInfoRoleAllList", infoRoleManageVO);
	}    

}