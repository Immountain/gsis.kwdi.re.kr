package infomind.com.cms.mnu.mpm.web;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.mnu.mpm.service.InfoMenuManageService;
import infomind.com.cms.mnu.mpm.vo.InfoMenuManageVO;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.utils.web.InfoViewUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 메뉴목록 관리및 메뉴생성, 사이트맵 생성을 처리하는 비즈니스 구현 클래스
 *
 * @author 개발환경 개발팀 이용
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 * 	 2011.07.01	 서준식	   	메뉴정보 삭제시 참조되고 있는 하위 메뉴가 있는지 체크하는 로직 추가
 * 	 2011.07.27	 서준식	   	deleteMenuManageList() 메서드에서 메뉴 멀티 삭제 버그 수정
 * 	 2011.08.26	 정진오		IncludedInfo annotation 추가
 * 	 2011.10.07	 이기하		보안취약점 수정(파일 업로드시 엑셀파일만 가능하도록 추가)
 *  2015.05.28	조정국		메뉴리스트관리 선택시 "정상적으로 조회되었습니다"라는 alert창이 제일 먼저 뜨는것 수정 : 출력메시지 주석처리
 * </pre>
 * @since 2009.06.01
 */

@Controller
public class InfoMenuManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoMenuManageController.class);

    /* Validator */
    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * EgovMenuManageService
     */
    @Resource(name = "infoMenuManageService")
    private InfoMenuManageService infoMenuManageService;

    /**
     * EgovMenuManageService
     */
    @Resource(name = "progrmManageService")
    private EgovProgrmManageService progrmManageService;

    /**
     * EgovMessageSource
     */
    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    private String pagePath = "mnu/mpm/";

    /**
     * 메뉴정보목록을 상세화면 호출 및 상세조회한다.
     *
     * @param req_menuNo String
     * @return 출력페이지정보 "sym/mnu/mpm/EgovMenuDetailSelectUpdt"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuManageListDetailSelect.do")
    public String selectMenuManage(
            @RequestParam("req_menuNo") String req_menuNo,
            @ModelAttribute("searchVO") CmsSearchVO searchVO,
            ModelMap model)
            throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        searchVO.setSearchKeyword(req_menuNo);

        InfoMenuManageVO resultVO = infoMenuManageService.selectMenuManage(searchVO);
        model.addAttribute("menuManageVO", resultVO);

        return InfoViewUtils.adminTilesView(pagePath, "InfoMenuDetailSelectUpdt", "sub");
    }

    /**
     * 메뉴목록 리스트조회한다.
     *
     * @param searchVO CmsSearchVO
     * @return 출력페이지정보 "sym/mnu/mpm/EgovMenuManage"
     * @throws Exception
     */
    @IncludedInfo(name = "메뉴관리리스트", order = 1091, gid = 60)
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuManageSelect.do")
    public String selectMenuManageList(
            @ModelAttribute("searchVO") CmsSearchVO searchVO,
            ModelMap model)
            throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
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

        List<?> list_menumanage = infoMenuManageService.selectMenuManageList(searchVO);
        model.addAttribute("list_menumanage", list_menumanage);

        int totCnt = infoMenuManageService.selectMenuManageListTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath, "InfoMenuManage", "sub");
    }

    /**
     * 메뉴목록 멀티 삭제한다.
     *
     * @param checkedMenuNoForDel String
     * @return 출력페이지정보 "forward:/sym/mnu/mpm/EgovMenuManageSelect.do"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuManageListDelete.do")
    public String deleteMenuManageList(
            @RequestParam("checkedMenuNoForDel") String checkedMenuNoForDel,
            @ModelAttribute("menuManageVO") InfoMenuManageVO menuManageVO,
            ModelMap model)
            throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        String sLocationUrl = null;
        String resultMsg = "";

        String[] delMenuNo = checkedMenuNoForDel.split(",");
        menuManageVO.setMenuNo(Integer.parseInt(delMenuNo[0]));

        if (infoMenuManageService.selectUpperMenuNoByPk(menuManageVO) != 0) {
            resultMsg = egovMessageSource.getMessage("fail.common.delete.upperMenuExist");
            sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuManageSelect.do";
        } else if (delMenuNo == null || (delMenuNo.length == 0)) {
            resultMsg = egovMessageSource.getMessage("fail.common.delete");
            sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuManageSelect.do";
        } else {
            infoMenuManageService.deleteMenuManageList(checkedMenuNoForDel);
            resultMsg = egovMessageSource.getMessage("success.common.delete");
            sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuManageSelect.do";
        }
        model.addAttribute("resultMsg", resultMsg);
        return sLocationUrl;
    }

    /**
     * 메뉴정보를 등록화면으로 이동 및 등록 한다.
     *
     * @param menuManageVO InfoMenuManageVO
     * @param commandMap   Map
     * @return 출력페이지정보 등록화면 호출시 "sym/mnu/mpm/EgovMenuRegist",
     * 출력페이지정보 등록처리시 "forward:/sym/mnu/mpm/EgovMenuManageSelect.do"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuRegistInsert.do")
    public String insertMenuManage(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("menuManageVO") InfoMenuManageVO menuManageVO,
            BindingResult bindingResult,
            ModelMap model)
            throws Exception {
        String sLocationUrl = null;
        String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        if (sCmd.equals("insert")) {
            beanValidator.validate(menuManageVO, bindingResult);
            if (bindingResult.hasErrors()) {
                sLocationUrl = InfoViewUtils.adminTilesView(pagePath, "InfoMenuRegist", "sub");
                return sLocationUrl;
            }
            if (infoMenuManageService.selectMenuNoByPk(menuManageVO) == 0) {
                ComDefaultVO searchVO = new ComDefaultVO();
                searchVO.setSearchKeyword(menuManageVO.getProgrmFileNm());
                if (progrmManageService.selectProgrmNMTotCnt(searchVO) == 0) {
                    resultMsg = egovMessageSource.getMessage("fail.common.insert");
                    sLocationUrl = InfoViewUtils.adminTilesView(pagePath, "InfoMenuRegist", "sub");
                } else {
                    infoMenuManageService.insertMenuManage(menuManageVO);
                    resultMsg = egovMessageSource.getMessage("success.common.insert");
                    sLocationUrl = "forward:/cms/mnu/mpm/EgovMenuManageSelect.do";
                }
            } else {
                resultMsg = egovMessageSource.getMessage("common.isExist.msg");
                sLocationUrl = InfoViewUtils.adminTilesView(pagePath, "InfoMenuRegist", "sub");
            }
            model.addAttribute("resultMsg", resultMsg);
        } else {
            sLocationUrl = InfoViewUtils.adminTilesView(pagePath, "InfoMenuRegist", "sub");
        }
        return sLocationUrl;
    }

    /**
     * 메뉴정보를 수정 한다.
     *
     * @param menuManageVO InfoMenuManageVO
     * @return 출력페이지정보 "forward:/sym/mnu/mpm/EgovMenuManageSelect.do"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuDetailSelectUpdt.do")
    public String updateMenuManage(
            @ModelAttribute("menuManageVO") InfoMenuManageVO menuManageVO,
            BindingResult bindingResult,
            ModelMap model)
            throws Exception {
        String sLocationUrl = null;
        String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        beanValidator.validate(menuManageVO, bindingResult);
        if (bindingResult.hasErrors()) {
            sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuManageListDetailSelect.do";
            return sLocationUrl;
        }
        ComDefaultVO searchVO = new ComDefaultVO();
        searchVO.setSearchKeyword(menuManageVO.getProgrmFileNm());
        if (progrmManageService.selectProgrmNMTotCnt(searchVO) == 0) {
            resultMsg = egovMessageSource.getMessage("fail.common.update");
            sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuManageListDetailSelect.do";
        } else {
            infoMenuManageService.updateMenuManage(menuManageVO);
            resultMsg = egovMessageSource.getMessage("success.common.update");
            sLocationUrl = "forward:/sym/mnu/mpm/InfoMenuManageSelect.do";
        }
        model.addAttribute("resultMsg", resultMsg);
        return sLocationUrl;
    }

    /**
     * 메뉴정보를 삭제 한다.
     *
     * @param menuManageVO InfoMenuManageVO
     * @return 출력페이지정보 "forward:/sym/mnu/mpm/EgovMenuManageSelect.do"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuManageDelete.do")
    public String deleteMenuManage(
            @ModelAttribute("menuManageVO") InfoMenuManageVO menuManageVO,
            ModelMap model)
            throws Exception {
        String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        if (infoMenuManageService.selectUpperMenuNoByPk(menuManageVO) != 0) {
            resultMsg = egovMessageSource.getMessage("fail.common.delete.upperMenuExist");
            model.addAttribute("resultMsg", resultMsg);
            return "forward:/cms/mnu/mpm/InfoMenuManageSelect.do";
        }

        infoMenuManageService.deleteMenuManage(menuManageVO);
        resultMsg = egovMessageSource.getMessage("success.common.delete");
        String _MenuNm = "%";
        menuManageVO.setMenuNm(_MenuNm);
        model.addAttribute("resultMsg", resultMsg);
        return "forward:/cms/mnu/mpm/InfoMenuManageSelect.do";
    }

    /**
     * 메뉴리스트를 조회한다.
     *
     * @param searchVO CmsSearchVO
     * @return 출력페이지정보 "sym/mnu/mpm/EgovMenuList"
     * @throws Exception
     */
    @IncludedInfo(name = "메뉴리스트관리", order = 1090, gid = 60)
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuListSelect.do")
    public String selectMenuList(
            @ModelAttribute("searchVO") CmsSearchVO searchVO,
            ModelMap model)
            throws Exception {
        String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        List<?> list_menulist = infoMenuManageService.selectMenuList();
        resultMsg = egovMessageSource.getMessage("success.common.select");
        model.addAttribute("list_menulist", list_menulist);
//        model.addAttribute("resultMsg", resultMsg);
        return InfoViewUtils.adminTilesView(pagePath, "InfoMenuList", "ax5ui");
    }

    /**
     * 메뉴리스트의 메뉴정보를 등록한다.
     *
     * @param menuManageVO InfoMenuManageVO
     * @return 출력페이지정보 "sym/mnu/mpm/EgovMenuList"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuListInsert.do")
    public String insertMenuList(
            @ModelAttribute("menuManageVO") InfoMenuManageVO menuManageVO,
            BindingResult bindingResult,
            ModelMap model)
            throws Exception {
        String sLocationUrl = null;
        String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        beanValidator.validate(menuManageVO, bindingResult);
        if (bindingResult.hasErrors()) {
            sLocationUrl = InfoViewUtils.adminTilesView(pagePath, "InfoMenuList", "sub");
            return sLocationUrl;
        }

        if (infoMenuManageService.selectMenuNoByPk(menuManageVO) == 0) {
            ComDefaultVO searchVO = new ComDefaultVO();
            searchVO.setSearchKeyword(menuManageVO.getProgrmFileNm());
            if (progrmManageService.selectProgrmNMTotCnt(searchVO) == 0) {
                resultMsg = egovMessageSource.getMessage("fail.common.insert");
                sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuListSelect.do";
            } else {
                infoMenuManageService.insertMenuManage(menuManageVO);
                resultMsg = egovMessageSource.getMessage("success.common.insert");
                sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuListSelect.do";
            }
        } else {
            resultMsg = egovMessageSource.getMessage("common.isExist.msg");
            sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuListSelect.do";
        }
        model.addAttribute("resultMsg", resultMsg);
        return sLocationUrl;
    }

    /**
     * 메뉴리스트의 메뉴정보를 수정한다.
     *
     * @param menuManageVO InfoMenuManageVO
     * @return 출력페이지정보 "sym/mnu/mpm/EgovMenuList"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuListUpdt.do")
    public String updateMenuList(
            @ModelAttribute("menuManageVO") InfoMenuManageVO menuManageVO,
            BindingResult bindingResult,
            ModelMap model)
            throws Exception {
        String sLocationUrl = null;
        String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        beanValidator.validate(menuManageVO, bindingResult);
        if (bindingResult.hasErrors()) {
            sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuListSelect.do";
            return sLocationUrl;
        }
        ComDefaultVO searchVO = new ComDefaultVO();
        searchVO.setSearchKeyword(menuManageVO.getProgrmFileNm());
        if (progrmManageService.selectProgrmNMTotCnt(searchVO) == 0) {
            resultMsg = egovMessageSource.getMessage("fail.common.update");
            sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuListSelect.do";
        } else {
            infoMenuManageService.updateMenuManage(menuManageVO);
            resultMsg = egovMessageSource.getMessage("success.common.update");
            sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuListSelect.do";
        }
        model.addAttribute("resultMsg", resultMsg);
        return sLocationUrl;
    }

    /**
     * 메뉴리스트의 메뉴정보를 삭제한다.
     *
     * @param menuManageVO InfoMenuManageVO
     * @return 출력페이지정보 "sym/mnu/mpm/EgovMenuList"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuListDelete.do")
    public String deleteMenuList(
            @ModelAttribute("menuManageVO") InfoMenuManageVO menuManageVO,
            BindingResult bindingResult,
            ModelMap model)
            throws Exception {
        String sLocationUrl = null;
        String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        beanValidator.validate(menuManageVO, bindingResult);
        if (bindingResult.hasErrors()) {
            sLocationUrl = InfoViewUtils.adminTilesView(pagePath, "InfoMenuList", "sub");
            return sLocationUrl;
        }
        infoMenuManageService.deleteMenuManage(menuManageVO);
        resultMsg = egovMessageSource.getMessage("success.common.delete");
        sLocationUrl = "forward:/cms/mnu/mpm/InfoMenuListSelect.do";
        model.addAttribute("resultMsg", resultMsg);
        return sLocationUrl;
    }

    /**
     * 메뉴리스트의 메뉴정보를 이동 메뉴목록을 조회한다.
     *
     * @param searchVO CmsSearchVO
     * @return 출력페이지정보 "sym/mnu/mpm/EgovMenuMvmn"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuListSelectMvmn.do")
    public String selectMenuListMvmn(
            @ModelAttribute("searchVO") CmsSearchVO searchVO,
            ModelMap model)
            throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        List<?> list_menulist = infoMenuManageService.selectMenuList();
        model.addAttribute("list_menulist", list_menulist);
        return InfoViewUtils.adminTilesView(pagePath, "InfoMenuMvmn", "sub");
    }

    /**
     * 메뉴리스트의 메뉴정보를 이동 메뉴목록을 조회한다. (New)
     *
     * @param searchVO CmsSearchVO
     * @return 출력페이지정보 "sym/mnu/mpm/EgovMenuMvmn"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuListSelectMvmnNew.do")
    public String selectMenuListMvmnNew(
            @ModelAttribute("searchVO") CmsSearchVO searchVO,
            ModelMap model)
            throws Exception {
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }

        List<?> list_menulist = infoMenuManageService.selectMenuList();
        model.addAttribute("list_menulist", list_menulist);
        return InfoViewUtils.adminTilesView(pagePath, "InfoMenuMvmnNew", "sub");
    }


    /*### 일괄처리 프로세스 ###*/

    /**
     * 메뉴생성 일괄삭제프로세스
     *
     * @param menuManageVO InfoMenuManageVO
     * @return 출력페이지정보 "sym/mnu/mpm/EgovMenuBndeRegist"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuBndeAllDelete.do")
    public String menuBndeAllDelete(
            @ModelAttribute("menuManageVO") InfoMenuManageVO menuManageVO,
            ModelMap model)
            throws Exception {
        String resultMsg = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        infoMenuManageService.menuBndeAllDelete();
        resultMsg = egovMessageSource.getMessage("success.common.delete");
        model.addAttribute("resultMsg", resultMsg);
        return InfoViewUtils.adminTilesView(pagePath, "InfoMenuBndeRegist", "sub");
    }


    /**
     * 메뉴일괄등록화면 호출 및  메뉴일괄등록처리 프로세스
     *
     * @param commandMap   Map
     * @param menuManageVO InfoMenuManageVO
     * @param request      HttpServletRequest
     * @return 출력페이지정보 "sym/mnu/mpm/EgovMenuBndeRegist"
     * @throws Exception
     */
    @RequestMapping(value = "/cms/mnu/mpm/InfoMenuBndeRegist.do")
    public String menuBndeRegist(
            @RequestParam Map<?, ?> commandMap,
            final HttpServletRequest request,
            @ModelAttribute("menuManageVO") InfoMenuManageVO menuManageVO,
            ModelMap model)
            throws Exception {
        String sLocationUrl = null;
        String resultMsg = "";
        String sMessage = "";
        // 0. Spring Security 사용자권한 처리
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
            return "egovframework/com/uat/uia/EgovLoginUsr";
        }
        String sCmd = commandMap.get("cmd") == null ? "" : (String) commandMap.get("cmd");
        if (sCmd.equals("bndeInsert")) {
            final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            final Map<String, MultipartFile> files = multiRequest.getFileMap();
            Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
            MultipartFile file;
            while (itr.hasNext()) {
                Entry<String, MultipartFile> entry = itr.next();
                file = entry.getValue();
                if (!"".equals(file.getOriginalFilename())) {
                    // 2011.10.07 업로드 파일에 대한 확장자를 체크
                    if (file.getOriginalFilename().endsWith(".xls")
                            || file.getOriginalFilename().endsWith(".xlsx")
                            || file.getOriginalFilename().endsWith(".XLS")
                            || file.getOriginalFilename().endsWith(".XLSX")) {

                        if (infoMenuManageService.menuBndeAllDelete()) {
                            sMessage = infoMenuManageService.menuBndeRegist(menuManageVO, file.getInputStream());
                            resultMsg = sMessage;
                        } else {
                            resultMsg = egovMessageSource.getMessage("fail.common.msg");
                            menuManageVO.setTmpCmd("EgovMenuBndeRegist Error!!");
                            model.addAttribute("resultVO", menuManageVO);
                        }
                    } else {
                        LOGGER.info("xls, xlsx 파일 타입만 등록이 가능합니다.");
                        resultMsg = egovMessageSource.getMessage("fail.common.msg");
                        model.addAttribute("resultMsg", resultMsg);
                        return InfoViewUtils.adminTilesView(pagePath, "InfoMenuBndeRegist", "sub");
                    }
                    // *********** 끝 ***********

                } else {
                    resultMsg = egovMessageSource.getMessage("fail.common.msg");
                }
            }
            sLocationUrl = InfoViewUtils.adminTilesView(pagePath, "InfoMenuBndeRegist", "sub");
            model.addAttribute("resultMsg", resultMsg);
        } else {
            sLocationUrl = InfoViewUtils.adminTilesView(pagePath, "InfoMenuBndeRegist", "sub");
        }
        return sLocationUrl;
    }


    @RequestMapping(value = "/cms/mnu/mpm/infoMenuListObject.do")
    @ResponseBody
    public ModelAndView menuListObject(InfoMenuManageVO searchVO) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("list", infoMenuManageService.selectCmsAllMenuList(searchVO));
        return modelAndView;
    }
}