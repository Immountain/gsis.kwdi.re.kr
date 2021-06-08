package infomind.com.cms.info.banner.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.banner.service.InfoBannerGroupService;
import infomind.com.cms.info.banner.service.InfoBannerService;
import infomind.com.cms.info.banner.vo.InfoBannerGroupVO;
import infomind.com.cms.info.banner.vo.InfoBannerGroupValidator;
import infomind.com.cms.info.banner.vo.InfoBannerVO;
import infomind.com.cms.info.banner.vo.InfoBannerValidator;
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
public class InfoBannerController {

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoBannerGroupService")
    private InfoBannerGroupService infoBannerGroupService;

    @Resource(name = "InfoBannerService")
    private InfoBannerService infoBannerService;





    private String pagePath ="info/banner/";

    @IncludedInfo(name="배너 관리",listUrl="/cms/info/banner/bannerList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/banner/bannerList.*")
    public String selectListView(@ModelAttribute("searchVO") InfoBannerVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoBannerService.selectBannerList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoBannerService.selectBannerTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);


        model.addAttribute("bannerGroup", infoBannerGroupService.getSelectBannerGroup(null));

        return InfoViewUtils.adminTilesView(pagePath,"InfoBannerList","sub");
    }

    @RequestMapping(value="/cms/info/banner/RegistBannerView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoBannerVO vo, BindingResult bindingResult, ModelMap model)throws Exception {

        model.addAttribute("bannerGroup", infoBannerGroupService.getSelectBannerGroup(null));

        return InfoViewUtils.adminTilesView(pagePath,"InfoBannerRegist","sub");
    }

    @RequestMapping("/cms/info/banner/insertBanner.do")
    public String insertBanner(
            @ModelAttribute("resultVO") InfoBannerVO InfoBannerVO,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoBannerValidator mValidator = new InfoBannerValidator();
        mValidator.validate(InfoBannerVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            model.addAttribute("bannerGroup", infoBannerGroupService.getSelectBannerGroup(null));
            return InfoViewUtils.adminTilesView(pagePath,"InfoBannerRegist","sub");
        }

        if(InfoBannerVO.getBannerId() != null){
            InfoBannerVO result = infoBannerService.selectBanner(InfoBannerVO);
            if(result != null){
                model.addAttribute("bannerGroup", infoBannerGroupService.getSelectBannerGroup(null));
                model.addAttribute("message", "이미 등록된 페이지 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoBannerRegist","sub");
            }
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        InfoBannerVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        InfoBannerVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoBannerService.insertBanner(InfoBannerVO);
        return "forward:/cms/info/banner/bannerList.do";
    }

    @RequestMapping("/cms/info/banner/UpdateBannerView.do")
    public String updateView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("searchVO") InfoBannerVO searchVO, ModelMap model) throws Exception {

        model.addAttribute("bannerGroup", infoBannerGroupService.getSelectBannerGroup(null));

        InfoBannerVO result = infoBannerService.selectBanner(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoBannerUpdt","sub");
    }

    @RequestMapping("/cms/info/banner/updateBanner.do")
    public String updateBanner(
            @ModelAttribute("resultVO") InfoBannerVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoBannerValidator mValidator = new InfoBannerValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            model.addAttribute("bannerGroup", infoBannerGroupService.getSelectBannerGroup(null));
            return InfoViewUtils.adminTilesView(pagePath,"InfoBannerUpdt","sub");
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());


        infoBannerService.updateBanner(vo);
        return "forward:/cms/info/banner/bannerList.do";
    }
}
