package infomind.com.cmm.filter;

import infomind.com.utils.web.InfoReadableRequestWrapper;
import infomind.com.utils.web.InfoWebIncludeResponseWrapper;

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

public class CmsLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        // Do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

            InfoReadableRequestWrapper wrapper = new InfoReadableRequestWrapper((HttpServletRequest)request);
            chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {
        // Do nothing
    }

}
