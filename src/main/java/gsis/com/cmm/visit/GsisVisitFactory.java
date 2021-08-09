package gsis.com.cmm.visit;
import gsis.com.cms.data.vo.JewThemaFileHisVO;
import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import infomind.com.cms.visit.vo.InfoCmsVisitVO;
import infomind.com.exception.SiteNotFoundException;
import infomind.com.file.vo.InfoFileDownloadInfoVO;
import infomind.com.utils.http.InfoHttpUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class GsisVisitFactory {





    public static final String IS_MOBILE = "MOBILE";
    private static final String IS_PHONE = "PHONE";
    public static final String IS_TABLET = "TABLET";
    public static final String IS_PC = "PC";


    public static JewThemaFileHisVO ThemaFileHis(HttpServletRequest request) {
        JewThemaFileHisVO v = new JewThemaFileHisVO();
        String ip = InfoHttpUtils.getClientIpAddr(request);
        String browser = InfoHttpUtils.getClientBrowser(request);
        String os = InfoHttpUtils.getClientOS(request);
        String userAgent = InfoHttpUtils.getUserAgent(request);
        String browser_s = InfoHttpUtils.getClientBrowser_s(request);
        String loc = request.getLocale().getLanguage().toLowerCase();
        String referer_url =request.getHeader("referer");
        String site_url =request.getServletPath();
        String isDevice =isDevice(userAgent);

        v.setBrowser(browser);
        v.setIp(ip);
        v.setOs(os);
        v.setUserAgent(userAgent);

        v.setLoc(loc);
        v.setBrowserS(browser_s);
        v.setRefererUrl(referer_url);
        v.setIsDeviec(isDevice);

        return v;
    }

    /**
     * 모바일,타블렛,PC구분
     * @param userAgent
     * @return
     */
    public static String isDevice(String userAgent) {


        if(userAgent.indexOf(IS_MOBILE) > -1) {
            if(userAgent.indexOf(IS_PHONE) == -1){

                return IS_MOBILE;
            }else{
                return IS_TABLET;
            }

        } else{

            return IS_PC;
        }

    }
}
