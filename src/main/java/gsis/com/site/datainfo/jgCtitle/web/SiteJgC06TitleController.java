package gsis.com.site.datainfo.jgCtitle.web;


import gsis.com.cms.datainfo.jgCtitle.service.JgC06TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC06TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class SiteJgC06TitleController extends BaseAjaxController {



    @Resource(name="JgC06TitleService")
    private JgC06TitleService jgC06TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/c06/List.do")
    public ModelAndView List(@RequestBody JewC06TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgC06TitleService.getSelectList(searchVO));

        return modelAndView;
    }



}
