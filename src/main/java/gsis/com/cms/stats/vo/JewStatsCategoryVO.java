package gsis.com.cms.stats.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JewStatsCategoryVO extends CmsSearchVO {

    /* 카테고리아이디 **/
    private String categoryId;

    /* 카테고리명 **/
    private String categoryIdNm;

    /* 부모카테고리 **/
    private String parentId;

    /* 정렬순번 **/
    private String orderCnt;

    /* kosis_서비스코드 **/
    private String vwCd;

    /* kosis_통계아이디 **/
    private String tblId;

    /* 통계URL **/
    private String statsUrl;

    /* 사용여부 **/
    private String useYn;

    /* 삭제여부 **/
    private String deleteYn;


    /* 타이 **/
    private String titleNm;

    /* 서브타이틀 **/
    private String subTitleNm;
}
