package gsis.com.site.datainfo.jgBtitle.web;
import gsis.com.cms.datainfo.jgBtitle.service.JgB01TitleService;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB01TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class SiteJgB01TitleController extends BaseAjaxController {



    @Resource(name="JgB01TitleService")
    private JgB01TitleService jgB01TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/b01/List.do")
    public ModelAndView List(@RequestBody JewB01TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgB01TitleService.selectList(searchVO));

        return modelAndView;
    }



}
