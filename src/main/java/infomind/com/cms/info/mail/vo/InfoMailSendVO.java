package infomind.com.cms.info.mail.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InfoMailSendVO extends CmsSearchVO {

    private Integer mailSendId; // MAIL_SEND_ID bigint auto_increment comment '메일 발송 키'
    private String mailFrom; // MAIL_FROM varchar(200) not null comment '메일 전송 대상',
    private String mailTo; // MAIL_TO varchar(200) not null comment '메일 수신 대상',
    private String mailCc; // MAIL_CC varchar(500) null comment '메일 참조 대상',
    private String mailSubject; // MAIL_SUBJECT varchar(255) not null comment '메일 제목',
    private String mailContent; // MAIL_CONTENT text not null comment '메일 내용',
    private Date mailSendDt; // MAIL_SEND_DT datetime null comment '전송일시'
    private String mailSendType;
    private String mailErrorMsg;
}
