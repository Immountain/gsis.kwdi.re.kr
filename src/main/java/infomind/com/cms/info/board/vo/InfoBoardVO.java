package infomind.com.cms.info.board.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InfoBoardVO extends CmsSearchVO {

    private String boardId; // BOARD_ID is '게시판아이디' VARCHAR2(20) not null,

    private String boardSkinId; // BOARD_SKIN_ID is '게시판스킨아이디'  VARCHAR2(20),
    private String boardNm; // BOARD_NM is '게시판 명'  VARCHAR2(50),
    private String boardTxt; // BOARD_TXT is '게시판 내용'    VARCHAR2(500),
    private String useComment; // USE_COMMENT is '댓글 사용여부' VARCHAR2(2),
    private String useReply; // USE_REPLY is '답글 사용여부'   VARCHAR2(2),
    private String useNotice; // USE_NOTICE is '공지기능 사용여부'    VARCHAR2(2),
    private String Categories; // CATEGORIES is '카테고리 목록'  VARCHAR2(20),
    private Integer listRow; // LIST_ROW is '게시판 한페이지 보여주는 갯수'   NUMBER,
    private String loginUserListYn; // LOGIN_USER_LIST_YN is '자신쓴글만 보여주는 여부'    VARCHAR2(2),
    private String wrSecretYn; // WR_SECRET_YN is '비밀글여부'  VARCHAR2(2),
    private String secretPwYn; // SECRET_PW_YN is '패스워드 입력 여부' VARCHAR2(2),
    private String fileAtchPosblAt; // FILE_ATCH_POSBL_AT is '파일첨부가능여부' VARCHAR2(2),
    private Integer atchPosblFileNumber; // ATCH_POSBL_FILE_NUMBER is '첨부가능파일숫자' NUMBER,
    private Integer atchPosblFileSize; // ATCH_POSBL_FILE_SIZE is '첨부가능파일사이즈'  NUMBER,
    private String stsfdgAt; // STSFDG_AT is '만족도여부' VARCHAR2(2),
    private String inquireYn; // INQUIRE_YN is '문의여부' VARCHAR2(2),
    private String boardUseUser; // BOARD_USE_USER is '담당자'  VARCHAR2(200),

    private String useYn; // USE_YN is '사용여부' VARCHAR2(2),

    private String temp1; // TEMP1 is '임시필드1' VARCHAR2(500),
    private String temp2; // TEMP2 is '임시필드2' VARCHAR2(500),
    private String temp3; // TEMP3 is '임시필드3' VARCHAR2(500),
    private String temp4; // TEMP4 is '임시필드4' VARCHAR2(500),
    private String temp5; // TEMP5 is '임시필드5' VARCHAR2(500),

    private String regId; // REG_ID is '등록자'  VARCHAR2(20),
    private String regDt; // REG_DT is '등록일'  DATE,
    private String modId; // MOD_ID is '수정자'  VARCHAR2(20),
    private String modDt; // MOD_DT is '수정일'  DATE


    private String skinType ="";
    private String styleClass="";
    private String cssUrl   ="";


    private String boardListType   ="";

    private String listClass   ="";
    private String viewClass   ="";
    private String writeClass   ="";


    private String tempStrDay   ="";
    private String tempEndDay   ="";

    /**
     * 서브키 사용여부
     */
    private String subKeyYn   ="";



    private InfoBoardAuthVO boardAuth = new InfoBoardAuthVO();



    public String getTempStrDay(){

        this.tempStrDay = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

        return this.tempStrDay;
    }

    public String getTempEndDay(){

        this.tempEndDay = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

        return this.tempEndDay;
    }





    @Override
    public String getLayoutCssUrl() {
        String addLayout ="";

        if(!"".equals(this.skinType)||this.skinType !=null){

            if("BASIC".equals(this.skinType)){

                addLayout=  "/board/basic/css/board.css";
            }else if("URL".equals(this.skinType)){
                addLayout=  this.cssUrl;
             }else if("BOARD".equals(this.skinType)){
                addLayout=  "/board/"+this.boardId+"/css/"+this.boardId+".css";
            }
        }else{
            addLayout ="";

        }
        return  addLayout;
    }


}
