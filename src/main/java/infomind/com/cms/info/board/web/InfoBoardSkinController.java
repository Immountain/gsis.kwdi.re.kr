package infomind.com.cms.info.board.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cmm.InfoConstants;
import infomind.com.cms.info.board.service.InfoBoardSkinService;
import infomind.com.cms.info.board.vo.InfoBoardSkinVO;
import infomind.com.cms.info.board.vo.InfoBoardSkinValidator;
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
public class InfoBoardSkinController {
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoBoardSkinService")
    private InfoBoardSkinService infoBoardSkinService;

    private String pagePath ="info/board/";

    @IncludedInfo(name="게시판 스킨 관리",listUrl="/cms/info/board/boardSkinList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/board/boardSkinList.*")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoBoardSkinVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoBoardSkinService.selectBoardSkinList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoBoardSkinService.selectBoardSkinTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"InfoBoardSkinList","sub");
    }

    @RequestMapping(value="/cms/info/board/RegistBoardSkinView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoBoardSkinVO vo, BindingResult bindingResult, ModelMap model)throws Exception {


        model.addAttribute("skinList", InfoConstants.BOARD_SKIN_LIST());
        return InfoViewUtils.adminTilesView(pagePath,"InfoBoardSkinRegist","sub");
    }

    @RequestMapping("/cms/info/board/insertBoardSkin.do")
    public String insertBoardSkin(
            @ModelAttribute("resultVO") InfoBoardSkinVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoBoardSkinValidator mValidator = new InfoBoardSkinValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoBoardSkinRegist","sub");
        }

        if(vo.getBoardSkinId() != null){
            InfoBoardSkinVO result = infoBoardSkinService.selectBoardSkin(vo);
            if(result != null){
                model.addAttribute("message", "이미 등록된 페이지 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoBoardSkinRegist","sub");
            }
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        infoBoardSkinService.insertBoardSkin(vo);
        return "forward:/cms/info/board/boardSkinList.do";
    }

    @RequestMapping("/cms/info/board/UpdateBoardSkinView.do")
    public String updateView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("resultVO") InfoBoardSkinVO searchVO, ModelMap model) throws Exception {

        InfoBoardSkinVO result = infoBoardSkinService.selectBoardSkin(searchVO);
        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminTilesView(pagePath,"InfoBoardSkinUpdt","sub");
    }

    @RequestMapping("/cms/info/board/updateBoardSkin.do")
    public String updateBoardSkin(
            @ModelAttribute("resultVO") InfoBoardSkinVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoBoardSkinValidator mValidator = new InfoBoardSkinValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoBoardSkinUpdt","sub");
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoBoardSkinService.updateBoardSkin(vo);
        return "forward:/cms/info/board/boardSkinList.do";
    }
}
