package infomind.com.cms.info.banner.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoPageBannerLangVO extends CmsSearchVO {

        private String pageBannerId;// PAGE_BANNER_ID varchar(20) not null comment '서브배너아이디',
        private String langCode;// LANG_CODE varchar(10) not null comment '언어코드',
        private String title;// TITLE varchar(200) null comment '제목',
        private String memo;// MEMO varchar(200) null comment '메모',
        private String contentInfo;// CONTENT_INFO varchar(500) null comment '내용',

}
