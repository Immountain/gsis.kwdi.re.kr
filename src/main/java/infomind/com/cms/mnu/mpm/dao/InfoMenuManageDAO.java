package infomind.com.cms.mnu.mpm.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.mnu.mpm.vo.InfoMenuManageVO;
import infomind.com.ext.vo.CmsSearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 메뉴관리, 메뉴생성, 사이트맵 생성에 대한 DAO 클래스를 정의한다.
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
 *   2011.07.01  서준식			자기 메뉴 정보를 상위메뉴 정보로 참조하는 메뉴정보가 있는지 조회하는
 *   							selectUpperMenuNoByPk() 메서드 추가
 *
 * </pre>
 */

@Repository("infoMenuManageDAO")
public class InfoMenuManageDAO extends EgovComAbstractDAO{

	/**
	 * 메뉴목록을 조회
	 * @param vo CmsSearchVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectMenuManageList(CmsSearchVO vo) throws Exception{
		return selectList("infoMenuManageDAO.selectMenuManageList_D", vo);
	}

    /**
	 * 메뉴목록관리 총건수를 조회한다.
	 * @param vo CmsSearchVO
	 * @return int
	 * @exception Exception
	 */
    public int selectMenuManageListTotCnt(CmsSearchVO vo) {
        return (Integer)selectOne("infoMenuManageDAO.selectMenuManageListTotCnt_S", vo);
    }

	/**
	 * 메뉴목록관리 기본정보를 조회
	 * @param vo CmsSearchVO
	 * @return InfoMenuManageVO
	 * @exception Exception
	 */
	public InfoMenuManageVO selectMenuManage(CmsSearchVO vo)throws Exception{
		return (InfoMenuManageVO)selectOne("infoMenuManageDAO.selectMenuManage_D", vo);
	}

	/**
	 * 메뉴목록 기본정보를 등록
	 * @param vo InfoMenuManageVO
	 * @exception Exception
	 */
	public void insertMenuManage(InfoMenuManageVO vo){
		insert("infoMenuManageDAO.insertMenuManage_S", vo);
	}

	/**
	 * 메뉴목록 기본정보를 수정
	 * @param vo InfoMenuManageVO
	 * @exception Exception
	 */
	public void updateMenuManage(InfoMenuManageVO vo){
		update("infoMenuManageDAO.updateMenuManage_S", vo);
	}

	/**
	 * 메뉴목록 기본정보를 삭제
	 * @param vo InfoMenuManageVO
	 * @exception Exception
	 */
	public void deleteMenuManage(InfoMenuManageVO vo){
		delete("infoMenuManageDAO.deleteMenuManage_S", vo);
	}

	/**
	 * 메뉴 전체목록을 조회
	 * @return list
	 * @exception Exception
	 */
	public List<?> selectMenuList() throws Exception{
		CmsSearchVO vo  = new CmsSearchVO();
		return selectList("infoMenuManageDAO.selectMenuListT_D", vo);
	}


	/**
	 * 메뉴번호 존재여부를 조회
	 * @param vo InfoMenuManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectMenuNoByPk(InfoMenuManageVO vo) throws Exception{
		return (Integer)selectOne("infoMenuManageDAO.selectMenuNoByPk", vo);
	}



	/**
	 * 메뉴번호를 상위메뉴로 참조하고 있는 메뉴 존재여부를 조회
	 * @param vo InfoMenuManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectUpperMenuNoByPk(InfoMenuManageVO vo) throws Exception{
		return (Integer)selectOne("infoMenuManageDAO.selectUpperMenuNoByPk", vo);
	}


	/**
	 * 메뉴정보 전체삭제 초기화
	 * @return boolean
	 * @exception Exception
	 */
	public boolean deleteAllMenuList(){
		InfoMenuManageVO vo = new InfoMenuManageVO();
		insert("infoMenuManageDAO.deleteAllMenuList", vo);
		return true;
	}

    /**
	 * 메뉴정보 존재여부 조회한다.
	 * @return int
	 * @exception Exception
	 */
    public int selectMenuListTotCnt() {
    	InfoMenuManageVO vo = new InfoMenuManageVO();
        return (Integer)selectOne("infoMenuManageDAO.selectMenuListTotCnt", vo);
    }


	/*### 메뉴관련 프로세스 ###*/
	/**
	 * MainMenu Head Menu 조회
	 * @param vo InfoMenuManageVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectMainMenuHead(InfoMenuManageVO vo) throws Exception{
		return selectList("infoMenuManageDAO.selectMainMenuHead", vo);
	}

	/**
	 * MainMenu Left Menu 조회
	 * @param vo InfoMenuManageVO
	 * @return List
	 * @exception Exception
	 */
	public List<?> selectMainMenuLeft(InfoMenuManageVO vo) throws Exception{
		return selectList("infoMenuManageDAO.selectMainMenuLeft", vo);
	}

	/**
	 * MainMenu Head MenuURL 조회
	 * @param vo InfoMenuManageVO
	 * @return  String
	 * @exception Exception
	 */
	public String selectLastMenuURL(InfoMenuManageVO vo) throws Exception{
		return (String)selectOne("infoMenuManageDAO.selectLastMenuURL", vo);
	}

	/**
	 * MainMenu Left Menu 조회
	 * @param vo InfoMenuManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectLastMenuNo(InfoMenuManageVO vo) throws Exception{
		return (Integer)selectOne("infoMenuManageDAO.selectLastMenuNo", vo);
	}

	/**
	 * MainMenu Left Menu 조회
	 * @param vo InfoMenuManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectLastMenuNoCnt(InfoMenuManageVO vo) throws Exception{
		return (Integer)selectOne("infoMenuManageDAO.selectLastMenuNoCnt", vo);
	}

	/**
	 * 메뉴전체 리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<InfoMenuManageVO> selectMainMenuAll(InfoMenuManageVO vo) throws Exception{
		return selectList("infoMenuManageDAO.selectMainMenuAll", vo);
	}

	/**
	 * 메뉴정보
	 * @param menuTargetNo
	 * @return
	 * @throws Exception
	 */
	public InfoMenuManageVO selectMenuInfo(String menuTargetNo) throws Exception{

		InfoMenuManageVO vo = new InfoMenuManageVO();
		vo.setMenuTargetNo(menuTargetNo);

		return (InfoMenuManageVO)selectOne("infoMenuManageDAO.selectMenuInfo", vo);
	}

	public List<InfoMenuManageVO> selectCmsMenuAll(InfoMenuManageVO vo) throws Exception{
		return selectList("infoMenuManageDAO.selectMenuManageList_all", vo);
	}


}