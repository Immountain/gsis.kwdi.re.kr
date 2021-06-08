package infomind.com.snsmodule.pcc.web;



import com.sci.v2.pccv2.secu.SciSecuManager;
import egovframework.com.cmm.service.EgovProperties;
import infomind.com.site.service.SiteMyPageService;
import infomind.com.snsmodule.pcc.service.PccModuleService;
import infomind.com.snsmodule.pcc.vo.PccSmsInfoVO;
import infomind.com.utils.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
public class PccModuleController {

    /** PccModuleService */
    @Resource(name = "PccModuleService")
    private PccModuleService pccModuleService;
    /** PccModuleService */
    @Resource(name = "SiteMyPageService")
    private SiteMyPageService siteMyPageService;




    /**
     * 본인인증 gate 페이지
     *
     * <p/>
     *
     */
    @RequestMapping(value="/site/sns/authResultPopup.do")
    public String authResultPopup(ModelMap model, HttpServletRequest request) throws Exception{

        //리턴
        String msg ="";
        PccSmsInfoVO pccVo = new PccSmsInfoVO();
        try {

            //쿠키값 가져 오기
            String[] reqNumList = CommonUtil.getCookieReqNum(request);
            String reqNum ="";
            String retInfo = request.getParameter("retInfo").trim();
            if(reqNumList.length>0){
                System.out.println("retInfo==>"+reqNumList[0]);
                reqNum =reqNumList[0];
            }else{
                System.out.println("에러");
                msg ="에러가 발생하였습니다.";
            }

            //복화화용 변수
            String encPara		= "";
            String encMsg		= "";
            String msgChk       = "N";

            //

            SciSecuManager sciSecuMg = new SciSecuManager();
            sciSecuMg.setInfoPublic(EgovProperties.getProperty("biz_pcc_userid"),EgovProperties.getProperty("biz_pcc_secuKey")); //패스워드는 16자리 필수 영문 무관

            // 3. 1차 파싱---------------------------------------------------------------
            retInfo  = sciSecuMg.getDec(retInfo,reqNum);

            // 4. 요청결과 복호화
            String[] aRetInfo1 = retInfo.split("\\^");

            encPara  = aRetInfo1[0];         //암호화된 통합 파라미터
            encMsg   = aRetInfo1[1];    //암호화된 통합 파라미터의 Hash값

            String encMsg2   = sciSecuMg.getMsg(encPara);
            // 5. 위/변조 검증 ---------------------------------------------------------------
            if(encMsg2.equals(encMsg)){
                msgChk="Y";
            }
            if(msgChk.equals("N")){
                System.out.println("HMAC 확인이 필요합니다.");
                msg ="HMAC 확인이 필요합니다.";
            }


            // 복호화 및 위/변조 검증 ---------------------------------------------------------------
            retInfo  = sciSecuMg.getDec(encPara, reqNum);
            String[] aRetInfo = retInfo.split("\\^");


            System.out.println("name==>"+aRetInfo[0]);
            System.out.println("birYMD==>"+aRetInfo[1]);
            System.out.println("sex==>"+aRetInfo[2]);
            System.out.println("fgnGbn==>"+aRetInfo[3]);
            System.out.println("di==>"+aRetInfo[4]);
            System.out.println("ci1==>"+aRetInfo[5]);
            System.out.println("ci2==>"+aRetInfo[6]);
            System.out.println("civersion==>"+aRetInfo[7]);
            System.out.println("reqNum==>"+aRetInfo[8]);
            System.out.println("result==>"+aRetInfo[9]);
            System.out.println("certGb==>"+aRetInfo[10]);
            System.out.println("cellNo==>"+aRetInfo[11]);
            System.out.println("cellCorp==>"+aRetInfo[12]);
            System.out.println("certDate==>"+aRetInfo[13]);
            System.out.println("addVar==>"+aRetInfo[14]);
            System.out.println("ext1==>"+aRetInfo[15]);
            System.out.println("ext2==>"+aRetInfo[16]);
            System.out.println("ext3==>"+aRetInfo[17]);
            System.out.println("ext4==>"+aRetInfo[18]);
            System.out.println("ext5==>"+aRetInfo[19]);





            pccVo.setName(aRetInfo[0]);
            pccVo.setBirYmd(aRetInfo[1]);
            pccVo.setSex(aRetInfo[2]);
            pccVo.setFgnGbn(aRetInfo[3]);
            pccVo.setDi(aRetInfo[4]);
            pccVo.setCi1(aRetInfo[5]);
            pccVo.setCi2(aRetInfo[6]);
            pccVo.setCiversion(aRetInfo[7]);
            pccVo.setReqNum(aRetInfo[8]);
            pccVo.setResult(aRetInfo[9]);
            pccVo.setCerGb(aRetInfo[10]);
            pccVo.setCellNo(aRetInfo[11]);
            pccVo.setCellCorp(aRetInfo[12]);
            pccVo.setCerDate(aRetInfo[13]);
            pccVo.setAddVer(aRetInfo[14]);


            try {
                int getDiCount  =  siteMyPageService.getDiCount(pccVo.getDi());

                if(getDiCount>0){
                    msg="이미 가입된 인증 핸드폰번호 입니다.";

                }else{
                    int cnt  =  pccModuleService.getUpdatePccSmsInfo(pccVo);
                    if(cnt ==0){
                        msg="인증 처리시 에러가 발생했습니다";
                    }else{
                        msg="";
                    }
                }
            } catch (Exception e) {
                msg="인증 처리시 에러가 발생했습니다";
            }



        }catch (Exception e){


            msg="인증 처리시 에러가 발생했습니다";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("di", pccVo.getDi());
        model.addAttribute("reqNum", pccVo.getReqNum());

        return "page/mypage/pcc_V3_popup_seed_v2";
    }


    /**
     *  휴대폰 인증시 ajax로 PCC에서 필요한 데이터 세팅
     * @return
     */
    @RequestMapping(value = "/site/sns/setPccInfo.do")
    @ResponseBody
    public ModelAndView setPccInfo(HttpServletRequest request, HttpServletResponse response) {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        String  phoneCertId =EgovProperties.getProperty("biz_pcc_userid");
        String srvNo = "";
        String returnUrl = "";
        srvNo = EgovProperties.getProperty("biz_pcc_srvNo");
        returnUrl =EgovProperties.getProperty("biz_pcc_returnUrl");

        PccSmsInfoVO pcc = pccModuleService.setPcc(phoneCertId, srvNo, returnUrl);


        try {
             pccModuleService.getInsertPccSmsInfo(pcc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Cookie c = new Cookie("reqNum", pcc.getReqNum());
//        // c.setMaxAge(600); // <==초단위로 설정됩니다.
//        // c.setSecure(true);
       response.addCookie(c);
       modelAndView.addObject("pcc", pcc);


        return modelAndView;
    }

}



