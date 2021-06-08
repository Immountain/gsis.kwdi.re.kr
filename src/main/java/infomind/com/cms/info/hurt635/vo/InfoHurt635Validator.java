package infomind.com.cms.info.hurt635.vo;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class InfoHurt635Validator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return InfoHurt635VO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        InfoHurt635VO infoHurt635VO = (InfoHurt635VO) target;

        String allwYn = infoHurt635VO.getAllwYn();
        if(allwYn == null || allwYn.trim().isEmpty()) {
            errors.rejectValue("allwYn","","허용여부 선택하세요.");
        }

        String ip1 = infoHurt635VO.getIp1();
        if(ip1 == null || ip1.trim().isEmpty()) {
            errors.rejectValue("ip1","","아이피 입력하세요.");
        }

        String ip2 = infoHurt635VO.getIp2();
        if(ip2 == null || ip2.trim().isEmpty()) {
            errors.rejectValue("ip2","","아이피 입력하세요.");
        }

        String ip3 = infoHurt635VO.getIp3();
        if(ip3 == null || ip3.trim().isEmpty()) {
            errors.rejectValue("ip3","","아이피 입력하세요.");
        }

        String ip4 = infoHurt635VO.getIp4();
        if(ip4 == null || ip4.trim().isEmpty()) {
            errors.rejectValue("ip4","","아이피 입력하세요.");
        }
       
    }


}
