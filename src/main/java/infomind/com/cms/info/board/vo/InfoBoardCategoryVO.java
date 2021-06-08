package infomind.com.cms.info.board.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoBoardCategoryVO extends CmsSearchVO {

    private Integer boardCategoryId;        // BOARD_CATEGORY_ID NUMBER not null,

    private String boardId;                 // BOARD_ID          VARCHAR2(20),
    private String boardCategoryNm;         // BOARD_CATEGORY_NM VARCHAR2(200),
    private Integer ord;                    // ORD               NUMBER,
    private Integer parentId;               // PARENT_ID         NUMBER,
    private Integer Depth;                  // DEPTH             NUMBER,

    private String useYn;                   // USE_YN            VARCHAR2(20),

    private String regId;                   // REG_ID            VARCHAR2(20),
    private String regDt;                   // REG_DT            DATE,
    private String modId;                   // MOD_ID            VARCHAR2(20),
    private String modDt;                   // MOD_DT            DATE
}
