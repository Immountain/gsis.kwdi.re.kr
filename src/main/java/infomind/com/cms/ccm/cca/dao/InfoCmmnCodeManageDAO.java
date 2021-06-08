package infomind.com.cms.ccm.cca.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import infomind.com.cms.ccm.cca.vo.InfoCmmnCodeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
* 공통코드에 대한 데이터 접근 클래스를 정의한다
* @author 인포마인드 개발팀 양진혁
* @since 2020.10.19
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2020.10.19  양진혁         최초 생성
*
* </pre>
*/

@Repository("InfoCmmnCodeManageDAO")
public class InfoCmmnCodeManageDAO extends    EgovComAbstractDAO {

   /**
	 * 공통코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통코드 총 갯수)
     */
	public int selectCmmnCodeListTotCnt(InfoCmmnCodeVO searchVO) throws Exception{
		return (Integer)selectOne("InfoCmmnCodeManageDAO.selectCmmnCodeListTotCnt", searchVO);
	}

   /**
	 * 공통코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통코드 목록)
     * @throws Exception
     */
	public List<?> selectCmmnCodeList(InfoCmmnCodeVO searchVO) throws Exception{
		 return list("InfoCmmnCodeManageDAO.selectCmmnCodeList", searchVO);
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 * @param cmmnCodeVO
	 * @return CmmnCode(공통코드)
	 */
	public InfoCmmnCodeVO selectCmmnCodeDetail(InfoCmmnCodeVO cmmnCodeVO) throws Exception{
		return selectOne("InfoCmmnCodeManageDAO.selectCmmnCodeDetail", cmmnCodeVO);
	}

	/**
	 * 공통코드를 수정한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	public void updateCmmnCode(InfoCmmnCodeVO cmmnCode) throws Exception{
		update("InfoCmmnCodeManageDAO.updateCmmnCode", cmmnCode);
	}

	/**
	 * 공통코드를 등록한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	public void insertCmmnCode(InfoCmmnCodeVO cmmnCode) throws Exception{
		insert("InfoCmmnCodeManageDAO.insertCmmnCode", cmmnCode);
	}

	/**
	 * 공통코드를 삭제한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	public void deleteCmmnCode(InfoCmmnCodeVO cmmnCode) {

		delete("InfoCmmnCodeManageDAO.deleteCmmnCode", cmmnCode);
	}

}
