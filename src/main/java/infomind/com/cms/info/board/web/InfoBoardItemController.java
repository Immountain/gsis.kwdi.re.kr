package infomind.com.cms.info.board.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.board.service.InfoBoardItemService;
import infomind.com.cms.info.board.service.InfoBoardService;
import infomind.com.cms.info.board.vo.InfoBoardItemProcDto;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;
import infomind.com.cms.info.board.vo.InfoBoardVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class InfoBoardItemController {

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoBoardItemService")
    private InfoBoardItemService infoBoardItemService;

    @Resource(name = "InfoBoardService")
    private InfoBoardService infoBoardService;

    private String pagePath ="info/board/item/";

    @IncludedInfo(name = "게시판 아이템 리스트", listUrl = "/cms/info/board/boardItemList.do", order = 1111, gid = 60)
    @RequestMapping(value = "/cms/info/board/boardItemList.do")
    public String selectListView(@ModelAttribute("searchVO") InfoBoardItemVO searchVO, ModelMap model) throws Exception {

        InfoBoardVO ibv = new InfoBoardVO();
        ibv.setSearchAllPage();
        model.addAttribute("board", infoBoardService.selectBoardList(ibv));

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

        List<?> list = infoBoardItemService.selectInfoBoardItemList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoBoardItemService.selectInfoBoardItemTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath, "list", "sub");
    }

    @RequestMapping(value = "/cms/info/board/RegistBoardItemView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoBoardItemVO vo, BindingResult bindingResult, ModelMap model) throws Exception {

        return InfoViewUtils.adminTilesView(pagePath, "edit", "sub");
    }

    @RequestMapping("/cms/info/board/insertBoardItem.do")
    public String insertBanner(
            @ModelAttribute("resultVO") InfoBoardItemVO infoBoardItemVO,
            BindingResult bindingResult, ModelMap model) throws Exception {

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoBoardItemVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoBoardItemVO.setRegIp(user.getIp());


        model.addAttribute("searchVO", infoBoardItemVO);
        infoBoardItemService.insertBoardItem(infoBoardItemVO);
        return "forward:/cms/info/board/boardItemList.do";
    }

    @RequestMapping(value = "/cms/info/board/boardItemView.do")
    public String boardItemView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("searchVO") InfoBoardItemVO vo, BindingResult bindingResult, ModelMap model) throws Exception {

        InfoBoardItemVO result = infoBoardItemService.selectBoardItem(vo);
        model.addAttribute("resultVO", result);

        return InfoViewUtils.adminTilesView(pagePath, "view", "sub");
    }

    @RequestMapping("/cms/info/board/latest.do")
    public String bannerlatest(@RequestParam("skinName") String skinName, HttpServletRequest request) throws Exception {
        return InfoViewUtils.adminLatesBoardView(skinName, "");
    }

    @RequestMapping(value = "/cms/info/board/boardItemProc.do")
    public String boardItemProc(@ModelAttribute("searchVO") InfoBoardItemProcDto dto, RedirectAttributes redirectAttributes) throws Exception {

        infoBoardItemService.boardItemProc(dto);

        redirectAttributes.addFlashAttribute("searchVO", dto);

        redirectAttributes.addFlashAttribute("resultMsg", String.format("[%s] 처리되었습니다.", dto.getCmd()));

        return String.format("redirect:%s", "/cms/info/board/boardItemList.do");
    }
}
