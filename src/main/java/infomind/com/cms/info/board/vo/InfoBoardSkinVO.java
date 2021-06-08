package infomind.com.cms.info.board.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoBoardSkinVO extends CmsSearchVO {
    private String boardSkinId; // BOARD_SKIN_ID VARCHAR2(20) not null,
    private String boardSkinNm; // BOARD_SKIN_NM VARCHAR2(50),
    private String skinType;    // SKIN_TYPE     VARCHAR2(20),
    private String styleClass;  // STYLE_CLASS   VARCHAR2(50),
    private String cssUrl;  // CSS_URL       VARCHAR2(500),
    private String useYn;   // USE_YN        VARCHAR2(2),
    private String deleteYn;    // DELETE_YN     VARCHAR2(2),

    private String regId;   // REG_ID        VARCHAR2(20),
    private String regDt;   // REG_DT        DATE,
    private String modId;   // MOD_ID        VARCHAR2(20),
    private String modDt;   // MOD_DT        DATE


    private String listClass;   // 리스트 스타일
    private String viewClass;   // 상세 스타
    private String writeClass;   //글쓰기 스타일



}
