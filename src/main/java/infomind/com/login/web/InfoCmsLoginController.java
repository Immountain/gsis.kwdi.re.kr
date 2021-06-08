package infomind.com.login.web;

import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import infomind.com.cmm.bean.InfoLoginPageCacheManager;
import infomind.com.cmm.bean.InfoUserMenuCacheManager;
import infomind.com.utils.http.InfoHttpResponseUtils;
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
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Controller
public class InfoCmsLoginController {


    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovMenuManageService */
    @Resource(name = "meunManageService")
    private EgovMenuManageService menuManageService;

    /** log */
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoCmsLoginController.class);


    @Resource
    private InfoLoginPageCacheManager infoLoginPageCacheManager;


    /**
     * 로그인 화면으로 들어간다
     * @param loginVO - 로그인후 이동할 URL이 담긴 LoginVO
     * @return 로그인 페이지
     * @exception Exception
     */
    @IncludedInfo(name = "로그인", listUrl = "/cms/LoginUsr.do", order = 10, gid = 10)
    @RequestMapping(value = "/cms/LoginUsr.do")
    public String loginUsrView(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        infoLoginPageCacheManager.loadLoginPage();

        if (EgovComponentChecker.hasComponent("mberManageService")) {
            model.addAttribute("useMemberManage", "true");
        }

        //권한체크시 에러 페이지 이동
        String auth_error =  request.getParameter("auth_error") == null ? "" : (String)request.getParameter("auth_error");
        String login_error =  request.getParameter("login_error") == null ? "" : (String)request.getParameter("login_error");


         System.out.println("auth_error======>"+auth_error);
         System.out.println("login_error======>"+login_error);
         System.out.println("request======>"+request.getParameter("redirect"));
         System.out.println("loginVO======>"+loginVO);
         System.out.println("getServletPath======>"+request.getServletPath());
         System.out.println("loginVO======>"+loginVO.getName());
         System.out.println("loginVO======>"+loginVO.getUserSe());




        String returnUrl =  request.getParameter("redirect") == null ? "" : (String)request.getParameter("redirect");
        String pageRedirect =  request.getParameter("pageRedirect") == null ? "" : (String)request.getParameter("pageRedirect");

        String message = (String)request.getParameter("message");



        String url ="infomind/com/login/infoCmsLoginUsr";


        String page =infoLoginPageCacheManager.getPage();

        System.out.println("page======>"+page);
        System.out.println("pageRedirect======>"+pageRedirect);

        if("".equals(page)){

            if("page".equals(pageRedirect)){

                infoLoginPageCacheManager.setPage("page");
                url ="page/mypage/siteLoginUsr.mypage";

            }else{
                infoLoginPageCacheManager.setPage("cms");
                url ="infomind/com/login/infoCmsLoginUsr";
            }



        }else if("page".equals(page)){

            infoLoginPageCacheManager.setPage("page");
            url ="page/mypage/siteLoginUsr.mypage";
        }else{

            infoLoginPageCacheManager.setPage("cms");
            url ="infomind/com/login/infoCmsLoginUsr";
        }




         if(auth_error != null && auth_error.equals("1")){
                return "infomind/com/cmm/error/accessDenied";
         }

        if (message!=null) model.addAttribute("message", message);

       // return "infomind/com/login/infoCmsLoginUsr";
        return url;
    }



    /**
     *  중복 로그인 했을 경우 테스트
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = "/cms/actionLogoutTest.do")
    public String Logout(HttpServletRequest request, ModelMap model,HttpServletResponse response) throws Exception {


        System.out.println("/cms/actionLogoutTest.do");

        String referer = (String)request.getHeader("REFERER");

        System.out.println("referer===>"+referer);

        String referer2 = (String)request.getHeader("REFERER")!=null? (String)request.getHeader("REFERER") : "/";

        System.out.println("referer2===>"+referer2);





        return "중복 로그인";
    }


    /**
     *  중복 로그인 했을 경우 테스트
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = "/cms/logoutSuccessUrl.do")
    public String logoutSuccessUrl(HttpServletRequest request, ModelMap model,HttpServletResponse response) throws Exception {


        System.out.println("/cms/logoutSuccessUrl.do");

        String referer = (String)request.getHeader("REFERER");

        System.out.println("logoutSuccessUrl referer===>"+referer);

        String referer2 = (String)request.getHeader("REFERER")!=null? (String)request.getHeader("REFERER") : "/";

        System.out.println("logoutSuccessUrl referer2===>"+referer2);





        return "로그아웃 성공시";
    }

    /**
     *  권한이 없을때
     * @return String
     * @exception Exception
     */
    @RequestMapping(value = "/cms/accessDenied.do")
    public String accessDenied(HttpServletRequest request, ModelMap model,HttpServletResponse response) throws Exception {


            System.out.println("권한이 없을때");




        return "infomind/com/cmm/error/accessDenied";
    }




}
