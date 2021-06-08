package infomind.com.cms.ccm.cde.dao;

import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import infomind.com.cms.ccm.cde.vo.InfoCmmnDetailCodeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
* 공통상세코드에 대한 데이터 접근 클래스를 정의한다
* @author 공통서비스 개발팀 이중호
* @since 2009.04.01
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2009.04.01  이중호          최초 생성
*
* </pre>
*/

@Repository("InfoCmmnDetailCodeManageDAO")
public class InfoCmmnDetailCodeManageDAO extends EgovComAbstractDAO {

    /**
	 * 공통상세코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통상세코드 총 갯수)
     */
    public int selectCmmnDetailCodeListTotCnt(InfoCmmnDetailCodeVO searchVO) throws Exception {
        return (Integer)selectOne("InfoCmmnDetailCodeManageDAO.selectCmmnDetailCodeListTotCnt", searchVO);
    }
    
    /**
	 * 공통상세코드 목록을 조회한다.
     * @param searchVO
     * @return List(공통상세코드 목록)
     * @throws Exception
     */
    public List<?> selectCmmnDetailCodeList(InfoCmmnDetailCodeVO searchVO) throws Exception {
        return list("InfoCmmnDetailCodeManageDAO.selectCmmnDetailCodeList", searchVO);
    }

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param cmmnDetailCodeVO
	 * @return CmmnDetailCodeVO(공통상세코드)
	 */
	public InfoCmmnDetailCodeVO selectCmmnDetailCodeDetail(InfoCmmnDetailCodeVO cmmnDetailCodeVO) throws Exception{
		return (InfoCmmnDetailCodeVO) selectOne("InfoCmmnDetailCodeManageDAO.selectCmmnDetailCodeDetail", cmmnDetailCodeVO);
	}
	
	/**
	 * 공통상세코드를 삭제한다.
	 * @param cmmnDetailCodeVO
	 * @throws Exception
	 */
	public void deleteCmmnDetailCode(InfoCmmnDetailCodeVO cmmnDetailCodeVO) throws Exception{
		delete("InfoCmmnDetailCodeManageDAO.deleteCmmnDetailCode", cmmnDetailCodeVO);
		
	}

	/**
	 * 공통상세코드를 등록한다.
	 * @param cmmnDetailCodeVO
	 * @throws Exception
	 */
	public void insertCmmnDetailCode(InfoCmmnDetailCodeVO cmmnDetailCodeVO) throws Exception{
		insert("InfoCmmnDetailCodeManageDAO.insertCmmnDetailCode", cmmnDetailCodeVO);
		
	}

	/**
	 * 공통상세코드를 수정한다.
	 * @param cmmnDetailCodeVO
	 * @throws Exception
	 */
	public void updateCmmnDetailCode(InfoCmmnDetailCodeVO cmmnDetailCodeVO) throws Exception{
		insert("InfoCmmnDetailCodeManageDAO.updateCmmnDetailCode", cmmnDetailCodeVO);
		
	}
    
}
