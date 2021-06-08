package infomind.com.cms.info.site.vo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoSiteValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoSiteVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoSiteVO infoSiteVO = (InfoSiteVO) target;

        String siteId = infoSiteVO.getSiteId();
        if(siteId == null || siteId.trim().isEmpty()) {
            errors.rejectValue("siteId","","사이트 아이디 입력하세요.");
        }
       
    }


}
