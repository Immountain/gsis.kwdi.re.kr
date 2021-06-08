package infomind.com.cms.info.page.vo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoPageInfoValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoPageInfoVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoPageInfoVO infoPageInfoVO = (InfoPageInfoVO) target;

        String pageId = infoPageInfoVO.getPageId();
        if(pageId == null || pageId.trim().isEmpty()) {
            errors.rejectValue("pageId","","페이지 아이디 입력하세요.");
        }
        String pageNm = infoPageInfoVO.getPageNm();
        if(pageNm == null || pageNm.trim().isEmpty()) {
            errors.rejectValue("pageNm","","페이지 명칭 입력하세요.");
        }
        String contentInfo = infoPageInfoVO.getContentInfo();
        if(contentInfo == null || contentInfo.trim().isEmpty()) {
            errors.rejectValue("contentInfo","","내용 입력하세요.");
        }
    }


}
