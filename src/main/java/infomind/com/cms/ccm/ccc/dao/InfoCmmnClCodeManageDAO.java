package infomind.com.cms.ccm.ccc.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.ccm.ccc.vo.InfoCmmnClCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
* 공통분류코드에 대한 데이터 접근 클래스를 정의한다
* @author 인포마인드 개발팀 양진혁
* @since 2020.10.07
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2020.10.07  양진혁          최초 생성
*
* </pre>
*/
@Repository("InfoCmmnClCodeManageDAO")
public class InfoCmmnClCodeManageDAO extends EgovComAbstractDAO {
	
	   /**
		 * 공통분류코드 총 갯수를 조회한다.
	     * @param searchVO
	     * @return int(공통분류코드 총 갯수)
	     */
	    public int selectCmmnClCodeListTotCnt(InfoCmmnClCode searchVO) throws Exception {
	        return (Integer)selectOne("InfoCmmnClCodeManageDAO.selectCmmnClCodeListTotCnt", searchVO);
	    }
	    
	    /**
		 * 공통분류코드 목록을 조회한다.
	     * @param searchVO
	     * @return List(공통분류코드 목록)
	     * @throws Exception
	     */
	    public List<?> selectCmmnClCodeList(InfoCmmnClCode searchVO) throws Exception {
	        return list("InfoCmmnClCodeManageDAO.selectCmmnClCodeList", searchVO);
	    }
	    
	    /**
		 * 공통분류코드 상세항목을 조회한다.
		 * @param cmmnClCode
		 * @return CmmnClCode(공통분류코드)
		 */
		public InfoCmmnClCode selectCmmnClCodeDetail(InfoCmmnClCode cmmnClCode) throws Exception {
			return (InfoCmmnClCode)selectOne("InfoCmmnClCodeManageDAO.selectCmmnClCodeDetail", cmmnClCode);
		}
		
		/**
		 * 공통분류코드를 등록한다.
		 * @param cmmnClCodeVO
		 * @throws Exception
		 */
		public void insertCmmnClCode(InfoCmmnClCode cmmnClCodeVO) throws Exception{
			System.out.println("TEST5 : 등록 DAO insertCmmnClCode");
			 insert("InfoCmmnClCodeManageDAO.insertCmmnClCode", cmmnClCodeVO);
		}

			/**
			 * 공통분류코드를 등록한다.
			 * @param cmmnClCodeVO
			 * @throws Exception
			 */
			public void insertCmmnClCodeTest(InfoCmmnClCode cmmnClCodeVO) throws Exception{
				System.out.println("TEST5 : 등록 insertCmmnClCodeTest");
				insert("InfoCmmnClCodeManageDAO.insertCmmnClCodeTest", cmmnClCodeVO);
			}


		/**
		 * 공통분류코드를 삭제한다.
		 * @param cmmnClCodeVO
		 * @throws Exception
		 */
		public void deleteCmmnClCode(InfoCmmnClCode cmmnClCodeVO) throws Exception {
			delete("InfoCmmnClCodeManageDAO.deleteCmmnClCode", cmmnClCodeVO);
			
		}


	public void deleteCmmnClCodeTest(InfoCmmnClCode cmmnClCodeVO) throws Exception {
		delete("InfoCmmnClCodeManageDAO.deleteCmmnClCodeTest", cmmnClCodeVO);

	}
		
		/**
		 * 공통분류코드를 수정한다.
		 * @param cmmnClCodeVO
		 * @throws Exception
		 */
		public void updateCmmnClCode(InfoCmmnClCode cmmnClCodeVO) throws Exception{
			update("InfoCmmnClCodeManageDAO.updateCmmnClCode", cmmnClCodeVO);
			
		}


		public List<InfoCmmnClCode> selectCmmnClCodeAllList(InfoCmmnClCode searchVO) throws Exception {

			System.out.println("양진혁 테스트 ");


			return (List<InfoCmmnClCode>) list("InfoCmmnClCodeManageDAO.selectCmmnClCodeAllList", searchVO);
		}



}
