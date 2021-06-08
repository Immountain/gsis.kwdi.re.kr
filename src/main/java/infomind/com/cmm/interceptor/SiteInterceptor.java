package infomind.com.cmm.interceptor;

import egovframework.com.cmm.LoginVO;
import infomind.com.cmm.bean.InfoUserMenuCacheManager;
import infomind.com.cmm.visit.InfoVisitFactory;
import infomind.com.cms.info.board.service.InfoBoardAuthService;
import infomind.com.cms.info.site.service.InfoSiteVisitService;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import infomind.com.utils.http.InfoHttpUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SiteInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private InfoUserMenuCacheManager cacheUserMenu;

    @Resource(name = "InfoSiteVisitService")
    private InfoSiteVisitService infoSiteVisitService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);
        InfoSiteMenuVO menu = new InfoSiteMenuVO();
        String url = InfoHttpUtils.getUrl(request);

        if (StringUtils.isNotEmpty(url)) {

            if(StringUtils.isNotEmpty(request.getContextPath())) {
                url = url.replace(request.getContextPath(), "");
            }

            if(StringUtils.isNotEmpty(infoSite.getSubPath())) {
                url = url.replace("/" + infoSite.getSubPath(), "");
            }


           try {
               menu = cacheUserMenu.getMenuByUrl(infoSite, url);
           }catch (Exception e){

               menu= new InfoSiteMenuVO();
           }



            if (StringUtils.isEmpty(menu.getSiteMemuId())) {
                String urlPath = InfoHttpUtils.getUrlPath(request);
                //비슷한 패스에 애들 가져온다.
                menu = cacheUserMenu.getMenuPathUrl(urlPath);
            }
        }

        //로그인 여부 확인
        if (StringUtils.isNotEmpty(menu.getSiteMemuId())) {
            InfoSiteVisitVO visit = InfoVisitFactory.fromRequest(request);
            visit.setPageType(menu.getSlug());
            visit.setVisitType("SITE");
            infoSiteVisitService.insertSiteVisit(visit);

            if ("Y".equals(menu.getLoginYn())) {
                LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
                if (user == null || user.getUniqId() == null) {
                    response.sendRedirect(request.getContextPath() + "/mypage/login.do");
                    return false;
                }
            }
        }

        request.setAttribute("menuInfo", menu);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
        // request.setAttribute("menuInfo", menu);
    }
}
