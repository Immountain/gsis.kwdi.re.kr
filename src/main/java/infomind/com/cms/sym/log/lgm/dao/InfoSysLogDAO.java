package infomind.com.cms.sym.log.lgm.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.sym.log.lgm.vo.InfoSysLogVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @Class Name : SysLogDAO.java
* @Description : 로그관리(시스템)를 위한 데이터 접근 클래스
* @Modification Information
*
*    수정일         수정자         수정내용
*    -------        -------     -------------------
*    2009. 3. 11.   이삼섭         최초생성
*    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.lgm)
*
* @author 공통 서비스 개발팀 이삼섭
* @since 2009. 3. 11.
* @version
* @see
*
*/
@Repository("InfoSysLogDAO")
public class InfoSysLogDAO extends EgovComAbstractDAO{

	/**
	 * 시스템 로그정보를 생성한다.
	 *
	 * @param SysLog
	 * @return
	 * @throws Exception
	 */
	public void logInsertSysLog(InfoSysLogVO sysLog) {
		insert("InfoSysLogDAO.logInsertSysLog", sysLog);
		
	}

	/**
	 * 시스템 로그정보를 요약한다.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void logInsertSysLogSummary() {
		insert("InfoSysLogDAO.logInsertSysLogSummary", null);
		delete("InfoSysLogDAO.logDeleteSysLogSummary", null);
		
	}

	/**
	 * 시스템 로그목록을 조회한다.
	 *
	 * @param sysLog
	 * @return sysLog
	 * @throws Exception
	 */
	public List<?> selectSysLogInf(InfoSysLogVO sysLog) {
		return list("InfoSysLogDAO.selectSysLogInf", sysLog);
	}

	/**
	 * 시스템 로그정보 목록의 숫자를 조회한다.
	 * @param sysLog
	 * @return
	 * @throws Exception
	 */
	public int selectSysLogInfCnt(InfoSysLogVO sysLog) {
		return (Integer)selectOne("InfoSysLogDAO.selectSysLogInfCnt", sysLog);
	}

	/**
	 * 시스템 로그 상세정보를 조회한다.
	 *
	 * @param sysLog
	 * @return sysLog
	 * @throws Exception
	 */
	public InfoSysLogVO selectSysLog(InfoSysLogVO sysLog) {
		return (InfoSysLogVO) selectOne("InfoSysLogDAO.selectSysLog", sysLog);
	}
}
