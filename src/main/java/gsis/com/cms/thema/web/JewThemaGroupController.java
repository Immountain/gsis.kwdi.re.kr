package gsis.com.cms.thema.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.utils.web.InfoViewUtils;
import gsis.com.cms.thema.service.JewThemaGroupService;
import gsis.com.cms.thema.vo.JewThemaGroupVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class JewThemaGroupController extends BaseAjaxController {

    @Resource(name="JewThemaGroupService")
    private JewThemaGroupService jewThemaGroupService;

    private String pagePath = "thema/";

    /**테마통계관리그룹 목록*/
    @IncludedInfo(name="테마통계관리그룹", listUrl = "/cms/gsis/thema/jewThemaGroupList.do", order = 1111, gid = 60)
    @RequestMapping(value="/cms/gsis/thema/jewThemaGroupList.do")
    public String jewThemaGroupList(@ModelAttribute("searchVO") JewThemaGroupVO searchVO)throws Exception{

        return InfoViewUtils.adminJsView(pagePath,"jewThemaGroupList","ax5ui");
    }

    /** 테마통계관리그룹 grid ajax */
    @RequestMapping(value="/cms/gsis/thema/jewThemaGroupListObject.do")
    public ModelAndView jewThemaGroupListObject(@RequestBody JewThemaGroupVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jewThemaGroupService.selectThemaGroupList(searchVO));

        return modelAndView;
    }

    @RequestMapping(value="/cms/gsis/thema/jewThemaGroupRegistView.do")
    public String jewThemaGroupRegistView(@ModelAttribute("searchVO")JewThemaGroupVO searchVO, ModelMap model)throws Exception{
        model.addAttribute("jewThemaGroupVO",new JewThemaGroupVO());
        return InfoViewUtils.adminJsView(pagePath,"jewThemaGroupRegist","axmodal");
    }

    @RequestMapping(value="/cms/gsis/thema/jewThemaGroupRegist.do")
    @ResponseBody
    public ApiResponse jewThemaGroupRegist(JewThemaGroupVO jewThemaGroupVO) throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewThemaGroupVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jewThemaGroupService.insertThemaGroup(jewThemaGroupVO);

        return ok();
    }
    
}
