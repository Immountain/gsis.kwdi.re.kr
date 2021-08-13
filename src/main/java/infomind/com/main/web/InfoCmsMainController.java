package infomind.com.main.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import gsis.com.cms.banner.vo.JewBannerVO;
import gsis.com.cms.data.dao.JejuDataDAO;
import gsis.com.cms.data.service.JejuDataService;
import gsis.com.cms.data.vo.JewThemaFileHisVO;
import infomind.com.cms.mnu.mpm.service.InfoMenuManageService;
import infomind.com.cms.mnu.mpm.vo.InfoMenuManageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Controller
public class InfoCmsMainController {


    /**
     * EgovMessageSource
     */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * EgovMenuManageService
     */
    @Resource(name = "infoMenuManageService")
    private InfoMenuManageService menuManageService;

    @Resource(name="JejuDataService")
    private JejuDataService jejuDataService;



    /**
     * log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InfoCmsMainController.class);

    /**
     * 로그인 후 메인화면으로 들어간다
     *
     * @param
     * @return 로그인 페이지
     * @throws Exception
     */
    @RequestMapping(value = "/cms/index.do")
    //  @RequestMapping({"/cms/index.do", "/cms"})
    public String actionInfoCmsMain(ModelMap model) throws Exception {

        // 1. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        System.out.println("isAuthenticated==>" + isAuthenticated);


        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "infomind/com/login/infoCmsLoginUsr";
        }


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

        LOGGER.debug("User Id : {}", user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

        /*	*/
        // 2. 메뉴조회
        InfoMenuManageVO menuManageVO = new InfoMenuManageVO();
        menuManageVO.setTmpId(user.getId());
        menuManageVO.setTmpUserSe(user.getUserSe());
        menuManageVO.setTmpName(user.getName());
        menuManageVO.setTmpEmail(user.getEmail());
        menuManageVO.setTmpOrgnztId(user.getOrgnztId());
        menuManageVO.setTmpUniqId(user.getUniqId());


        List<InfoMenuManageVO> list_headmenu = menuManageService.selectMainMenuAll(menuManageVO);


        Map<Integer, InfoMenuManageVO> menuMaps = list_headmenu.stream()
                .collect(toMap(InfoMenuManageVO::getMenuNo, Function.identity()));


        Consumer<InfoMenuManageVO> setChildMenu = m -> {
            InfoMenuManageVO parentMenu = menuMaps.get(m.getUpperMenuId());
            if (parentMenu != null)
                parentMenu.addChildMenu(m);
        };

        list_headmenu.forEach(setChildMenu::accept);

        List<InfoMenuManageVO> parentMenus = list_headmenu.stream()
                .filter(m -> m.getUpperMenuId() == 0)
                .sorted(Comparator.comparingInt(InfoMenuManageVO::getMenuOrdr))
                .collect(toList());


        model.addAttribute("list_headmenu", parentMenus);


        // 3. 메인 페이지 이동
        String main_page = Globals.MAIN_PAGE;

        LOGGER.debug("Globals.MAIN_PAGE > " + Globals.MAIN_PAGE);
        LOGGER.debug("main_page > {}", main_page);


        return "infomind/com/main/InfoCmsMainView.main";
    }


    @RequestMapping(value = "/cms")
    public String actionCmsMain(ModelMap model) throws Exception {


        return "redirect:/cms/index.do";
    }


    @RequestMapping(value = "/cms/dashboard.do")
    public String dashboard(ModelMap model) throws Exception {

       // List<WjMainDashBoardVO> wjMainDashBoardVOList = wjMainDashBoardServcie.getPeopleCountSiteMain(null);


        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd일 HH시");
        Date time = new Date();
        String formatTime = format.format(time);
        model.addAttribute("formatTime", formatTime);


        List<JewThemaFileHisVO> updateList = new ArrayList<>();
        updateList =jejuDataService.getSelectMainUpdateList(null);
        model.addAttribute("updateList", updateList);





        //   model.addAttribute("wjMainDashBoardVOList", wjMainDashBoardVOList);
        return "infomind/com/main/InfoCmsDashBoardView.sub";
    }


}