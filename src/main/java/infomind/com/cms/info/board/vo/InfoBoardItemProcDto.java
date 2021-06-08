package infomind.com.cms.info.board.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

import java.util.List;

import static infomind.com.utils.web.ReplaceTag.ReplaceTag;

@Data
public class InfoBoardItemProcDto extends InfoBoardItemVO {

    private String targetBoardId;
    private String cmd;
    private List<InfoBoardItemVO> list;
}
