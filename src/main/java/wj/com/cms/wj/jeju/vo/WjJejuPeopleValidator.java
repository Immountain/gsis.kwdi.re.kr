package wj.com.cms.wj.jeju.vo;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class WjJejuPeopleValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return WjJejuPeopleVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        WjJejuPeopleVO wjJejuPeopleVO = (WjJejuPeopleVO) target;

        String userId = wjJejuPeopleVO.getUserId();
        if (userId == null || userId.trim().isEmpty()) {
            errors.rejectValue("userId", "", messageGenerate("아이디"));
        }
        String password = wjJejuPeopleVO.getPassword();
        if (password == null || password.trim().isEmpty()) {
            errors.rejectValue("password", "", messageGenerate("비밀번호"));
        }
        String emaill = wjJejuPeopleVO.getEmaill();
        if (emaill == null || emaill.trim().isEmpty()) {
            errors.rejectValue("emaill", "", messageGenerate("이메일"));
        }
        String korName = wjJejuPeopleVO.getKorName();
        if (korName == null || korName.trim().isEmpty()) {
            errors.rejectValue("korName", "", messageGenerate("한글명"));
        }
        String enName = wjJejuPeopleVO.getEnName();
        if (enName == null || enName.trim().isEmpty()) {
            errors.rejectValue("enName", "", messageGenerate("영문명"));
        }
        String sex = wjJejuPeopleVO.getSex();
        if (sex == null || sex.trim().isEmpty()) {
            errors.rejectValue("sex", "", messageGenerate("성별"));
        }
        String birth = wjJejuPeopleVO.getBirth();
        if (birth == null || birth.trim().isEmpty()) {
            errors.rejectValue("birth", "", messageGenerate("생년월일"));
        }
        String phone = wjJejuPeopleVO.getPhone();
        if (phone == null || phone.trim().isEmpty()) {
            errors.rejectValue("phone", "", messageGenerate("연락처"));
        }
    }

    private String messageGenerate(String field) {
        return String.format("%s을(를) 입력하세요.", field);
    }
}
