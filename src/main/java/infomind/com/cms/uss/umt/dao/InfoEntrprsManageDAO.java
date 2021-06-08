package infomind.com.cms.uss.umt.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.uss.umt.vo.InfoEntrprsManageVO;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.site.vo.SiteJoinVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 기업회원관리에 관한 데이터 접근 클래스를 정의한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2017.07.21  장동한 			로그인인증제한 작업
 *
 * </pre>
 */
@Repository("InfoEntrprsManageDAO")
public class InfoEntrprsManageDAO extends EgovComAbstractDAO{

    /**
     * 화면에 조회된 기업회원의 정보를 데이터베이스에서 삭제
     * @param delId
     */
    public void deleteEntrprsmber(String delId){
        delete("InfoEntrprsManageDAO.deleteEntrprs_S", delId);
    }

    /**
     * 기업회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param entrprsManageVO 기업회원 등록정보
     * @return String 등록결과
     */
    public String insertEntrprsmber(InfoEntrprsManageVO entrprsManageVO){
        return String.valueOf((int)insert("InfoEntrprsManageDAO.insertEntrprs_S", entrprsManageVO));
    }

    /**
     * 기 등록된 사용자 중 검색조건에 맞는 기업회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param entrprsmberId 상세조회대상 기업회원아이디
     * @return EntrprsManageVO 기업회원 상세정보
     */
    public InfoEntrprsManageVO selectEntrprsmber(String entrprsmberId){
        return (InfoEntrprsManageVO) selectOne("InfoEntrprsManageDAO.selectEntrprs_S", entrprsmberId);
    }

    /**
     * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param entrprsManageVO 기업회원 수정정보
     */
    public void updateEntrprsmber(InfoEntrprsManageVO entrprsManageVO){
        update("InfoEntrprsManageDAO.updateEntrprs_S",entrprsManageVO);
    }

    /**
     * 약관정보를 조회
     * @param stplatId 기업회원 약관아이디
     * @return List 기업회원약관정보
     */
    public List<?> selectStplat(String stplatId) {
    	return list("InfoEntrprsManageDAO.selectStplat_S", stplatId);
    }

    /**
     * 기업회원 암호수정
     * @param passVO 기업회원수정정보(비밀번호)
     */
    public void updatePassword(InfoEntrprsManageVO passVO) {
    	update("InfoEntrprsManageDAO.updatePassword_S", passVO);
    }

    /**
     * 기업회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param entrprsManageVO 기업회원암호 조회조건정보
     * @return EntrprsManageVO 기업회원암호정보
     */
    public InfoEntrprsManageVO selectPassword(InfoEntrprsManageVO entrprsManageVO){
    	return (InfoEntrprsManageVO) selectOne("InfoEntrprsManageDAO.selectPassword_S", entrprsManageVO);
    }

    /**
     * 기 등록된 특정 기업회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param userSearchVO 검색조건
     * @return List<EntrprsManageVO>
     */
    @SuppressWarnings("unchecked")
	public List<InfoEntrprsManageVO> selectEntrprsMberList(CmsSearchVO userSearchVO){
        return (List<InfoEntrprsManageVO>) list("InfoEntrprsManageDAO.selectEntrprsMberList", userSearchVO);
    }
    /**
     * 기업회원 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return int 기업회원총갯수
     */
    public int selectEntrprsMberListTotCnt(CmsSearchVO userSearchVO) {
        return (Integer)selectOne("InfoEntrprsManageDAO.selectEntrprsMberListTotCnt", userSearchVO);
    }
    
    
    /**
     * 로그인인증제한 해제
     * @param entrprsManageVO 기업회원정보
     */
    public void updateLockIncorrect(InfoEntrprsManageVO entrprsManageVO) {
        update("InfoEntrprsManageDAO.updateLockIncorrect", entrprsManageVO);
    }


    /**
     * 기업회원 찾기 팝업에서 회원조회
     * @param userSearchVO 검색조건
     * @return List<EntrprsManageVO>
     */
    @SuppressWarnings("unchecked")
    public List<InfoEntrprsManageVO> selectEntrprsMberPopupList(CmsSearchVO userSearchVO) {
        return this.selectList("InfoEntrprsManageDAO.selectEntrprsMberPopupList", userSearchVO);
    }


    /**
     * 기업회원 찾기 팝업에서의 회원 총 건수
     * @param userSearchVO 검색조건
     * @return int 기업회원총갯수
     */
    public int selectEntrprsMberPopupListTotCnt(CmsSearchVO userSearchVO) {
        return (Integer)selectOne("InfoEntrprsManageDAO.selectEntrprsMberPopupListTotCnt", userSearchVO);
    }

    /**
     * 권한 정보 저장 처리
     * @param mberManageVO
     * @return
     */
    public String insertUserAUthorCode(InfoEntrprsManageVO mberManageVO){
        return String.valueOf((int)insert("InfoEntrprsManageDAO.insertUserAUthorCode", mberManageVO));
    }

}