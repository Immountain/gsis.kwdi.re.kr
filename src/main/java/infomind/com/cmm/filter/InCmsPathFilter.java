package infomind.com.cmm.filter;

import infomind.com.cmm.InfoConstants;
import infomind.com.cmm.bean.InfoSiteCacheManager;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.exception.SiteNotFoundException;
import infomind.com.utils.MatchesUtils;
import infomind.com.utils.http.InfoHttpUtils;
import infomind.com.utils.web.InfoViewUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

@Service("inCmsPathFilter")
public class InCmsPathFilter implements Filter {

    @SuppressWarnings("unused")
    private FilterConfig config;

    @Resource(name = "infoSiteCacheManager")
    private InfoSiteCacheManager infoSiteCacheManager;

    private final String[] CMS_PATH = {
            "/cms/**"
    };
    private final String[] RESOURCE_PATH = {
            "/site/**", "/source/**", "/assets/**",
            "/css/**", "/html/**", "/images/**", "/fonts/**", "/js/**", "/resource/**", "/favicon.ico"
    };

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        switch (InfoConstants.SERVICE_MODE()) {
            case InfoConstants.SERVICE_MODE_SITE:
                if (Arrays.stream(CMS_PATH).anyMatch(path -> MatchesUtils.checkAntPattern(path, request.getRequestURI()))) {
                    throw new SiteNotFoundException();
                }
                break;
            case InfoConstants.SERVICE_MODE_CMS:
                if (Stream.concat(Arrays.stream(CMS_PATH), Arrays.stream(RESOURCE_PATH)).noneMatch(path -> MatchesUtils.checkAntPattern(path, request.getRequestURI()))) {
                    throw new SiteNotFoundException();
                }
                break;
            case InfoConstants.SERVICE_MODE_ALL:
            default:

        }

        // 제외 대상 항목 체크
        if (Stream.concat(Arrays.stream(CMS_PATH), Arrays.stream(RESOURCE_PATH)).anyMatch(path -> MatchesUtils.checkAntPattern(path, request.getRequestURI()))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 이전 사이트 정보 조회
        try {
            InfoSiteVO refererInfoSite = InfoWebUtils.getCurrentSiteInfo(request);
            WebUtils.setSessionAttribute(request, InfoConstants.REFERER_SITE_INFO_NAME, refererInfoSite);
        }catch (SiteNotFoundException e) {
            WebUtils.setSessionAttribute(request, InfoConstants.REFERER_SITE_INFO_NAME, null);
        }

        // 현재 사이트 정보 조회
        InfoSiteVO param = getSiteParameter(request);
        InfoSiteVO infoSite = infoSiteCacheManager.findSiteInfo(param);

        if (infoSite == null) {
            WebUtils.setSessionAttribute(request, InfoConstants.SESSION_SITE_INFO_NAME, null);
            throw new SiteNotFoundException("사이트 정보가 없습니다.");
        }
        WebUtils.setSessionAttribute(request, InfoConstants.SESSION_SITE_INFO_NAME, infoSite);

        String uri = request.getServletPath();
        String path = "";
        String subPath = "";
        boolean isRediretion = false;

        if (!param.getSubPath().equals(infoSite.getSubPath())) {
            int idx = StringUtils.isNotEmpty(param.getSubPath()) ? param.getSubPath().length() + 1 : 0;
            subPath = (StringUtils.isNotEmpty(infoSite.getSubPath()) ? "/" : "") + infoSite.getSubPath();
            path = uri.substring(idx, uri.length());
            isRediretion = true;
        } else if (StringUtils.isNotEmpty(param.getSubPath())) {
            int idx = StringUtils.isNotEmpty(param.getSubPath()) ? param.getSubPath().length() + 1 : 0;
            subPath = (StringUtils.isNotEmpty(infoSite.getSubPath()) ? "/" : "") + infoSite.getSubPath();
            path = uri.substring(idx, uri.length());
        } else {
            path = uri.substring(0, uri.length());
        }

        if (isRediretion) {
            response.sendRedirect(subPath + path);
            return;
        }

        // 추후 [INFO_SITE]에 따른 대표 언어 코드 설정
        Locale locale = new Locale.Builder().setLanguage(infoSite.getLangCd()).build();
        WebUtils.setSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
        LocaleContextHolder.setLocale(locale);

        request.setAttribute("clientLanguage", locale.getLanguage());

        System.out.println(String.format("[INFO-%s] %s", this.getClass().getName(), path));

        request.getRequestDispatcher(path).forward(servletRequest, servletResponse);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void destroy() {

    }

    private InfoSiteVO getSiteParameter(HttpServletRequest request) {
        String host = InfoHttpUtils.getHostWithAppendWWW(request);
        String subPath = InfoHttpUtils.getSubPath(request);

        // 입력 언어 코드 (in_lang 이란 파라미터는 프로퍼티 쪽으로 빠져야할 듯 함.)
        String inputLang = request.getParameter("in_lang");

        InfoSiteVO infoSiteVO = InfoSiteVO.builder()
                .host(host)
                .subPath("checkRequest")
                .build();

        // 서브패스 어권 조회
        if (StringUtils.isNotEmpty(inputLang)) {
            if (infoSiteCacheManager.isSiteHostLangPathUsed(infoSiteVO, inputLang)) {
                infoSiteVO.setLangCd(inputLang);
            }
        }

        if (StringUtils.isNotEmpty(subPath)) {
            if (infoSiteCacheManager.isSiteHostSubPathUsed(infoSiteVO, subPath)) {
                infoSiteVO.setSubPath(subPath);
            }
        }
        return infoSiteVO;
    }
}
