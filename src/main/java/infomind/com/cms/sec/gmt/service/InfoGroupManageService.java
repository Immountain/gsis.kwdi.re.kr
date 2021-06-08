package infomind.com.cms.sec.gmt.service;

import infomind.com.cms.sec.gmt.vo.InfoGroupManage;
import infomind.com.cms.sec.gmt.vo.InfoGroupManageVO;

import java.util.List;

public interface InfoGroupManageService {

    /**
     * 검색조건에 따른 그룹정보를 조회
     * @param infoGroupManageVO InfoGroupManageVO
     * @return GroupManageVO
     * @exception Exception
     */
    public InfoGroupManageVO selectInfoGroup(InfoGroupManageVO infoGroupManageVO) throws Exception;

    /**
     * 시스템사용 목적별 그룹 목록 조회
     * @param infoGroupManageVO InfoGroupManageVO
     * @return InfoGroupManageVO
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public List<InfoGroupManageVO> selectInfoGroupList(InfoGroupManageVO infoGroupManageVO) throws Exception;

    /**
     * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param infoGroupManage InfoGroupManage
     * @exception Exception
     */
    public InfoGroupManageVO insertInfoGroup(InfoGroupManage infoGroupManage, InfoGroupManageVO infoGroupManageVO) throws Exception;

    /**
     * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param infoGroupManage InfoGroupManage
     * @exception Exception
     */
    public void updateInfoGroup(InfoGroupManage infoGroupManage) throws Exception;


    /**
     * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param infoGroupManage InfoGroupManage
     * @exception Exception
     */
    public void deleteInfoGroup(InfoGroupManage infoGroupManage) throws Exception;

    /**
     * 롤목록 총 갯수를 조회한다.
     * @param infoGroupManage InfoGroupManage
     * @return int
     * @exception Exception
     */
    public int selectInfoGroupListTotCnt(InfoGroupManageVO infoGroupManageVO) throws Exception;



    List<InfoGroupManageVO> getSelectInfoGroupList(InfoGroupManageVO infoGroupManageVO) throws Exception;
}
