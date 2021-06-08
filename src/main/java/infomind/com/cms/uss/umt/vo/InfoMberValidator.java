package infomind.com.cms.uss.umt.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * 기업회원VO클래스로서 기업회원관리 비지니스로직 처리용 항목을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2017.07.21  장동한 			로그인인증제한 작업
 *
 * </pre>
 */
@Data
public class InfoMberValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return InfoMberManageVO.class.isAssignableFrom(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        InfoMberManageVO vo = (InfoMberManageVO) target;

        if(StringUtils.isBlank(vo.getMberId())) {
            errors.rejectValue("mberId","","일반회원 ID를 입력하세요.");
        }

        if(StringUtils.isBlank(vo.getMberNm())) {
            errors.rejectValue("mberNm","","일반회원 이름을 입력하세요.");
        }

        if(StringUtils.isBlank(vo.getStartMoblno())) {
            errors.rejectValue("startMoblno","","핸드폰번호1을 입력하세요.");
        }

        if(StringUtils.isBlank(vo.getMiddleMoblno())) {
            errors.rejectValue("middleMoblno","","핸드폰번호2을 입력하세요.");
        }

        if(StringUtils.isBlank(vo.getEndMoblno())) {
            errors.rejectValue("endMoblno","","핸드폰번호3을 입력하세요.");
        }

        if(StringUtils.isBlank(vo.getMberEmailAdres())) {
            errors.rejectValue("mberEmailAdres","","이메일 주소를 입력하세요.");
        }

        if(StringUtils.isBlank(vo.getJtpOrganCd())) {
            errors.rejectValue("jtpOrganCd","","소속 기업/기관을 입력하세요.");
        }

        if(StringUtils.isBlank(vo.getMberSttus())) {
            errors.rejectValue("mberSttus","","일반회원상태코드를 입력하세요.");
        }
    }

}
