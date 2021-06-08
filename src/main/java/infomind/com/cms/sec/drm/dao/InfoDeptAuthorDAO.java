package infomind.com.cms.sec.drm.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.sec.drm.vo.InfoDeptAuthor;
import infomind.com.cms.sec.drm.vo.InfoDeptAuthorVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 부서권한에 대한 DAO 클래스를 정의한다.
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

@Repository("InfoDeptAuthorDAO")
public class InfoDeptAuthorDAO extends EgovComAbstractDAO {

	/**
	 * 부서별 할당된 권한목록 조회
	 * @param infoDeptAuthorVO InfoDeptAuthorVO
	 * @return List<InfoDeptAuthorVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<InfoDeptAuthorVO> selectInfoDeptAuthorList(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception {
		return (List<InfoDeptAuthorVO>) list("InfoDeptAuthorDAO.selectInfoDeptAuthorList", infoDeptAuthorVO);
	}

	/**
	 * 부서에 해당하는 사용자에게 시스템 메뉴/접근권한을 일괄 할당
	 * @param infoDeptAuthor InfoDeptAuthor
	 * @exception Exception
	 */
	public void insertInfoDeptAuthor(InfoDeptAuthor infoDeptAuthor) throws Exception {
		insert("InfoDeptAuthorDAO.insertInfoDeptAuthor", infoDeptAuthor);
	}

	/**
	 * 부서별 시스템 메뉴 접근권한을 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param infoDeptAuthor InfoDeptAuthor
	 * @exception Exception
	 */
	public void updateInfoDeptAuthor(InfoDeptAuthor infoDeptAuthor) throws Exception {
		update("InfoDeptAuthorDAO.updateInfoDeptAuthor", infoDeptAuthor);
	}

	/**
	 * 불필요한 부서권한를 조회하여 데이터베이스에서 삭제
	 * @param infoDeptAuthor InfoDeptAuthor
	 * @exception Exception
	 */
	public void deleteInfoDeptAuthor(InfoDeptAuthor infoDeptAuthor) throws Exception {
		delete("InfoDeptAuthorDAO.deleteInfoDeptAuthor", infoDeptAuthor);
	}

    /**
	 * 부서권한 목록조회 카운트를 반환한다
	 * @param infoDeptAuthorVO InfoDeptAuthorVO
	 * @return int
	 * @exception Exception
	 */
	public int selectInfoDeptAuthorListTotCnt(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception {
		return (Integer)selectOne("InfoDeptAuthorDAO.selectInfoDeptAuthorListTotCnt", infoDeptAuthorVO);
	}

	/**
	 * 부서목록 조회
	 * @param infoDeptAuthorVO infoDeptAuthorVOinfoDeptAuthorVO
	 * @return List<DeptAuthorVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<InfoDeptAuthorVO> selectInfoDeptList(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception {
		return (List<InfoDeptAuthorVO>) list("InfoDeptAuthorDAO.selectInfoDeptList", infoDeptAuthorVO);
	}
	
    /**
	 * 부서목록 조회 카운트를 반환한다
	 * @param infoDeptAuthorVO nfoDeptAuthorVO
	 * @return int
	 * @exception Exception
	 */
	public int selectInfoDeptListTotCnt(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception {
		return (Integer)selectOne("InfoDeptAuthorDAO.selectInfoDeptListTotCnt", infoDeptAuthorVO);
	}	
}