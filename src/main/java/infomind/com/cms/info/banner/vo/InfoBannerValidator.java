package infomind.com.cms.info.banner.vo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoBannerValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoBannerVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoBannerVO infoBannerVO = (InfoBannerVO) target;

        String bannerId = infoBannerVO.getBannerId();
        if(bannerId == null || bannerId.trim().isEmpty()) {
            errors.rejectValue("bannerId","","배너 아이디 입력하세요.");
        }
        String bannerNm = infoBannerVO.getBannerNm();
        if(bannerNm == null || bannerNm.trim().isEmpty()) {
            errors.rejectValue("bannerNm","","배너 명칭 입력하세요.");
        }
    }


}
