package infomind.com.cms.info.board.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cms.info.board.service.InfoBoardCommentOptionService;
import infomind.com.cms.info.board.service.InfoBoardCommentOptionService;
import infomind.com.cms.info.board.vo.InfoBoardCommentOptionVO;
import infomind.com.cms.info.board.vo.InfoBoardVO;
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
public class InfoBoardCommentOptionController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoBoardCommentOptionService")
    protected InfoBoardCommentOptionService infoBoardCommentOptionService;
    
    private String pagePath ="info/board/commentoption/";

    @IncludedInfo(name="게시판 권한 관리",listUrl="/cms/info/board/boardCommentOption.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/info/board/boardCommentOption.*")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoBoardCommentOptionVO searchVO, ModelMap model) throws Exception {

        InfoBoardCommentOptionVO result = infoBoardCommentOptionService.selectBoardCommentOption(searchVO);
        if(result != null) {
            result.set__modified__(true);
            model.addAttribute("resultVO", result);
        }else{
            model.addAttribute("resultVO", searchVO);
        }
        return InfoViewUtils.adminTilesView(pagePath,"InfoBoardCommentOption","axmodal");
    }

    @RequestMapping(value = "/cms/info/board/boardCommentOptionUpdateObject.do")
    @ResponseBody
    public ModelAndView boardCommentOptionUpdateObject(@RequestBody InfoBoardCommentOptionVO vo) throws Exception {

        infoBoardCommentOptionService.save(vo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("message", egovMessageSource.getMessage("success.common.update"));
        return modelAndView;
    }
}
