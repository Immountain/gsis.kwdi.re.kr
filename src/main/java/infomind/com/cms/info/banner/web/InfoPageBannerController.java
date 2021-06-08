package infomind.com.cms.info.banner.web;

import com.google.common.collect.ImmutableMap;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.banner.service.InfoPageBannerService;
import infomind.com.cms.info.banner.vo.InfoPageBannerVO;
import infomind.com.cms.info.banner.vo.InfoPageBannerValidator;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class InfoPageBannerController {


    @ModelAttribute("lang")
    private List<Map<String, String>> getLangData() {
        List<Map<String, String>> list = new ArrayList<>();

        list.add(ImmutableMap.<String, String>builder().put("langCode", "KR").put("langNm", "한국어").build());
        list.add(ImmutableMap.<String, String>builder().put("langCode", "EN").put("langNm", "영어").build());
        list.add(ImmutableMap.<String, String>builder().put("langCode", "JP").put("langNm", "일본어").build());
        list.add(ImmutableMap.<String, String>builder().put("langCode", "CN").put("langNm", "중국어").build());

        return list;
    }

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoPageBannerService")
    private InfoPageBannerService infoPageBannerService;

    private String pagePath ="info/banner/";

    @IncludedInfo(name="서브 배너 관리",listUrl="/cms/info/banner/pageBannerList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/banner/pageBannerList.*")
    public String selectListView(@ModelAttribute("searchVO") InfoPageBannerVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoPageBannerService.selectPageBannerList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoPageBannerService.selectPageBannerTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"InfoPageBannerList","sub");
    }

    @RequestMapping(value="/cms/info/banner/RegistPageBannerView.do")
    public String registPageView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoPageBannerVO vo, BindingResult bindingResult, ModelMap model)throws Exception {



        return InfoViewUtils.adminTilesView(pagePath,"InfoPageBannerRegist","sub");
    }

    @RequestMapping("/cms/info/banner/insertPageBanner.do")
    public String insertPageBanner(
            @ModelAttribute("resultVO") InfoPageBannerVO resultVO,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoPageBannerValidator mValidator = new InfoPageBannerValidator();
        mValidator.validate(resultVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoPageBannerRegist","sub");
        }

        if(resultVO.getPageBannerId() != null){
            InfoPageBannerVO result = infoPageBannerService.selectPageBanner(resultVO);
            if(result != null){
                model.addAttribute("message", "이미 등록된 페이지 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoPageBannerRegist","sub");
            }
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoPageBannerService.insertPageBanner(resultVO);
        return "forward:/cms/info/banner/pageBannerList.do";
    }

    @RequestMapping("/cms/info/banner/UpdatePageBannerView.do")
    public String updatePageView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("searchVO") InfoPageBannerVO searchVO, ModelMap model) throws Exception {


        InfoPageBannerVO result = infoPageBannerService.selectPageBanner(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoPageBannerUpdt","sub");
    }

    @RequestMapping("/cms/info/banner/updatePageBanner.do")
    public String updatePageBanner(
            @ModelAttribute("resultVO") InfoPageBannerVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoPageBannerValidator mValidator = new InfoPageBannerValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoPageBannerUpdt","sub");
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());


        infoPageBannerService.updatePageBanner(vo);
        return "forward:/cms/info/banner/pageBannerList.do";
    }
}
