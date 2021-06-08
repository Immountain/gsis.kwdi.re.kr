package infomind.com.cms.info.board.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cms.ccm.ccc.vo.InfoCmmnClCode;
import infomind.com.cms.info.board.service.InfoBoardCategoryService;
import infomind.com.cms.info.board.vo.InfoBoardAuthVO;
import infomind.com.cms.info.board.vo.InfoBoardCategoryVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class InfoBoardCategoryController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoBoardCategoryService")
    protected InfoBoardCategoryService infoBoardCategoryService;
    
    private String pagePath ="info/board/category/";

    @IncludedInfo(name="게시판 권한 관리",listUrl="/cms/info/board/boardCategoryList.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/board/boardCategoryList.*")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoBoardCategoryVO searchVO, ModelMap model) throws Exception {

        return InfoViewUtils.adminTilesView(pagePath,"InfoBoardCategoryList","axmodal");
    }

    @RequestMapping(value = "/cms/info/board/boardCategoryListObject.do")
    @ResponseBody
    public ModelAndView boardCategoryListObject(InfoBoardCategoryVO searchVO) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list", infoBoardCategoryService.selectInfoBoardCategoryList(searchVO));


        return modelAndView;
    }

    @RequestMapping(value = "/cms/info/board/boardCategoryUpdateObject.do")
    @ResponseBody
    public ModelAndView boardCategoryUpdateObject(@RequestBody List<InfoBoardCategoryVO> list) throws Exception {

        infoBoardCategoryService.updateList(list);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("message", egovMessageSource.getMessage("success.common.update"));
        return modelAndView;
    }
}
