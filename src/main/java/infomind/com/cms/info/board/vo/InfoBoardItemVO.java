package infomind.com.cms.info.board.vo;

import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.file.vo.InfoFileDetailVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static infomind.com.utils.web.ReplaceTag.ReplaceTag;

@Data
public class InfoBoardItemVO extends CmsSearchVO {


    /** 기본키 */
    private String itemId ="";
    /** 암호화키 */
    private String idCode="";
    /** 게시판아이디 */
    private String boardId="";
    /** 제목 */
    private String title="";
    /** 내용 */
    private String boardContent="";
    /** 메모 */
    private String memo="";
    /** 공지여부 */
    private String noticeYn="";
    /** 공지 게시일 */
    private String noticeStartDate="";
    /** 공지 종료일 */
    private String noticeEndDate="";
    /** 비밀글여부 */
    private String secretYn="";
    /** 비밀번호 */
    private String password="";
    /** 문의 타입 */
    private String inquireType="";
    /** 답변여부 */
    private String commentYn="";
    /** 파일1 */
    private String file01="";
    /** 파일2 */
    private String file02="";
    /** 파일3 */
    private String file03="";
    /** 파일4 */
    private String file04="";
    /** 파일5 */
    private String file05="";
    /** 카테고리 값 */
    private String category="";
    /** 카테고리 키 */
    private String categories="";
    /** 링크타입 */
    private String linkType="";
    /** 링크URL */
    private String linkUrl="";
    /** 조회수 */
    private int readCnt=0;
    /** 임시필드1 */
    private String temp1="";
    /** 임시필드2 */
    private String temp2="";
    /** 임시필드3 */
    private String temp3="";
    /** 임시필드4 */
    private String temp4="";
    /** 임시필드5 */
    private String temp5="";
    /** 등록자 */
    private String regId="";
    /** 등록일 */
    private String regDt="";
    /** 등록자아이피 */
    private String regIp="";
    /** 수정자아이디 */
    private String modId="";
    /** 수정일 */
    private String modDt="";
    /** 수정자아이피 */
    private String modIp="";
    /** 해당 게시판 파일 업로드(관리자용) */
    private String boardFile="";

    /** 작성년도 */
    private String regDtYyyy="";
    /** 작성월 */
    private String regDtMm="";
    /** 작성일 */
    private String regDtDd="";
    /** 작성자명 */
    private String regNm="";
    /** 파일수 */
    private int fileCnt=0;


    /** 삭제타입  A:관리자*/
    private String deleteType="";
    /** 삭제여부 */
    private String deleteYn="N";


    /** 사용여부 */
    private String useYn="";


    /** 서브 키 */
    private String subKey="";

    /**공지 시작년도*/
    private String noticeStartYyyy ="";
    /**공지 시작월*/
    private String noticeStartMm="";
    /**공지 시작일*/
    private String noticeStartDd="";
    /**공지 시작시간*/
    private String noticeStartHour="";
    /**공지 시작분*/
    private String noticeStartMin="";
    /**공지 종료년도*/
    private String noticeEndYyyy="";
    /**공지 종료월*/
    private String noticeEndMm="";
    /**공지 종료일*/
    private String noticeEndDd="";
    /**공지 종료시간*/
    private String noticeEndHour="";
    /**공지 종료분*/
    private String noticeEndMin="";
    /**공지 시작 종료 체크*/
    private String checkDayYn="";

    /**카테고리명*/
    private String categoryNm="";

    //히스토리 아이디
    private String itemIdHis="";
    //등록/수정타입
    private String modType="";




    public String getNoticeStartDay(){

        String strDay ="";
        strDay =this.noticeStartYyyy+"-"+this.noticeStartMm+"-"+this.noticeStartDd;
        return  strDay;
    }

    public String getNoticeEndDay(){

        String strDay ="";
        strDay =this.noticeEndYyyy+"-"+this.noticeEndMm+"-"+this.noticeEndDd;
        return  strDay;
    }





//    private List<InfoFileDetailVO>boardFileList = new ArrayList<>();


    public String getContentInfoDecode(){

        String tempString =this.boardContent;
        tempString=ReplaceTag(tempString,"decode");

        return tempString;
    }

}
