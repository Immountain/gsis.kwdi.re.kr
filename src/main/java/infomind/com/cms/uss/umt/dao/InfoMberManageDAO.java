package infomind.com.cms.uss.umt.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.umt.service.MberManageVO;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.cms.uss.umt.vo.InfoMberManageVO;
import egovframework.com.uss.umt.service.UserDefaultVO;
import infomind.com.site.vo.SiteJoinVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 일반회원관리에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("InfoMberManageDAO")
public class InfoMberManageDAO extends EgovComAbstractDAO{

    /**
     * 기 등록된 특정 일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param userSearchVO 검색조건
     * @return List<MberManageVO> 기업회원 목록정보
     */
    @SuppressWarnings("unchecked")
	public List<InfoMberManageVO> selectMberList(CmsSearchVO userSearchVO){
        return (List<InfoMberManageVO>) list("InfoMberManageDAO.selectMberList", userSearchVO);
    }

    /**
     * 일반회원 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return int 일반회원총갯수
     */
    public int selectMberListTotCnt(CmsSearchVO userSearchVO) {
        return (Integer)selectOne("InfoMberManageDAO.selectMberListTotCnt", userSearchVO);
    }

    /**
     * 화면에 조회된 일반회원의 정보를 데이터베이스에서 삭제
     * @param delId 삭제 대상 일반회원아이디
     */
    public void deleteMber(String delId){
        delete("InfoMberManageDAO.deleteMber_S", delId);
    }

    /**
     * 일반회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param mberManageVO 일반회원 등록정보
     * @return String 등록결과
     */
    public String insertMber(InfoMberManageVO mberManageVO){
        return String.valueOf((int)insert("InfoMberManageDAO.insertMber_S", mberManageVO));
    }

    /**
     * 기 등록된 사용자 중 검색조건에 맞는일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param mberId 상세조회대상 일반회원아이디
     * @return MberManageVO 일반회원 상세정보
     */
    public InfoMberManageVO selectMber(String mberId){
        return (InfoMberManageVO) selectOne("InfoMberManageDAO.selectMber_S", mberId);
    }

    /**
     * 화면에 조회된일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param mberManageVO 일반회원수정정보
     */
    public void updateMber(InfoMberManageVO mberManageVO){
        update("InfoMberManageDAO.updateMber_S",mberManageVO);
    }

    /**
     * 일반회원 약관확인
     * @param stplatId 일반회원약관아이디
     * @return List 일반회원약관정보
     */
    public List<?> selectStplat(String stplatId){
    	return list("InfoMberManageDAO.selectStplat_S", stplatId);
    }

    /**
     * 일반회원 암호수정
     * @param passVO 기업회원수정정보(비밀번호)
     */
    public void updatePassword(InfoMberManageVO passVO) {
        update("InfoMberManageDAO.updatePassword_S", passVO);
    }

    /**
     * 일반회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param mberManageVO 일반회원암호 조회조건정보
     * @return MberManageVO 일반회원 암호정보
     */
    public InfoMberManageVO selectPassword(InfoMberManageVO mberManageVO){
    	return (InfoMberManageVO) selectOne("InfoMberManageDAO.selectPassword_S", mberManageVO);
    }
    
    
    /**
     * 로그인인증제한 해제
     * @param mberManageVO 일반회원정보
     */
    public void updateLockIncorrect(InfoMberManageVO mberManageVO) {
        update("InfoMberManageDAO.updateLockIncorrect", mberManageVO);
    }

    /**
     * 권한 정보 저장 처리
     * @param mberManageVO
     * @return
     */
    public String insertUserAUthorCode(InfoMberManageVO mberManageVO){
        return String.valueOf((int)insert("SiteMyPageDAO.insertUserAUthorCode", mberManageVO));
    }

}