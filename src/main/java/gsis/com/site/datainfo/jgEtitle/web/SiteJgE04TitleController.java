package gsis.com.site.datainfo.jgEtitle.web;


import gsis.com.cms.datainfo.jgEtitle.service.JgE04TitleService;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE04TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class SiteJgE04TitleController extends BaseAjaxController {



    @Resource(name="JgE04TitleService")
    private JgE04TitleService jgE04TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/e04/List.do")
    public ModelAndView List(@RequestBody JewE04TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgE04TitleService.getSelectList(searchVO));

        return modelAndView;
    }



}
