package js.com.cms.stats.web;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.utils.web.InfoViewUtils;
import js.com.cms.stats.service.JewStatsCategoryService;
import js.com.cms.stats.vo.JewStatsCategoryVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class JewStatsCategoryController extends BaseAjaxController {

    @Resource(name="JewStatsCategoryService")
    private JewStatsCategoryService jewStatsCategoryService;

    private String pagePath = "stats/";


    /** 대 카테고리 목록*/
    @IncludedInfo(name="통계DB카테고리", listUrl = "/cms/js/stats/jewStatsCategoryList.do", order = 1111, gid = 60)
    @RequestMapping(value="/cms/js/stats/jewStatsCategoryList.do")
    public String jewStatsCategoryList(@ModelAttribute("searchVO") JewStatsCategoryVO searchVO)throws Exception{

        return InfoViewUtils.adminJsView(pagePath,"jewStatsCategoryList","ax5ui");
    }

    /** 대 카테고리 grid ajax */
    @RequestMapping(value="/cms/js/stats/jewStatsCategoryObject.do")
    public ModelAndView jewStatsCategoryObject(@RequestBody JewStatsCategoryVO searchVO) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list",jewStatsCategoryService.selectStatsCategoryList(searchVO));

        return modelAndView;
    }

    /** 대 카테고리 등록화면 */
    @RequestMapping(value="/cms/js/stats/jewStatsCategoryRegistView.do")
    public String jewStatsCategoryRegistView(@ModelAttribute("searchVO") JewStatsCategoryVO searchVO, ModelMap model) throws Exception{
        model.addAttribute("jewStatsCategoryVO", new JewStatsCategoryVO());
        return InfoViewUtils.adminJsView(pagePath,"jewStatsCategoryRegist","axmodal");
    }

    /** 카테고리 insert */
    @RequestMapping(value="/cms/js/stats/jewStatsCategoryRegist.do")
    @ResponseBody
    public ApiResponse jewStatsCategoryRegist(JewStatsCategoryVO jewStatsCategoryVO)throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewStatsCategoryVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jewStatsCategoryService.insertStatsCategory(jewStatsCategoryVO);

        return ok();
    }

    /** 대 카테고리 수정화면 */
    @RequestMapping(value="/cms/js/stats/jewStatsCategoryUpdtView.do")
    public String jewStatsCategoryUpdtView(@ModelAttribute("searchVO") JewStatsCategoryVO searchVO, ModelMap model) throws Exception{


        model.addAttribute("jewStatsCategoryVO",jewStatsCategoryService.selectStatsCategory(searchVO));
        return InfoViewUtils.adminJsView(pagePath,"jewStatsCategoryUpdt","axmodal");
    }

    /** 카테고리 UPDATE */
    @RequestMapping(value="/cms/js/stats/jewStatsCategoryUpdt.do")
    @ResponseBody
    public ApiResponse jewStatsCategoryUpdt(JewStatsCategoryVO jewStatsCategoryVO)throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewStatsCategoryVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jewStatsCategoryService.updateStatsCategory(jewStatsCategoryVO);

        return ok();
    }

    /** 카테고리 삭제 */
    @RequestMapping(value="/cms/js/stats/jewStatsCategoryDelete.do")
    @ResponseBody
    public ApiResponse jewStatsCategoryDelete(JewStatsCategoryVO jewStatsCategoryVO)throws Exception{

        jewStatsCategoryService.deleteStatsCategory(jewStatsCategoryVO);

        return ok();
    }

    /** 하위 카테고리 List */
    @RequestMapping(value="/cms/js/stats/jewStatsCategorySubList.do")
    public String jewStatsCategorySubList(@ModelAttribute("searchVO") JewStatsCategoryVO searchVO, ModelMap model)throws Exception{
        List<?> jewStatsCategoryVO = jewStatsCategoryService.selectStatsCategorySearchList(searchVO);
        model.addAttribute("jsCategoryList",jewStatsCategoryVO);
        System.out.println("mountain"+jewStatsCategoryVO);
        return InfoViewUtils.adminJsView(pagePath,"jewStatsCategorySubList","ax5ui");
    }

    /** 하위 카테고리 등록화면 */
    @RequestMapping(value="/cms/js/stats/jewStatsCategorySubRegistView.do")
    public String jewStatsCategorySubRegistView(@ModelAttribute("searchVO") JewStatsCategoryVO searchVO, ModelMap model) throws Exception{
        model.addAttribute("jewStatsCategoryVO", new JewStatsCategoryVO());
        return InfoViewUtils.adminJsView(pagePath,"jewStatsCategorySubRegist","axmodal");
    }

    /** 카테고리 insert */
    @RequestMapping(value="/cms/js/stats/jewStatsCategorySubRegist.do")
    @ResponseBody
    public ApiResponse jewStatsCategorySubRegist(JewStatsCategoryVO jewStatsCategoryVO)throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewStatsCategoryVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jewStatsCategoryService.insertStatsCategory(jewStatsCategoryVO);
        System.out.println("mountain"+jewStatsCategoryVO);
        return ok();
    }

    /** 하위 카테고리 수정화면 */
    @RequestMapping(value="/cms/js/stats/jewStatsCategorySubUpdtView.do")
    public String jewStatsCategorySubUpdtView(@ModelAttribute("searchVO") JewStatsCategoryVO searchVO, ModelMap model) throws Exception{

        model.addAttribute("jewStatsCategoryVO",jewStatsCategoryService.selectStatsCategory(searchVO));
        return InfoViewUtils.adminJsView(pagePath,"jewStatsCategoryUpdt","ax5ui");
    }

    /** 하위 카테고리 UPDATE */
    @RequestMapping(value="/cms/js/stats/jewStatsCategorySubUpdt.do")
    @ResponseBody
    public ApiResponse jewStatsCategorySubUpdt(JewStatsCategoryVO jewStatsCategoryVO)throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewStatsCategoryVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        jewStatsCategoryService.updateStatsCategory(jewStatsCategoryVO);

        return ok();
    }
}
