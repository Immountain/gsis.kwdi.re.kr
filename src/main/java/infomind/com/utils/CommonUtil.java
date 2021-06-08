package infomind.com.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class CommonUtil {

    /**
     * Debug Request Parameter
     *
     * @param request
     * @return String
     */
    public static String debugRequestParameters(HttpServletRequest request) {

        StringBuffer buffer = new StringBuffer();
        buffer.append(request.getServerName() + request.getRequestURI());
        Enumeration<?> paramNames = request.getParameterNames();
        int cnt = 0;
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            if (cnt == 0) {
                buffer.append("?");
            } else {
                buffer.append("&");
            }
            buffer.append(paramName + "=");
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                buffer.append(paramValues[0]);
            } else {
                for (int i = 0; i < paramValues.length; i++) {
                    if (i > 0) {
                        buffer.append(",");
                    }
                    buffer.append(paramValues[i]);
                }
            }
            cnt++;
        }

        return buffer.toString();
    }
    /**
     *  인증 호출시 저장한 쿠키값 조회
     * @param request
     * @return
     */
    public static String[] getCookieReqNum(HttpServletRequest request) {
        //쿠키값 가져 오기
        Cookie[] cookies = request.getCookies();
        String reqName = "";
        String[] reqNum = new String[2];
        if(cookies!=null){
            for (int i = 0; i < cookies.length; i++){
                Cookie c = cookies[i];
                reqName = c.getName();
                reqNum = c.getValue().split(",");
                if(reqName.compareTo("reqNum")==0) break;

                reqNum = null;
            }
        }
        return reqNum;
    }

    public static String getSessionSiteCode(HttpServletRequest request) {
        String siteCode = "";

        siteCode = (String) request.getSession().getAttribute("siteCode");

        if (StringUtil.isEmpty(siteCode)) {
            siteCode = "30";
        }

        return siteCode;
    }

}
