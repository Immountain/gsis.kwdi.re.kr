package infomind.com.cms.mnu.auth.vo;

import infomind.com.cms.info.site.vo.InfoSiteMenuAuthVO;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

import java.util.List;

@Data
public class InfoCmsMenuAuthConfigVO extends CmsSearchVO {

    private String cmsMemuId;            // AUTH_TYPE     VARCHAR2(200),
    private String authType;            // AUTH_TYPE     VARCHAR2(200),
    private String authTypeId;          // AUTH_TYPE_ID  VARCHAR2(200),
    private String authTypeIdNm;          // AUTH_TYPE_ID  VARCHAR2(200),

    private boolean list = false;
    private boolean read = false;
    private boolean write = false;
    private boolean modify = false;
    private boolean delete = false;
    private boolean organ = false;
    private boolean cloud = false;

    private List<InfoCmsMenuAuthVO> auths;
}
