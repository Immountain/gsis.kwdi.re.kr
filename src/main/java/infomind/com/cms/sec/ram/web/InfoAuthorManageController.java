package infomind.com.cms.sec.ram.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.sec.ram.service.InfoAuthorManageService;
import infomind.com.cms.sec.ram.vo.InfoAuthorManage;
import infomind.com.cms.sec.ram.vo.InfoAuthorManageVO;
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
public class InfoAuthorManageController {

    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "InfoAuthorManageService")
    private InfoAuthorManageService infoAuthorManageService;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    private String pagePath ="sec/ram/";

    @Autowired
    private DefaultBeanValidator beanValidator;

    /**
     * 권한 목록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/cms/sec/ram/InfoAuthorListView.do")
    public String selectInfoAuthorListView()
            throws Exception {
        return InfoViewUtils.adminTilesView(pagePath,"infoAuthorManage","sub");
    }

    /**
     * 권한 목록을 조회한다
     * @param infoAuthorManageVO InfoAuthorManageVO
     * @return String
     * @exception Exception
     */    @IncludedInfo(name = "권한관리", listUrl = "/cms/sec/ram/InfoAuthorList.do", order = 60, gid = 20)
    @RequestMapping(value = "/cms/sec/ram/InfoAuthorList.do")
    public String selectInfoAuthorList(@ModelAttribute("infoAuthorManageVO") InfoAuthorManageVO infoAuthorManageVO,
                                       ModelMap model)
            throws Exception {
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(infoAuthorManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(infoAuthorManageVO.getPageUnit());
        paginationInfo.setPageSize(infoAuthorManageVO.getPageSize());

        infoAuthorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        infoAuthorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        infoAuthorManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        infoAuthorManageVO.setAuthorManageList(infoAuthorManageService.selectInfoAuthorList(infoAuthorManageVO));
        model.addAttribute("authorList", infoAuthorManageVO.getAuthorManageList());

        int totCnt = infoAuthorManageService.selectInfoAuthorListTotCnt(infoAuthorManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
        return InfoViewUtils.adminTilesView(pagePath,"infoAuthorManage","sub");


    }

    /**
     * 권한 세부정보를 조회한다.
     * @param authorCode String
     * @param infoAuthorManageVO InfoAuthorManageVO
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/ram/InfoAuthor.do")
    public String InfoselectAuthor(@RequestParam("authorCode") String authorCode,
                               @ModelAttribute("infoAuthorManageVO") InfoAuthorManageVO infoAuthorManageVO,
                               ModelMap model) throws Exception {

        infoAuthorManageVO.setAuthorCode(authorCode);

        model.addAttribute("authorManage", infoAuthorManageService.selectInfoAuthor(infoAuthorManageVO));
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
        return InfoViewUtils.adminTilesView(pagePath,"infoAuthorUpdate","sub");
    }

    /**
     * 권한 등록화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/cms/sec/ram/InfoAuthorInsertView.do")
    public String insertInfoAuthorView(@ModelAttribute("infoAuthorManage") InfoAuthorManage infoAuthorManage)
            throws Exception {
        return InfoViewUtils.adminTilesView(pagePath,"infoAuthorInsert","sub");
    }

    /**
     * 권한 세부정보를 등록한다.
     * @param infoAuthorManage InfoAuthorManage
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/ram/InfoAuthorInsert.do")
    public String insertInfoAuthor(@ModelAttribute("infoAuthorManage") InfoAuthorManage infoAuthorManage,
                               BindingResult bindingResult,
                               ModelMap model) throws Exception {

        beanValidator.validate(infoAuthorManage, bindingResult); //validation 수행

        if (bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"infoAuthorInsert","sub");
        } else {
            infoAuthorManageService.insertInfoAuthor(infoAuthorManage);
            model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
            return "forward:/cms/sec/ram/InfoAuthorList.do";
        }
    }

    /**
     * 권한 세부정보를 수정한다.
     * @param infoAuthorManage InfoAuthorManage
     * @param bindingResult BindingResult
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/ram/InfoAuthorUpdate.do")
    public String updateInfoAuthor(@ModelAttribute("infoAuthorManage") InfoAuthorManage infoAuthorManage,
                               BindingResult bindingResult,
                               Model model) throws Exception {

        beanValidator.validate(infoAuthorManage, bindingResult); //validation 수행

        if (bindingResult.hasErrors()) {
            return InfoViewUtils.adminTilesView(pagePath,"infoAuthorUpdate","sub");
        } else {
            infoAuthorManageService.updateInfoAuthor(infoAuthorManage);
            model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
            return "forward:/cms/sec/ram/InfoAuthorList.do";
        }
    }

    /**
     * 권한 세부정보를 삭제한다.
     * @param infoAuthorManage InfoAuthorManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/ram/InfoAuthorDelete.do")
    public String deleteInfoAuthor(@ModelAttribute("infoAuthorManage") InfoAuthorManage infoAuthorManage,
                               Model model) throws Exception {

        infoAuthorManageService.deleteInfoAuthor(infoAuthorManage);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/cms/sec/ram/InfoAuthorList.do";
    }

    /**
     * 권한목록을 삭제한다.
     * @param authorCodes String
     * @param infoAuthorManage InfoAuthorManage
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/cms/sec/ram/InfoAuthorListDelete.do")
    public String deleteInfoAuthorList(@RequestParam("authorCodes") String authorCodes,
                                   @ModelAttribute("infoAuthorManage") InfoAuthorManage infoAuthorManage,
                                   Model model) throws Exception {

        String [] strAuthorCodes = authorCodes.split(";");
        for(int i=0; i<strAuthorCodes.length;i++) {
            infoAuthorManage.setAuthorCode(strAuthorCodes[i]);
            infoAuthorManageService.deleteInfoAuthor(infoAuthorManage);
        }
        model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
        return "forward:/cms/sec/ram/InfoAuthorList.do";
    }

    /**
     * 권한제한 화면 이동
     * @return String
     * @exception Exception
     */
    @RequestMapping("/cms/sec/ram/infoAccessDenied.do")
    public String accessDenied()
            throws Exception {
        return "infomind/com/cms/sec/infoAccessDenied";
    }

    @RequestMapping(value = "/cms/sec/ram/PopInfoAuthorList.do")
    public String PopInfoAuthorList(@ModelAttribute("infoAuthorManageVO") InfoAuthorManageVO infoAuthorManageVO,
                                       ModelMap model)
            throws Exception {
        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(infoAuthorManageVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(infoAuthorManageVO.getPageUnit());
        paginationInfo.setPageSize(infoAuthorManageVO.getPageSize());

        infoAuthorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
        infoAuthorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
        infoAuthorManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

        infoAuthorManageVO.setAuthorManageList(infoAuthorManageService.selectInfoAuthorList(infoAuthorManageVO));
        model.addAttribute("authorList", infoAuthorManageVO.getAuthorManageList());

        int totCnt = infoAuthorManageService.selectInfoAuthorListTotCnt(infoAuthorManageVO);
        paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));
        return InfoViewUtils.adminTilesView(pagePath,"PoPinfoAuthorManage","sub");


    }
}
