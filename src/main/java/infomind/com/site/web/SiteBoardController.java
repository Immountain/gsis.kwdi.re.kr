package infomind.com.site.web;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cmm.visit.InfoVisitFactory;
import infomind.com.cms.info.board.service.InfoBoardCategoryService;
import infomind.com.cms.info.board.service.InfoBoardItemService;
import infomind.com.cms.info.board.vo.InfoBoardCategoryVO;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;
import infomind.com.cms.info.board.vo.InfoBoardVO;
import infomind.com.cms.info.site.service.InfoSiteVisitService;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SiteBoardController {


    @Resource(name = "InfoBoardItemService")
    private InfoBoardItemService infoBoardItemService;

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


    @Resource(name = "InfoFileService")
    private InfoFileService infoFileService;


    @Resource(name = "InfoBoardCategoryService")
    private InfoBoardCategoryService infoBoardCategoryService;


    @Resource(name = "InfoSiteVisitService")
    private InfoSiteVisitService infoSiteVisitService;

    @RequestMapping("/board/latest.do")
    public String boardlatest(@RequestParam("skinName") String skinName, HttpServletRequest request) throws Exception {

        // System.out.println("latest====>"+ InfoViewUtils.boardLatesView(skinName + "latest"));
        return InfoViewUtils.boardLatesView(skinName, "latest");
    }


    @RequestMapping("/board/NoticLatest.do")
    public String NoticLatest(@RequestParam("skinName") String skinName, HttpServletRequest request) throws Exception {

        // System.out.println("latest====>"+ InfoViewUtils.boardLatesView(skinName + "latest"));
        return InfoViewUtils.boardLatesView(skinName, "latest");
    }


    @RequestMapping("/board/{slug}/list.do")
    public String boardList(HttpServletRequest request, ModelMap model
            , @PathVariable("slug") String slug
            , @ModelAttribute("searchVO") InfoBoardItemVO searchVO
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @RequestAttribute(value = "boardVO", required = false) InfoBoardVO boardVO
    ) throws Exception {

        if (boardVO == null || "".equals(boardVO.getBoardId()) || boardVO.getBoardId() == null) {
            model.addAttribute("message", "???????????? ???????????? ????????????.");
            return InfoViewUtils.errorContentView(menuInfo.getLayout());
        }

        if (boardVO.getBoardAuth().getListYn() == 0) {
            model.addAttribute("message", "????????? ???????????? ????????????.");
            return InfoViewUtils.errorContentView(menuInfo.getLayout());
        }

//        //?????? ??????
//        InfoSiteVisitVO visit = InfoVisitFactory.fromRequest(request);
//        visit.setPageType(slug);
//        visit.setTargetId(boardVO.getBoardId());
//        visit.setVisitType("LIST");
//        infoSiteVisitService.insertSiteVisit(visit);


        String skin = boardVO.getBoardSkinId();
        int listRow = boardVO.getListRow(); //????????? ?????????
        //  System.out.println("????????? ==>"+boardVO.getBoardId());

        //????????? ????????? ????????????.
        searchVO.setBoardId(boardVO.getBoardId());
        //????????? ???????????? y ?????? ????????? ????????????..
        if ("Y".equals(boardVO.getSubKeyYn())) {

        } else {
            searchVO.setSubKey("");
        }

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

        model.addAttribute("boardItemList", list);

        int totalCnt = infoBoardItemService.selectInfoBoardItemTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        //??????
        List<?> noticelist = infoBoardItemService.selectInfoBoardItemNoticeList(searchVO);
        model.addAttribute("noticelist", noticelist);

        // ???????????? ?????????
        InfoBoardCategoryVO infoBoardCategoryVO = new InfoBoardCategoryVO();
        infoBoardCategoryVO.setBoardId(boardVO.getBoardId());

        List<?> categoryDepthList = infoBoardCategoryService.selectBoardCategoryDepthList(infoBoardCategoryVO);
        model.addAttribute("categoryList", categoryDepthList);


        String url = InfoViewUtils.boardContentView(skin, "list", menuInfo.getLayout());
      //  System.out.println("boardUrl==>" + url);

        model.addAttribute("searchVO", searchVO);
        model.addAttribute("pageCss", boardVO.getListClass());

        return url;
    }


    @RequestMapping("/board/{slug}/edit.do")
    public String boardEdit(@PathVariable("slug") String slug, ModelMap model, @ModelAttribute("searchVO") InfoBoardItemVO searchVO
            , HttpServletRequest request
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @RequestAttribute(value = "boardVO", required = false) InfoBoardVO boardVO
    ) throws Exception {

        if (boardVO.getBoardAuth().getWriteYn() == 0) {
            model.addAttribute("message", "????????? ???????????? ????????????.");
            return InfoViewUtils.errorContentView(menuInfo.getLayout());
        }

//        //?????? ??????
//        InfoSiteVisitVO visit = InfoVisitFactory.fromRequest(request);
//        visit.setPageType(slug);
//        visit.setTargetId(boardVO.getBoardId());
//        visit.setVisitType("EDIT");
//        infoSiteVisitService.insertSiteVisit(visit);

        searchVO.setBoardId(boardVO.getBoardId());

        // ???????????? ?????????
        InfoBoardCategoryVO infoBoardCategoryVO = new InfoBoardCategoryVO();
        infoBoardCategoryVO.setBoardId(boardVO.getBoardId());

        List<?> categoryDepthList = infoBoardCategoryService.selectBoardCategoryDepthList(infoBoardCategoryVO);
        model.addAttribute("categoryList", categoryDepthList);

        model.addAttribute("pageCss", boardVO.getWriteClass());

        String skin = boardVO.getBoardSkinId();
        String url = InfoViewUtils.boardContentView(skin, "edit", menuInfo.getLayout());

        System.out.println("boardUrl==>" + url);

        return url;
    }


    @RequestMapping("/board/{slug}/view.do")
    public String boardView(@PathVariable("slug") String slug, ModelMap model, @ModelAttribute("searchVO") InfoBoardItemVO searchVO
            , HttpServletRequest request
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @RequestAttribute(value = "boardVO", required = false) InfoBoardVO boardVO
    ) throws Exception {

        if (boardVO.getBoardAuth().getReadYn() == 0) {
            model.addAttribute("message", "????????? ???????????? ????????????.");
            return InfoViewUtils.errorContentView(menuInfo.getLayout());
        }

        String skin = boardVO.getBoardSkinId();
        //????????? ????????? ??????
        InfoBoardItemVO boardItemVO = infoBoardItemService.selectBoardItem(searchVO);

        if (boardItemVO == null) {
            model.addAttribute("message", "????????? ????????? ?????????");
            return InfoViewUtils.errorContentView(menuInfo.getLayout());
        }

        model.addAttribute("boardItem", boardItemVO);
        model.addAttribute("searchVO", searchVO);

        // ???????????? ?????????
        InfoBoardCategoryVO infoBoardCategoryVO = new InfoBoardCategoryVO();
        infoBoardCategoryVO.setBoardId(boardVO.getBoardId());

        List<?> categoryDepthList = infoBoardCategoryService.selectBoardCategoryDepthList(infoBoardCategoryVO);
        model.addAttribute("categoryList", categoryDepthList);

        List<InfoFileDetailVO> fileList = new ArrayList<>();
        if (boardItemVO.getBoardFile() != null || !"".equals(boardItemVO.getBoardFile())) {
            InfoFileDetailVO file = new InfoFileDetailVO();
            file.setAtchFileId(boardItemVO.getBoardFile());
            fileList = infoFileService.getInfoFileList(file);
        }


        model.addAttribute("fileList", fileList);


        List<InfoFileDetailVO> fileList2 = new ArrayList<>();
        if (boardItemVO.getFile01() != null || !"".equals(boardItemVO.getFile01())) {
            InfoFileDetailVO file = new InfoFileDetailVO();
            file.setAtchFileId(boardItemVO.getFile01());
            fileList2 = infoFileService.getInfoFileList(file);
        }

        model.addAttribute("fileList2", fileList2);





        infoBoardItemService.updateBoardReadCnt(searchVO);

        model.addAttribute("pageCss", boardVO.getViewClass());

        String url = InfoViewUtils.boardContentView(skin, "view", menuInfo.getLayout());

        return url;
    }


    @RequestMapping("/board/{slug}/updt.do")
    public String boardUpdt(HttpServletRequest request
            , @PathVariable("slug") String slug, ModelMap model
            , @ModelAttribute("searchVO") InfoBoardItemVO searchVO
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @RequestAttribute(value = "boardVO", required = false) InfoBoardVO boardVO
    ) throws Exception {

        if (boardVO.getBoardAuth().getModifyYn() == 0) {
            model.addAttribute("message", "????????? ???????????? ????????????.");
            return InfoViewUtils.errorContentView(menuInfo.getLayout());
        }

        String skin = boardVO.getBoardSkinId();
        //????????? ????????? ??????
        InfoBoardItemVO boardItemVO = infoBoardItemService.selectBoardItem(searchVO);
        model.addAttribute("boardItem", boardItemVO);
        model.addAttribute("pageCss", boardVO.getWriteClass());

        String url = InfoViewUtils.boardContentView(skin, "updt", menuInfo.getLayout());
        return url;
    }


    @RequestMapping("/board/{slug}/insert.do")
    public String boardInsert(HttpServletRequest request, ModelMap model
            , @PathVariable("slug") String slug
            , @ModelAttribute("editVO") InfoBoardItemVO resultVO
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @RequestAttribute(value = "boardVO", required = false) InfoBoardVO boardVO
            , BindingResult bindingResult
    ) throws Exception {

        LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegIp(request.getRemoteAddr());
        resultVO.setBoardId(boardVO.getBoardId());

        // ????????? ?????? ???
        // bindingResult.addError(new FieldError("editVO", "pwd", "??????????????? ???????????????"));
        // WpHttpResponseUtils.alertRedirect((HttpServletResponse) response, "???????????????????????????.", request.getContextPath() + "/");

        infoBoardItemService.insertBoardItem(resultVO);
        model.addAttribute("searchVO", resultVO);
        String url = boardVO.getActionListUrl();

        return "redirect:" + url;
    }


    @RequestMapping("/board/{slug}/update.do")
    public String boardUpdate(HttpServletRequest request, ModelMap model
            , @PathVariable("slug") String slug
            , @ModelAttribute("editVO") InfoBoardItemVO resultVO
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @RequestAttribute(value = "boardVO", required = false) InfoBoardVO boardVO
            , BindingResult bindingResult
    ) throws Exception {

        LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setModIp(request.getRemoteAddr());
        resultVO.setBoardId(boardVO.getBoardId());

        infoBoardItemService.updateBoardItem(resultVO);
        model.addAttribute("searchVO", resultVO);
        String url = boardVO.getActionListUrl();

        return "redirect:" + url;
    }

    @RequestMapping("/board/{slug}/delete.do")
    public String boarDelete(HttpServletRequest request, ModelMap model
            , @PathVariable("slug") String slug
            , @ModelAttribute("editVO") InfoBoardItemVO resultVO
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @RequestAttribute(value = "boardVO", required = false) InfoBoardVO boardVO
            , BindingResult bindingResult
    ) throws Exception {

        LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setModIp(request.getRemoteAddr());
        resultVO.setBoardId(boardVO.getBoardId());

        resultVO.setDeleteYn("Y");
        resultVO.setDeleteType("U");

        infoBoardItemService.deleteBoardItem(resultVO);

        model.addAttribute("searchVO", resultVO);
        String url = boardVO.getActionListUrl();

        return "redirect:" + url;
    }
}
