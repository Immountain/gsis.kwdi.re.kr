package infomind.com.cms.info.board.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

import java.util.List;

@Data
public class InfoBoardAuthConfigVO extends CmsSearchVO {

    private String boardId;            // AUTH_TYPE     VARCHAR2(200),
    private String authType;            // AUTH_TYPE     VARCHAR2(200),
    private String authTypeId;          // AUTH_TYPE_ID  VARCHAR2(200),

    private boolean list = false;
    private boolean read = false;
    private boolean write = false;
    private boolean comment = false;
    private boolean reply = false;
    private boolean notice = false;

    private List<InfoBoardAuthVO> auths;
}
