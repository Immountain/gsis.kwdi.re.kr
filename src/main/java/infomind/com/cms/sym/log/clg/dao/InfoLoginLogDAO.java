package infomind.com.cms.sym.log.clg.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.sym.log.clg.vo.InfoLoginLogVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Class Name : LoginLogDAO.java
 * @Description : 시스템 로그 관리를 위한 데이터 접근 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------       -------     -------------------
 *    2009. 3. 11.  이삼섭       최초생성
 *    2011. 7. 01.  이기하       패키지 분리(sym.log -> sym.log.clg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Repository("InfoLoginLogDAO")
public class InfoLoginLogDAO extends EgovComAbstractDAO {

	/**
	 * 접속로그를 기록한다.
	 *
	 * @param LoginLog
	 * @return
	 * @throws Exception
	 */
	public void logInsertLoginLog(InfoLoginLogVO loginLog) throws Exception{
		insert("InfoLoginLogDAO.logInsertLoginLog", loginLog);
	}

	/**
	 * 접속로그 상세보기를 조회한다.
	 *
	 * @param loginLog
	 * @return loginLog
	 * @throws Exception
	 */
	public InfoLoginLogVO selectLoginLog(InfoLoginLogVO loginLog) throws Exception{

		return (InfoLoginLogVO) selectOne("InfoLoginLogDAO.selectLoginLog", loginLog);
	}

	/**
	 * 접속로그를 목록을 조회한다.
	 *
	 * @param loginLog
	 * @return
	 * @throws Exception
	 */
	public List<?> selectLoginLogInf(InfoLoginLogVO loginLog) throws Exception{
		return list("InfoLoginLogDAO.selectLoginLogInf", loginLog);
	}

	/**
	 * 접속로그 목록의 숫자를 조회한다.
	 * @param loginLog
	 * @return
	 * @throws Exception
	 */
	public int selectLoginLogInfCnt(InfoLoginLogVO loginLog) throws Exception{

		return (Integer)selectOne("InfoLoginLogDAO.selectLoginLogInfCnt", loginLog);
	}

}
