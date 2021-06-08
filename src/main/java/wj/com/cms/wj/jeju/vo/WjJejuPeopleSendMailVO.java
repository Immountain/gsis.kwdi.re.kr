package wj.com.cms.wj.jeju.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

import java.util.List;


@Data
public class WjJejuPeopleSendMailVO extends CmsSearchVO {

    private String sendAgentKey;

    private List<WjJejuPeopleVO> sendUserList;

    private String subject;
    private String text;
}

