package infomind.com.cms.pwd.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import infomind.com.site.service.SiteMyPageService;
import infomind.com.site.vo.SiteSuccessVO;
import infomind.com.site.vo.SiteUserVO;
import infomind.com.sms.service.InfoSmsInfoService;
import infomind.com.sms.service.ScTranService;
import infomind.com.sms.vo.InfoSmsInfoVO;
import infomind.com.sms.vo.ScTranVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

@Controller
public class PwdController {


    /** InfoSmsInfoService */
    @Resource(name = "InfoSmsInfoService")
    private InfoSmsInfoService infoSmsInfoService;

    /**
     * siteMyPageService
     */
    @Resource(name = "SiteMyPageService")
    private SiteMyPageService siteMyPageService;


    /** ScTranService */
    @Resource(name = "ScTranService")
    private ScTranService scTranService;


    @RequestMapping(value = "/cms/user/mberPasswordUpdt.do")
    @ResponseBody
    public ModelAndView getSiteUserPwFind(@ModelAttribute("searchVO") SiteUserVO searchVO, ModelMap model,HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        SiteSuccessVO sVo = new SiteSuccessVO();
        InfoSmsInfoVO infoSmsInfoVO= new InfoSmsInfoVO();

        SiteUserVO siteUserVO = new SiteUserVO();
        List<SiteUserVO> getSiteUserAllFind =siteMyPageService.getSiteUserAllFind(searchVO);
        if(getSiteUserAllFind.size()==0){


            sVo.setSuccessCode("9999");
            sVo.setMsg("실패");
            sVo.setInfo("");

        }else{

            siteUserVO = getSiteUserAllFind.get(0);

            String userId =siteUserVO.getUserId();
            Random rnd =new Random();
            StringBuffer buf =new StringBuffer();

            for(int i=0;i<8;i++){
                if(rnd.nextBoolean()){
                    buf.append((char)((int)(rnd.nextInt(26))+97));

                }else{
                    buf.append((rnd.nextInt(10)));
                }
            }


            sVo.setInfo(buf.toString());
            String encryptPass = EgovFileScrty.encryptPassword(buf.toString(), userId);
            siteUserVO.setPassword(encryptPass);

            siteMyPageService.getUserPw(siteUserVO);

            String msg ="제주연구장비활용서비스 임시 패시워드는["+buf.toString()+"] 입니다.";
            infoSmsInfoVO.setSmsMsg(msg);
            infoSmsInfoVO.setModIp(request.getRemoteAddr());
            infoSmsInfoService.update(infoSmsInfoVO);

            ScTranVO scTranVO = new ScTranVO();
            scTranVO.setTrMsg(msg);
            scTranVO.setTrPhone(siteUserVO.getMbtlnum());
            scTranService.insert(scTranVO);
            sVo.setSuccessCode("0000");
            sVo.setMsg("성공");
            sVo.setInfo("성공");

        }


        modelAndView.addObject("info", sVo);
        return modelAndView;
    }


}
