package gsis.com.site.datainfo.jgFtitle.web;
import gsis.com.cms.datainfo.jgFtitle.service.JgF06TitleService;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF06TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class SiteJgF06TitleController extends BaseAjaxController {



    @Resource(name="JgF06TitleService")
    private JgF06TitleService jgF06TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/f06/List.do")
    public ModelAndView List(@RequestBody JewF06TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgF06TitleService.getSelectList(searchVO));

        return modelAndView;
    }



}
