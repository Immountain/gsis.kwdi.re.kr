package infomind.com.cms.uss.umt.service;

import infomind.com.cms.uss.umt.vo.InfoDeptManageVO;

import java.util.List;

public interface InfoDeptManageService {

	/**
	 * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
	 * @param infoDeptManageVO - 부서 Vo
	 * @return List - 부서 목록
	 * 
	 * @param infoDeptManageVO
	 */
	public List<InfoDeptManageVO> selectDeptManageList(InfoDeptManageVO infoDeptManageVO) throws Exception;

	/**
	 * 부서목록 총 갯수를 조회한다.
	 * @param infoDeptManageVO - 부서 Vo
	 * @return int - 부서 카운트 수
	 * 
	 * @param infoDeptManageVO
	 */
	public int selectDeptManageListTotCnt(InfoDeptManageVO infoDeptManageVO) throws Exception;
	
	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * @param infoDeptManageVO - 부서 Vo
	 * @return infoDeptManageVO - 부서 Vo
	 * 
	 * @param infoDeptManageVO
	 */
	public InfoDeptManageVO selectDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception;

	/**
	 * 부서정보를 신규로 등록한다.
	 * @param infoDeptManageVO - 부서 model
	 * 
	 * @param infoDeptManageVO
	 */
	public void insertDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception;

	/**
	 * 기 등록된 부서정보를 수정한다.
	 * @param infoDeptManageVO - 부서 model
	 * 
	 * @param infoDeptManageVO
	 */
	public void updateDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception;

	/**
	 * 기 등록된 부서정보를 삭제한다.
	 * @param infoDeptManageVO - 부서 model
	 * 
	 * @param infoDeptManageVO
	 */
	public void deleteDeptManage(InfoDeptManageVO infoDeptManageVO) throws Exception;


}
