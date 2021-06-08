package infomind.com.cms.info.site.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.site.service.InfoSiteMenuGroupService;
import infomind.com.cms.info.site.service.InfoSiteService;
import infomind.com.cms.info.site.vo.InfoSiteMenuGroupVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuGroupValidator;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.ext.service.CodeSearchService;
import infomind.com.ext.vo.CodeSearchVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
public class InfoSiteMenuGroupController {

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoSiteMenuGroupService")
    protected InfoSiteMenuGroupService infoSiteMenuGroupService;

    @Resource(name = "CodeSearchService")
    private CodeSearchService codeSearchService;

    @Resource(name = "InfoSiteService")
    private InfoSiteService infoSiteService;

    @ModelAttribute(name = "langCd")
    public List<?> getLangCdList() throws Exception {
        return codeSearchService.selectComtccmmndetailcodeList(CodeSearchVO.builder()
                .codeId("LANG_CD")
                .build());
    }

    @ModelAttribute(name = "infoSite")
    public List<?> getSiteList() throws Exception {
        InfoSiteVO infoSiteVO = new InfoSiteVO();
        infoSiteVO.setSearchAllPage();
        return infoSiteService.selectSiteList(infoSiteVO);
    }

    private String pagePath ="info/site/";

    @RequestMapping(value = "/cms/info/site/InfoSiteMenuGroupListObject.do")
    @ResponseBody
    public Object menuGroupListObject(InfoSiteMenuGroupVO searchVO) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        searchVO.setSearchAllPage();
        modelAndView.addObject("list", infoSiteMenuGroupService.selectSiteMenuGroupList(searchVO));
        return modelAndView;
    }

    @IncludedInfo(name = "사이트 메뉴 그룹 관리", listUrl = "/cms/info/site/InfoSiteMenuGroupList.do", order = 1111, gid = 60)
    @RequestMapping(value = "/cms/info/site/InfoSiteMenuGroupList.*")
    public String selectInfoSiteMenuGroupVO(@ModelAttribute("searchVO") InfoSiteMenuGroupVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoSiteMenuGroupService.selectSiteMenuGroupList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoSiteMenuGroupService.selectInfoSiteMenuGroupTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuGroupList","ax5ui");
    }
    @RequestMapping(value="/cms/info/site/InfoSiteMenuGroupView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoSiteMenuGroupVO vo, BindingResult bindingResult, ModelMap model)throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuGroupRegist","sub");
    }
    @RequestMapping("/cms/info/site/insertSiteMenuGroup.do")
    public String insertSiteMenuGroup(
            @ModelAttribute("resultVO") InfoSiteMenuGroupVO infoSiteMenuGroupVO,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoSiteMenuGroupValidator mValidator = new InfoSiteMenuGroupValidator();
        mValidator.validate(infoSiteMenuGroupVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuGroupRegist","sub");
        }

        if(infoSiteMenuGroupVO.getMenuGroupId() != null){
            InfoSiteMenuGroupVO result = infoSiteMenuGroupService.selectInfoSiteMenuGroup(infoSiteMenuGroupVO);
            if(result != null){
                model.addAttribute("message", "이미 등록된 메뉴그룹입니다 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuGroupRegist","sub");
            }
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoSiteMenuGroupVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoSiteMenuGroupVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());




        infoSiteMenuGroupService.insertSiteMenuGroup(infoSiteMenuGroupVO);
        return "forward:/cms/info/site/InfoSiteMenuGroupList.do";
    }
    @RequestMapping("/cms/info/site/UpdateSiteMenuGroupView.do")
    public String updateView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("searchVO") InfoSiteMenuGroupVO searchVO, ModelMap model) throws Exception {

        InfoSiteMenuGroupVO result = infoSiteMenuGroupService.selectInfoSiteMenuGroup(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuGroupUpdt","sub");
    }
    @RequestMapping("/cms/info/site/updateSiteMenuGroup.do")
    public String updateSiteMenuGroup(
            @ModelAttribute("resultVO") InfoSiteMenuGroupVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoSiteMenuGroupValidator mValidator = new InfoSiteMenuGroupValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuGroupUpdt","sub");
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoSiteMenuGroupService.updateSiteMenuGroup(vo);
        return "forward:/cms/info/site/InfoSiteMenuGroupList.do";
    }










}


