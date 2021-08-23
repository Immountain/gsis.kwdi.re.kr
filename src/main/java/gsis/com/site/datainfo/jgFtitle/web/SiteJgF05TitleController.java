package gsis.com.site.datainfo.jgFtitle.web;


import gsis.com.cms.datainfo.jgFtitle.service.JgF05TitleService;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF05TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class SiteJgF05TitleController extends BaseAjaxController {



    @Resource(name="JgF05TitleService")
    private JgF05TitleService jgF05TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/f05/List.do")
    public ModelAndView List(@RequestBody JewF05TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgF05TitleService.getSelectList(searchVO));

        return modelAndView;
    }



}
