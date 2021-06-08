package infomind.com.cms.info.site.vo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoSiteMenuAuthValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoSiteMenuAuthVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoSiteMenuAuthVO infoSiteMenuAuthVO = (InfoSiteMenuAuthVO) target;

        Integer memuAuthId = infoSiteMenuAuthVO.getMemuAuthId();
        if(memuAuthId == null) {
            errors.rejectValue("memuAuthId","","게시판권한 아이디 입력하세요.");
        }
        String siteMemuId = infoSiteMenuAuthVO.getSiteMemuId();
        if(siteMemuId == null || siteMemuId.trim().isEmpty()) {
            errors.rejectValue("siteMemuId","","메뉴 아이디 입력하세요.");
        }
    }


}
