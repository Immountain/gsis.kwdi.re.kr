package gsis.com.site.datainfo.jgAtitle.web;

import gsis.com.cms.datainfo.jgAtitle.service.JgA02TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA02TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class SiteJgA02TitleController extends BaseAjaxController {



    @Resource(name="JgA02TitleService")
    private JgA02TitleService jgA02TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/a02/List.do")
    public ModelAndView List(@RequestBody JewA02TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgA02TitleService.getSelectList(searchVO));

        return modelAndView;
    }


}
