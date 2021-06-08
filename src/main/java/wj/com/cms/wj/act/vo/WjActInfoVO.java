package wj.com.cms.wj.act.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WjActInfoVO extends CmsSearchVO {

    private String userId;   //varchar(20) not null comment '등록아이디',
    private String actCode;   //varchar(20) not null comment '활동분야코드'

}
