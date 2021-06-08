package infomind.com.cms.info.layout.vo;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoLayoutInfoValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoPageInfoVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoLayoutInfoVO infoLayoutInfoVO = (InfoLayoutInfoVO) target;

        String layoutId = infoLayoutInfoVO.getLayoutId();
        if(layoutId == null || layoutId.trim().isEmpty()) {
            errors.rejectValue("layoutId","","레이아웃 아이디 입력하세요.");
        }
        String layoutNm = infoLayoutInfoVO.getLayoutNm();
        if(layoutNm == null || layoutNm.trim().isEmpty()) {
            errors.rejectValue("layoutNm","","레이아웃 명칭 입력하세요.");
        }

        String contentInfo = infoLayoutInfoVO.getContentInfo();
        if(contentInfo == null || contentInfo.trim().isEmpty()) {
            errors.rejectValue("contentInfo","","레이아웃 내용 입력하세요.");
        }

    }
    
}
