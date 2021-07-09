package gsis.com.site.jejudb.web;


import gsis.com.cms.mdis.vo.JewMdisVO;
import gsis.com.site.jejudb.service.JejuDbService;
import gsis.com.site.jejudb.vo.JejuDbVO;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.utils.web.InfoViewUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RequestMapping(value = {"/statsdb"})
@Controller
public class JejuDbController extends BaseAjaxController {


    @Resource(name = "JejuDbService")
    private JejuDbService jejuDbService;



    @RequestMapping("/list.do")
    public String list(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("searchVO") JejuDbVO searchVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);
        String layout = "";
        if (menuInfo != null) {
            layout = menuInfo.getLayout();
        }
        return InfoViewUtils.gsisPpageContentView("jejudb", "jejudbList", layout);
    }




    /** ajax */
    @RequestMapping(value="/infoList.do")
    public ModelAndView infoList(@RequestBody JejuDbVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jejuDbService.getSelectJejuDbList(searchVO));

        return modelAndView;
    }



    /** ajax */
    @RequestMapping(value="/infoSearchList.do")
    public ModelAndView infoSearchList(@RequestBody JejuDbVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jejuDbService.getSelectSearchJejuDbList(searchVO));

        return modelAndView;
    }


}
