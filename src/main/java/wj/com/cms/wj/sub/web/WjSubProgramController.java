package wj.com.cms.wj.sub.web;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.ext.service.LangPackService;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.festivity.service.WjFestivityInfoService;
import wj.com.cms.wj.festivity.vo.WjFestivityInfoVO;
import wj.com.cms.wj.program.vo.WjProgramInfoVO;
import wj.com.cms.wj.sub.service.WjSubProgramLangService;
import wj.com.cms.wj.sub.service.WjSubProgramService;
import wj.com.cms.wj.sub.vo.WjSubProgramVO;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Controller
public class WjSubProgramController {

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "LangPackService")
    private LangPackService langPackService;

    @Resource(name = "WjSubProgramService")
    private WjSubProgramService wjSubProgramService;

    @Resource(name="WjSubProgramLangService")
    private WjSubProgramLangService wjSubProgramLangService;

    @Resource(name="WjFestivityInfoService")
    private WjFestivityInfoService wjFestivityInfoService;


    private String pagePath = "wj/sub/";



    @IncludedInfo(name="부대프로그램", listUrl ="/cms/wj/sub/WjSubProgramList.do", order = 1111, gid = 60 )
    @RequestMapping(value="/cms/wj/sub/WjSubProgramList.do")
    public String WjSubProgramList(@ModelAttribute("searchVO")WjSubProgramVO searchVO,WjFestivityInfoVO wjFestivityInfoVO, ModelMap model)
        throws Exception{

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

            List<?> list = wjSubProgramService.selectSubProgramList(searchVO);

            wjFestivityInfoVO.setFirstIndex(0);
            List<?>wjFestivityList = wjFestivityInfoService.selectFestivityInfoSearchList(wjFestivityInfoVO);

            model.addAttribute("wjFestivityList",wjFestivityList);
            model.addAttribute("list", list);
            model.addAttribute("searchVO", searchVO);

            int totalCnt = wjSubProgramService.selectSubProgramTotalCount(searchVO);
            paginationInfo.setTotalRecordCount(totalCnt);
            model.addAttribute("paginationInfo", paginationInfo);

            return InfoViewUtils.adminWjView(pagePath, "wjSubProgramList", "ax5ui");
    }

    @RequestMapping(value="/cms/wj/sub/RegistWjSubProgramView.do")
    public String registView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") WjSubProgramVO wjSubProgramVO) throws Exception {

        wjSubProgramVO.setListLang(langPackService.getWjSubProgramLang(wjSubProgramVO));

        model.addAttribute("userSearchVO", wjSubProgramVO);
        return InfoViewUtils.adminWjView(pagePath,"wjSubProgramRegist","axmodal");
    }

    //	다국어 코드언어팩 ajax
    @RequestMapping(value="/cms/wj/sub/RegistWjSubProgram.do")
    @ResponseBody
    public ModelAndView RegistWjSubProgram(@ModelAttribute("resultVO") WjSubProgramVO resultVO
    ) throws Exception{

        String resultMessage="";

        if(wjSubProgramService.selectSubProgram(resultVO)==null){

            resultMessage = "등록되었습니다";

        }else {

            resultMessage = "수정되었습니다";
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMessage);

        wjSubProgramService.insertSubProgram(resultVO);


        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/sub/WjSubProgramUpdtView.do")
    public String WjSubProgramUpdtView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO")CmsSearchVO userSearchVO,
                                       @ModelAttribute("resultVO") WjSubProgramVO wjSubProgramVO) throws Exception{

        WjSubProgramVO resultVO = wjSubProgramService.selectSubProgram(wjSubProgramVO);
        resultVO.setListLang(langPackService.getWjSubProgramLang(wjSubProgramVO));

        model.addAttribute("resultVO", resultVO);

        return InfoViewUtils.adminWjView(pagePath,"wjSubProgramUpdt","axmodal");
    }

    @RequestMapping(value="/cms/wj/sub/WjSubProgramUpdt.do")
    @ResponseBody
    public ModelAndView WjSubProgramUpdt(@ModelAttribute("resultVO")WjSubProgramVO resultVO)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="수정되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjSubProgramService.updateSubProgram(resultVO);

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/sub/WjSubProgramContentUpdtView.do")
    public String WjSubProgramContentUpdtView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO")CmsSearchVO userSearchVO,
                                       @ModelAttribute("resultVO") WjSubProgramVO wjSubProgramVO) throws Exception{

        WjSubProgramVO resultVO = wjSubProgramService.selectSubProgram(wjSubProgramVO);
        resultVO.setListLang(langPackService.getWjSubProgramLang(wjSubProgramVO));

        model.addAttribute("resultVO", resultVO);

        return InfoViewUtils.adminWjView(pagePath,"wjSubProgramContentUpdt","axmodal");
    }

    @RequestMapping(value="/cms/wj/sub/WjSubProgramContentUpdt.do")
    @ResponseBody
    public ModelAndView WjSubProgramContentUpdt(@ModelAttribute("resultVO")WjSubProgramVO resultVO)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String resultMsg="수정되었습니다";

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMsg);

        wjSubProgramLangService.updateSubProgramContentLang(resultVO);

        return modelAndView;
    }
}
