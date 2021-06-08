package infomind.com.cms.mnu.mcm.web;


import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.mnu.mcm.web.EgovMenuCreateManageController;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.mnu.mcm.service.InfoMenuCreateManageService;
import infomind.com.cms.mnu.mcm.vo.InfoMenuCreatVO;
import infomind.com.cms.mnu.mcm.vo.InfoMenuSiteMapVO;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class InfoMenuCreateManageController {

    /* Validator */
    @Autowired
    private DefaultBeanValidator beanValidator;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovMenuManageService */
    @Resource(name = "infoMeunCreateManageService")
    private InfoMenuCreateManageService infoMenuCreateManageService;

    private String pagePath ="mnu/mcm/";

    /**
     * *메뉴생성목록을 조회한다.
     *
     * @param searchVO
     *            ComDefaultVO
     * @return 출력페이지정보 "sym/mnu/mcm/EgovMenuCreatManage"
     * @exception Exception
     */
    @IncludedInfo(name = "메뉴생성관리", order = 1100, gid = 60)
    @RequestMapping(value = "/cms/mnu/mcm/InfoMenuCreatManageSelect.do")
    public String selectMenuCreatManagList(@ModelAttribute("searchVO") CmsSearchVO searchVO, ModelMap model) throws Exception {
        String resultMsg = "";
        // 내역 조회
        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		/*
		if (searchVO.getSearchKeyword() != null && !searchVO.getSearchKeyword().equals("")) {

			int IDcnt = menuCreateManageService.selectUsrByPk(searchVO);
			if (IDcnt == 0) {
				resultMsg = egovMessageSource.getMessage("info.nodata.msg");
			} else {
				// AuthorCode 검색
				MenuCreatVO vo = new MenuCreatVO();
				vo = menuCreateManageService.selectAuthorByUsr(searchVO);
				searchVO.setSearchKeyword(vo.getAuthorCode());
			}
		}
		*/
        List<?> list_menumanage = infoMenuCreateManageService.selectMenuCreatManagList(searchVO);
        if ( list_menumanage.size() == 0 )
            resultMsg = egovMessageSource.getMessage("info.nodata.msg");
        model.addAttribute("list_menumanage", list_menumanage);

        int totCnt = infoMenuCreateManageService.selectMenuCreatManagTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("resultMsg", resultMsg);

        //return "egovframework/com/sym/mnu/mcm/EgovMenuCreatManage";
        return InfoViewUtils.adminTilesView(pagePath,"InfoMenuCreatManage","ax5ui");
    }

    /* 메뉴생성 세부조회 */
    /**
     * 메뉴생성 세부화면을 조회한다.
     *
     * @param menuCreatVO
     *            MenuCreatVO
     * @return 출력페이지정보 "sym/mnu/mcm/EgovMenuCreat"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/mnu/mcm/InfoMenuCreatSelect.do")
    public String selectMenuCreatList(@ModelAttribute("menuCreatVO") InfoMenuCreatVO menuCreatVO, ModelMap model) throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        List<?> list_menulist = infoMenuCreateManageService.selectMenuCreatList(menuCreatVO);
        model.addAttribute("list_menulist", list_menulist);
        model.addAttribute("resultVO", menuCreatVO);

        // return "egovframework/com/sym/mnu/mcm/EgovMenuCreat";
        return InfoViewUtils.adminTilesView(pagePath,"InfoMenuCreat","axmodal");
    }

    /**
     * 메뉴생성처리 및 메뉴생성내역을 등록한다.
     *
     * @param checkedAuthorForInsert
     *            String
     * @param checkedMenuNoForInsert
     *            String
     * @return 출력페이지정보 등록처리시 "forward:/sym/mnu/mcm/EgovMenuCreatSelect.do"
     * @exception Exception
     */
    @RequestMapping("/cms/mnu/mcm/InfoMenuCreatInsert.do")
    public String insertMenuCreatList(@RequestParam("checkedAuthorForInsert") String checkedAuthorForInsert, @RequestParam("checkedMenuNoForInsert") String checkedMenuNoForInsert,
                                      @ModelAttribute("menuCreatVO") InfoMenuCreatVO menuCreatVO, ModelMap model) throws Exception {
        String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        String[] insertMenuNo = checkedMenuNoForInsert.split(",");
        if (insertMenuNo == null || (insertMenuNo.length == 0)) {
            resultMsg = egovMessageSource.getMessage("fail.common.insert");
        } else {
            infoMenuCreateManageService.insertMenuCreatList(checkedAuthorForInsert, checkedMenuNoForInsert);
            resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        model.addAttribute("resultMsg", resultMsg);
        return "forward:/sym/mnu/mcm/EgovMenuCreatSelect.do";
    }

    /* 메뉴사이트맵 생성조회 */
    /**
     * 메뉴사이트맵을 생성할 내용을 조회한다.
     *
     * @param menuSiteMapVO
     *            MenuSiteMapVO
     * @return 출력페이지정보 등록처리시 "sym/mnu/mcm/EgovMenuCreatSiteMap"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/mnu/mcm/InfoMenuCreatSiteMapSelect.do")
    public String selectMenuCreatSiteMap(@ModelAttribute("menuSiteMapVO") InfoMenuSiteMapVO menuSiteMapVO, ModelMap model) throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        List<?> list_menulist = infoMenuCreateManageService.selectMenuCreatSiteMapList(menuSiteMapVO);
        model.addAttribute("list_menulist", list_menulist);
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        menuSiteMapVO.setCreatPersonId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
        model.addAttribute("resultVO", menuSiteMapVO);
        return "egovframework/com/sym/mnu/mcm/EgovMenuCreatSiteMap";
    }

    /**
     * 메뉴사이트맵 생성처리 및 사이트맵을 등록한다.
     *
     * @param menuSiteMapVO
     *            MenuSiteMapVO
     * @param valueHtml
     *            String
     * @return 출력페이지정보 "sym/mnu/mcm/EgovMenuCreatSiteMap"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/mnu/mcm/InfoMenuCreatSiteMapInsert.do")
    public String selectMenuCreatSiteMapInsert(@ModelAttribute("menuSiteMapVO") InfoMenuSiteMapVO menuSiteMapVO, @RequestParam("valueHtml") String valueHtml, ModelMap model
            , HttpServletResponse response)
            throws Exception {
        boolean chkCreat = false;
        String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

		/*menuSiteMapVO.setTmpRootPath(EgovProperties.RELATIVE_PATH_PREFIX
				+ ".." + System.getProperty("file.separator") + ".."
				+ System.getProperty("file.separator") + "..");*/

        /* 사이트맵 파일 생성 위치 지정 */
        //String currentPath = EgovMenuCreateManageController.class.getResource("").getPath();
        String currentPath = EgovMenuCreateManageController.class.getProtectionDomain().getCodeSource() == null ? "" : EgovStringUtil.isNullToString(EgovMenuCreateManageController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        //System.out.println("===>>> currentPath = "+currentPath);
        String path = currentPath.substring(0, currentPath.lastIndexOf("WEB-INF"));
        menuSiteMapVO.setTmpRootPath(path);
        menuSiteMapVO.setBndeFilePath("/html/egovframework/com/sym/mnu/mcm/");
        //System.out.println("===>>> path = "+path);
        //System.out.println("===>>> menuSiteMapVO.getMapCreatId() = "+menuSiteMapVO.getMapCreatId());

        /*
         * 사이트맵 파일 생성 위치 지정 if ("WINDOWS".equals(Globals.OS_TYPE)) {
         * menuSiteMapVO
         * .setTmp_rootPath("D:/egovframework/workspace/egovcmm/src/main/webapp"
         * ); }else{menuSiteMapVO.setTmp_rootPath(
         * "/product/jeus/webhome/was_com/egovframework-com-1_0/egovframework-com-1_0_war___"
         * ); }
         */
        chkCreat = infoMenuCreateManageService.creatSiteMap(menuSiteMapVO, valueHtml);
        if (!chkCreat) {
            resultMsg = egovMessageSource.getMessage("fail.common.insert");
        } else {
            resultMsg = egovMessageSource.getMessage("success.common.insert");
        }
        List<?> list_menulist = infoMenuCreateManageService.selectMenuCreatSiteMapList(menuSiteMapVO);

        model.addAttribute("list_menulist", list_menulist);
        model.addAttribute("resultVO", menuSiteMapVO);
        model.addAttribute("resultMsg", resultMsg);

        return "egovframework/com/sym/mnu/mcm/EgovMenuCreatSiteMap";
    }

    /* 메뉴사이트맵 생성조회 */
    /**
     * 메뉴사이트맵을 생성할 내용을 조회한다.
     *
     * @param menuSiteMapVO
     *            MenuSiteMapVO
     * @return 출력페이지정보 등록처리시 "sym/mnu/mcm/EgovMenuCreatSiteMap"
     * @exception Exception
     */
    @RequestMapping(value = "/cms/mnu/mcm/InfoSiteMap.do")
    public String selectSiteMap(@ModelAttribute("menuCreatVO") InfoMenuSiteMapVO menuSiteMapVO, ModelMap model) throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        menuSiteMapVO.setCreatPersonId(user == null ? "" : EgovStringUtil.isNullToString(user.getId()));

        List<?> list_menulist = infoMenuCreateManageService.selectSiteMapByUser(menuSiteMapVO);
        model.addAttribute("list_menulist", list_menulist);

        model.addAttribute("resultVO", menuSiteMapVO);
        return "egovframework/com/sym/mnu/mcm/EgovSiteMap";
    }
}
