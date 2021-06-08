package infomind.com.cms.info.banner.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.banner.service.InfoBannerGroupService;
import infomind.com.cms.info.banner.vo.InfoBannerGroupVO;
import infomind.com.cms.info.banner.vo.InfoBannerGroupValidator;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import infomind.com.cms.info.page.vo.InfoPageInfoValidator;
import infomind.com.utils.page.InfoPageUtils;
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
public class InfoBannerGroupController {

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoBannerGroupService")
    private InfoBannerGroupService infoBannerGroupService;

    private String pagePath ="info/banner/";

    @IncludedInfo(name="배너 그룹 관리",listUrl="/cms/info/banner/bannerGroupList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/banner/bannerGroupList.*")
    public String selectListView(@ModelAttribute("searchVO") InfoBannerGroupVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoBannerGroupService.selectBannerGroupList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoBannerGroupService.selectBannerGroupTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"InfoBannerGroupList","sub");
    }

    @RequestMapping(value="/cms/info/banner/RegistBannerGroupView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoBannerGroupVO vo, BindingResult bindingResult, ModelMap model)throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoBannerGroupRegist","sub");
    }

    @RequestMapping("/cms/info/banner/insertBannerGroup.do")
    public String insertBannerGroup(
            @ModelAttribute("resultVO") InfoBannerGroupVO infoBannerGroupVO,
             BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoBannerGroupValidator mValidator = new InfoBannerGroupValidator();
        mValidator.validate(infoBannerGroupVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoBannerGroupRegist","sub");
        }

        if(infoBannerGroupVO.getBannerGroupId() != null){
            InfoBannerGroupVO result = infoBannerGroupService.selectBannerGroup(infoBannerGroupVO);
            if(result != null){
                model.addAttribute("message", "이미 등록된 페이지 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoBannerGroupRegist","sub");
            }
        }


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoBannerGroupVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoBannerGroupVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());


        infoBannerGroupService.insertBannerGroup(infoBannerGroupVO);
        return "forward:/cms/info/banner/bannerGroupList.do";
    }

    @RequestMapping("/cms/info/banner/UpdateBannerGroupView.do")
    public String updateView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("searchVO") InfoBannerGroupVO searchVO, ModelMap model) throws Exception {

        InfoBannerGroupVO result = infoBannerGroupService.selectBannerGroup(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoBannerGroupUpdt","sub");
    }

    @RequestMapping("/cms/info/banner/updateBannerGroup.do")
    public String updateBannerGroup(
            @ModelAttribute("resultVO") InfoBannerGroupVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoBannerGroupValidator mValidator = new InfoBannerGroupValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoBannerGroupUpdt","sub");
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoBannerGroupService.updateBannerGroup(vo);
        return "forward:/cms/info/banner/bannerGroupList.do";
    }

}
