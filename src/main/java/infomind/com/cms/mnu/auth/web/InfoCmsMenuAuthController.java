package infomind.com.cms.mnu.auth.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthConfigVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthVO;
import infomind.com.cms.mnu.auth.service.InfoCmsMenuAuthService;
import infomind.com.cms.mnu.auth.vo.InfoCmsMenuAuthConfigVO;
import infomind.com.cms.mnu.auth.vo.InfoCmsMenuAuthVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class InfoCmsMenuAuthController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoCmsMenuAuthService")
    private InfoCmsMenuAuthService infoCmsMenuAuthService;

    private String pagePath = "mnu/auth/";

    @IncludedInfo(name="CMS 메뉴 권한 관리",listUrl="/cms/info/site/menu/auth.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/mnu/auth/view.*")
    public String selectPageInfoList(@ModelAttribute("searchVO") InfoCmsMenuAuthVO searchVO, ModelMap model) throws Exception {
        return InfoViewUtils.adminTilesView(pagePath,"InfoCmsMenuAuthList","axmodal");
    }

    @RequestMapping(value = "/cms/mnu/auth/menuAuthConfigListObject.do")
    @ResponseBody
    public ModelAndView menuAuthConfigListObject(InfoCmsMenuAuthVO searchVO) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list", infoCmsMenuAuthService.selectInfoMenuAuthConfigList(searchVO));

        return modelAndView;
    }

    @RequestMapping(value = "/cms/mnu/auth/menuAuthConfigUpdateObject.do")
    @ResponseBody
    public ModelAndView menuAuthConfigUpdateObject(@RequestParam String cmsMemuId, @RequestBody List<InfoCmsMenuAuthConfigVO> list) throws Exception {

        infoCmsMenuAuthService.updateConfigList(cmsMemuId, list);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.setStatus(HttpStatus.OK);
        modelAndView.addObject("message", egovMessageSource.getMessage("success.common.update"));
        return modelAndView;
    }
}
