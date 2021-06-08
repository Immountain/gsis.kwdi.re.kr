package infomind.com.site.web;

import infomind.com.cmm.visit.InfoVisitFactory;
import infomind.com.cms.info.page.service.InfoPageInfoService;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import infomind.com.cms.info.site.service.InfoSiteVisitService;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class SiteTestController {

    @Resource(name = "InfoPageInfoService")
    private InfoPageInfoService infoPageInfoService;


    @Resource(name = "InfoSiteVisitService")
    private InfoSiteVisitService infoSiteVisitService;


    @RequestMapping("/{slug}/aaa/list.do")
    public String page(ModelMap model
            , HttpServletRequest request
            ,@RequestAttribute(value ="menuInfo" ,required = false) InfoSiteMenuVO menuInfo
            ) throws Exception{



//
//        String layout = menuInfo.getLayout();
//        InfoPageInfoVO pageInfoVO = new InfoPageInfoVO();
//
//


        return "jejutp/test/view";
    }


}
