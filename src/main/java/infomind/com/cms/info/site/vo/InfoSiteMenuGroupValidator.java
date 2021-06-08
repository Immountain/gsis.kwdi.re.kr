package infomind.com.cms.info.site.vo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoSiteMenuGroupValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoSiteMenuGroupVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoSiteMenuGroupVO infoSiteMenuGroupVO = (InfoSiteMenuGroupVO) target;

        String menuGroupId = infoSiteMenuGroupVO.getMenuGroupId();
        if(menuGroupId == null || menuGroupId.trim().isEmpty()) {
            errors.rejectValue("menuGroupId","","사이트 메뉴그룹 아이디 입력하세요.");
        }
        String menuGroupNm = infoSiteMenuGroupVO.getMenuGroupNm();
        if(menuGroupNm == null || menuGroupNm.trim().isEmpty()) {
            errors.rejectValue("menuGroupNm","","사이트 메뉴그룹  명칭 입력하세요.");
        }
        String useYn = infoSiteMenuGroupVO.getUseYn();
        if(useYn == null || useYn.trim().isEmpty()) {
            errors.rejectValue("useYn","","사용여부 입력하세요.");
        }
        String menuGroupLocale = infoSiteMenuGroupVO.getMenuGroupLocale();
        if(menuGroupLocale == null || menuGroupLocale.trim().isEmpty()) {
            errors.rejectValue("menuGroupLocale","","언어 입력하세요.");
        }
    }


}
