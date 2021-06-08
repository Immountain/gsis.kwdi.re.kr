package infomind.com.cms.info.page.vo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoPageImageGroupValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoPageInfoVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoPageImageGroupVO vo = (InfoPageImageGroupVO) target;

        String imageId = vo.getImageId();
        if(imageId == null || imageId.trim().isEmpty()) {
            errors.rejectValue("imageId","","이미지 아이디 입력하세요.");
        }
        String imageNm = vo.getImageNm();
        if(imageNm == null || imageNm.trim().isEmpty()) {
            errors.rejectValue("imageNm","","이미지 명칭 입력하세요.");
        }
        String imageDc = vo.getImageDc();
        if(imageDc == null || imageDc.trim().isEmpty()) {
            errors.rejectValue("imageDc","","이미지 설명 입력하세요.");
        }
    }


}
