package gsis.com.site.datainfo.jgAtitle.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import gsis.com.cms.datainfo.jgAtitle.service.JgA08TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA08TiileDataVO;
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
public class SiteJgA08TitleController extends BaseAjaxController {



    @Resource(name="JgA08TitleService")
    private JgA08TitleService jgA08TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/a08/List.do")
    public ModelAndView List(@RequestBody JewA08TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgA08TitleService.getSelectList(searchVO));

        return modelAndView;
    }


}
