package infomind.com.cms.info.banner.vo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoBannerGroupValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoBannerGroupVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoBannerGroupVO infoBannerGroupVO = (InfoBannerGroupVO) target;

        String bannerGroupId = infoBannerGroupVO.getBannerGroupId();
        if(bannerGroupId == null || bannerGroupId.trim().isEmpty()) {
            errors.rejectValue("bannerGroupId","","배너그룹 아이디 입력하세요.");
        }
        String bannerGroupNm = infoBannerGroupVO.getBannerGroupNm();
        if(bannerGroupNm == null || bannerGroupNm.trim().isEmpty()) {
            errors.rejectValue("bannerGroupNm","","배너그룹 명칭 입력하세요.");
        }

        String bannerGroupTxt = infoBannerGroupVO.getBannerGroupTxt();
        if(bannerGroupTxt == null || bannerGroupTxt.trim().isEmpty()) {
            errors.rejectValue("bannerGroupTxt","","배너그룹 설명 입력하세요.");
        }
    }


}
