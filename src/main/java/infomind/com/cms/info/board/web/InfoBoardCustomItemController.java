package infomind.com.cms.info.board.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.board.service.InfoBoardAuthService;
import infomind.com.cms.info.board.service.InfoBoardCategoryService;
import infomind.com.cms.info.board.service.InfoBoardItemService;
import infomind.com.cms.info.board.service.InfoBoardService;
import infomind.com.cms.info.board.vo.InfoBoardAuthVO;
import infomind.com.cms.info.board.vo.InfoBoardCategoryVO;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;
import infomind.com.cms.info.board.vo.InfoBoardVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
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
import java.util.Map;

@Controller
public class InfoBoardCustomItemController {

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoBoardItemService")
    private InfoBoardItemService infoBoardItemService;

    @Resource(name = "InfoBoardService")
    private InfoBoardService infoBoardService;

    @Resource(name = "InfoFileService")
    private InfoFileService infoFileService;

    @Resource(name = "InfoBoardCategoryService")
    protected InfoBoardCategoryService infoBoardCategoryService;

    @Resource
    private InfoBoardAuthService infoBoardAuthService;



    @IncludedInfo(name = "게시판 아이템 리스트", listUrl = "/cms/info/board/c/boardItemList.do", order = 1111, gid = 60)
    @RequestMapping(value = "/cms/info/board/c/boardItemList.do")
    public String selectListView(@ModelAttribute("searchVO") InfoBoardItemVO searchVO, ModelMap model,HttpServletRequest request) throws Exception {


        System.out.println("searchVO==>"+searchVO.getBoardId());


        searchVO.setBoardId(searchVO.getBoardId());
        String boardId = searchVO.getBoardId();
        String path = "";
        if ("".equals(boardId) || boardId == null) {
            path = "basic";
        } else {
            path = boardId;

        }



        InfoBoardAuthVO authVO = new InfoBoardAuthVO();
        authVO.setBoardId(boardId);



        InfoBoardVO   boardVO = new InfoBoardVO();
        boardVO.setBoardId(searchVO.getBoardId());

        boardVO = infoBoardService.selectBoard(boardVO);

        LoginVO user = (LoginVO)((HttpServletRequest) request).getSession().getAttribute("loginVO");

        String itemId ="";
        authVO.setCommonLogin("common_login");
        authVO.setOrgnzt(user.getOrgnztId());
        authVO.setGroup(user.getGroupId());
        authVO.setRegId(user.getUniqId());
        authVO.setItemId(itemId);


        authVO =infoBoardAuthService.selectBoardAuthInfo(authVO);

       // System.out.println("authVO===>"+authVO);

        boardVO.setBoardAuth(authVO);




        model.addAttribute("boardVO", boardVO);

        searchVO.setBoardId(boardId);

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



        // 카테고리 리스트
        InfoBoardCategoryVO infoBoardCategoryVO = new InfoBoardCategoryVO();
        infoBoardCategoryVO.setBoardId(boardVO.getBoardId());

        List<?> categoryDepthList = infoBoardCategoryService.selectBoardCategoryDepthList(infoBoardCategoryVO);
        model.addAttribute("categoryList", categoryDepthList);




        System.out.println("게시판===>" + InfoViewUtils.adminBoardView(boardId, "list", "sub"));

        return InfoViewUtils.adminBoardView(path, "list", "sub");
    }

    @RequestMapping(value = "/cms/info/board/c/RegistBoardItemView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoBoardItemVO vo, BindingResult bindingResult, ModelMap model) throws Exception {

        String boardId = vo.getBoardId();
        String path = "";


        InfoBoardVO   boardVO = new InfoBoardVO();
        boardVO.setBoardId(vo.getBoardId());

        boardVO = infoBoardService.selectBoard(boardVO);
        model.addAttribute("boardVO", boardVO);


        // 카테고리 리스트
        InfoBoardCategoryVO infoBoardCategoryVO = new InfoBoardCategoryVO();
        infoBoardCategoryVO.setBoardId(vo.getBoardId());

        List<?> categoryDepthList =infoBoardCategoryService.selectBoardCategoryDepthList(infoBoardCategoryVO);
        model.addAttribute("categoryList", categoryDepthList);



        if ("".equals(boardId) || boardId == null) {
            path = "basic";
        } else {
            path = boardId;
        }
        return InfoViewUtils.adminBoardView(vo.getBoardId(), "edit", "sub");
    }

