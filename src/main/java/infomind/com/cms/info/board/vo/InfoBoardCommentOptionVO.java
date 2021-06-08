package infomind.com.cms.info.board.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InfoBoardCommentOptionVO extends CmsSearchVO {
    private String boardId;// BOARD_ID     VARCHAR2(20) not null,
    private String titleUseYn;// TITLE_USE_YN VARCHAR2(2),
    private String txtUsrYn;// TXT_USR_YN   VARCHAR2(2),
    private String pwUseYn;// PW_USE_YN    VARCHAR2(2),
    private String fileUsrYn;// FILE_USR_YN  VARCHAR2(2),
    private String scoreYn;// SCORE_YN     VARCHAR2(2),
    private String secretYn;// SECRET_YN    VARCHAR2(2),
    private String regId;// REG_ID       VARCHAR2(20),
    private String regDt;// REG_DT       DATE,
    private String modId;// MOD_ID       VARCHAR2(20),
    private String modDt;// MOD_DT       DATE,
    private String commentType;// COMMENT_TYPE VARCHAR2(5)
}
