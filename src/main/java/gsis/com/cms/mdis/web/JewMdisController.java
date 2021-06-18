package gsis.com.cms.mdis.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.utils.web.InfoViewUtils;
import gsis.com.cms.mdis.service.JewMdisService;
import gsis.com.cms.mdis.vo.JewMdisVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class JewMdisController extends BaseAjaxController {

    @Resource(name="JewMdisService")
    private JewMdisService jewMdisService;

    private String pagePath ="mdis/";

    /** 마이크로데이터 목록 */
    @IncludedInfo(name="마이크로데이터", listUrl ="/cms/gsis/mdis/jewMdisList.do", order = 1111, gid = 60)
    @RequestMapping(value="/cms/gsis/mdis/jewMdisList.do")
    public String jewMdisList(@ModelAttribute("searchVO") JewMdisVO searchVO)throws Exception{

        return InfoViewUtils.adminJsView(pagePath,"jewMdisList","ax5ui");
    }

    /** 마이크로데이터 grid ajax */
    @RequestMapping(value="/cms/gsis/stats/jewMdisObject.do")
    public ModelAndView jewMdisObject(@RequestBody JewMdisVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jewMdisService.selectMdisList(searchVO));

        return modelAndView;
    }

    /** 마이크로데이터 등록화면 */
    @RequestMapping(value="/cms/gsis/stats/jewMdisRegistView.do")
    public String jewMdisRegistView(@ModelAttribute("searchVO")JewMdisVO searchVO, ModelMap model)throws Exception{
        model.addAttribute("jewMdisVO", new JewMdisVO());
        return InfoViewUtils.adminJsView(pagePath,"jewMdisRegist","ax5ui");
    }
    
    /** 마이크로데이터 등록 */
    @RequestMapping(value="/cms/gsis/stats/jewMdisRegist.do")
    @ResponseBody
    public ApiResponse jewMdisRegist(JewMdisVO jewMdisVO) throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewMdisVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        jewMdisService.insertMdis(jewMdisVO);

        return ok();
    }

    /** 마이크로데이터 수정화면 */
    @RequestMapping(value="/cms/gsis/stats/jewMdisUpdtView.do")
    public String jewMdisUpdtView(@ModelAttribute("searchVO")JewMdisVO searchVO, ModelMap model)throws Exception{
        model.addAttribute("jewMdisVO",jewMdisService.selectMdis(searchVO));

        return InfoViewUtils.adminJsView(pagePath,"jewMdisUpdt","ax5ui");
    }

    /** 마이크로데이터 수정 */
    @RequestMapping(value="/cms/gsis/stats/jewMdisUpdt.do")
    @ResponseBody
    public ApiResponse jewMdisUpdt(JewMdisVO jewMdisVO) throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewMdisVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        jewMdisService.updateMdis(jewMdisVO);

        return ok();
    }

    /** 마이크로데이터 삭제 */
    @RequestMapping(value="/cms/gsis/stats/jewMdisDelete.do")
    @ResponseBody
    public ApiResponse jewMdisDelete(JewMdisVO jewMdisVO) throws Exception{

        jewMdisService.deleteMdis(jewMdisVO);
        return ok();
    }

}
