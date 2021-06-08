package infomind.com.cmm.interceptor;

import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmsSessionInterceptor extends HandlerInterceptorAdapter {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{


        System.out.println("CmsSessionInterceptor");

        boolean result = false;
        String webRoot = request.getContextPath();

        try {
            String id = (String) request.getSession().getAttribute("oprId");
            if(id == null ){
                if(isAjaxRequest(request)){
                    response.sendError(400);
                    return false;
                }else{
                    response.sendRedirect(webRoot + "/loginPage.do");
                    result =  false;
                }
            }else{
                result =  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
        return result;
    }

    private boolean isAjaxRequest(HttpServletRequest req) {
        String header = req.getHeader("AJAX");
        if ("true".equals(header)){
            return true;
        }else{
            return false;
        }
    }



}
