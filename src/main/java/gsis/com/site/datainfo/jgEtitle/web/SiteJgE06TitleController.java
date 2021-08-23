package gsis.com.site.datainfo.jgEtitle.web;


import gsis.com.cms.datainfo.jgEtitle.service.JgE06TitleService;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE06TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class SiteJgE06TitleController extends BaseAjaxController {



    @Resource(name="JgE06TitleService")
    private JgE06TitleService jgE06TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/e06/List.do")
    public ModelAndView List(@RequestBody JewE06TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgE06TitleService.getSelectList(searchVO));

        return modelAndView;
    }



}
