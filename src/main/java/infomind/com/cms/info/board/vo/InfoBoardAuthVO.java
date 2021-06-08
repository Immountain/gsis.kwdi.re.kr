package infomind.com.cms.info.board.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class InfoBoardAuthVO extends CmsSearchVO {
    public InfoBoardAuthVO() { }

    public InfoBoardAuthVO(InfoBoardAuthConfigVO infoBoardAuthConfigVO, Integer boardAuthId, String authAccess) {
        this.boardAuthId = boardAuthId;
        this.boardId = infoBoardAuthConfigVO.getBoardId();
        this.authType = infoBoardAuthConfigVO.getAuthType();
        this.authTypeId = infoBoardAuthConfigVO.getAuthTypeId();
        this.authAccess = authAccess;
    }
    private Integer boardAuthId;        // BOARD_AUTH_ID NUMBER not null,
    private String boardId;             // BOARD_ID      VARCHAR2(20),
    private String authType;            // AUTH_TYPE     VARCHAR2(200),
    private String authTypeId;          // AUTH_TYPE_ID  VARCHAR2(200),
    private String authAccess;          // AUTH_ACCESS   VARCHAR2(200),
    private String regId;               // REG_ID        VARCHAR2(20),
    private String regDt;               // REG_DT        DATE
    private String itemId;               // 게시판 아이디


    //권한
    private int commentyn=0;
    private int listYn=1;
    private int readYn=1;
    private int replyYn=0;
    private int writeYn=0;
    private int noticeYn=0;
    private int modifyYn=0;

    //권한 타입
    private String group;
    private String commonLogin ="common_login";
    private String commonNotLogin ="common_not_login";
    private String orgnzt;


}
