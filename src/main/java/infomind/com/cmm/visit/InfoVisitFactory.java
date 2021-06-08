package infomind.com.cmm.visit;
import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import infomind.com.cms.visit.vo.InfoCmsVisitVO;
import infomind.com.exception.SiteNotFoundException;
import infomind.com.file.vo.InfoFileDownloadInfoVO;
import infomind.com.utils.http.InfoHttpUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class InfoVisitFactory {





    public static final String IS_MOBILE = "MOBILE";
    private static final String IS_PHONE = "PHONE";
    public static final String IS_TABLET = "TABLET";
    public static final String IS_PC = "PC";

    public static InfoSiteVisitVO fromRequest(HttpServletRequest request) {

        InfoSiteVO infoSiteVO = (InfoSiteVO) WebUtils.getSessionAttribute(request, InfoConstants.SESSION_SITE_INFO_NAME);
        if(infoSiteVO == null) {
            throw new SiteNotFoundException("사이트 정보가 없습니다.");
        }

        InfoSiteVisitVO infoSiteVisit = InfoSiteVisitVO
                .builder()
                .browser(InfoHttpUtils.getClientBrowser(request))
                .ip(InfoHttpUtils.getClientIpAddr(request))
                .os(InfoHttpUtils.getClientOS(request))
                .userAgent(InfoHttpUtils.getUserAgent(request))
                .siteId(infoSiteVO.getSiteId())
                .loc(request.getLocale().getLanguage().toLowerCase())
                .browserS(InfoHttpUtils.getClientBrowser_s(request))
                .refererUrl(request.getHeader("referer"))
                .siteUrl(request.getServletPath())
                .isDeviec(isDevice(InfoHttpUtils.getUserAgent(request)))
                .build();

        return infoSiteVisit;
    }


    public static InfoCmsVisitVO fromRequest(HttpServletRequest request,String menuTargetNo) {
        InfoCmsVisitVO v = new InfoCmsVisitVO();
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
        v.setCmsMenuTargetNo(menuTargetNo);
        v.setLoc(loc);
        v.setBrowserS(browser_s);
        v.setRefererUrl(referer_url);
        v.setSiteUrl(site_url);
        v.setIsDeviec(isDevice);

        return v;
    }




    public static InfoFileDownloadInfoVO downliadRequest(HttpServletRequest request) {
        InfoFileDownloadInfoVO v = new InfoFileDownloadInfoVO();
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