    @RequestMapping("/cms/info/board/c/insertBoardItem.do")
    public String insertBanner(
            @ModelAttribute("resultVO") InfoBoardItemVO infoBoardItemVO, HttpServletRequest request,
            BindingResult bindingResult, ModelMap model) throws Exception {

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoBoardItemVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoBoardItemVO.setRegIp(request.getRemoteAddr());
        infoBoardItemVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoBoardItemVO.setModIp(request.getRemoteAddr());

        model.addAttribute("searchVO", infoBoardItemVO);
        infoBoardItemService.insertBoardItem(infoBoardItemVO);
        return "forward:/cms/info/board/c/boardItemList.do";
    }

    @RequestMapping(value = "/cms/info/board/c/boardItemView.do")
    public String boardItemView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("searchVO") InfoBoardItemVO vo, BindingResult bindingResult, ModelMap model,HttpServletRequest request) throws Exception {

        String boardId = vo.getBoardId();
        String path = "";

        if ("".equals(boardId) || boardId == null) {
            path = "basic";
        } else {
            path = boardId;
        }


        InfoBoardVO   boardVO = new InfoBoardVO();
        boardVO.setBoardId(boardId);
        boardVO = infoBoardService.selectBoard(boardVO);



        LoginVO user = (LoginVO)((HttpServletRequest) request).getSession().getAttribute("loginVO");

        InfoBoardAuthVO authVO = new InfoBoardAuthVO();
        authVO.setBoardId(boardId);


        String itemId =vo.getItemId();
        authVO.setCommonLogin("common_login");
        authVO.setOrgnzt(user.getOrgnztId());
        authVO.setGroup(user.getGroupId());
        authVO.setRegId(user.getUniqId());
        authVO.setItemId(itemId);


        authVO =infoBoardAuthService.selectBoardAuthInfo(authVO);
        boardVO.setBoardAuth(authVO);



        // 카테고리 리스트
        InfoBoardCategoryVO infoBoardCategoryVO = new InfoBoardCategoryVO();
        infoBoardCategoryVO.setBoardId(vo.getBoardId());

        List<?> categoryDepthList =infoBoardCategoryService.selectBoardCategoryDepthList(infoBoardCategoryVO);
        model.addAttribute("categoryList", categoryDepthList);


        InfoBoardItemVO result = infoBoardItemService.selectBoardItem(vo);
        model.addAttribute("resultVO", result);


        List<InfoFileDetailVO> fileList = new ArrayList<>();
        if(result.getBoardFile()!=null || !"".equals(result.getBoardFile())){

            InfoFileDetailVO file = new InfoFileDetailVO();
            file.setAtchFileId(result.getBoardFile());
            fileList =infoFileService.getInfoFileList(file);

        }

        List<InfoFileDetailVO> fileList2 = new ArrayList<>();
        if(result.getFile01()!=null || !"".equals(result.getFile01())){

            InfoFileDetailVO file = new InfoFileDetailVO();
            file.setAtchFileId(result.getFile01());
            fileList2 =infoFileService.getInfoFileList(file);

        }

        model.addAttribute("fileList", fileList);
        model.addAttribute("fileList2", fileList2);


        return InfoViewUtils.adminBoardView(path, "view", "sub");
    }



    @RequestMapping(value = "/cms/info/board/c/boardPopItemView.do")
    public String boardPopItemView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("searchVO") InfoBoardItemVO vo, BindingResult bindingResult, ModelMap model,HttpServletRequest request) throws Exception {

        String boardId = vo.getBoardId();
        String path = "";

        if ("".equals(boardId) || boardId == null) {
            path = "basic";
        } else {
            path = boardId;
        }


        InfoBoardVO   boardVO = new InfoBoardVO();
        boardVO.setBoardId(boardId);
        boardVO = infoBoardService.selectBoard(boardVO);



        LoginVO user = (LoginVO)((HttpServletRequest) request).getSession().getAttribute("loginVO");

        InfoBoardAuthVO authVO = new InfoBoardAuthVO();
        authVO.setBoardId(boardId);


        String itemId =vo.getItemId();
        authVO.setCommonLogin("common_login");
        authVO.setOrgnzt(user.getOrgnztId());
        authVO.setGroup(user.getGroupId());
        authVO.setRegId(user.getUniqId());
        authVO.setItemId(itemId);


        authVO =infoBoardAuthService.selectBoardAuthInfo(authVO);
        boardVO.setBoardAuth(authVO);



        // 카테고리 리스트
        InfoBoardCategoryVO infoBoardCategoryVO = new InfoBoardCategoryVO();
        infoBoardCategoryVO.setBoardId(vo.getBoardId());

        List<?> categoryDepthList =infoBoardCategoryService.selectBoardCategoryDepthList(infoBoardCategoryVO);
        model.addAttribute("categoryList", categoryDepthList);


        InfoBoardItemVO result = infoBoardItemService.selectBoardItem(vo);
        model.addAttribute("resultVO", result);
        List<InfoFileDetailVO> fileList = new ArrayList<>();
        if(result.getBoardFile()!=null || !"".equals(result.getBoardFile())){
            InfoFileDetailVO file = new InfoFileDetailVO();
            file.setAtchFileId(result.getBoardFile());
            fileList =infoFileService.getInfoFileList(file);

        }


        List<InfoFileDetailVO> fileList2 = new ArrayList<>();
        if(result.getFile01()!=null || !"".equals(result.getFile01())){
            InfoFileDetailVO file = new InfoFileDetailVO();
            file.setAtchFileId(result.getFile01());
            fileList2 =infoFileService.getInfoFileList(file);

        }



        model.addAttribute("popYn", "Y");
        model.addAttribute("fileList", fileList);
        model.addAttribute("fileList2", fileList2);
        return InfoViewUtils.adminBoardView(path, "view", "sub");
    }





    @RequestMapping(value = "/cms/info/board/c/updataBoardItemView.do")
    public String updataBoardItemView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoBoardItemVO vo, BindingResult bindingResult, ModelMap model) throws Exception {

        String boardId = vo.getBoardId();
        String path = "";


        InfoBoardVO   boardVO = new InfoBoardVO();
        boardVO.setBoardId(vo.getBoardId());

        boardVO = infoBoardService.selectBoard(boardVO);
        model.addAttribute("boardVO", boardVO);


        // 카테고리 리스트
        InfoBoardCategoryVO infoBoardCategoryVO = new InfoBoardCategoryVO();
        infoBoardCategoryVO.setBoardId(vo.getBoardId());

        List<?> categoryDepthList =infoBoardCategoryService.selectBoardCategoryDepthList(infoBoardCategoryVO);
        model.addAttribute("categoryList", categoryDepthList);



        InfoBoardItemVO boardItemVO = infoBoardItemService.selectBoardItem(vo);
        model.addAttribute("resultVO", boardItemVO);

        List<InfoFileDetailVO> fileList = new ArrayList<>();
        if(boardItemVO.getBoardFile()!=null || !"".equals(boardItemVO.getBoardFile())){

            InfoFileDetailVO file = new InfoFileDetailVO();
            file.setAtchFileId(boardItemVO.getBoardFile());
            fileList =infoFileService.getInfoFileList(file);

        }
        List<InfoFileDetailVO> fileList2 = new ArrayList<>();
        if(boardItemVO.getFile01()!=null || !"".equals(boardItemVO.getFile01())){

            InfoFileDetailVO file = new InfoFileDetailVO();
            file.setAtchFileId(boardItemVO.getFile01());
            fileList2 =infoFileService.getInfoFileList(file);

        }



        model.addAttribute("fileList", fileList);
        model.addAttribute("fileList2", fileList2);


        if ("".equals(boardId) || boardId == null) {
            path = "basic";
        } else {
            path = boardId;
        }
        return InfoViewUtils.adminBoardView(vo.getBoardId(), "updt", "sub");
    }





    @RequestMapping("/cms/info/board/c/latest.do")
    public String bannerlatest(@RequestParam("skinName") String skinName, HttpServletRequest request) throws Exception {
        return InfoViewUtils.adminLatesBoardView(skinName, "");
    }




    @RequestMapping("/cms/info/board/c/update.do")
    public String boardUpdate( @ModelAttribute("resultVO") InfoBoardItemVO infoBoardItemVO,HttpServletRequest request,
                               BindingResult bindingResult, ModelMap model) throws Exception {


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        infoBoardItemVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        infoBoardItemVO.setModIp(request.getRemoteAddr());



        infoBoardItemService.updateBoardItem(infoBoardItemVO);
        model.addAttribute("searchVO",infoBoardItemVO);


        return "forward:/cms/info/board/c/boardItemList.do";


    }



}
