package infomind.com.cms.mnu.mcm.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.mnu.mcm.vo.InfoMenuCreatVO;
import infomind.com.cms.mnu.mcm.vo.InfoMenuSiteMapVO;
import infomind.com.ext.vo.CmsSearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 메뉴생성, 사이트맵 생성에 대한 DAO 클래스를 정의한다. *
 * @author 공통컴포넌트 개발팀 서준식
 * @since 2011.06.30
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.30  서 준 식   최초 생성(MenuManageDAO 클래스로 부터 분리
 *   					   메소드들을 MenuManageDAO 클래스에서 분리해옮)
 *
 * </pre>
 */

@Repository("infoMenuCreateManageDAO")
public class InfoMenuCreateManageDAO extends EgovComAbstractDAO{



	/**
	 * ID 존재여부를 조회
	 * @param vo MenuManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectUsrByPk(CmsSearchVO vo) throws Exception{
		return (Integer)selectOne("menuManageDAO.selectUsrByPk", vo);
	}

	/**
	 * ID에 대한 권한코드를 조회
	 * @param vo InfoMenuCreatVO
	 * @return int
	 * @exception Exception
	 */
	public InfoMenuCreatVO selectAuthorByUsr(CmsSearchVO vo) throws Exception{
		return (InfoMenuCreatVO)selectOne("menuManageDAO.selectAuthorByUsr", vo);
	}

	/**
	 * 메뉴생성관리 내역을 조회
	 * @param vo CmsSearchVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectMenuCreatManagList(CmsSearchVO vo) throws Exception{
		return selectList("menuManageDAO.selectMenuCreatManageList_D", vo);
	}

	/**
	 * 메뉴생성관리 총건수를 조회한다.
	 * @param vo CmsSearchVO
	 * @return int
	 * @exception Exception
	 */
	public int selectMenuCreatManagTotCnt(CmsSearchVO vo) {
		return (Integer)selectOne("menuManageDAO.selectMenuCreatManageTotCnt_S", vo);
	}

	/*********** 메뉴 생성 관리 ***************/
	/**
	 * 메뉴생성 내역을 조회
	 * @param vo InfoMenuCreatVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectMenuCreatList(InfoMenuCreatVO vo) throws Exception{
		return selectList("menuManageDAO.selectMenuCreatList_D", vo);
	}

	/**
	 * 메뉴생성내역 등록
	 * @param vo InfoMenuCreatVO
	 * @exception Exception
	 */
	public void insertMenuCreat(InfoMenuCreatVO vo){
		insert("menuManageDAO.insertMenuCreat_S", vo);
	}

	/**
	 * 메뉴생성 사이트맵 내용 조회
	 * @param vo InfoMenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectMenuCreatSiteMapList(InfoMenuSiteMapVO vo) throws Exception{
		return selectList("menuManageDAO.selectMenuCreatSiteMapList_D", vo);
	}



	/**
	 * 사이트맵 등록
	 * @param vo InfoMenuSiteMapVO
	 * @exception Exception
	 */
	public void creatSiteMap(InfoMenuSiteMapVO vo){
		insert("menuManageDAO.insertSiteMap_S", vo);
	}

	/**
	 * 사용자 권한별 사이트맵 내용 조회
	 * @param vo InfoMenuSiteMapVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectSiteMapByUser(InfoMenuSiteMapVO vo) throws Exception{
		return selectList("menuManageDAO.selectSiteMapByUser", vo);
	}

	/**
	 * 메뉴생성내역 존재여부 조회한다.
	 * @param vo InfoMenuCreatVO
	 * @return int
	 * @exception Exception
	 */
	public int selectMenuCreatCnt(InfoMenuCreatVO vo) {
		return (Integer)selectOne("menuManageDAO.selectMenuCreatCnt_S", vo);
	}


	/**
	 * 메뉴생성내역 수정
	 * @param vo InfoMenuCreatVO
	 * @exception Exception
	 */
	public void updateMenuCreat(InfoMenuCreatVO vo){
		update("menuManageDAO.updateMenuCreat_S", vo);
	}


	/**
	 * 메뉴생성내역 삭제
	 * @param vo InfoMenuCreatVO
	 * @exception Exception
	 */
	public void deleteMenuCreat(InfoMenuCreatVO vo){
		delete("menuManageDAO.deleteMenuCreat_S", vo);
	}

	/**
	 * 사이트맵 존재여부 조회한다.
	 * @param vo InfoMenuSiteMapVO
	 * @return int
	 * @exception Exception
	 */
	public int selectSiteMapCnt(InfoMenuSiteMapVO vo) {
		return (Integer)selectOne("menuManageDAO.selectSiteMapCnt_S", vo);
	}


}
