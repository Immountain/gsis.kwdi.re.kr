package infomind.com.cms.sec.rmt.service;

import infomind.com.cms.sec.rmt.vo.InfoRoleManage;
import infomind.com.cms.sec.rmt.vo.InfoRoleManageVO;

import java.util.List;

public interface InfoRoleManageService {

    public InfoRoleManageVO selectInfoRole(InfoRoleManageVO infoRoleManageVO) throws Exception;

    /**
     * 등록된 롤 정보 목록 조회
     * @param infoRoleManageVO InfoRoleManageVO
     * @return List<RoleManageVO>
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<InfoRoleManageVO> selectInfoRoleList(InfoRoleManageVO infoRoleManageVO) throws Exception;

    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
     * @param infoRoleManage InfoRoleManage
     * @exception Exception
     */
    InfoRoleManageVO insertInfoRole(InfoRoleManage infoRoleManage, InfoRoleManageVO infoRoleManageVO) throws Exception;
    /**
     * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
     * @param infoRoleManage InfoRoleManage
     * @exception Exception
     */
     void updateInfoRole(InfoRoleManage infoRoleManage) throws Exception;
    /**
     * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param infoRoleManage InfoRoleManage
     * @exception Exception
     */
     void deleteInfoRole(InfoRoleManage infoRoleManage) throws Exception;

    /**
     * 롤목록 총 갯수를 조회한다.
     * @param infoRoleManageVO InfoRoleManageVO
     * @return int
     * @exception Exception
     */
     int selectInfoRoleListTotCnt(InfoRoleManageVO infoRoleManageVO) throws Exception;

    /**
     * 등록된 모든 롤 정보 목록 조회
     * @param infoRoleManageVO InfoRoleManageVO
     * @return List<InfoRoleManageVO>
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
     List<InfoRoleManageVO> selectInfoRoleAllList(InfoRoleManageVO infoRoleManageVO) throws Exception;

}
