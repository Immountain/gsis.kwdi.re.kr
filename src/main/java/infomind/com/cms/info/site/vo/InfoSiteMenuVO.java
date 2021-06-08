package infomind.com.cms.info.site.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class InfoSiteMenuVO extends CmsSearchVO {
    /**
     * 메뉴아이디
     */
    private String siteMemuId = "";
    /**
     * 메뉴명
     */
    private String siteMemuNm = "";
    /**
     * 메뉴타이틀
     */
    private String siteMemuTile = "";
    /**
     * 슬로거
     */
    private String slug = "";
    /**
     * 링크주소
     */
    private String url = "";
    /**
     * 깊이
     */
    private int depth = 0;
    /**
     * 정렬순서
     */
    private int ord = 0;
    /**
     * 메뉴그룹아이디
     */
    private String menuGroupId = "";
    /**
     * 메뉴타입
     */
    private String menuType = "";
    /**
     * 메뉴페이지
     */
    private String menuPage = "";
    /**
     * 메뉴이미지
     */
    private String siteMemuImage = "";
    /**
     * 속성
     */
    private String attr = "";
    /**
     * 타켓
     */
    private String target = "";
    /**
     * css클레스1
     */
    private String class1 = "";
    /**
     * css클레스2
     */
    private String class2 = "";
    /**
     * css클레스3
     */
    private String class3 = "";
    /**
     * 레이아웃
     */
    private String layout = "";
    /**
     * 평가 가능사용여부
     */
    private String useGrade = "";
    /**
     * 임시필드1
     */
    private String temp01 = "";
    /**
     * 임시필드2
     */
    private String temp02 = "";
    /**
     * 임시필드3
     */
    private String temp03 = "";
    /**
     * 임시필드4
     */
    private String temp04 = "";
    /**
     * 임시필드5
     */
    private String temp05 = "";
    /**
     * 로그인 여부
     */
    private String loginYn = "";
    /**
     * 보이는여부
     */
    private String viewYn = "";
    /**
     * 사용여부
     */
    private String useYn = "Y";
    /**
     * 등록자
     */
    private String regId = "";
    /**
     * 등록일
     */
    private String regDt = "";
    /**
     * 수정자
     */
    private String modId = "";
    /**
     * 수정일
     */
    private String modDt = "";
    /**
     * 부모아이디
     */
    private String parentId = "";
    /**
     * 부모명
     */
    private String parentNm = "";



    /**
     * 평가담당자
     */
    private String gradePerson = "";
    /**
     * 평가부서
     */
    private String gradeDepart = "";
    /**
     *
     */
    private int tempDept = 0;
    /**
     *
     */
    private String addCss = "";
    /**
     * 레이아웃 타입
     */
    private String layoutType = "";

    /**
     * 권한 사용여부
     */
    private String authYn = "";


    /**
     * 기본 css
     */
    private String defaultCss = "sub-ready";

    /**/
    private InfoSiteVO infoSite;

    private InfoSiteMenuGroupVO infoSiteMenuGroup;

    /**
     *
     */
    //노드에 처리중 쟈신에 노드 일경우
    private String checkYn = "";
    private List<InfoSiteMenuVO> childMenu = new ArrayList<>();

    //자식 저장
    public void addChildMenu(InfoSiteMenuVO menu) {
        int addDept = tempDept + 1;
        menu.setTempDept(addDept);
        childMenu.add(menu);
        childMenu.sort(Comparator.comparingInt(InfoSiteMenuVO::getOrd));
    }

}
