package infomind.com.cms.info.lang.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class InfoLangCodeVO extends CmsSearchVO {

        private String codeId; //not null comment '코드아이디',
        private String code; //not null comment '코드',
        private String langCode; //not null comment '언어코드',
        private String codeNm; //null comment '코드명',
        private String langCodeNm;
        private String checkCode;
        private String userId;


        private List<InfoLangCodeVO>listLang;
}
