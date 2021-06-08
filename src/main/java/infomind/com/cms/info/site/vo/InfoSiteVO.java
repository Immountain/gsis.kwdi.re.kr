package infomind.com.cms.info.site.vo;


import infomind.com.cmm.InfoConstants;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.ext.vo.CodeSearchVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InfoSiteVO extends CmsSearchVO {

    /**
     * 싸이트아이디
     */
    private String siteId = "";
    /**
     * 호스트명
     */
    private String host = "";
    /**
     * 개발 호스트명
     */
    private String debugHost = "";
    /**
     * 서브패스
     */
    private String subPath = "";
    /**
     * 테마
     */
    private String theme = "";
    /**
     * 메인싸이트여부
     */
    private String mainSiteYn = "";
    /**
     * 인덱스페이지
     */
    private String indexPage = "";
    /**
     * 레이아웃
     */
    private String layout = "";

    private String langCd = "ko";

    /**
     * 사용여부
     */
    private String useYn = "";
    /**
     * 등록자
     */
    private String regId = "";
    /**
     * 등록일
     */
    private String regDt = "";
    /**
     * 수정자
     */
    private String modId = "";
    /**
     * 수정일
     */
    private String modDt = "";
    /**
     * CUSTOM
     */
    private String useYnNm = "";
    private String mainSiteYnNm = "";
    private String fullPath;

    private CodeSearchVO lang;

    public String getFullPath() {
        String path = this.host;
        if(StringUtils.isNotEmpty(this.subPath)) {
            path += "/" + this.subPath;
        }
        return path;
    }

}
