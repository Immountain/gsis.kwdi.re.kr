package infomind.com.cmm.interceptor;

import infomind.com.cmm.bean.InfoUserMenuCacheManager;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.utils.http.InfoHttpUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private InfoUserMenuCacheManager cacheUserMenu;

    String slug = "";

    InfoSiteMenuVO menu = new InfoSiteMenuVO();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);
        slug = InfoHttpUtils.getPageSlugPath(request);
        menu = cacheUserMenu.getSiteMenuBySlug(infoSite, slug);

        request.setAttribute("menuInfo", menu);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
        request.setAttribute("menuInfo", menu);
    }
}
