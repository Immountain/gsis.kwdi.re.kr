package infomind.com.cms.info.site.vo;

import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.ext.vo.CodeSearchVO;
import lombok.Data;


@Data
public class InfoSiteMenuGroupVO extends CmsSearchVO {

    /**
     * 메뉴그룹아이디
     */
    private String menuGroupId = "";
    /**
     * 그룹명
     */
    private String menuGroupNm = "";
    /**
     * 사용여부
     */
    private String useYn = "";
    /**
     * 등록일
     */
    private String regDt = "";
    /**
     * 등록자아이디
     */
    private String regId = "";
    /**
     * 수정일
     */
    private String modDt = "";
    /**
     * 수정자아이디
     */
    private String modId = "";
    /**
     * 언어
     */
    private String menuGroupLocale = "";
    /**
     * SiteId
     */
    private String siteId = "";


    private InfoSiteVO infoSite;
    private CodeSearchVO lang;
}
