package gsis.com.cms.thema.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import gsis.com.cms.thema.service.JewThemaGroupService;
import gsis.com.cms.thema.vo.JewThemaGroupVO;
import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.cms.info.site.vo.InfoSiteStatsVO;
import infomind.com.ext.vo.AxGridDataVO;
import infomind.com.ext.vo.AxGridPageVO;
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
import java.util.List;

@Controller
public class JewThemaInfoController extends BaseAjaxController {

    @Resource(name="JewThemaInfoService")
    private JewThemaInfoService jewThemaInfoService;

    @Resource(name="JewThemaGroupService")
    private JewThemaGroupService jewThemaGroupService;

    private final String pagePath = "thema/";

    /**테마통계관리 목록*/
    @IncludedInfo(name="테마통계관리", listUrl = "/cms/gsis/thema/jewThemaInfoList.do", order = 1111, gid = 60)
    @RequestMapping(value="/cms/gsis/thema/jewThemaInfoList.do")
    public String jewThemaInfoList(@ModelAttribute("searchVO") JewThemaInfoVO searchVO,ModelMap model)throws Exception{

        JewThemaGroupVO jewThemaGroupVO = new JewThemaGroupVO();
        model.addAttribute("jewGroupList",jewThemaGroupService.selectThemaGroupList(jewThemaGroupVO));
        System.out.println("MountainTest====================================="+jewThemaGroupService.selectThemaGroupList(jewThemaGroupVO));
        return InfoViewUtils.adminJsView(pagePath,"jewThemaInfoList","ax5ui");
    }

    /**테마통계관리 grid ajax */
    @RequestMapping(value="/cms/gsis/thema/jewThemaInfoListObject.do")
    @ResponseBody
    public Object jewThemaInfoListObject(@RequestBody JewThemaInfoVO searchVO) throws Exception{

        searchVO.setPageIndex(searchVO.getPageIndex() + 1);
        PaginationInfo paginationInfo = initPagination(searchVO, jewThemaInfoService.selectThemaInfoTotalCount(searchVO));

        AxGridDataVO<JewThemaInfoVO> data = new AxGridDataVO();

        data.setList((List<JewThemaInfoVO>) jewThemaInfoService.selectThemaInfoList(searchVO));
        data.setPage(AxGridPageVO.builder()
                .currentPage(searchVO.getPageIndex() - 1)
                .pageSize(searchVO.getPageUnit())
                .totalElements(paginationInfo.getTotalRecordCount())
                .totalPages(paginationInfo.getTotalPageCount())
                .build());
        return data;
    }

    /** 테마통계관리 등록화면 */
    @RequestMapping(value="/cms/gsis/thema/jewThemaInfoRegistView.do")
    public String jewThemaInfoRegistView(@ModelAttribute("searchVO") JewThemaInfoVO searchVO, ModelMap model) throws Exception{

        JewThemaGroupVO jewThemaGroupVO = new JewThemaGroupVO();
        model.addAttribute("jewThemaInfoVO", new JewThemaInfoVO());
        model.addAttribute("jewGroupList",jewThemaGroupService.selectThemaGroupList(jewThemaGroupVO));

        return InfoViewUtils.adminJsView(pagePath,"jewThemaInfoRegist","axmodal");
    }

    @RequestMapping(value = "/cms/gsis/thema/jewThemaInfoRegist.do")
    @ResponseBody
    public ApiResponse jewThemaInfoRegist(JewThemaInfoVO jewThemaInfoVO)throws Exception{
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewThemaInfoVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        System.out.println("mountain"+jewThemaInfoVO);
        jewThemaInfoService.insertThemaInfo(jewThemaInfoVO);
        return ok();
    }

    @RequestMapping(value="/cms/gsis/thema/jewThemaInfoUpdtView.do")
    public String jewThemaInfoUpdtView(@ModelAttribute("searchVO")JewThemaInfoVO searchVO,ModelMap model)throws Exception{

        JewThemaGroupVO jewThemaGroupVO = new JewThemaGroupVO();
        model.addAttribute("jewThemaInfoVO",jewThemaInfoService.selectThemaInfo(searchVO));
        model.addAttribute("jewGroupList",jewThemaGroupService.selectThemaGroupList(jewThemaGroupVO));
        return InfoViewUtils.adminJsView(pagePath,"jewThemaInfoUpdt","axmodal");
    }

    @RequestMapping(value="/cms/gsis/thema/jewThemaInfoUpdt.do")
    @ResponseBody
    public ApiResponse jewThemaInfoUpdt(JewThemaInfoVO jewThemaInfoVO)throws Exception{

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        jewThemaInfoVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        jewThemaInfoService.updateThemaInfo(jewThemaInfoVO);

        return ok();
    }

    @RequestMapping(value="/cms/gsis/thema/jewThemaInfoDelete.do")
    @ResponseBody
    public ApiResponse jewThemaInfoDelete(JewThemaInfoVO jewThemaInfoVO)throws Exception {

        jewThemaInfoService.deleteThemaInfo(jewThemaInfoVO);

        return ok();
    }

    @RequestMapping(value = "/cms/gsis/thema/getDashBoardThemaInfo.do")
    @ResponseBody
    public Object getSiteMenuTotYear(JewThemaInfoVO vo) throws Exception {
        //파인 차트
        return jewThemaInfoService.getDashBoardThemaInfo(vo);
    }
}
