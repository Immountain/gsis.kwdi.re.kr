package infomind.com.sms.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.sms.dao.InfoSmsInfoDAO;
import infomind.com.sms.service.InfoSmsInfoService;
import infomind.com.sms.service.ScTranService;
import infomind.com.sms.vo.InfoSmsInfoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

@Service("InfoSmsInfoService")
public class InfoSmsInfoServiceImpl extends EgovAbstractServiceImpl implements InfoSmsInfoService {


    @Resource(name="InfoSmsInfoDAO")
    private InfoSmsInfoDAO infoSmsInfoDAO;

    @Override
    public int selectSmsCheckCount(InfoSmsInfoVO vo) {
        return infoSmsInfoDAO.selectSmsCheckCount(vo);
    }

    @Override
    public String insert(InfoSmsInfoVO vo) {
        String key ="";

        //날짜 생성
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String day = sdf.format(today.getTime());

        java.util.Random ran = new Random();
        //랜덤 문자 길이
        int numLength = 6;
        String randomStr = "";

        for (int i = 0; i < numLength; i++) {
            //0 ~ 9 랜덤 숫자 생성
            randomStr += ran.nextInt(10);
        }
        key= day + randomStr;
        vo.setSmsInfoId(key);


        infoSmsInfoDAO.insert(vo);



        return key;
    }

    @Override
    public void update(InfoSmsInfoVO vo) {
        infoSmsInfoDAO.update(vo);
    }
}
