package infomind.com.cms.info.page.web;


import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.page.service.InfoPageGroupService;
import infomind.com.cms.info.page.vo.InfoPageGroupVO;
import infomind.com.cms.info.page.vo.InfoPageGroupValidator;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class InfoPageGroupController {

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name="InfoPageGroupService")
    private InfoPageGroupService infoPageGroupService;


    private final String pagePath = "info/pageGroup/";

    @Autowired
    MappingJackson2JsonView jsonView;

    @IncludedInfo(name = "페이지 그룹 관리", listUrl = "/cms/info/pageGroup/InfoPageGroupList.do", order = 1111, gid = 60)
    @RequestMapping(value = "/cms/info/pageGroup/InfoPageGroupList.do")
    public String selectPageGroupList(@ModelAttribute("searchVO") InfoPageGroupVO searchVO, ModelMap model) throws Exception {


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

        List<?> list = infoPageGroupService.selectPageGroupList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totCnt = infoPageGroupService.selectPageGroupTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath, "InfoPageGroupList", "sub");
    }

    @RequestMapping(value="/cms/info/pageGroup/RegistPageGroupView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoPageGroupVO vo, BindingResult bindingResult, ModelMap model)throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoPageGroupRegist","sub");
    }

    @RequestMapping("/cms/info/pageGroup/insertPageGroup.do")
    public String insertPageGroup(
            @ModelAttribute("resultVO") InfoPageGroupVO infoPageGroupVO,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoPageGroupValidator mValidator = new InfoPageGroupValidator();
        mValidator.validate(infoPageGroupVO, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoPageGroupRegist","sub");
        }

        if(infoPageGroupVO.getPageGroupId() != null){
            InfoPageGroupVO result = infoPageGroupService.selectPageGroup(infoPageGroupVO);
            if(result != null){
                model.addAttribute("message", "이미 등록된 페이지 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoPageGroupRegist","sub");
            }
        }


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoPageGroupVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoPageGroupVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());


        infoPageGroupService.insertPageGroup(infoPageGroupVO);
        return "forward:/cms/info/pageGroup/InfoPageGroupList.do";
    }
    @RequestMapping("/cms/info/pageGroup/UpdatePageGroupView.do")
    public String updateGroupView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("searchVO") InfoPageGroupVO searchVO, ModelMap model) throws Exception {

        InfoPageGroupVO result = infoPageGroupService.selectPageGroup(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoPageGroupUpdt","sub");
    }
    @RequestMapping("/cms/info/pageGroup/updatePageGroup.do")
    public String updatePageGroup(
            @ModelAttribute("resultVO") InfoPageGroupVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoPageGroupValidator mValidator = new InfoPageGroupValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoPageGroupUpdt","sub");
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoPageGroupService.updatePageGroup(vo);
        return "forward:/cms/info/pageGroup/InfoPageGroupList.do";
    }
}
