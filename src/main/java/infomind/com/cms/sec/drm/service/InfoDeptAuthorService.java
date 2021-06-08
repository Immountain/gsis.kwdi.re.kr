package infomind.com.cms.sec.drm.service;

import infomind.com.cms.sec.drm.vo.InfoDeptAuthor;
import infomind.com.cms.sec.drm.vo.InfoDeptAuthorVO;

import java.util.List;

public interface InfoDeptAuthorService {
     List<InfoDeptAuthorVO> selectInfoDeptAuthorList(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception;

    /**
     * 부서에 해당하는 사용자에게 시스템 메뉴/접근권한을 일괄 할당
     * @param infoDeptAuthor InfoDeptAuthor
     * @exception Exception
     */
     void insertInfoDeptAuthor(InfoDeptAuthor infoDeptAuthor) throws Exception;

    /**
     * 부서별 시스템 메뉴 접근권한을 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param infoDeptAuthor InfoDeptAuthor
     * @exception Exception
     */
     void updateInfoDeptAuthor(InfoDeptAuthor infoDeptAuthor) throws Exception;

    /**
     * 불필요한 부서권한를 조회하여 데이터베이스에서 삭제
     * @param infoDeptAuthor InfoDeptAuthor
     * @exception Exception
     */
     void deleteInfoDeptAuthor(InfoDeptAuthor infoDeptAuthor) throws Exception;

    /**
     * 부서권한 목록조회 카운트를 반환한다
     * @param infoDeptAuthorVO InfoDeptAuthorVO
     * @return int
     * @exception Exception
     */
     int selectInfoDeptAuthorListTotCnt(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception;

    /**
     * 부서목록 조회
     * @param infoDeptAuthorVO infoDeptAuthorVOinfoDeptAuthorVO
     * @return List<DeptAuthorVO>
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
     List<InfoDeptAuthorVO> selectInfoDeptList(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception;

    /**
     * 부서목록 조회 카운트를 반환한다
     * @param infoDeptAuthorVO InfoDeptAuthorVO
     * @return int
     * @exception Exception
     */
    public int selectInfoDeptListTotCnt(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception;

}
