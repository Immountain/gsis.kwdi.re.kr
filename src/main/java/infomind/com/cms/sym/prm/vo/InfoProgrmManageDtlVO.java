package infomind.com.cms.sym.prm.vo;

import lombok.Data;

@Data
public class InfoProgrmManageDtlVO {


    /** 프로그램파일명 */
    private String progrmFileNm;
    /** 요청번호 */
    private int rqesterNo;
    /** 요청제목	 */
    private String rqesterSj;
    /** 요청자ID */
    private String rqesterPersonId;
    /** 요청일자 */
    private String rqesterDe;
    /** 변경요청내용	 */
    private String changerqesterCn;
    /** 처리자ID	 */
    private String opetrId;
    /** 처리상태코드	 */
    private String processSttus;
    /** 처리일자	 */
    private String processDe;
    /** 요청처리내용 */
    private String rqesterProcessCn;

    /** 요청시작일자 */
    private String rqesterDeBegin;
    /** 요청종료일자 */
    private String rqesterDeEnd;

    /** 프로그램파일명 */
    private String tmpProgrmNm;
    /** 요청번호 */
    private int tmpRqesterNo;
    /** tmp_Email */
    private   String   tmpEmail;
}
