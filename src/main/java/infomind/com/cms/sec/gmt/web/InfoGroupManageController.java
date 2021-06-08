package infomind.com.cms.sec.gmt.web;


import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.sec.gmt.service.InfoGroupManageService;
import infomind.com.cms.sec.gmt.vo.InfoGroupManage;
import infomind.com.cms.sec.gmt.vo.InfoGroupManageVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;


@Controller
@SessionAttributes(types= SessionVO.class)
public class InfoGroupManageController {


    private String pagePath = "sec/gmt/";


    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "InfoGroupManageService")
    private InfoGroupManageService infoGroupManageService;

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * Message ID Generation
     */
    @Resource(name = "egovGroupIdGnrService")
    private EgovIdGnrService egovGroupIdGnrService;

    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 그룹 목록화면 이동
     *
     * @return String
     * @throws Exception
     */
    @RequestMapping("/cms/sec/gmt/InfoGroupListView.do")
    public String selectInfoGroupListView()
            throws Exception {
        return InfoViewUtils.adminTilesView(pagePath, "infoGroupManage", "sub");
    }

    /**
     * 시스템사용 목적별 그룹 목록 조회
     * @param infoGroupManageVO InfoGroupManageVO
     * @return String
     * @exception Exception
     */
    @IncludedInfo(name="그룹관리", listUrl="/cms/sec/gmt/InfoGroupList.do", order = 80,gid = 20)
    @RequestMapping(value="/cms/sec/gmt/InfoGroupList.do")
    public String selectInfoGroupList(@ModelAttribute("infoGroupManageVO") InfoGroupManageVO infoGroupManageVO,
                                  ModelMap model) throws Exception {
        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(infoGroupManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(infoGroupManageVO.getPageUnit());
        paginationInfo.setPageSize(infoGroupManageVO.getPageSize());

        infoGroupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        infoGroupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        infoGroupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        infoGroupManageVO.setGroupManageList(infoGroupManageService.selectInfoGroupList(infoGroupManageVO));
        model.addAttribute("groupList", infoGroupManageVO.getGroupManageList());

        int totCnt = infoGroupManageService.selectInfoGroupListTotCnt(infoGroupManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return InfoViewUtils.adminTilesView(pagePath, "infoGroupManage", "sub");
    }

    /**
     * 검색조건에 따른 그룹정보를 조회
     * @param infoGroupManageVO InfoGroupManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/gmt/InfoGroup.do")
    public String selectInfoGroup(@ModelAttribute("infoGroupManageVO") InfoGroupManageVO infoGroupManageVO,
                              @ModelAttribute("infoGroupManage") InfoGroupManage infoGroupManage,
                              ModelMap model) throws Exception {

        model.addAttribute("infoGroupManage", infoGroupManageService.selectInfoGroup(infoGroupManageVO));
        return InfoViewUtils.adminTilesView(pagePath, "infoGroupUpdate", "sub");
    }

    /**
     * 그룹 등록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/gmt/InfoGroupInsertView.do")
    public String insertInfoGroupView(@ModelAttribute("infoGroupManage") InfoGroupManage infoGroupManage)
            throws Exception {
        return InfoViewUtils.adminTilesView(pagePath, "infoGroupInsert", "sub");
    }

    /**
     * 그룹 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param infoGroupManage GroupInfoGroupManageManage
     * @param infoGroupManageVO InfoGroupManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/gmt/InfoGroupInsert.do")
    public String insertInfoGroup(@ModelAttribute("infoGroupManage") InfoGroupManage infoGroupManage,
                              @ModelAttribute("infoGroupManageVO") InfoGroupManageVO infoGroupManageVO,
                              BindingResult bindingResult,
                              ModelMap model) throws Exception {

        beanValidator.validate(infoGroupManage, bindingResult); //validation 수행

        if (bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath, "infoAuthorManage", "sub");
        } else {
            infoGroupManage.setGroupId(egovGroupIdGnrService.getNextStringId());
            infoGroupManageVO.setGroupId(infoGroupManage.getGroupId());

            model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
            model.addAttribute("infoGroupManage", infoGroupManageService.insertInfoGroup(infoGroupManage, infoGroupManageVO));
            return "forward:/cms/sec/gmt/InfoGroupList.do";
        }
    }

    /**
     * 화면에 조회된 그룹의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param infoGroupManage InfoGroupManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/gmt/InfoGroupUpdate.do")
    public String updateInfoGroup(@ModelAttribute("infoGroupManage") InfoGroupManage infoGroupManage,
                              BindingResult bindingResult,
                              Model model) throws Exception {

        beanValidator.validate(infoGroupManage, bindingResult); //validation 수행

        if (bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath, "infoAuthorManage", "sub");
        } else {
            infoGroupManageService.updateInfoGroup(infoGroupManage);
            model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
            return "forward:/cms/sec/gmt/InfoGroupList.do";
        }
    }

    /**
     * 불필요한 그룹정보를 화면에 조회하여 데이터베이스에서 삭제
     * @param infoGroupManage InfoGroupManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/gmt/InfoGroupDelete.do")
    public String deleteInfoGroup(@ModelAttribute("infoGroupManage") InfoGroupManage infoGroupManage,
                              Model model) throws Exception {
        infoGroupManageService.deleteInfoGroup(infoGroupManage);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/cms/sec/gmt/InfoGroupList.do";
    }

    /**
     * 불필요한 그룹정보 목록을 화면에 조회하여 데이터베이스에서 삭제
     * @param groupIds String
     * @param infoGroupManage InfoGroupManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/gmt/InfoGroupListDelete.do")
    public String deleteInfoGroupList(@RequestParam("groupIds") String groupIds,
                                  @ModelAttribute("infoGroupManage") InfoGroupManage infoGroupManage,
                                  Model model) throws Exception {
        String [] strGroupIds = groupIds.split(";");
        for(int i=0; i<strGroupIds.length;i++) {
            infoGroupManage.setGroupId(strGroupIds[i]);
            infoGroupManageService.deleteInfoGroup(infoGroupManage);
        }

        model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/cms/sec/gmt/InfoGroupList.do";
    }

    /**
     * 그룹팝업 화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/cms/sec/gmt/InfoGroupSearchView.do")
    public String selectInfoGroupSearchView()
            throws Exception {
        return InfoViewUtils.adminTilesView(pagePath, "infoGroupSearch", "sub");
    }

    /**
     * 시스템사용 목적별 그룹 목록 조회
     * @param infoGroupManageVO InfoGroupManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/gmt/InfoGroupSearchList.do")
    public String selectInfoGroupSearchList(@ModelAttribute("infoGroupManageVO") InfoGroupManageVO infoGroupManageVO,
                                        ModelMap model) throws Exception {
        /** paging */
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(infoGroupManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(infoGroupManageVO.getPageUnit());
        paginationInfo.setPageSize(infoGroupManageVO.getPageSize());

        infoGroupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        infoGroupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        infoGroupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        infoGroupManageVO.setGroupManageList(infoGroupManageService.selectInfoGroupList(infoGroupManageVO));
        model.addAttribute("groupList", infoGroupManageVO.getGroupManageList());

        int totCnt = infoGroupManageService.selectInfoGroupListTotCnt(infoGroupManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

        return InfoViewUtils.adminTilesView(pagePath, "infoGroupSearch", "axmodal");
    }
}
