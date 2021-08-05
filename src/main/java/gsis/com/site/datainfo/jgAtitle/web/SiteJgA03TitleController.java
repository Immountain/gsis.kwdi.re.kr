package gsis.com.site.datainfo.jgAtitle.web;

import gsis.com.cms.datainfo.jgAtitle.service.JgA03TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
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
public class SiteJgA03TitleController extends BaseAjaxController {



    @Resource(name="JgA03TitleService")
    private JgA03TitleService jgA03TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/a03/List.do")
    public ModelAndView List(@RequestBody JewA03TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgA03TitleService.selectList(searchVO));

        return modelAndView;
    }



}
