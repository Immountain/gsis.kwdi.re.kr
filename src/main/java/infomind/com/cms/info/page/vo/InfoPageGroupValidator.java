package infomind.com.cms.info.page.vo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoPageGroupValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoPageGroupVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoPageGroupVO infoPageGroupVO = (InfoPageGroupVO) target;

        String pageGroupId = infoPageGroupVO.getPageGroupId();
        if(pageGroupId == null || pageGroupId.trim().isEmpty()) {
            errors.rejectValue("pageGroupId","","페이지그룹 아이디 입력하세요.");
        }
        String pageGroupNm = infoPageGroupVO.getPageGroupNm();
        if(pageGroupNm == null || pageGroupNm.trim().isEmpty()) {
            errors.rejectValue("pageGroupNm","","페이지그룹 명칭 입력하세요.");
        }
    }


}
