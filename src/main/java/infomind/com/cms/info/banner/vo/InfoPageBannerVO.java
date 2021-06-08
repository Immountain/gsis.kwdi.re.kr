package infomind.com.cms.info.banner.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

import java.util.List;

@Data
public class InfoPageBannerVO extends CmsSearchVO {

    private String pageBannerId; // PAGE_BANNER_ID varchar(20) not null comment '서브배너아이디',
    private String siteMemuId; // SITE_MEMU_ID varchar(20) null comment '메뉴아이디',
    private String festivityId; // FESTIVITY_ID varchar(20) null comment '대회아이디',
    private String titme; // TITME varchar(200) null comment '배너명칭',
    private String memo; // MEMO varchar(200) null comment '배너메모',
    private String atchFileId; // ATCH_FILE_ID varchar(20) null comment '이미지',
    private String pageBannerUrl; // PAGE_BANNER_URL varchar(300) null comment '링크URL',
    private String useYn; // USE_YN varchar(2) null comment '사용여부',
    private String deleteYn; // DELETE_YN varchar(2) null comment '삭제여부',

    private List<InfoPageBannerLangVO> bannerLangList;

}
