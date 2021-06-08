package infomind.com.cms.info.banner.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

import java.util.Date;

@Data
public class InfoBannerVO extends CmsSearchVO {
    private String bannerId;        // BANNER_ID       VARCHAR2(20) not null,
    private String bannerGroupId;   // BANNER_GROUP_ID VARCHAR2(20),
    private String bannerNm;        // BANNER_NM       VARCHAR2(200),
    private String bannerTxt;       // BANNER_TXT      VARCHAR2(1000),
    private String bannerImage;     // BANNER_IMAGE    VARCHAR2(20),
    private String styleClass;      // STYLE_CLASS     VARCHAR2(20),
    private Integer sortOrdr;        // SORT_ORDR       NUMBER,
    private String ntceBgnde;       // NTCE_BGNDE      VARCHAR2(20),
    private String ntceEndde;       // NTCE_ENDDE      VARCHAR2(20),
    private String linkType;        // LINK_TYPE       VARCHAR2(20),
    private String linkUrl;         // LINK_URL        VARCHAR2(50),
    private String temp1;           // TEMP1           VARCHAR2(500),
    private String temp2;           // TEMP2           VARCHAR2(500),
    private String temp3;           // TEMP3           VARCHAR2(500),
    private String temp4;           // TEMP4           VARCHAR2(500),
    private String temp5;           // TEMP5           VARCHAR2(500),
    private String useYn;           // USE_YN          VARCHAR2(2),
    private String deleteYn;        // DELETE_YN       VARCHAR2(2),

    private String regId;           // REG_ID          VARCHAR2(20),
    private String regDt;             // REG_DT          DATE,
    private String modId;           // MOD_ID          VARCHAR2(20),
    private String modDt;             // MOD_DT          DATE
    private String fileSn;
    private String bannerGroupNm;
}
