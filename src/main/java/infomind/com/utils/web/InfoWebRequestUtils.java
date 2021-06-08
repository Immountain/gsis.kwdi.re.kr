package infomind.com.utils.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

public class InfoWebRequestUtils {
    public static void include(PageContext pageContext, String url) throws Exception {
        RequestDispatcher dispatcher = pageContext.getServletContext().getRequestDispatcher(url);
        dispatcher.include(pageContext.getRequest(), pageContext.getResponse());
    }

    public static void include(PageContext pageContext, JspWriter out, String url) {
        try {


            RequestDispatcher dispatcher = pageContext.getServletContext().getRequestDispatcher(url);
            InfoWebIncludeResponseWrapper irw = new InfoWebIncludeResponseWrapper((HttpServletResponse) pageContext.getResponse());
            dispatcher.include(pageContext.getRequest(), irw);
            out.println(irw.getString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
