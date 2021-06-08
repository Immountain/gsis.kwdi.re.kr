package infomind.com.sms.vo;

import lombok.Data;


@Data
public class InfoSmsInfoVO {
    /**
     * 문자인증아이디
     */
    private String smsInfoId ="";
    /**
     * 문자인증번호
     */
    private String smsNum="";
    /**
     * 문자구분
     */
    private String smsGb="";
    /**
     * 문자타입
     */
    private String smsType="";
    /**
     * 문자내역
     */
    private String smsMsg="";

    /**
     * 확인여부
     */
    private String smsCheckYn="";
    /**
     * 등록일
     */
    private String regDt="";
    /**
     * 등록아이피
     */
    private String regIp="";
    /**
     * 수정일
     */
    private String modDt="";
    /**
     * 수정아이피
     */
    private String modIp="";
}
