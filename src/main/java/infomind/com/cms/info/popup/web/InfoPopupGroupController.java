package infomind.com.cms.info.popup.web;


import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.popup.service.InfoPopupGroupService;
import infomind.com.cms.info.popup.vo.InfoPopupGroupVO;
import infomind.com.cms.info.popup.vo.InfoPopupGroupValidator;
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
public class InfoPopupGroupController {

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoPopupGroupService")
    private InfoPopupGroupService infoPopupGroupService;

    private String pagePath ="info/popup/";

    @IncludedInfo(name="팝업그룹 관리",listUrl="/cms/info/popup/popupGroupList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/popup/popupGroupList.*")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoPopupGroupVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoPopupGroupService.selectPopupGroupList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoPopupGroupService.selectPopupGroupTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"InfoPopupGroupList","sub");
    }

    @RequestMapping(value="/cms/info/popup/RegistPopupGroupView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoPopupGroupVO vo, BindingResult bindingResult, ModelMap model)throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoPopupGroupRegist","sub");
    }

    @RequestMapping("/cms/info/popup/insertPopupGroup.do")
    public String insertPopupGroup(
            @ModelAttribute("resultVO") InfoPopupGroupVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoPopupGroupValidator mValidator = new InfoPopupGroupValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoPopupGroupRegist","sub");
        }

        if(vo.getPopupGroupId() != null){
            InfoPopupGroupVO result = infoPopupGroupService.selectPopupGroup(vo);
            if(result != null){
                model.addAttribute("message", "이미 등록된 팝업그룹 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoPopupGroupRegist","sub");
            }
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoPopupGroupService.insertPopupGroup(vo);
        return "forward:/cms/info/popup/popupGroupList.do";
    }

    @RequestMapping("/cms/info/popup/UpdatePopupGroupView.do")
    public String updateView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("resultVO") InfoPopupGroupVO searchVO, ModelMap model) throws Exception {

        InfoPopupGroupVO result = infoPopupGroupService.selectPopupGroup(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoPopupGroupUpdt","sub");
    }

    @RequestMapping("/cms/info/popup/updatePopupGroup.do")
    public String updatePopupGroup(
            @ModelAttribute("resultVO") InfoPopupGroupVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoPopupGroupValidator mValidator = new InfoPopupGroupValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoPopupGroupUpdt","sub");
        }



        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoPopupGroupService.updatePopupGroup(vo);
        return "forward:/cms/info/popup/popupGroupList.do";
    }
}

