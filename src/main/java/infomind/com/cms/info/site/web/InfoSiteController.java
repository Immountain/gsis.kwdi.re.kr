package infomind.com.cms.info.site.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.site.service.InfoSiteService;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.cms.info.site.vo.InfoSiteValidator;
import infomind.com.ext.service.CodeSearchService;
import infomind.com.ext.vo.CodeSearchVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class InfoSiteController {

    /** egovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name="InfoSiteService")
    InfoSiteService infoSiteService;

    @Resource(name = "CodeSearchService")
    private CodeSearchService codeSearchService;

    private String pagePath ="info/site/";

    @ModelAttribute(name = "langCd")
    public List<?> getLangCdList() throws Exception {
        return codeSearchService.selectComtccmmndetailcodeList(CodeSearchVO.builder()
                .codeId("LANG_CD")
                .build());
    }

    @IncludedInfo(name = "사이트 관리", listUrl = "/cms/info/site/InfoSiteList.do", order = 1111, gid = 60)
    @RequestMapping(value = "/cms/info/site/InfoSiteList.*")
    public String selectInfoSiteVO(@ModelAttribute("searchVO") InfoSiteVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoSiteService.selectSiteList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoSiteService.selectInfoSiteTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"infoSiteList","ax5ui");
    }

    @RequestMapping(value="/cms/info/site/InfoSiteView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoSiteVO vo, BindingResult bindingResult, ModelMap model)throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteRegist","sub");
    }
    @RequestMapping("/cms/info/site/insertSite.do")
    public String insertSite(
            @ModelAttribute("resultVO") InfoSiteVO infoSiteVO,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoSiteValidator mValidator = new InfoSiteValidator();
        mValidator.validate(infoSiteVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoSiteRegist","sub");
        }

        if(infoSiteVO.getSiteId() != null){
            InfoSiteVO result = infoSiteService.selectInfoSite(infoSiteVO);
            if(result != null){
                model.addAttribute("message", "이미 등록된 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoSiteRegist","sub");
            }
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoSiteVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoSiteVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());




        infoSiteService.insertSite(infoSiteVO);
        return "forward:/cms/info/site/InfoSiteList.do";
    }

    @RequestMapping("/cms/info/site/UpdateSiteView.do")
    public String updateView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("searchVO") InfoSiteVO searchVO, ModelMap model) throws Exception {

        InfoSiteVO result = infoSiteService.selectInfoSite(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteUpdt","sub");
    }
    @RequestMapping("/cms/info/site/updateSite.do")
    public String updateSite(
            @ModelAttribute("resultVO") InfoSiteVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoSiteValidator mValidator = new InfoSiteValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoSiteUpdt","sub");
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoSiteService.updateSite(vo);
        return "forward:/cms/info/site/InfoSiteList.do";
    }
}
