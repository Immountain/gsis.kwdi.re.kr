package wj.com.cms.wj.festivity.vo;


import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WjFestivityLangVO extends CmsSearchVO {

    public WjFestivityLangVO() {
    }

    public WjFestivityLangVO(String festivityId) {
        this.festivityId = festivityId;
    }

    public WjFestivityLangVO(String festivityId, String langCode) {
        this.festivityId = festivityId;
        this.langCode = langCode;
    }

    /** 대회아이디 */
    private String festivityId;
    /** 언어코드 */
    private String langCode;

    /** 언어코드한글 */
    private String langCodeNm;

    /** 장소 */
    private String festivityPlace;
    /** 참가규모 */
    private String festivityScale;
    /** 주제 */
    private String festivitySubject;
    /** 주최 */
    private String festivityHost;
    /** 주관 */
    private String festivitySupervises;
    /** 주요추진상황 */
    private String festivitySituation;
    /** 임시필드1 */
    private String temp1;
    /** 임시필드2 */
    private String temp2;
    /** 임시필드3 */
    private String temp3;
    /** 임시필드4 */
    private String temp4;
    /** 임시필드5 */
    private String temp5;
    /** 등록자 */
    private String regId;
    /** 등록일 */
    private String regDt;
    /** 수정자 */
    private String modId;
    /** 수정일 */
    private String modDt;

}
