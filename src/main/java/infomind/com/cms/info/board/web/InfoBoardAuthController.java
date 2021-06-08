package infomind.com.cms.info.board.web;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.sec.drm.service.DeptAuthorVO;
import egovframework.com.sec.drm.service.EgovDeptAuthorService;
import egovframework.com.sec.gmt.service.EgovGroupManageService;
import egovframework.com.sec.gmt.service.GroupManageVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cms.info.board.service.InfoBoardAuthService;
import infomind.com.cms.info.board.vo.InfoBoardAuthConfigVO;
import infomind.com.cms.info.board.vo.InfoBoardAuthVO;
import infomind.com.cms.info.board.vo.InfoBoardCategoryVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class InfoBoardAuthController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "egovGroupManageService")
    protected EgovGroupManageService egovGroupManageService;

    @Resource(name = "egovDeptAuthorService")
    protected EgovDeptAuthorService egovDeptAuthorService;

    @Resource(name = "InfoBoardAuthService")
    protected InfoBoardAuthService infoBoardAuthService;
    
    private String pagePath ="info/board/auth/";

    @IncludedInfo(name="게시판 권한 관리",listUrl="/cms/info/board/boardAuthList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/board/boardAuthList.*")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoBoardAuthVO searchVO, ModelMap model) throws Exception {


        return InfoViewUtils.adminTilesView(pagePath,"InfoBoardAuthList","axmodal");
    }

    @RequestMapping(value = "/cms/info/board/boardAuthNameCode.do")
    @ResponseBody
    public ModelAndView boardAuthNameCode() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        GroupManageVO groupManageVO = new GroupManageVO();
        groupManageVO.setFirstIndex(0);
        groupManageVO.setRecordCountPerPage(Integer.MAX_VALUE);
        DeptAuthorVO deptAuthorVO = new DeptAuthorVO();
        deptAuthorVO.setFirstIndex(0);
        deptAuthorVO.setRecordCountPerPage(Integer.MAX_VALUE);
        modelAndView.addObject("group", egovGroupManageService.selectGroupList(groupManageVO));
        modelAndView.addObject("orgnzt", egovDeptAuthorService.selectDeptList(deptAuthorVO));

        return modelAndView;
    }

    @RequestMapping(value = "/cms/info/board/boardAuthConfigListObject.do")
    @ResponseBody
    public ModelAndView boardAuthConfigListObject(InfoBoardAuthVO searchVO) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list", infoBoardAuthService.selectInfoBoardAuthConfigList(searchVO));

        return modelAndView;
    }

    @RequestMapping(value = "/cms/info/board/boardAuthConfigUpdateObject.do")
    @ResponseBody
    public ModelAndView boardCategoryUpdateObject(@RequestParam String boardId, @RequestBody List<InfoBoardAuthConfigVO> list) throws Exception {

        infoBoardAuthService.updateConfigList(boardId, list);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("message", egovMessageSource.getMessage("success.common.update"));
        return modelAndView;
    }
}
