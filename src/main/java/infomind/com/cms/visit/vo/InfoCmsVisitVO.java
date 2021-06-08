package infomind.com.cms.visit.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoCmsVisitVO extends CmsSearchVO {

    /** 아이디 자동증가 */
    private String id;
    /** 아이피 */
    private String ip;
    /** 방문일 */
    private String vDate;
    /** OS */
    private String os;
    /** 브라우저 */
    private String browser;
    /** 유저에이전트 */
    private String userAgent;
    /** 방문유저 */
    private String vistUserId;
    /** 메뉴 아이디 */
    private String cmsMenuTargetNo;
    /** 타켓브라우저 */
    private String browserS;
    /** 위치 */
    private String loc;
    /** 방문타입 */
    private String visitType;
    /** 타켓아이디 */
    private String targetId;
    /** 방문URL */
    private String siteUrl;
    /** 이전URL */
    private String refererUrl;
    /** 디바이스타일 */
    private String isDeviec;

}
