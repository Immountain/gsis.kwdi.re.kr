package infomind.com.cmm.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 정보 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
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

public class CmsFilter implements Filter {

    private String subPathFilter = ".*";
    private Pattern pattern;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        String subPathFilter = filterConfig.getInitParameter("subPathFilter");
        if (subPathFilter != null) {
            this.subPathFilter = subPathFilter;
        }
        pattern = Pattern.compile(this.subPathFilter);



    }


    public static String getFullURL(HttpServletRequest request) {

        String uri = request.getRequestURI().toString().trim();

        System.out.println("uri===>"+uri);

        return uri;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String contextPath = request.getContextPath();
        String msg ="";
        Matcher m = pattern.matcher(getFullURL((HttpServletRequest) servletRequest));
//        MenuManageVO menuManageVO = new MenuManageVO();
        if (m.matches()) {
            msg ="매칭 성공";

        }else{
            msg ="매칭 실패";
        }

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }

//
//    /** * 요청 래퍼 입니다. */
//    class TestRequestWrapper extends HttpServletRequestWrapper {
//        public TestRequestWrapper(HttpServletRequest request) {
//            super(request);
//
//        } /** * 입력 파라미터에서 <, > 를 제거 합니다. */
//        @Override public String getParameter(String name) {
//            String value = super.getParameter(name); if(value == null) value = "";
//
//
//            return value.replaceAll("[<>]", "");
//        }
//    }


    private boolean excludeUrl(HttpServletRequest request) {
        String uri = request.getRequestURI().toString().trim();


        if (uri.startsWith("/cms/index.do")||uri.startsWith("/cms/index.do")) {
            return true;
        } else {
            return false;
        }

    }
}
