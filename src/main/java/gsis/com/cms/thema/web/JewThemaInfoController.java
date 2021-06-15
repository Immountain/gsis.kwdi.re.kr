package gsis.com.cms.thema.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.utils.web.InfoViewUtils;
import gsis.com.cms.thema.service.JewThemaInfoService;
import gsis.com.cms.thema.vo.JewThemaInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class JewThemaInfoController extends BaseAjaxController {

    @Resource(name="JewThemaInfoService")
    private JewThemaInfoService jewThemaInfoService;

    private String pagePath = "thema/";

    /**테마통계관리 목록*/
    @IncludedInfo(name="테마통계관리", listUrl = "/cms/gsis/thema/jewThemaInfoList.do", order = 1111, gid = 60)
    @RequestMapping(value="/cms/gsis/thema/jewThemaInfoList.do")
    public String jewThemaInfoList(@ModelAttribute("searchVO") JewThemaInfoVO searchVO)throws Exception{

        return InfoViewUtils.adminJsView(pagePath,"jewThemaInfoList","ax5ui");
    }

    /**테마통계관리 grid ajax */
    @RequestMapping(value="/cms/gsis/thema/jewThemaInfoListObject.do")
    public ModelAndView jewThemaInfoListObject(@RequestBody JewThemaInfoVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jewThemaInfoService.selectThemaInfoList(searchVO));

        return modelAndView;
    }

    /** 테마통계관리 등록화면 */
    @RequestMapping(value="/cms/gsis/thema/jewThemaInfoRegistView.do")
    public String jewThemaInfoRegistView(@ModelAttribute("searchVO") JewThemaInfoVO searchVO, ModelMap model) throws Exception{
        model.addAttribute("jewThemaInfoVO", new JewThemaInfoVO());
        return InfoViewUtils.adminJsView(pagePath,"jewThemaInfoRegist","axmodal");
    }

    @RequestMapping(value = "/cms/gsis/thema/jewThemaInfoRegist.do")
    @ResponseBody
    public ApiResponse jewThemaInfoRegist(JewThemaInfoVO jewThemaInfoVO)throws Exception{
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewThemaInfoVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        return ok();
    }

}
