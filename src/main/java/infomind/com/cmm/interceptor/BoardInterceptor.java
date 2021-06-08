package infomind.com.cmm.interceptor;

import egovframework.com.cmm.LoginVO;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import infomind.com.cmm.bean.InfoUserMenuCacheManager;
import infomind.com.cmm.visit.InfoVisitFactory;
import infomind.com.cms.info.board.service.InfoBoardAuthService;
import infomind.com.cms.info.board.service.InfoBoardService;
import infomind.com.cms.info.board.vo.InfoBoardAuthVO;
import infomind.com.cms.info.board.vo.InfoBoardVO;
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

public class BoardInterceptor extends HandlerInterceptorAdapter {

    @Resource(name = "InfoBoardService")
    private InfoBoardService infoBoardService;

    @Resource
    private InfoUserMenuCacheManager cacheUserMenu;

    @Resource
    private InfoBoardAuthService infoBoardAuthService;

    @Resource(name = "InfoSiteVisitService")
    private InfoSiteVisitService infoSiteVisitService;

    String slug = "";
    InfoSiteMenuVO menu = new InfoSiteMenuVO();
    InfoBoardVO boardVO = new InfoBoardVO();
    String addCss = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);
        slug = InfoHttpUtils.getBoardSlugPath(request);

        menu = cacheUserMenu.getSiteMenuBySlug(infoSite, slug);

        if (StringUtils.isNotEmpty(menu.getMenuPage())) {
            boardVO.setBoardId(menu.getMenuPage());
            //게시판 정보 호출
            boardVO = infoBoardService.selectBoard(boardVO);

            if (StringUtils.isNotEmpty(boardVO.getBoardId())) {
                //메뉴 통계
                InfoSiteVisitVO visit = InfoVisitFactory.fromRequest(request);
                visit.setPageType(slug);
                visit.setTargetId(boardVO.getBoardId());
                visit.setVisitType("BOARD");
                infoSiteVisitService.insertSiteVisit(visit);

                boardVO.setActionListUrl("/board/" + slug + "/list.do");
                boardVO.setActionEditUrl("/board/" + slug + "/edit.do");
                boardVO.setActionInsertUrl("/board/" + slug + "/insert.do");
                boardVO.setActionViewUrl("/board/" + slug + "/view.do");
                boardVO.setActionUpdViewUrl("/board/" + slug + "/updt.do");
                boardVO.setActionUpdateUrl("/board/" + slug + "/update.do");
                boardVO.setActionDeleteUrl("/board/" + slug + "/delete.do");

                addCss = boardVO.getLayoutCssUrl();
                //게시판 권한 넣어준다..
                InfoBoardAuthVO authVO = new InfoBoardAuthVO();
                authVO.setBoardId(boardVO.getBoardId());

                //로그인 체크
                LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
                if (user == null || user.getUniqId() == null) {
                    authVO.setCommonLogin("common_not_login");
                } else {
                    String itemId = "";
                    if (request.getParameter("itemId") != null) {
                        itemId = request.getParameter("itemId");
                    } else {

                        itemId = "";
                    }
                    authVO.setCommonLogin("common_login");
                    authVO.setOrgnzt(user.getOrgnztId());
                    authVO.setGroup(user.getGroupId());
                    authVO.setRegId(user.getUniqId());
                    authVO.setItemId(itemId);
                }
                authVO = infoBoardAuthService.selectBoardAuthInfo(authVO);
                boardVO.setBoardAuth(authVO);
            } else {

                boardVO = new InfoBoardVO();
            }
        }

        request.setAttribute("boardVO", boardVO);
        request.setAttribute("menuInfo", menu);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

        request.setAttribute("menuInfo", menu);
        request.setAttribute("addCss", addCss);
        request.setAttribute("boardVO", boardVO);

    }
}
