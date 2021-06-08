package infomind.com.cms.info.banner.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoBannerGroupVO extends CmsSearchVO {
    private String bannerGroupId;       //BANNER_GROUP_ID    VARCHAR2(20) not null,
    private String bannerGroupNm;       //BANNER_GROUP_NM    VARCHAR2(200),
    private String bannerGroupTxt;      //BANNER_GROUP_TXT   VARCHAR2(1000),
    private String bannerGroupImage;    //BANNER_GROUP_IMAGE VARCHAR2(20),
    private String styleClass;          //STYLE_CLASS        VARCHAR2(20),
    private String useYn;               //USE_YN             VARCHAR2(2),
    private String deleteYn;            //DELETE_YN          VARCHAR2(2),
    private String temp1;               //TEMP1              VARCHAR2(500),
    private String temp2;               //TEMP2              VARCHAR2(500),
    private String temp3;               //TEMP3              VARCHAR2(500),
    private String temp4;               //TEMP4              VARCHAR2(500),
    private String temp5;               //TEMP5              VARCHAR2(500),

    private String regId;               //REG_ID             VARCHAR2(20),
    private String regDt;               //REG_DT             DATE,
    private String modId;               //MOD_ID             VARCHAR2(20),
    private String modDt;               //MOD_DT             DATE
}
