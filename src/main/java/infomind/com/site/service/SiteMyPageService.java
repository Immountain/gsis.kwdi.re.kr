package infomind.com.site.service;

import infomind.com.cms.uss.umt.vo.InfoMberManageVO;
import infomind.com.cms.uss.umt.vo.InfoStplatVO;
import infomind.com.site.vo.SiteJoinVO;
import infomind.com.site.vo.SiteUserVO;

import java.util.List;


public interface SiteMyPageService {





    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param checkId 중복체크대상 아이디
     * @return int 사용가능여부(아이디 사용회수 )
     */
    int checkUserId(String userId);

    int checkEmail(String userId);

    /**
     * 일반회원의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param mberManageVO 일반회원 등록정보
     * @return String 등록결과
     */
    String insertMber(SiteJoinVO mberManageVO)throws Exception;

    /**
     * 화면에 조회된일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param mberManageVO 일반회원수정정보
     */
    void updateMber(SiteJoinVO mberManageVO)throws Exception;

    /**
     * 일반회원 약관확인
     * @param stplatId 일반회원약관아이디
     * @return List 일반회원약관정보
     */
    InfoStplatVO selectStplat(String stplatId);

    /**
     * 일반회원 암호수정
     * @param passVO 기업회원수정정보(비밀번호)
     */
    void updatePassword(InfoMberManageVO passVO);

    /**
     * 일반회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param mberManageVO 일반회원암호 조회조건정보
     * @return MberManageVO 일반회원 암호정보
     */
    InfoMberManageVO selectPassword(InfoMberManageVO mberManageVO);



    /**
     * 일반 회원 조회 한다
     */
    SiteJoinVO selectMber(SiteJoinVO mberManageVO);


    //아이디 찾기
     List<SiteUserVO> getSiteUserIdFind(SiteUserVO searchVO) throws Exception;


    //패스워 찾기
     List<SiteUserVO> getSiteUserPwFind(SiteUserVO searchVO) throws Exception;



    //패스워드  변경
     List<SiteUserVO> getSiteUserPwChangeFind(SiteUserVO searchVO) throws Exception;

    //패스워드 변경
    void getUserPw(SiteUserVO passVO) ;

    //본인인증 di 값 체크
    int getDiCount(String di);



    //사용자정보
    List<SiteUserVO> getSiteUserAllFind(SiteUserVO searchVO) throws Exception;

}
