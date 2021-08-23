package gsis.com.site.datainfo.jgFtitle.web;


import gsis.com.cms.datainfo.jgFtitle.service.JgF01TitleService;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF01TiileDataVO;
import infomind.com.cmm.web.BaseAjaxController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
public class SiteJgF01TitleController extends BaseAjaxController {



    @Resource(name="JgF01TitleService")
    private JgF01TitleService jgF01TitleService;


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/site/gsis/f05/List.do")
    public ModelAndView List(@RequestBody JewF01TiileDataVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jgF01TitleService.getSelectList(searchVO));

        return modelAndView;
    }



}
