package infomind.com.cms.sec.gmt.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.sec.gmt.vo.InfoGroupManage;
import infomind.com.cms.sec.gmt.vo.InfoGroupManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 그룹관리에 대한 DAO 클래스를 정의한다.
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

@Repository("InfoGroupManageDAO")
public class InfoGroupManageDAO extends EgovComAbstractDAO {

	/**
	 * 검색조건에 따른 그룹정보를 조회
	 * @param infoGroupManageVO InfoGroupManageVO
	 * @return GroupManageVO
	 * @exception Exception
	 */
	public InfoGroupManageVO selectInfoGroup(InfoGroupManageVO infoGroupManageVO) throws Exception {
		return (InfoGroupManageVO) selectOne("InfoGroupManageDAO.selectInfoGroup", infoGroupManageVO);
	}

	/**
	 * 시스템사용 목적별 그룹 목록 조회
	 * @param infoGroupManageVO InfoGroupManageVO
	 * @return InfoGroupManageVO
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<InfoGroupManageVO> selectInfoGroupList(InfoGroupManageVO infoGroupManageVO) throws Exception {
		return (List<InfoGroupManageVO>) list("InfoGroupManageDAO.selectInfoGroupList", infoGroupManageVO);
	}
	public List<InfoGroupManageVO> getSelectInfoGroupList(InfoGroupManageVO infoGroupManageVO) throws Exception {
		return (List<InfoGroupManageVO>) list("InfoGroupManageDAO.selectCheckInfoGroupList", infoGroupManageVO);
	}




	/**
	 * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param infoGroupManage InfoGroupManage
	 * @exception Exception
	 */
	public void insertInfoGroup(InfoGroupManage infoGroupManage) throws Exception {
		insert("InfoGroupManageDAO.insertInfoGroup", infoGroupManage);
	}

	/**
	 * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
	 * @param infoGroupManage InfoGroupManage
	 * @exception Exception
	 */
	public void updateInfoGroup(InfoGroupManage infoGroupManage) throws Exception {
		update("InfoGroupManageDAO.updateInfoGroup", infoGroupManage);
	}
	
	/**
	 * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param infoGroupManage InfoGroupManage
	 * @exception Exception
	 */
	public void deleteInfoGroup(InfoGroupManage infoGroupManage) throws Exception {
		delete("InfoGroupManageDAO.deleteInfoGroup", infoGroupManage);
	}

    /**
	 * 롤목록 총 갯수를 조회한다.
	 * @param infoGroupManageVO InfoGroupManageVO
	 * @return int
	 * @exception Exception
	 */
    public int selectInfoGroupListTotCnt(InfoGroupManageVO infoGroupManageVO) throws Exception {
        return (Integer)selectOne("InfoGroupManageDAO.selectInfoGroupListTotCnt", infoGroupManageVO);
    }
}