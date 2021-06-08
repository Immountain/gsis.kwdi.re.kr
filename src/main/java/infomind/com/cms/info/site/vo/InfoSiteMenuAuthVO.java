package infomind.com.cms.info.site.vo;


import infomind.com.cms.info.board.vo.InfoBoardAuthConfigVO;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class InfoSiteMenuAuthVO extends CmsSearchVO {

    public InfoSiteMenuAuthVO() {}
    public InfoSiteMenuAuthVO(InfoSiteMenuAuthConfigVO infoSiteMenuAuthConfigVO, Integer memuAuthId, String authAccess) {
        this.memuAuthId = memuAuthId;
        this.siteMemuId = infoSiteMenuAuthConfigVO.getSiteMemuId();
        this.authType = infoSiteMenuAuthConfigVO.getAuthType();
        this.authTypeId = infoSiteMenuAuthConfigVO.getAuthTypeId();
        this.authAccess = authAccess;
    }
    /**
     * 게시판권한아이디
     */
    private Integer memuAuthId;
    /**
     * 메뉴아이디
     */
    private String siteMemuId = "";
    /**
     * 권한타입
     */
    private String authType = "";
    /**
     * 권한 부여아이디
     */
    private String authTypeId = "";
    /**
     * 권한요소
     */
    private String authAccess = "";
    /**
     * 등록자
     */
    private String regId = "";
    /**
     * 등록일
     */
    private String regDt = "";


}
