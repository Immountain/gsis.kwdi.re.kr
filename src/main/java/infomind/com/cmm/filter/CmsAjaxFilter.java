package infomind.com.cmm.filter;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import infomind.com.utils.web.InfoReadableRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *  AjaxFilter 정의한다
 *
 * @author 인포마인드 개발팀 양진혁
 * @since 2020.10.08
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *	     수정일		수정자          			 수정내용
 *  ---------  -------   ---------------------------
 *  2020.10.08	양진혁         최초 생성
 *
 *
 *
 *      </pre>
 */

public class CmsAjaxFilter implements Filter {
    private int errorCode = 401;
    private String ajaxHaeder = "AJAX";

    private static final Logger LOG = LoggerFactory.getLogger(CmsAjaxFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;







            System.out.println("CmsAjaxFilter 시작");
            System.out.println("CmsAjaxFilter 확인"+isAjaxRequest(req));


            //파일 업로드 일시
            if(isFileUploadRequest(req)){

                System.out.println("파일 업로드  일때 그냥 패스");
                filterChain.doFilter(req, res);

            }else{

                InfoReadableRequestWrapper wrapper = new InfoReadableRequestWrapper((HttpServletRequest)request);
                if(isAjaxRequest(req)) {
                    System.out.println("CmsAjaxFilter 성공");

                    try {
                        LoginVO user = (LoginVO)((HttpServletRequest) request).getSession().getAttribute("loginVO");
                        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
                        LOG.debug("user===>"+user);
                        LOG.debug("isAuthenticated===>"+isAuthenticated);

                        if(user!=null){
                            // 변경 값 wrapper 로 보내줘여됨...
                            filterChain.doFilter(wrapper, res);
                        }else{

                            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        }
                    } catch (AccessDeniedException e) {
                        res.sendError(HttpServletResponse.SC_FORBIDDEN);
                    } catch (AuthenticationException e) {

                        res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }

                } else{

                    System.out.println("CmsAjaxFilter 실패");
                    filterChain.doFilter(req, res);
                }


            }




        } catch (IOException e) {
            throw e;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    private boolean isAjaxRequest(HttpServletRequest req) {

        return req.getHeader(ajaxHaeder) != null && req.getHeader(ajaxHaeder).equals(Boolean.TRUE.toString());

    }



    private boolean isFileUploadRequest(HttpServletRequest aRequest){

        return aRequest.getMethod().equalsIgnoreCase("POST") && aRequest.getContentType().startsWith("multipart/form-data");

    }


}
