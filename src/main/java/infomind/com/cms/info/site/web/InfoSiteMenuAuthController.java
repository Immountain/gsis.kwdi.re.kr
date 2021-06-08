package infomind.com.cms.info.site.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.site.service.InfoSiteMenuAuthService;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthValidator;
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
public class InfoSiteMenuAuthController {

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoSiteMenuAuthService")
    private InfoSiteMenuAuthService infoSiteMenuAuthService;

    private String pagePath ="info/site/";

    @IncludedInfo(name="사이트 인증 관리",listUrl="/cms/info/site/siteMenuAuthList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/site/siteMenuAuthList.*")
    public String selectListView(@ModelAttribute("searchVO") InfoSiteMenuAuthVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoSiteMenuAuthService.selectSiteMenuAuthList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoSiteMenuAuthService.selectSiteMenuAuthTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuAuthList","sub");
    }

    @RequestMapping(value="/cms/info/site/RegistSiteMenuAuthView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoSiteMenuAuthVO vo, BindingResult bindingResult, ModelMap model)throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuAuthRegist","sub");
    }

    @RequestMapping("/cms/info/site/insertSiteMenuAuth.do")
    public String insertSiteMenuAuth(
            @ModelAttribute("resultVO") InfoSiteMenuAuthVO infoSiteMenuAuthVO,
             BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoSiteMenuAuthValidator mValidator = new InfoSiteMenuAuthValidator();
        mValidator.validate(infoSiteMenuAuthVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuAuthRegist","sub");
        }

        if(infoSiteMenuAuthVO.getMemuAuthId() != null){
            InfoSiteMenuAuthVO result = infoSiteMenuAuthService.selectSiteMenuAuth(infoSiteMenuAuthVO);
            if(result != null){
                model.addAttribute("message", "이미 등록된 페이지 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuAuthRegist","sub");
            }
        }


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoSiteMenuAuthVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());


        infoSiteMenuAuthService.insertSiteMenuAuth(infoSiteMenuAuthVO);
        return "forward:/cms/info/site/SiteMenuAuthList.do";
    }

    @RequestMapping("/cms/info/site/UpdateSiteMenuAuthView.do")
    public String updateView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("searchVO") InfoSiteMenuAuthVO searchVO, ModelMap model) throws Exception {

        InfoSiteMenuAuthVO result = infoSiteMenuAuthService.selectSiteMenuAuth(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuAuthUpdt","sub");
    }

    @RequestMapping("/cms/info/site/updateSiteMenuAuth.do")
    public String updateSiteMenuAuth(
            @ModelAttribute("resultVO") InfoSiteMenuAuthVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoSiteMenuAuthValidator mValidator = new InfoSiteMenuAuthValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoSiteMenuAuthUpdt","sub");
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoSiteMenuAuthService.updateSiteMenuAuth(vo);
        return "forward:/cms/info/site/SiteMenuAuthList.do";
    }

}
