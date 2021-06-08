package infomind.com.cms.info.page.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoPageGroupVO extends CmsSearchVO {

       /** 페이지그룹아이디 */
       private String pageGroupId="";
       /** 페이지그룹명칭 */
       private String pageGroupNm="";
       /** 사용여부 */
       private String useYn="";
       /** 등록일 */
       private String regDt="";
       /** 수정일 */
       private String modDt="";
       /** 등록자 */
       private String regId="";
       /** 수정자 */
       private String modId="";

       /** CUSTOM */
       private String useYnNm="";
}
