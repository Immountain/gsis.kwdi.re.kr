package gsis.com.cms.banner.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JewBannerVO extends CmsSearchVO {


    private String bannerSno ="0";
    private String bannerType="";
    private String bannerNm="";
    private String bannerMemo="";
    private String bannerTxt="";
    private String bannerImage="";
    private String sortOrdr="";
    private String strDt="";
    private String endDt="";
    private String linkType="";
    private String linkUrl="";
    private String useYn="";
    private String deleteYn="";
    private String regId="";
    private String regDt="";
    private String modId="";
    private String modDt="";
    private String mobileBannerImage="";




}
