package gsis.com.site.datainfo.jgCtitle.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import gsis.com.cms.datainfo.jgCtitle.service.JgC04TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC04TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.utils.poi.ss.reader.XlsxReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class SiteJgC04TitleController extends BaseAjaxController {



    @Resource(name="JgC04TitleService")
    private JgC04TitleService jgC04TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/c04/List.do")
    public ModelAndView List(@RequestBody JewC04TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgC04TitleService.getSelectList(searchVO));

        return modelAndView;
    }



}
