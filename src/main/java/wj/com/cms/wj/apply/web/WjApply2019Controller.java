package wj.com.cms.wj.apply.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.apply.service.WjApply2019Service;
import wj.com.cms.wj.apply.vo.WjApply2019VO;

import javax.annotation.Resource;

@Controller
public class WjApply2019Controller {

    @Resource(name="WjApply2019Service")
    private WjApply2019Service wjApply2019Service;

    private String pagePath = "wj/apply/";

    @IncludedInfo(name="2019년도참가신청", listUrl="/cms/wj/apply/wjApply2019List.do", order = 1111, gid = 60)
    @RequestMapping(value="/cms/wj/apply/wjApply2019List.do")
    public String wjApply2019List(@ModelAttribute("searchVO") WjApply2019VO searchVO, ModelMap model ) throws Exception{

        return InfoViewUtils.adminWjView(pagePath, "wjApply2019List", "ax5ui");
    }


    @RequestMapping(value = "/cms/wj/apply/SelectWjApply2019Object.do")
    @ResponseBody
    public ModelAndView SelectWjApply2019Object(@RequestBody WjApply2019VO searchVO) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list", wjApply2019Service.selectWjApply2019List(searchVO));

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/apply/wjApply2019Detail.do")
    public String wjApply2019Detail(ModelMap model,@ModelAttribute("resultVO")WjApply2019VO resultVO)throws Exception{

        WjApply2019VO result = wjApply2019Service.selectWjApply2019(resultVO);

        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminWjView(pagePath, "wjApply2019Detail", "axmodal");
    }
}
