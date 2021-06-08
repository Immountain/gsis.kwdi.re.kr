package infomind.com.cms.info.banner.vo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoPageBannerValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoPageBannerVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoPageBannerVO validVO = (InfoPageBannerVO) target;

        String titme = validVO.getTitme();
        if(titme == null || titme.trim().isEmpty()) {
            errors.rejectValue("titme","","배너 명칭 입력하세요.");
        }
    }


}
