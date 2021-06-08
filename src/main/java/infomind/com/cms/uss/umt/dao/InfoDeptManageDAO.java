package infomind.com.cms.uss.umt.dao;

import java.util.List;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.uss.umt.vo.InfoDeptManageVO;

import org.springframework.stereotype.Repository;

@Repository("infoDeptManageDAO")
public class InfoDeptManageDAO extends EgovComAbstractDAO {

	/**
	 * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
	 * @param infoDeptManageVO - 부서 Vo
	 * @return List - 부서 목록
	 * @exception Exception
	 */
	public List<InfoDeptManageVO> selectDeptManageList(InfoDeptManageVO infoDeptManageVO) throws Exception {
		return selectList("infoDeptManageDAO.selectDeptManageList", infoDeptManageVO);
	}

    /**
	 * 부서목록 총 갯수를 조회한다.
	 * @param infoDeptManageVO - 부서 Vo
	 * @return int - 부서 카운트 수
	 * @exception Exception
	 */
    public int selectDeptManageListTotCnt(InfoDeptManageVO infoDeptManageVO) throws Exception {
        return (Integer)selectOne("infoDeptManageDAO.selectDeptManageListTotCnt", infoDeptManageVO);
    }

	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * @param infoDeptManageVO - 부서 Vo
	 * @return infoDeptManageVO - 부서 Vo
	 * 

	 */
	public InfoDeptManageVO selectDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception {
		return (InfoDeptManageVO) selectOne("infoDeptManageDAO.selectDeptManage", infoDeptManageVO);
	}

	/**
	 * 부서정보를 신규로 등록한다.
	 * @param infoDeptManageVO - 부서 model
	 */
	public void insertDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception {
		insert("infoDeptManageDAO.insertDeptManage", infoDeptManageVO);
	}

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * @param infoDeptManageVO - 부서 model
	 */
	public void updateDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception {
        update("infoDeptManageDAO.updateDeptManage", infoDeptManageVO);
	}

	/**
	 * 기 등록된 부서정보를 삭제한다.
	 * @param infoDeptManageVO - 부서 model
	 * 

	 */
	public void deleteDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception {
		delete("infoDeptManageDAO.deleteDeptManage", infoDeptManageVO);
	}


}
