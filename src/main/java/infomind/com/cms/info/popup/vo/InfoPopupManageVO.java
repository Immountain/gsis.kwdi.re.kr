package infomind.com.cms.info.popup.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoPopupManageVO extends CmsSearchVO {
    private String popupId;             // POPUP_ID          VARCHAR2(20) not null,
    private String popupNm;             // POPUP_NM          VARCHAR2(200),
    private String popupTxt;            // POPUP_TXT         VARCHAR2(1000),
    private String popupImage;          // POPUP_IMAGE       VARCHAR2(20),
    private String popupWidthLc;        // POPUP_WIDTH_LC    VARCHAR2(20),
    private Integer popupWidthSize;      // POPUP_WIDTH_SIZE  NUMBER,
    private String ntceBgnde;           // NTCE_BGNDE        VARCHAR2(20),
    private String ntceEndde;           // NTCE_ENDDE        VARCHAR2(20),
    private String stopvewSetupAt;      // STOPVEW_SETUP_AT  VARCHAR2(2),
    private String ntceAt;              // NTCE_AT           VARCHAR2(2),
    private String popupVrticlLc;       // POPUP_VRTICL_LC   VARCHAR2(20),
    private Integer popupVrticlSize;     // POPUP_VRTICL_SIZE NUMBER,
    private String styleClass;          // STYLE_CLASS       VARCHAR2(20),
    private String linkType;            // LINK_TYPE         VARCHAR2(20),
    private String linkUrl;             // LINK_URL          VARCHAR2(50),
    private String temp1;               // TEMP1             VARCHAR2(500),
    private String temp2;               // TEMP2             VARCHAR2(500),
    private String temp3;               // TEMP3             VARCHAR2(500),
    private String temp4;               // TEMP4             VARCHAR2(500),
    private String temp5;               // TEMP5             VARCHAR2(500),

    private String regId;               // REG_ID            VARCHAR2(20),
    private String regDt;               // REG_DT            DATE,
    private String modId;               // MOD_ID            VARCHAR2(20),
    private String modDt;               // MOD_DT            DATE


    private String ntceBgndeTime;               // MOD_ID            VARCHAR2(20),
    private String ntceEnddeTime;               // MOD_DT            DATE


    private String strHour="";
    private String strMin="";

    private String endHour="";
    private String endMin="";
}
