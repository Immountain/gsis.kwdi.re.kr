package infomind.com.cms.sec.ram.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.sec.ram.dao.InfoAuthorRoleManageDAO;
import infomind.com.cms.sec.ram.service.InfoAuthorRoleManageService;
import infomind.com.cms.sec.ram.vo.InfoAuthorRoleManage;
import infomind.com.cms.sec.ram.vo.InfoAuthorRoleManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 권한별 롤관리에 대한 DAO 클래스를 정의한다.
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

@Service("InfoAuthorRoleManageService")
public class InfoAuthorRoleManageServiceImpl extends EgovAbstractServiceImpl implements InfoAuthorRoleManageService {
	
	@Resource(name="InfoAuthorRoleManageDAO")
    private InfoAuthorRoleManageDAO infoAuthorRoleManageDAO;
	
	/**
	 * 권한 롤 관계정보를 조회
	 * @param infoAuthorRoleManageVO InfoAuthorRoleManageVO
	 * @return InfoAuthorRoleManageVO
	 * @exception Exception
	 */
	public InfoAuthorRoleManageVO selectInfoAuthorRole(InfoAuthorRoleManageVO infoAuthorRoleManageVO) throws Exception {
		return infoAuthorRoleManageDAO.selectInfoAuthorRole(infoAuthorRoleManageVO);
	}

	/**
	 * 권한 롤 관계정보 목록 조회
	 * @param infoAuthorRoleManageVO InfoAuthorRoleManageVO
	 * @return List<AuthorRoleManageVO>
	 * @exception Exception
	 */
	public List<InfoAuthorRoleManageVO> selectInfoAuthorRoleList(InfoAuthorRoleManageVO infoAuthorRoleManageVO) throws Exception {
		return infoAuthorRoleManageDAO.selectInfoAuthorRoleList(infoAuthorRoleManageVO);
	}
	
	/**
	 * 권한 롤 관계정보를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param infoAuthorRoleManage InfoAuthorRoleManage
	 * @exception Exception
	 */
	public void insertInfoAuthorRole(InfoAuthorRoleManage infoAuthorRoleManage) throws Exception {
		infoAuthorRoleManageDAO.insertInfoAuthorRole(infoAuthorRoleManage);
	}

	/**
	 * 수정된 권한 롤 관계정보를 데이터베이스에 반영
	 * @param infoAuthorRoleManage InfoAuthorRoleManage
	 * @exception Exception
	 */
	public void updateInfoAuthorRole(InfoAuthorRoleManage infoAuthorRoleManage) throws Exception {
		infoAuthorRoleManageDAO.updateInfoAuthorRole(infoAuthorRoleManage);
	}


	/**
	 * 권한 롤 관계정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param infoAuthorRoleManage InfoAuthorRoleManage
	 * @exception Exception
	 */
	public void deleteInfoAuthorRole(InfoAuthorRoleManage infoAuthorRoleManage) throws Exception {
		infoAuthorRoleManageDAO.deleteInfoAuthorRole(infoAuthorRoleManage);
	}

    /**
	 * 목록조회 카운트를 반환한다
	 * @param infoAuthorRoleManageVO InfoAuthorRoleManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectInfoAuthorRoleListTotCnt(InfoAuthorRoleManageVO infoAuthorRoleManageVO) throws Exception {
		return infoAuthorRoleManageDAO.selectInfoAuthorRoleListTotCnt(infoAuthorRoleManageVO);
	}
}