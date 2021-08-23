package gsis.com.site.datainfo.jgEtitle.web;


import gsis.com.cms.datainfo.jgEtitle.service.JgE05TitleService;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE05TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class SiteJgE05TitleController extends BaseAjaxController {



    @Resource(name="JgE05TitleService")
    private JgE05TitleService jgE05TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/e05/List.do")
    public ModelAndView List(@RequestBody JewE05TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgE05TitleService.getSelectList(searchVO));

        return modelAndView;
    }



}
