package infomind.com.cms.sym.prm.dao;


import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.sym.prm.vo.InfoProgrmManageDtlVO;
import infomind.com.cms.sym.prm.vo.InfoProgrmManageVO;
import infomind.com.ext.vo.CmsSearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 프로그램 목록관리및 프로그램변경관리에 대한 DAO 클래스를 정의한다.
 * @author 인포마인드 개발팀 양진혁
 * @since 2020.10.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.10.20  양진혁          최초 생성
 *
 * </pre>
 */

@Repository("InfoProgrmManageDAO")
public class InfoProgrmManageDAO extends EgovComAbstractDAO {

	/**
	 * 프로그램 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectProgrmList(CmsSearchVO vo) throws Exception{
		return selectList("InfoProgrmManageDAO.selectProgrmList_D", vo);
	}

    /**
	 * 프로그램목록 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
    public int selectProgrmListTotCnt(CmsSearchVO vo) {
        return (Integer)selectOne("InfoProgrmManageDAO.selectProgrmListTotCnt_S", vo);
    }

	/**
	 * 프로그램 기본정보를 조회
	 * @param vo ComDefaultVO
	 * @return ProgrmManageVO
	 * @exception Exception
	 */
	public InfoProgrmManageVO selectProgrm(InfoProgrmManageVO vo)throws Exception{
		return (InfoProgrmManageVO)selectOne("InfoProgrmManageDAO.selectProgrm_D", vo);
	}

	/**
	 * 프로그램 기본정보 및 URL을 등록
	 * @param vo ProgrmManageVO
	 * @exception Exception
	 */
	public void insertProgrm(InfoProgrmManageVO vo){
		insert("InfoProgrmManageDAO.insertProgrm_S", vo);
	}

	/**
	 * 프로그램 기본정보 및 URL을 수정
	 * @param vo ProgrmManageVO
	 * @exception Exception
	 */
	public void updateProgrm(InfoProgrmManageVO vo){
		update("InfoProgrmManageDAO.updateProgrm_S", vo);
	}

	/**
	 * 프로그램 기본정보 및 URL을 삭제
	 * @param vo ProgrmManageVO
	 * @exception Exception
	 */
	public void deleteProgrm(InfoProgrmManageVO vo){
		delete("InfoProgrmManageDAO.deleteProgrm_S", vo);
	}

	/**
	 * 프로그램 파일 존재여부를 조회
	 * @param vo ProgrmManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectProgrmNMTotCnt(CmsSearchVO vo) throws Exception{
		return (Integer)selectOne("InfoProgrmManageDAO.selectProgrmNMTotCnt", vo);
	}


	/**
	 * 프로그램변경요청 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */

	public List<?> selectProgrmChangeRequstList(CmsSearchVO vo) throws Exception{
		return selectList("InfoProgrmManageDAO.selectProgrmChangeRequstList_D", vo);
	}

    /**
	 * 프로그램변경요청 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return  int
	 * @exception Exception
	 */
    public int selectProgrmChangeRequstListTotCnt(CmsSearchVO vo) {
        return (Integer)selectOne("InfoProgrmManageDAO.selectProgrmChangeRequstListTotCnt_S", vo);
    }

	/**
	 * 프로그램변경요청 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 * @exception Exception
	 */
	public InfoProgrmManageDtlVO selectProgrmChangeRequst(InfoProgrmManageDtlVO vo)throws Exception{
		return (InfoProgrmManageDtlVO)selectOne("InfoProgrmManageDAO.selectProgrmChangeRequst_D", vo);
	}

	/**
	 * 프로그램변경요청을 등록
	 * @param vo ProgrmManageDtlVO
	 * @exception Exception
	 */
	public void insertProgrmChangeRequst(InfoProgrmManageDtlVO vo){
		insert("InfoProgrmManageDAO.insertProgrmChangeRequst_S", vo);
	}

	/**
	 * 프로그램변경요청을 수정
	 * @param vo ProgrmManageDtlVO
	 * @exception Exception
	 */
	public void updateProgrmChangeRequst(InfoProgrmManageDtlVO vo){
		update("InfoProgrmManageDAO.updateProgrmChangeRequst_S", vo);
	}

	/**
	 * 프로그램변경요청을 삭제
	 * @param vo ProgrmManageDtlVO
	 * @exception Exception
	 */
	public void deleteProgrmChangeRequst(InfoProgrmManageDtlVO vo){
		delete("InfoProgrmManageDAO.deleteProgrmChangeRequst_S", vo);
	}

	/**
	 * 프로그램변경요청 요청번호MAX 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 * @exception Exception
	 */
	public InfoProgrmManageDtlVO selectProgrmChangeRequstNo(InfoProgrmManageDtlVO vo){
		return (InfoProgrmManageDtlVO)selectOne("InfoProgrmManageDAO.selectProgrmChangeRequstNo_D", vo);
	}

	/**
	 * 프로그램변경요청 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectChangeRequstProcessList(CmsSearchVO vo) throws Exception{
		return selectList("InfoProgrmManageDAO.selectChangeRequstProcessList_D", vo);
	}

    /**
	 * 프로그램변경요청 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
    public int selectChangeRequstListProcessTotCnt(CmsSearchVO vo) {
        return (Integer)selectOne("InfoProgrmManageDAO.selectChangeRequstProcessListTotCnt_S", vo);
    }

	/**
	 * 프로그램변경요청 처리 수정
	 * @param vo InfoProgrmManageDtlVO
	 * @exception Exception
	 */
	public void updateProgrmChangeRequstProcess(InfoProgrmManageDtlVO vo){
		update("InfoProgrmManageDAO.updateProgrmChangeRequstProcess_S", vo);
	}


	/**
	 * 프로그램목록 전체삭제 초기화
	 * @return boolean
	 * @exception Exception
	 */
	public boolean deleteAllProgrm(){
		InfoProgrmManageVO vo = new InfoProgrmManageVO();
		update("InfoProgrmManageDAO.deleteAllProgrm", vo);
		return true;
	}

	/**
	 * 프로그램변경내역 전체삭제 초기화
	 * @return boolean
	 * @exception Exception
	 */
	public boolean deleteAllProgrmDtls(){
		InfoProgrmManageDtlVO vo = new InfoProgrmManageDtlVO();
		update("InfoProgrmManageDAO.deleteAllProgrmDtls", vo);
		return true;
	}

    /**
	 * 프로그램목록 데이타 존재여부 조회한다.
	 * @return int
	 * @exception Exception
	 */
    public int selectProgrmListTotCnt() {
		InfoProgrmManageVO vo = new InfoProgrmManageVO();
        return (Integer)selectOne("InfoProgrmManageDAO.selectProgrmListTotCnt", vo);
    }

	/**
	 * 프로그램변경요청자 Email 정보를 조회
	 * @param vo InfoProgrmManageDtlVO
	 * @return InfoProgrmManageDtlVO
	 * @exception Exception
	 */
	public InfoProgrmManageDtlVO selectRqesterEmail(InfoProgrmManageDtlVO vo){
		return (InfoProgrmManageDtlVO)selectOne("InfoProgrmManageDAO.selectRqesterEmail", vo);
	}
}