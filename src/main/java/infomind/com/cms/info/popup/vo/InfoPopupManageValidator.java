package infomind.com.cms.info.popup.vo;
import infomind.com.cms.info.banner.vo.InfoBannerVO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoPopupManageValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoPopupManageVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoPopupManageVO infoPopupManageVO = (InfoPopupManageVO) target;

        String popupId = infoPopupManageVO.getPopupId();
        if(popupId == null || popupId.trim().isEmpty()) {
            errors.rejectValue("popupId","","팝업 아이디 입력하세요.");
        }
        String popupNm = infoPopupManageVO.getPopupNm();
        if(popupNm == null || popupNm.trim().isEmpty()) {
            errors.rejectValue("popupNm","","팝업 명칭 입력하세요.");
        }
    }


}
