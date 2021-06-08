package infomind.com.cms.info.page.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoPageSatisfactionVO extends CmsSearchVO {

            private String satisfaction_sno="";
            private String siteMemu_id ="";
            private String url="";
            private String title="";
            private String meno="";
            private String score="";
            private String deleteYn="";
            private String regDt="";
            private String regId="";
            private String regIp="";
            private String modDt="";
            private String modId="";
            private String modIp="";
}
