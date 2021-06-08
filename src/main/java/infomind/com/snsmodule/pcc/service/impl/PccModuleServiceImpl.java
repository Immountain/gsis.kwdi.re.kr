package infomind.com.snsmodule.pcc.service.impl;


import com.sci.v2.pccv2.secu.SciSecuManager;
import com.sci.v2.pccv2.secu.hmac.SciHmac;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.ext.dao.CodeSearchDAO;
import infomind.com.snsmodule.pcc.dao.PccModuleDAO;
import infomind.com.snsmodule.pcc.service.PccModuleService;
import infomind.com.snsmodule.pcc.vo.PccSmsInfoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


@Service("PccModuleService")
public class PccModuleServiceImpl extends EgovAbstractServiceImpl implements PccModuleService {

    @Resource(name="PccModuleDAO")
    private PccModuleDAO pccModuleDAO;


    @Resource(name = "PccSmsSnoIdService")
    private EgovIdGnrService idgenService;


    @Override
    public PccSmsInfoVO setPcc(String id, String srvNo, String returnUrl) {

        PccSmsInfoVO pcc = new PccSmsInfoVO();


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

        //reqNum은 최대 40byte 까지 사용 가능
        String reqNum = day + randomStr;
        String certDate=day;

        String certGb = "H"; 				// 인증수단  H : 휴대폰 인증 화면 사용
        String exVar = "0000000000000000";  // 복호화용 임시필드
        String sno ="";
        try {
            sno =idgenService.getNextStringId();
        } catch (FdlException e) {
            e.printStackTrace();
        }


        String addVar = "";				    // 본인실명확인 추가 파라메터

        //01. 암호화 모듈 선언
        SciSecuManager seed  = new SciSecuManager();
        //02. 1차 암호화
        String reqInfo      = id+"^"+srvNo+"^"+reqNum+"^"+certDate+"^"+certGb+"^"+addVar+"^"+exVar;  // 데이터 암호화


        //seed.setInfoPublic(id,"비즈사이렌에서 설정한 암호화 키 입력"); // 회원사 ID 및 PWD 셋팅 패스워드는 16자리 필수 영문 무관
        seed.setInfoPublic(id, EgovProperties.getProperty("biz_pcc_secuKey")); // 회원사 ID 및 PWD 셋팅 패스워드는 16자리 필수 영문 무관
        String encStr = seed.getEncPublic(reqInfo);




        //03. 위변조 검증 값 생성
        SciHmac hmac = new SciHmac();
      //  String hmacMsg = hmac.HMacEncriptPublic(encStr);
        String hmacMsg  = seed.getEncReq(encStr,"HMAC");

        //03. 2차 암호화
        reqInfo  = seed.getEncPublic(encStr + "^" + hmacMsg + "^" + "0000000000000000");  //2차암호화
        reqInfo = seed.EncPublic(reqInfo + "^" + id + "^"  + "00000000");


        pcc.setPccSmsSno(sno);
        //요청번호
        pcc.setReqNum(reqNum);
        //요청 서비스번호
        pcc.setSrvNo(srvNo);
        //본인확인 데이터집함
        pcc.setRetInfo(reqInfo);
        //인증수단
        pcc.setCerGb(certGb);
        //리턴URL
        pcc.setReturnUrl(returnUrl);





        return pcc;
    }

    @Override
    public PccSmsInfoVO getSelectPccSmsInfo(PccSmsInfoVO vo) throws Exception {
        return pccModuleDAO.getSelectPccSmsInfo(vo);
    }

    @Override
    public int getUpdatePccSmsInfo(PccSmsInfoVO vo) throws Exception {
       return pccModuleDAO.getUpdatePccSmsInfo(vo);
    }

    @Override
    public void getInsertPccSmsInfo(PccSmsInfoVO vo) throws Exception {
         pccModuleDAO.getInsertPccSmsInfo(vo);


    }
}
