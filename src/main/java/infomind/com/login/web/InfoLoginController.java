package infomind.com.login.web;

import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.log.clg.service.EgovLoginLogService;
import egovframework.com.sym.log.clg.service.LoginLog;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import infomind.com.cmm.bean.InfoLoginPageCacheManager;
import infomind.com.cms.sym.log.clg.service.InfoLoginLogService;
import infomind.com.cms.sym.log.clg.vo.InfoLoginLogVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class InfoLoginController {


    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovMenuManageService */
    @Resource(name = "meunManageService")
    private EgovMenuManageService menuManageService;



    @Resource(name="InfoLoginLogService")
    private InfoLoginLogService loginLogService;


    /** log */
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoLoginController.class);

    @Resource
    private InfoLoginPageCacheManager infoLoginPageCacheManager;

    /**
     * 로그아웃한다.
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String actionLogout(HttpServletRequest request, ModelMap model,HttpServletResponse response,String redirect) throws Exception {


        System.out.println("logout==>"+redirect);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        System.out.println("auth==>"+redirect);
        System.out.println("auth==>"+auth);


        String uniqId="";
        String ip="";


        /*
            RequestContextHolder.getRequestAttributes().removeAttribute("LoginVO", RequestAttributes.SCOPE_SESSION);
            SecurityContextHolder.clearContext();//스프링 시큐리티 로그아웃 처리 추가
            */
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//



        /* Authenticated  */
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if(isAuthenticated.booleanValue()) {
            LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
            uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
            ip = (user == null || user.getIp() == null) ? "" : user.getIp();
        }

        InfoLoginLogVO loginLog = new InfoLoginLogVO();
        loginLog.setLoginId(uniqId);
        loginLog.setLoginIp(request.getRemoteAddr());
        loginLog.setLoginMthd("O"); // 로그인:I, 로그아웃:O
        loginLog.setErrOccrrAt("N");
        loginLog.setErrorCode("");
        loginLogService.logInsertLoginLog(loginLog);




        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        if(redirect==null||"".equals(redirect)||"null".equals(redirect)){

            redirect ="/";
        }

        SecurityContextHolder.clearContext();

        request.getSession().setAttribute("loginVO", null);
        //초기화
        infoLoginPageCacheManager.loadLoginPage();



        return "redirect:"+redirect;

    }






}
