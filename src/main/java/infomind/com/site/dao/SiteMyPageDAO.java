package infomind.com.site.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.uss.umt.vo.InfoEntrprsManageVO;
import infomind.com.cms.uss.umt.vo.InfoMberManageVO;
import infomind.com.cms.uss.umt.vo.InfoStplatVO;
import infomind.com.site.vo.SiteJoinVO;
import infomind.com.site.vo.SiteUserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SiteMyPageDAO")
public class SiteMyPageDAO extends EgovComAbstractDAO {




    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param userId 중복체크대상 아이디
     * @return int 사용가능여부(아이디 사용회수 )
     */
    public int checkUserId(String userId){
        return selectOne("SiteMyPageDAO.checkUserId", userId);
    }
    public int checkEmial(String email){
        return selectOne("SiteMyPageDAO.checkEmial", email);
    }

    /**
     * 일반회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param mberManageVO 일반회원 등록정보
     * @return String 등록결과
     */
    public String insertMber(InfoMberManageVO mberManageVO){
        return String.valueOf(insert("SiteMyPageDAO.insertMber", mberManageVO));
    }


    /**
     * 일반회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param mberManageVO 일반회원 등록정보
     * @return String 등록결과
     */
    public String insertMberHis(InfoMberManageVO mberManageVO){
        return String.valueOf(insert("SiteMyPageDAO.insertMberHis", mberManageVO));
    }

    /**
     * 기업회원
     * @param mberManageVO
     * @return
     */
    public String insertEntrprs(InfoEntrprsManageVO mberManageVO){
        return String.valueOf(insert("SiteMyPageDAO.insertEntrprs", mberManageVO));
    }





    /**
     * 권한 정보 저장 처리
     * @param mberManageVO
     * @return
     */
    public String insertUserAUthorCode(SiteJoinVO mberManageVO){
        return String.valueOf(insert("SiteMyPageDAO.insertUserAUthorCode", mberManageVO));
    }



    /**
     * 화면에 조회된일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param mberManageVO 일반회원수정정보
     */
    public void updateMber(InfoMberManageVO mberManageVO){
        update("SiteMyPageDAO.updateMber",mberManageVO);
    }

    /**
     * 기업 회원 업데이트
     * @param mberManageVO
     */
    public void updateEntMber(InfoEntrprsManageVO mberManageVO){
        update("SiteMyPageDAO.updateEntMber",mberManageVO);
    }


    /**
     * 일반회원 약관확인
     * @param stplatId 일반회원약관아이디
     * @return List 일반회원약관정보
     */
    public InfoStplatVO selectStplat(String stplatId){
        return selectOne("SiteMyPageDAO.selectStplat", stplatId);
    }

    /**
     * 일반회원 암호수정
     * @param passVO 기업회원수정정보(비밀번호)
     */
    public void updatePassword(InfoMberManageVO passVO) {
        update("SiteMyPageDAO.updatePassword", passVO);
    }





    /**
     * 일반회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param mberManageVO 일반회원암호 조회조건정보
     * @return MberManageVO 일반회원 암호정보
     */
    public InfoMberManageVO selectPassword(InfoMberManageVO mberManageVO){
        return selectOne("SiteMyPageDAO.selectPassword", mberManageVO);
    }


    /**
     * 일반 회원 조회 한다
     */
    public SiteJoinVO selectMber(SiteJoinVO mberManageVO){
        return selectOne("SiteMyPageDAO.selectMber", mberManageVO);
    }
  /**
     * 기업 회원 조회 한다
     */
    public SiteJoinVO selectEntMber(SiteJoinVO mberManageVO){
        return selectOne("SiteMyPageDAO.selectEntMber", mberManageVO);
    }

    //아이디 찾기
    public List<SiteUserVO> getSiteUserIdFind(SiteUserVO searchVO) throws Exception{
        return (List<SiteUserVO>) list("SiteMyPageDAO.getSiteUserIdFind", searchVO);
    }



    //패스워 찾기
    public List<SiteUserVO> getSiteUserPwFind(SiteUserVO searchVO) throws Exception{
        return (List<SiteUserVO>) list("SiteMyPageDAO.getSiteUserPwFind", searchVO);
    }



    //패스워드  변경
    public List<SiteUserVO> getSiteUserPwChangeFind(SiteUserVO searchVO) throws Exception{
        return (List<SiteUserVO>) list("SiteMyPageDAO.getSiteUserPwChangeFind", searchVO);
    }

    //패스워드 변경 기업
    public void getUpdateUserPwEnt(SiteUserVO passVO) {
        update("SiteMyPageDAO.getUpdateUserPwEnt", passVO);
    }


    //패스워드 일반 기업
    public void getUpdateUserPwUsr(SiteUserVO passVO) {
        update("SiteMyPageDAO.getUpdateUserPwUsr", passVO);
    }



    public int getDiCount(String di){

        return selectOne("SiteMyPageDAO.getDiCount", di);
    }


    //사용자정보
    public List<SiteUserVO> getSiteUserAllFind(SiteUserVO searchVO) throws Exception{
        return (List<SiteUserVO>) list("SiteMyPageDAO.getSiteUserAllFind", searchVO);
    }

}
