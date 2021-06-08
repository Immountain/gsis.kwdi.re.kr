package infomind.com.site.web;

import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SiteServcieController {


    @RequestMapping("/banner/latest.do")
    public String bannerlatest(@RequestParam("skinName") String skinName, HttpServletRequest request) throws Exception {


        return InfoViewUtils.bannerLatesView(skinName);
    }



    @RequestMapping("/menu/latest.do")
    public String menulatest(@RequestParam("skinName") String skinName, HttpServletRequest request) throws Exception {


        return "page/menu/latest/"+skinName;
    }



}
