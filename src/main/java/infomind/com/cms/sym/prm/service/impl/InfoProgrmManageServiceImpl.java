package infomind.com.cms.sym.prm.service.impl;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.com.sym.prm.service.ProgrmManageDtlVO;
import egovframework.com.sym.prm.service.ProgrmManageVO;
import egovframework.com.sym.prm.service.impl.ProgrmManageDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.sym.prm.dao.InfoProgrmManageDAO;
import infomind.com.cms.sym.prm.service.InfoProgrmManageService;
import infomind.com.cms.sym.prm.vo.InfoProgrmManageDtlVO;
import infomind.com.cms.sym.prm.vo.InfoProgrmManageVO;
import infomind.com.ext.vo.CmsSearchVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 프로그램목록관리 및 프로그램변경관리에 관한 비즈니스 구현 클래스를 정의한다.
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
@Service("InfoProgrmManageService")
public class InfoProgrmManageServiceImpl extends EgovAbstractServiceImpl implements InfoProgrmManageService {

	@Resource(name="InfoProgrmManageDAO")
    private InfoProgrmManageDAO progrmManageDAO;


	/**
	 * 프로그램 상세정보를 조회
	 * @param vo ComDefaultVO
	 * @return ProgrmManageVO
	 * @exception Exception
	 */
	@Override
	public InfoProgrmManageVO selectProgrm(InfoProgrmManageVO vo) throws Exception{
         	return progrmManageDAO.selectProgrm(vo);
	}
	/**
	 * 프로그램 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@Override
	public List<?> selectProgrmList(CmsSearchVO vo) throws Exception {
   		return progrmManageDAO.selectProgrmList(vo);
	}
	/**
	 * 프로그램목록 총건수를 조회한다.
	 * @param vo  ComDefaultVO
	 * @return Integer
	 * @exception Exception
	 */
    @Override
	public int selectProgrmListTotCnt(CmsSearchVO vo) throws Exception {
        return progrmManageDAO.selectProgrmListTotCnt(vo);
	}
	/**
	 * 프로그램 정보를 등록
	 * @param vo ProgrmManageVO
	 * @exception Exception
	 */
	@Override
	public void insertProgrm(InfoProgrmManageVO vo) throws Exception {
    	progrmManageDAO.insertProgrm(vo);
	}

	/**
	 * 프로그램 정보를 수정
	 * @param vo ProgrmManageVO
	 * @exception Exception
	 */
	@Override
	public void updateProgrm(InfoProgrmManageVO vo) throws Exception {
    	progrmManageDAO.updateProgrm(vo);
	}

	/**
	 * 프로그램 정보를 삭제
	 * @param vo ProgrmManageVO
	 * @exception Exception
	 */
	@Override
	public void deleteProgrm(InfoProgrmManageVO vo) throws Exception {
    	progrmManageDAO.deleteProgrm(vo);
	}

	/**
	 * 프로그램 파일 존재여부를 조회
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
	@Override
	public int selectProgrmNMTotCnt(CmsSearchVO vo) throws Exception{
		return progrmManageDAO.selectProgrmNMTotCnt(vo);
	}

	/**
	 * 프로그램변경요청 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 * @exception Exception
	 */
	@Override
	public InfoProgrmManageDtlVO selectProgrmChangeRequst(InfoProgrmManageDtlVO vo) throws Exception{
       	return progrmManageDAO.selectProgrmChangeRequst(vo);
	}

	/**
	 * 프로그램변경요청 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@Override
	public List<?> selectProgrmChangeRequstList(CmsSearchVO vo) throws Exception {
   		return progrmManageDAO.selectProgrmChangeRequstList(vo);
	}

	/**
	 * 프로그램변경요청목록 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
    @Override
	public int selectProgrmChangeRequstListTotCnt(CmsSearchVO vo) throws Exception {
        return progrmManageDAO.selectProgrmChangeRequstListTotCnt(vo);
	}

	/**
	 * 프로그램변경요청을 등록
	 * @param vo ProgrmManageDtlVO
	 * @exception Exception
	 */
	@Override
	public void insertProgrmChangeRequst(InfoProgrmManageDtlVO vo) throws Exception {
    	progrmManageDAO.insertProgrmChangeRequst(vo);
	}

	/**
	 * 프로그램변경요청을 수정
	 * @param vo ProgrmManageDtlVO
	 * @exception Exception
	 */
	@Override
	public void updateProgrmChangeRequst(InfoProgrmManageDtlVO vo) throws Exception {
    	progrmManageDAO.updateProgrmChangeRequst(vo);
	}

	/**
	 * 프로그램변경요청을 삭제
	 * @param vo ProgrmManageDtlVO
	 * @exception Exception
	 */
	@Override
	public void deleteProgrmChangeRequst(InfoProgrmManageDtlVO vo) throws Exception {
    	progrmManageDAO.deleteProgrmChangeRequst(vo);
	}

	/**
	 * 프로그램변경요청 요청번호MAX 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 * @exception Exception
	 */
	@Override
	public InfoProgrmManageDtlVO selectProgrmChangeRequstNo(InfoProgrmManageDtlVO vo) throws Exception {
   		return progrmManageDAO.selectProgrmChangeRequstNo(vo);
	}

	/**
	 * 프로그램변경요청처리 목록을 조회
	 * @param vo ComDefaultVO
	 * @return List
	 * @exception Exception
	 */
	@Override
	public List<?> selectChangeRequstProcessList(CmsSearchVO vo) throws Exception {
   		return progrmManageDAO.selectChangeRequstProcessList(vo);
	}

	/**
	 * 프로그램변경요청처리목록 총건수를 조회한다.
	 * @param vo ComDefaultVO
	 * @return int
	 * @exception Exception
	 */
    @Override
	public int selectChangeRequstProcessListTotCnt(CmsSearchVO vo) throws Exception {
        return progrmManageDAO.selectChangeRequstListProcessTotCnt(vo);
	}

	/**
	 * 프로그램변경요청처리를 수정
	 * @param vo ProgrmManageDtlVO
	 * @exception Exception
	 */
	@Override
	public void updateProgrmChangeRequstProcess(InfoProgrmManageDtlVO vo) throws Exception {
    	progrmManageDAO.updateProgrmChangeRequstProcess(vo);
	}

	/**
	 * 화면에 조회된 메뉴 목록 정보를 데이터베이스에서 삭제
	 * @param checkedProgrmFileNmForDel String
	 * @exception Exception
	 */
	@Override
	public void deleteProgrmManageList(String checkedProgrmFileNmForDel) throws Exception {
		InfoProgrmManageVO vo = null;
		String [] delProgrmFileNm = checkedProgrmFileNmForDel.split(",");
		for (int i=0; i<delProgrmFileNm.length ; i++){
			vo = new InfoProgrmManageVO();
			vo.setProgrmFileNm(delProgrmFileNm[i]);
			progrmManageDAO.deleteProgrm(vo);
		}
	}

	/**
	 * 프로그램변경요청자 Email 정보를 조회
	 * @param vo ProgrmManageDtlVO
	 * @return ProgrmManageDtlVO
	 * @exception Exception
	 */
	@Override
	public InfoProgrmManageDtlVO selectRqesterEmail(InfoProgrmManageDtlVO vo) throws Exception{
       	return progrmManageDAO.selectRqesterEmail(vo);
	}


}