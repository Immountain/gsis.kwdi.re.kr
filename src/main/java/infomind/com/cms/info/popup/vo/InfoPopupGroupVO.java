package infomind.com.cms.info.popup.vo;


import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoPopupGroupVO extends CmsSearchVO {

        /** 팝업그룹아이디 */
        private String popupGroupId="";
        /** 팝업그룹명 */
        private String popupGroupNm="";
        /** 사용여부 */
        private String useYn="";
        /** 등록자 */
        private String regId="";
        /** 등록일 */
        private String regDt="";
        /** 수정자 */
        private String modId="";
        /** 수정일 */
        private String modDt="";
        /** CUSTOM */
        private String useYnNm="";

}
