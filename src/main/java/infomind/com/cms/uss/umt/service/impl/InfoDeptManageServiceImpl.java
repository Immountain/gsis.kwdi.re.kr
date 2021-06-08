package infomind.com.cms.uss.umt.service.impl;

import java.util.List;

import infomind.com.cms.uss.umt.dao.InfoDeptManageDAO;
import infomind.com.cms.uss.umt.vo.InfoDeptManageVO;
import infomind.com.cms.uss.umt.service.InfoDeptManageService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("infoDeptManageService")
public class InfoDeptManageServiceImpl extends EgovAbstractServiceImpl implements InfoDeptManageService {
	
	@Resource(name="infoDeptManageDAO")
    private InfoDeptManageDAO infoDeptManageDAO;

	/**
	 * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
	 * @param infoDeptManageVO - 부서 Vo
	 * @return List - 부서 목록
	 * 
	 * @param infoDeptManageVO
	 */
	public List<InfoDeptManageVO> selectDeptManageList(InfoDeptManageVO infoDeptManageVO) throws Exception {
		return infoDeptManageDAO.selectDeptManageList(infoDeptManageVO);
	}

	/**
	 * 부서목록 총 갯수를 조회한다.
	 * @param infoDeptManageVO - 부서 Vo
	 * @return int - 부서 카운트 수
	 * 
	 * @param infoDeptManageVO
	 */
	public int selectDeptManageListTotCnt(InfoDeptManageVO infoDeptManageVO) throws Exception {
		return infoDeptManageDAO.selectDeptManageListTotCnt(infoDeptManageVO);
	}

	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * @param infoDeptManageVO - 부서 Vo
	 * @return infoDeptManageVO - 부서 Vo
	 * 
	 * @param infoDeptManageVO
	 */
	public InfoDeptManageVO selectDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception {
		return infoDeptManageDAO.selectDeptManage(infoDeptManageVO);
	}

	/**
	 * 부서정보를 신규로 등록한다.
	 * @param infoDeptManageVO - 부서 model
	 * 
	 * @param infoDeptManageVO
	 */
	public void insertDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception {
		infoDeptManageDAO.insertDeptManage(infoDeptManageVO);
	}

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * @param infoDeptManageVO - 부서 model
	 * 
	 * @param infoDeptManageVO
	 */
	public void updateDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception {
		infoDeptManageDAO.updateDeptManage(infoDeptManageVO);
	}

	/**
	 * 기 등록된 부서정보를 삭제한다.
	 * @param infoDeptManageVO - 부서 model
	 * 
	 * @param infoDeptManageVO
	 */
	public void deleteDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception {
		infoDeptManageDAO.deleteDeptManage(infoDeptManageVO);
	}


}
