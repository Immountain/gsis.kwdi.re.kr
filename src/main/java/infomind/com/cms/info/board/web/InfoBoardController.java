package infomind.com.cms.info.board.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.banner.vo.InfoBannerGroupVO;
import infomind.com.cms.info.board.service.InfoBoardService;
import infomind.com.cms.info.board.service.InfoBoardSkinService;
import infomind.com.cms.info.board.vo.InfoBoardSkinVO;
import infomind.com.cms.info.board.vo.InfoBoardVO;
import infomind.com.cms.info.board.vo.InfoBoardValidator;
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
public class InfoBoardController {
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "InfoBoardSkinService")
    private InfoBoardSkinService infoBoardSkinService;

    @Resource(name = "InfoBoardService")
    private InfoBoardService infoBoardService;
    
    private String pagePath ="info/board/";

    @IncludedInfo(name="게시판 관리",listUrl="/cms/info/board/boardList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/board/boardList.*")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoBoardVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoBoardService.selectBoardList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoBoardService.selectBoardTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"InfoBoardList","ax5ui");
    }

    @RequestMapping(value="/cms/info/board/RegistBoardView.do")
    public String registView(
            @RequestParam Map<?, ?> commandMap,
            @ModelAttribute("resultVO") InfoBoardVO vo, BindingResult bindingResult, ModelMap model)throws Exception {

        InfoBoardSkinVO infoBoardSkinVO = new InfoBoardSkinVO();
        infoBoardSkinVO.setFirstIndex(0);
        infoBoardSkinVO.setLastIndex(Integer.MAX_VALUE);
        infoBoardSkinVO.setRecordCountPerPage(Integer.MAX_VALUE);
        model.addAttribute("boardSkinList", infoBoardSkinService.selectBoardSkinList(infoBoardSkinVO));

        return InfoViewUtils.adminTilesView(pagePath,"InfoBoardRegist","sub");
    }

    @RequestMapping("/cms/info/board/insertBoard.do")
    public String insertBoard(
            @ModelAttribute("resultVO") InfoBoardVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoBoardValidator mValidator = new InfoBoardValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"InfoBoardRegist","ax5ui");
        }

        if(vo.getBoardId() != null){
            InfoBoardVO result = infoBoardService.selectBoard(vo);
            if(result != null){
                model.addAttribute("message", "이미 등록된 페이지 아이디 입니다");
                return InfoViewUtils.adminTilesView(pagePath,"InfoBoardRegist","ax5ui");
            }
        }

        infoBoardService.insertBoard(vo);
        return "forward:/cms/info/board/boardList.do";
    }

    @RequestMapping("/cms/info/board/UpdateBoardView.do")
    public String updateView(
            @ModelAttribute("loginVO") LoginVO loginVO,
            @ModelAttribute("searchVO") InfoBoardVO searchVO, ModelMap model) throws Exception {

        InfoBoardVO result = infoBoardService.selectBoard(searchVO);
        model.addAttribute("resultVO", result);

        InfoBoardSkinVO infoBoardSkinVO = new InfoBoardSkinVO();
        infoBoardSkinVO.setFirstIndex(0);
        infoBoardSkinVO.setLastIndex(Integer.MAX_VALUE);
        infoBoardSkinVO.setRecordCountPerPage(Integer.MAX_VALUE);
        model.addAttribute("boardSkinList", infoBoardSkinService.selectBoardSkinList(infoBoardSkinVO));


        return InfoViewUtils.adminTilesView(pagePath,"InfoBoardUpdt","ax5ui");
    }

    @RequestMapping("/cms/info/board/updateBoard.do")
    public String updateBoard(
            @ModelAttribute("resultVO") InfoBoardVO vo,
            BindingResult bindingResult, ModelMap model) throws Exception {
        //체크
        InfoBoardValidator mValidator = new InfoBoardValidator();
        mValidator.validate(vo, bindingResult);

        // 오류여부 확인
        if(bindingResult.hasErrors()) {

            System.out.println("게시판 오류");

            InfoBoardSkinVO infoBoardSkinVO = new InfoBoardSkinVO();
            infoBoardSkinVO.setFirstIndex(0);
            infoBoardSkinVO.setLastIndex(Integer.MAX_VALUE);
            infoBoardSkinVO.setRecordCountPerPage(Integer.MAX_VALUE);
            model.addAttribute("boardSkinList", infoBoardSkinService.selectBoardSkinList(infoBoardSkinVO));
            return InfoViewUtils.adminTilesView(pagePath,"InfoBoardUpdt","ax5ui");
        }

        infoBoardService.updateBoard(vo);
        return "forward:/cms/info/board/boardList.do";
    }


    @RequestMapping(value="/cms/info/board/popBoardList.*")
    public String popBoardList(@ModelAttribute("searchVO") InfoBoardVO searchVO, ModelMap model) throws Exception {

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

        List<?> list = infoBoardService.selectBoardList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = infoBoardService.selectBoardTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminTilesView(pagePath,"popInfoBoardList","ax5ui");
    }


}
