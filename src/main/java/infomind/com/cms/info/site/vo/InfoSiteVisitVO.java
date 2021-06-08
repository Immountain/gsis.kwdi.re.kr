package infomind.com.cms.info.site.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InfoSiteVisitVO extends CmsSearchVO {
    /**
     * 아이디
     */
    private int id = 0;
    /**
     * 아이피
     */
    private String ip = "";
    /**
     * 방문일
     */
    private String tempVdate;
    /**
     * OS
     */
    private String os = "";
    /**
     * 브라우저
     */
    private String browser = "";
    /**
     * 유저에이전트
     */
    private String userAgent = "";
    /**
     * 싸이트아이디
     */
    private String siteId = "";
    /**
     * 타켓브라우저
     */
    private String browserS = "";
    /**
     * 위치
     */
    private String loc = "";
    /**
     * 방문타입
     */
    private String visitType = "";
    /**
     * 페이지타입
     */
    private String pageType = "";
    /**
     * 타켓아이디
     */
    private String targetId = "";
    /**
     * 방문URL
     */
    private String siteUrl = "";
    /**
     * 이전URL
     */
    private String refererUrl = "";
    /**
     * 디바이스타입
     */
    private String isDeviec = "";

}
