package wj.com.cms.wj.existing.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.existing.service.WjExistingDataJejuService;
import wj.com.cms.wj.existing.vo.WjExistingDataJejuVO;

import javax.annotation.Resource;

@Controller
public class WjExistingDataJejuController {

    @Resource(name="WjExistingDataJejuService")
    private WjExistingDataJejuService wjExistingDataJejuService;

    private String pagePath = "wj/existing/";

    @IncludedInfo(name="기존제주인관리", listUrl="/cms/wj/existing/wjExistingDataJejuList.do", order = 1111, gid = 60)
    @RequestMapping(value="/cms/wj/existing/wjExistingDataJejuList.do")
    public String wjExistingDataJejuList(@ModelAttribute("searchVO") WjExistingDataJejuVO searchVO, ModelMap model ) throws Exception{

        return InfoViewUtils.adminWjView(pagePath, "wjExistingDataJejuList", "ax5ui");
    }


    @RequestMapping(value = "/cms/wj/existing/SelectWjExistingDataJejuObject.do")
    @ResponseBody
    public ModelAndView SelectWjExistingDataJejuObject(@RequestBody WjExistingDataJejuVO searchVO) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list", wjExistingDataJejuService.selectWjExistingDataJejuList(searchVO));

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/existing/wjExistingDataJejuDetail.do")
    public String wjExistingDataJejuDetail(ModelMap model,@ModelAttribute("resultVO")WjExistingDataJejuVO resultVO)throws Exception{

        WjExistingDataJejuVO result = wjExistingDataJejuService.selectWjExistingDataJeju(resultVO);

        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminWjView(pagePath, "wjExistingDataJejuDetail", "axmodal");
    }
}
