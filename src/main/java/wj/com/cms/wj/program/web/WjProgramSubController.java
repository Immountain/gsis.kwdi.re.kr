package wj.com.cms.wj.program.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cmm.web.BaseController;
import infomind.com.ext.service.LangPackService;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.festivity.vo.WjFestivityInfoVO;
import wj.com.cms.wj.festivity.vo.WjFestivityScheduleVO;
import wj.com.cms.wj.program.service.WjProgramSubService;
import wj.com.cms.wj.program.vo.WjProgramSubVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class WjProgramSubController extends BaseController {

    @Resource(name="WjProgramSubService")
    private WjProgramSubService wjProgramSubService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    private String pagePath ="wj/program/";

    @Resource(name = "LangPackService")
    private LangPackService langPackService;

    @RequestMapping(value="/cms/wj/program/WjProgramSubList.do")
    public String WjProgramSubList(@ModelAttribute("searchVO") WjProgramSubVO searchVO, ModelMap model)throws Exception{

        /** EgovPropertyService.sample */
        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
        searchVO.setPageSize(propertiesService.getInt("pageSize"));

        /** pageing */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
        paginationInfo.setPageSize(searchVO.getPageSize());

        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        List<?> list = wjProgramSubService.selectProgramSubList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = wjProgramSubService.selectProgramSubTotalCount(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        return InfoViewUtils.adminWjView(pagePath,"wjProgramSubList","axmodal");
    }

    @RequestMapping(value="/cms/wj/program/RegistWjProgramSubView.do")
    public String registView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") WjProgramSubVO wjProgramSubVO) throws Exception {

        wjProgramSubVO.setListLang(langPackService.getSelectWjProgramSubLang(wjProgramSubVO));

        model.addAttribute("resultVO", wjProgramSubVO);
        return InfoViewUtils.adminWjView(pagePath,"wjProgramSubRegist","axmodal");
    }

    @RequestMapping(value="/cms/wj/program/insertWjProgramSub.do")
    @ResponseBody
    public ModelAndView RegistWjProgramSub(@ModelAttribute("resultVO")WjProgramSubVO wjProgramSubVO
                ) throws Exception{

        String resultMessage="";
        if(wjProgramSubService.selectProgramSub(wjProgramSubVO)==null){

            resultMessage = "등록되었습니다";

        }else {

            resultMessage = "수정되었습니다";
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        wjProgramSubVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        wjProgramSubVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMessage);

        wjProgramSubService.insertProgramSub(wjProgramSubVO);

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/program/UpdtWjProgramSubView.do")
    public String UpdtWjProgramSubView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") WjProgramSubVO wjProgramSubVO) throws Exception {

        WjProgramSubVO result =  wjProgramSubService.selectProgramSub(wjProgramSubVO);

        result.setListLang(langPackService.getSelectWjProgramSubLang(wjProgramSubVO));

        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminWjView(pagePath,"wjProgramSubUpdt","axmodal");
    }

    @RequestMapping(value="/cms/wj/program/UpdtWjProgramSub.do")
    @ResponseBody
    public ModelAndView UpdtWjProgramSub(@ModelAttribute("resultVO")WjProgramSubVO wjProgramSubVO)throws Exception{

        String resultMessage="";
        if(wjProgramSubService.selectProgramSub(wjProgramSubVO)==null){

            resultMessage = "등록되었습니다";

        }else {

            resultMessage = "수정되었습니다";
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        wjProgramSubVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        wjProgramSubVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMessage);

        wjProgramSubService.updateProgramSub(wjProgramSubVO);

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/program/deleteWjProgramSub.do")
    @ResponseBody
    public ModelAndView deleteWjProgramSub(@ModelAttribute("resultVO") WjProgramSubVO vo) throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        vo.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        wjProgramSubService.deleteProgramSub(vo);

        return modelAndView;
    }
}
