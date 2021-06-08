package infomind.com.cms.sec.rgm.web;


import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.sec.ram.service.InfoAuthorManageService;
import infomind.com.cms.sec.ram.vo.InfoAuthorManageVO;
import infomind.com.cms.sec.rgm.service.InfoAuthorGroupService;
import infomind.com.cms.sec.rgm.vo.InfoAuthorGroup;
import infomind.com.cms.sec.rgm.vo.InfoAuthorGroupVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;


@Controller
@SessionAttributes(types= SessionVO.class)
public class InfoAuthorGroupController {

    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "InfoAuthorGroupService")
    private InfoAuthorGroupService infoAuthorGroupService;

    @Resource(name = "InfoAuthorManageService")
    private InfoAuthorManageService infoAuthorManageService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    private String pagePath = "sec/rgm/";

    @RequestMapping("/cms/sec/rgm/InfoAuthorGroupListView.do")
    public String selectInfoAuthorGroupListView() throws Exception {

        return InfoViewUtils.adminTilesView(pagePath, "infoAuthorGroupManage", "sub");
    }

    @IncludedInfo(name = "권한그룹관리", listUrl = "/cms/sec/rgm/InfoAuthorGroupList.do", order = 70, gid = 20)
    @RequestMapping(value = "/cms/sec/rgm/InfoAuthorGroupList.do")
    public String selectInfoAuthorGroupList(@ModelAttribute("infoAuthorGroupVO") InfoAuthorGroupVO infoAuthorGroupVO,
                                        @ModelAttribute("infoAuthorManageVO") InfoAuthorManageVO infoAuthorManageVO,
                                        ModelMap model) throws Exception {

        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(infoAuthorGroupVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(infoAuthorGroupVO.getPageUnit());
        paginationInfo.setPageSize(infoAuthorGroupVO.getPageSize());

        infoAuthorGroupVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        infoAuthorGroupVO.setLastIndex(paginationInfo.getLastRecordIndex());
        infoAuthorGroupVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        infoAuthorGroupVO.setAuthorGroupList(infoAuthorGroupService.selectInfoAuthorGroupList(infoAuthorGroupVO));
        model.addAttribute("authorGroupList", infoAuthorGroupVO.getAuthorGroupList());

        int totCnt = infoAuthorGroupService.selectInfoAuthorGroupListTotCnt(infoAuthorGroupVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        infoAuthorManageVO.setAuthorManageList(infoAuthorManageService.selectInfoAuthorAllList(infoAuthorManageVO));
        model.addAttribute("authorManageList", infoAuthorManageVO.getAuthorManageList());

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));


        return InfoViewUtils.adminTilesView(pagePath, "infoAuthorGroupManage", "ax5ui");


    }

    /**
     * 그룹에 권한정보를 할당하여 데이터베이스에 등록
     * @param userIds String
     * @param authorCodes String
     * @param regYns String
     * @param infoAuthorGroup InfoAuthorGroup
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/rgm/InfoAuthorGroupInsert.do")
    public String insertInfoAuthorGroup(@RequestParam("userIds") String userIds,
                                    @RequestParam("authorCodes") String authorCodes,
                                    @RequestParam("regYns") String regYns,
                                    @RequestParam("mberTyCodes") String mberTyCodes,// 2011.08.04 수정 부분
                                    @ModelAttribute("infoAuthorGroup") InfoAuthorGroup infoAuthorGroup,
                                    ModelMap model) throws Exception {

        String [] strUserIds = userIds.split(";");
        String [] strAuthorCodes = authorCodes.split(";");
        String [] strRegYns = regYns.split(";");
        String [] strMberTyCodes = mberTyCodes.split(";");// 2011.08.04 수정 부분

        for(int i=0; i<strUserIds.length;i++) {
            infoAuthorGroup.setUniqId(strUserIds[i]);
            infoAuthorGroup.setAuthorCode(strAuthorCodes[i]);
            infoAuthorGroup.setMberTyCode(strMberTyCodes[i]);// 2011.08.04 수정 부분
            if(strRegYns[i].equals("N"))
                infoAuthorGroupService.insertInfoAuthorGroup(infoAuthorGroup);
            else
                infoAuthorGroupService.updateInfoAuthorGroup(infoAuthorGroup);
        }

        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
        return "forward:/cms/sec/rgm/InfoAuthorGroupList.do";
    }
    /**
     * 그룹별 할당된 시스템 메뉴 접근권한을 삭제
     * @param userIds String
     * @param infoAuthorGroup InfoAuthorGroup
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/rgm/InfoAuthorGroupDelete.do")
    public String deleteInfoAuthorGroup(@RequestParam("userIds") String userIds,
                                    @ModelAttribute("infoAuthorGroup") InfoAuthorGroup infoAuthorGroup,
                                    ModelMap model) throws Exception {

        String [] strUserIds = userIds.split(";");
        for(int i=0; i<strUserIds.length;i++) {
            infoAuthorGroup.setUniqId(strUserIds[i]);
            infoAuthorGroupService.deleteInfoAuthorGroup(infoAuthorGroup);
        }

        model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/cms/sec/rgm/InfoAuthorGroupList.do";
    }
}
