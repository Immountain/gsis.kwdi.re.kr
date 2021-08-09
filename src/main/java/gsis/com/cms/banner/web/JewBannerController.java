package gsis.com.cms.banner.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import gsis.com.cms.banner.service.JewBannerService;
import gsis.com.cms.banner.vo.JewBannerVO;
import gsis.com.cms.data.vo.JejuDataVO;
import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
@Controller
public class JewBannerController extends BaseAjaxController {


    @Resource(name="JewBannerService")
    private JewBannerService jewBannerService;


    private String pagePath = "banner/";

    /**목록*/
    @IncludedInfo(name="여성가족 배너", listUrl = "/cms/gsis/banner/List.do", order = 1111, gid = 60)
    @RequestMapping(value="/cms/gsis/banner/List.do")
    public String List(@ModelAttribute("searchVO") JewBannerVO searchVO)throws Exception{




        return InfoViewUtils.adminJsView(pagePath,"List","ax5ui");
    }



    /**목록*/
    @RequestMapping(value="/cms/gsis/banner/Regist.do")
    public String Regist(@ModelAttribute("searchVO") JewBannerVO searchVO)throws Exception{

        return InfoViewUtils.adminJsView(pagePath,"Regist","ax5ui");
    }


    /**
     * 리스트
     * @param searchVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/cms/gsis/banner/ObjList.do")
    public ModelAndView ObjList(@RequestBody JewBannerVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jewBannerService.selectJewBannerList(searchVO));

        return modelAndView;
    }

    @RequestMapping(value="/cms/gsis/banner/insertJewBanner.do")
    @ResponseBody
    public ApiResponse insertJewBanner(JewBannerVO vo) throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jewBannerService.insertJewBanner(vo);

        return ok();
    }


    /**상세*/
    @RequestMapping(value="/cms/gsis/banner/view.do")
    public String List(@ModelAttribute("searchVO") JewBannerVO searchVO, ModelMap model)throws Exception{

        JewBannerVO jewBannerVO =jewBannerService.selectJewBannerView(searchVO);

        model.addAttribute("view",jewBannerVO);

        return InfoViewUtils.adminJsView(pagePath,"View","ax5ui");
    }


    @RequestMapping(value="/cms/gsis/banner/updateJewBanner.do")
    @ResponseBody
    public ApiResponse updateJewBanner(JewBannerVO vo) throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jewBannerService.updateJewBanner(vo);

        return ok();
    }



    @RequestMapping(value="/cms/gsis/banner/deleteJewBanner.do")
    @ResponseBody
    public ApiResponse deleteJewBanner(JewBannerVO vo) throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jewBannerService.deleteJewBanner(vo);

        return ok();
    }



}
