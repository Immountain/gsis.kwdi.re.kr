package infomind.com.cmm.service;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.log.clg.service.EgovLoginLogService;
import egovframework.com.sym.log.clg.service.LoginLog;
import infomind.com.cmm.bean.InfoLoginPageCacheManager;
import infomind.com.cmm.support.ApplicationContextProvider;
import infomind.com.cms.info.board.service.InfoBoardItemService;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.cms.sym.log.clg.service.InfoLoginLogService;
import infomind.com.cms.sym.log.clg.vo.InfoLoginLogVO;
import infomind.com.exception.SiteNotFoundException;
import infomind.com.utils.web.InfoWebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {


    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

    @Resource
    private InfoLoginPageCacheManager infoLoginPageCacheManager;


//    @Resource(name="EgovLoginLogService")
//    private EgovLoginLogService loginLogService;


//    public LoginSuccessHandler(String defaultTargetUrl) {
//
//        setDefaultTargetUrl(defaultTargetUrl);
//    }

    /**
     * 인증에 성공할 경우 아래 매서드로 이동.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession();

        System.out.println("인증에 성공할 경우 아래 매서드로 이동");

        String uniqId = "";
        String ip = "";

        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        InfoLoginLogService loginLogService = applicationContext.getBean(InfoLoginLogService.class);

        /* Authenticated  */
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//       System.out.println("isAuthenticated====>"+isAuthenticated);


        if (isAuthenticated.booleanValue()) {
            LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
            uniqId = (user == null || user.getUniqId() == null) ? "" : user.getUniqId();
            ip = (user == null || user.getIp() == null) ? "" : user.getIp();
        }

        InfoLoginLogVO loginLog = new InfoLoginLogVO();
        loginLog.setLoginId(uniqId);
        loginLog.setLoginIp(request.getRemoteAddr());
        loginLog.setLoginMthd("I"); // 로그인:I, 로그아웃:O
        loginLog.setErrOccrrAt("N");
        loginLog.setErrorCode("");


        try {

            loginLogService.logInsertLoginLog(loginLog);

        } catch (Exception e) {

//            System.out.println("e===>"+e);

        }


        resultRedirectStrategy(request, response, authentication);

    }


    protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
                                          Authentication authentication) throws IOException, ServletException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        StringBuilder sb = new StringBuilder();
        sb.append("/");
        String redirect = request.getParameter("redirect");
        String pageRedirect = request.getParameter("pageRedirect") == null ? "" : (String) request.getParameter("pageRedirect");

        // 이전사이트 정보
        InfoSiteVO infoSite = null;
        try {
            infoSite = InfoWebUtils.getRefererSiteInfo((HttpServletRequest) request);

            if(StringUtils.isNotEmpty(infoSite.getSubPath())) {
                sb.append(infoSite.getSubPath());
            }
        } catch (SiteNotFoundException e) {

        }

        try {
            infoLoginPageCacheManager.loadLoginPage();
        } catch (Exception e) {
        }

        if ("page".equals(pageRedirect)) {
            redirectStratgy.sendRedirect(request, response, sb.toString());
        } else {
            if (savedRequest != null) {
                System.out.println("인증에 성공할 경우 아래 매서드로 이동 1");
                String targetUrl = savedRequest.getRedirectUrl();

                System.out.println("targetUrl===>" + targetUrl);
                redirectStratgy.sendRedirect(request, response, targetUrl);
            } else {
                System.out.println("인증에 성공할 경우 아래 매서드로 이동 2");
                redirectStratgy.sendRedirect(request, response, redirect);
            }
        }
    }

}
