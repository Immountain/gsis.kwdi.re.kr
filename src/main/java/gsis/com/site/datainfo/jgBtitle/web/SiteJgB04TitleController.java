package gsis.com.site.datainfo.jgBtitle.web;

import gsis.com.cms.datainfo.jgBtitle.service.JgB04TitleService;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB04TiileDataVO;
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
public class SiteJgB04TitleController extends BaseAjaxController {



    @Resource(name="JgB04TitleService")
    private JgB04TitleService jgB04TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/b04/List.do")
    public ModelAndView List(@RequestBody JewB04TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgB04TitleService.getSelectList(searchVO));

        return modelAndView;
    }


}
