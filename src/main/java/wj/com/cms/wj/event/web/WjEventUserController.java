package wj.com.cms.wj.event.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import wj.com.cms.wj.event.service.WjEventUserService;
import wj.com.cms.wj.event.vo.WjEventUserVO;
import wj.com.cms.wj.festivity.service.WjFestivityApplicationService;
import wj.com.cms.wj.festivity.vo.WjFestivityApplicationVO;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class WjEventUserController {

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name="WjEventUserService")
    private WjEventUserService wjEventUserService;

    @Resource(name="WjFestivityApplicationService")
    private WjFestivityApplicationService wjFestivityApplicationService;

    String pagePath = "wj/event/";

//    @IncludedInfo(name="이벤트신청", listUrl = "/cms/wj/event/wjEventInfoList.do", order = 1111 ,gid = 60)
//    @RequestMapping(value ="/cms/wj/event/wjEventInfoList.do")
//    public String wjFestivityInfoList(@ModelAttribute("searchVO") WjEventUserVO searchVO,
//                                      ModelMap model) throws Exception {
//
//        /** EgovPropertyService.sample */
//        searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
//        searchVO.setPageSize(propertiesService.getInt("pageSize"));
//
//        /** pageing */
//        PaginationInfo paginationInfo = new PaginationInfo();
//        paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
//        paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
//        paginationInfo.setPageSize(searchVO.getPageSize());
//
//        searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
//        searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
//        searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
//
//        List<?> list = wjEventUserService.selectEventUserList(searchVO);
//
//        model.addAttribute("list", list);
//        model.addAttribute("searchVO", searchVO);
//
//        int totalCnt = wjEventUserService.selectEventUserTotalCount(searchVO);
//        paginationInfo.setTotalRecordCount(totalCnt);
//        model.addAttribute("paginationInfo", paginationInfo);
//
//        System.out.println("======================"+list);
//        System.out.println("======================"+totalCnt);
//        return InfoViewUtils.adminWjView(pagePath, "wjFestivityApplicationList", "ax5ui");
//
//    }
}
