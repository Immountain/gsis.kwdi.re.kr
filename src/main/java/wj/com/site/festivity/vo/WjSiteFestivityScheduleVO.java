package wj.com.site.festivity.vo;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

@Data
@ToString
public class WjSiteFestivityScheduleVO {

    private String festivityId = "";
    private String scheduleSno = "";
    private String scheduleDay = "";
    private String scheduleStrTime = "";
    private String scheduleEndTime = "";
    private String title = "";
    private String meo = "";
    private String orderCnt = "";
    private String langCode = "";
    private String scheduleTitle = "";
    private String scheduleProgram = "";
    private String schedulePlace = "";
}
