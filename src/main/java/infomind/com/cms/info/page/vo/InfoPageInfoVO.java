package infomind.com.cms.info.page.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static infomind.com.utils.web.ReplaceTag.ReplaceTag;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InfoPageInfoVO extends CmsSearchVO {

    private String pageId = "";
    private String layoutId = "";
    private String pageNm = "";
    private String pageUrl = "";
    private String contentInfo = "";
    private String modType = "";

    private String useYn = "";
    private String deleteYn = "";
    private String regDt = "";
    private String modDt = "";
    private String regId = "";
    private String modId = "";
    private String modGb = "";
    private String pageImage = "";

    private String pageGroupId = "";
    private String pageType = "";
    private String pageGroupNm = "";
    private String includeId = "";


    private int pageHisSno = 0;


    public String getContentInfoDecode() {

        String tempString = this.contentInfo;
        tempString = ReplaceTag(tempString, "decode");

        return tempString;
    }
}
