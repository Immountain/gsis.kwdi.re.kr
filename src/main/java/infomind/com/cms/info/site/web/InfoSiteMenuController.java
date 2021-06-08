package infomind.com.cms.info.site.web;


import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cmm.bean.InfoUserMenuCacheManager;
import infomind.com.cms.info.board.service.InfoBoardService;
import infomind.com.cms.info.board.vo.InfoBoardAuthConfigVO;
import infomind.com.cms.info.board.vo.InfoBoardAuthVO;
import infomind.com.cms.info.board.vo.InfoBoardVO;
import infomind.com.cms.info.layout.service.InfoLayoutInfoService;
import infomind.com.cms.info.layout.vo.InfoLayoutInfoVO;
import infomind.com.cms.info.page.service.InfoPageInfoService;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import infomind.com.cms.info.site.service.InfoSiteMenuAuthService;
import infomind.com.cms.info.site.service.InfoSiteMenuGroupService;
import infomind.com.cms.info.site.service.InfoSiteMenuService;
import infomind.com.cms.info.site.service.InfoSiteService;
import infomind.com.cms.info.site.vo.*;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class InfoSiteMenuController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "propertiesService")
    private EgovPropertyService propertiesService;

    @Resource(name = "InfoSiteService")
    private InfoSiteService infoSiteService;

    @Resource(name = "InfoSiteMenuGroupService")
    private InfoSiteMenuGroupService infoSiteMenuGroupService;

    @Resource(name = "infoUserMenuCacheManager")
    private InfoUserMenuCacheManager menuCacheManager;

    @Resource(name = "InfoSiteMenuService")
    private InfoSiteMenuService infoSiteMenuService;

    @Resource(name = "InfoSiteMenuAuthService")
    private InfoSiteMenuAuthService infoSiteMenuAuthService;

    @Resource(name = "InfoLayoutInfoService")
    private InfoLayoutInfoService infoLayoutInfoService;

    @Resource(name = "InfoPageInfoService")
    private InfoPageInfoService infoPageInfoService;

    @Resource(name = "InfoBoardService")
    private InfoBoardService infoBoardService;

    private String pagePath ="info/site/menu/";

    @IncludedInfo(name = "사이트 메뉴 관리", listUrl = "/cms/info/site/menu/menuList.do", order = 1111, gid = 60)
    @RequestMapping(value = "/cms/info/site/menu/menuList.*")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoSiteMenuVO searchVO, ModelMap model) throws Exception {

        InfoSiteVO infoSiteVO = new InfoSiteVO();
        infoSiteVO.setSearchAllPage();
        model.addAttribute("site", infoSiteService.selectSiteList(infoSiteVO));

        InfoSiteMenuGroupVO infoSiteMenuGroupVO = new InfoSiteMenuGroupVO();
        infoSiteMenuGroupVO.setSearchAllPage();
        model.addAttribute("group", infoSiteMenuGroupService.selectSiteMenuGroupList(infoSiteMenuGroupVO));

        InfoLayoutInfoVO layoutInfoVO = new InfoLayoutInfoVO();
        layoutInfoVO.setSearchAllPage();
        model.addAttribute("layout", infoLayoutInfoService.selectLayoutInfoList(layoutInfoVO));

        InfoPageInfoVO infoPageInfoVO = new InfoPageInfoVO();
        infoPageInfoVO.setSearchAllPage();
        model.addAttribute("page", infoPageInfoService.selectPageInfoList(infoPageInfoVO));

        InfoBoardVO infoBoardVO = new InfoBoardVO();
        infoBoardVO.setSearchAllPage();
        model.addAttribute("board", infoBoardService.selectBoardList(infoBoardVO));

        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuList","ax5ui");
    }

    @RequestMapping(value = "/cms/info/site/menu/menuListObject.do")
    @ResponseBody
    public ModelAndView menuListObject(InfoSiteMenuVO searchVO) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("list", infoSiteMenuService.selectSiteAllMenuList(searchVO));
        return modelAndView;
    }

    @RequestMapping(value = "/cms/info/site/menu/menuOneUpdateObject.do")
    @ResponseBody
    public ModelAndView menuOneUpdateObject(@RequestBody InfoSiteMenuVO vo) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);

        String status = infoSiteMenuService.saveSiteMenu(vo);
        switch (status) {
            case "NOT_FOUND":
                modelAndView.addObject("status", "error");
                modelAndView.addObject("message", "수정 대상 정보가 없습니다.");
                break;
            case "DUPLICATE_ID":
                modelAndView.addObject("status", "error");
                modelAndView.addObject("message", "아이디 중복이 있습니다.");
                break;
            case "DUPLICATE_SLUG":
                modelAndView.addObject("status", "error");
                modelAndView.addObject("message", "슬러그 중복이 있습니다.");
                break;
            case "NOT_CHANGED":
                modelAndView.addObject("status", "error");
                modelAndView.addObject("message", "변경내용이 없습니다.");
                break;
            default:
                modelAndView.addObject("status", "success");
                modelAndView.addObject("message", egovMessageSource.getMessage("success.common.update"));
        }

        menuCacheManager.loadMenu();
        return modelAndView;
    }

    @RequestMapping(value = "/cms/info/site/menu/menuUpdateObject.do")
    @ResponseBody
    public ModelAndView menuUpdateObject(@RequestBody List<InfoSiteMenuVO> list) throws Exception {

        infoSiteMenuService.updateList(list, "root");

        menuCacheManager.loadMenu();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("message", egovMessageSource.getMessage("success.common.update"));
        return modelAndView;
    }

    @RequestMapping(value = "/cms/info/site/menu/cacheMenuReload.do")
    @ResponseBody
    public ModelAndView cacheMenuReload(InfoSiteMenuVO searchVO) throws Exception {

        menuCacheManager.loadMenu();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("message", egovMessageSource.getMessage("success.common.update"));
        return modelAndView;
    }

    @IncludedInfo(name="사이트 메뉴 권한 관리",listUrl="/cms/info/site/menu/auth.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/site/menu/auth.*")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoSiteMenuAuthVO searchVO, ModelMap model) throws Exception {
        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuAuthList","axmodal");
    }

    @RequestMapping(value = "/cms/info/site/menu/menuAuthConfigListObject.do")
    @ResponseBody
    public ModelAndView menuAuthConfigListObject(InfoSiteMenuAuthVO searchVO) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list", infoSiteMenuAuthService.selectInfoMenuAuthConfigList(searchVO));

        return modelAndView;
    }

    @RequestMapping(value = "/cms/info/site/menu/menuAuthConfigUpdateObject.do")
    @ResponseBody
    public ModelAndView menuAuthConfigUpdateObject(@RequestParam String siteMemuId, @RequestBody List<InfoSiteMenuAuthConfigVO> list) throws Exception {

        infoSiteMenuAuthService.updateConfigList(siteMemuId, list);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("message", egovMessageSource.getMessage("success.common.update"));
        return modelAndView;
    }
}