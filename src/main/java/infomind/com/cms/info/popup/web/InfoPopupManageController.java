package infomind.com.cms.info.popup.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.popup.service.InfoPopupManageService;
import infomind.com.cms.info.popup.vo.InfoPopupManageVO;
import infomind.com.cms.info.popup.vo.InfoPopupManageValidator;
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
public class InfoPopupManageController {
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoPopupManageService")
    private InfoPopupManageService infoPopupManageService;

    private String pagePath ="info/popup/";

    @IncludedInfo(name="팝업 관리",listUrl="/cms/info/popup/popupManageList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/popup/popupManageList.*")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoPopupManageVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoPopupManageService.selectPopupManageList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoPopupManageService.selectPopupManageTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"InfoPopupManageList","sub");
    }

    @RequestMapping(value="/cms/info/popup/RegistPopupManageView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoPopupManageVO vo, BindingResult bindingResult, ModelMap model)throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoPopupManageRegist","sub");
    }

    @RequestMapping("/cms/info/popup/insertPopupManage.do")
    public String insertPopupManage(
            @ModelAttribute("resultVO") InfoPopupManageVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoPopupManageValidator mValidator = new InfoPopupManageValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoPopupManageRegist","sub");
        }

        if(vo.getPopupId() != null){
            InfoPopupManageVO result = infoPopupManageService.selectPopupManage(vo);
            if(result != null){
                model.addAttribute("message", "이미 등록된 팝업 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoPopupManageRegist","sub");
            }
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoPopupManageService.insertPopupManage(vo);
        return "forward:/cms/info/popup/popupManageList.do";
    }

    @RequestMapping("/cms/info/popup/UpdatePopupManageView.do")
    public String updateView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("resultVO") InfoPopupManageVO searchVO, ModelMap model) throws Exception {

        InfoPopupManageVO result = infoPopupManageService.selectPopupManage(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoPopupManageUpdt","sub");
    }

    @RequestMapping("/cms/info/popup/updatePopupManage.do")
    public String updatePopupManage(
            @ModelAttribute("resultVO") InfoPopupManageVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoPopupManageValidator mValidator = new InfoPopupManageValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoPopupManageUpdt","sub");
        }



        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoPopupManageService.updatePopupManage(vo);
        return "forward:/cms/info/popup/popupManageList.do";
    }
}
