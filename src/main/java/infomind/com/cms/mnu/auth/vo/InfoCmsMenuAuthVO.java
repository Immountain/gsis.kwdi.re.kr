package infomind.com.cms.mnu.auth.vo;

import infomind.com.cms.info.site.vo.InfoSiteMenuAuthConfigVO;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoCmsMenuAuthVO extends CmsSearchVO {

    public InfoCmsMenuAuthVO(){}
    public InfoCmsMenuAuthVO(InfoCmsMenuAuthConfigVO infoCmsMenuAuthConfigVO, Integer memuAuthId, String authAccess) {
        this.cmsMemuAuthId = memuAuthId;
        this.cmsMemuId = infoCmsMenuAuthConfigVO.getCmsMemuId();
        this.authType = infoCmsMenuAuthConfigVO.getAuthType();
        this.authTypeId = infoCmsMenuAuthConfigVO.getAuthTypeId();
        this.authAccess = authAccess;
    }

    private int    cmsMemuAuthId = 0;
    private String cmsMemuId = "";
    private String authType = "";
    private String authTypeId = "";
    private String authAccess = "";
    private String regId = "";
    private String regDt = "";
    private String authTypeIdNm = "";



    //권한
    /**
     * 리스트
     */
    private int listYn=0;
    /**
     * 쓰기
     */
    private int writeYn=0;
    /**
     * 읽기
     */
    private int readYn=0;
    /**
     * 수정
     */
    private int modifyYn=0;
    /**
     * 삭제
     */
    private int deleteYn=0;
    /**
     * 기관전체보기 권한
     */
    private int organAllYn=0;
    /**
     * 서비스 전체보기
     */
    private int cloudAllYn=0;



    //권한 타입
    private String group="";
    private String orgnzt="";
    private String role="";
    private String user="";


}
