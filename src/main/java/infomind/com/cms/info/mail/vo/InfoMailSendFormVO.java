package infomind.com.cms.info.mail.vo;

import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.ext.vo.ComvUserMasterVO;
import lombok.Data;

import java.util.List;


@Data
public class InfoMailSendFormVO extends CmsSearchVO {

    private String sendAgentKey;

    private List<ComvUserMasterVO> sendUserList;

    private String subject;
    private String text;
}

