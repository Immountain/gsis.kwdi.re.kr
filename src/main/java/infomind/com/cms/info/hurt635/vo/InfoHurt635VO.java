package infomind.com.cms.info.hurt635.vo;


import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoHurt635VO extends CmsSearchVO {
        /** 새 컬럼 */
        private String keySeq = "";
        /** 허용여부 */
        private String allwYn = "";
        /** 아이피 1 */
        private String ip1 = "";
        /** 아이피 2 */
        private String ip2 = "";
        /** 아이피 3 */
        private String ip3 = "";
        /** 아이피 4 */
        private String ip4 = "";
        /** 비고 */
        private String remk="";
        /** 작성자아이디 */
        private String regId = "";
        /** 작성일 */
        private String regDt = "";
        /** 작성자아이피 */
        private String regIp = "";
        /** 수정자아이디 */
        private String modId = "";
        /** 수정일 */
        private String modDt = "";
        /** 수정자아이피 */
        private String modIp = "";

        /** CUSTOM */
        private String allwYnNm="";

}
