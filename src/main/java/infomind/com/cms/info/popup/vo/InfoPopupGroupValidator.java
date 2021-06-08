package infomind.com.cms.info.popup.vo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoPopupGroupValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoPopupGroupVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoPopupGroupVO infoPopupGroupVO = (InfoPopupGroupVO) target;

        String popupGroupId = infoPopupGroupVO.getPopupGroupId();
        if(popupGroupId == null || popupGroupId.trim().isEmpty()) {
            errors.rejectValue("popupGroupId","","팝업그룹 아이디 입력하세요.");
        }
        String popupGroupNm = infoPopupGroupVO.getPopupGroupNm();
        if(popupGroupNm == null || popupGroupNm.trim().isEmpty()) {
            errors.rejectValue("popupGroupNm","","팝업그룹 명칭 입력하세요.");
        }
    }


}
