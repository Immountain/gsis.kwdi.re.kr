package gsis.com.site.jewmdis.web;


import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import gsis.com.site.jewmdis.service.SiteJewMdisService;
import gsis.com.site.jewmdis.vo.SiteJewMdisVO;
import infomind.com.cmm.web.BaseController;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;
import infomind.com.utils.web.InfoViewUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = {"/mdis"})
@Controller
public class SiteJewMdisController extends BaseController {



    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


    @Resource(name = "InfoFileService")
    private InfoFileService infoFileService;


    @Resource(name = "SiteJewMdisService")
    private SiteJewMdisService siteJewMdisService;



    @RequestMapping("/list.do")
    public String list(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("searchVO") SiteJewMdisVO searchVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);
        String layout = "";
        if (menuInfo != null) {
            layout = menuInfo.getLayout();
        }



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

        List<?> list = siteJewMdisService.getSelectJewMdisList(searchVO);

        model.addAttribute("list", list);
        model.addAttribute("searchVO", searchVO);

        int totalCnt = siteJewMdisService.getSelectJewMdisTotCnt(searchVO);
        paginationInfo.setTotalRecordCount(totalCnt);
        model.addAttribute("paginationInfo", paginationInfo);




        return InfoViewUtils.gsisPpageContentView("mdis", "jejuMdisList", layout);
    }




    @RequestMapping("/view.do")
    public String view(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("searchVO") SiteJewMdisVO searchVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);
        String layout = "";
        if (menuInfo != null) {
            layout = menuInfo.getLayout();
        }



        SiteJewMdisVO view =siteJewMdisService.getSelectJewMdisView(searchVO);
        model.addAttribute("view", view);


        List<InfoFileDetailVO> etcFileList = new ArrayList<>();
        if (view.getEtc() != null || !"".equals(view.getEtc())) {
            InfoFileDetailVO file = new InfoFileDetailVO();
            file.setAtchFileId(view.getEtc());
            etcFileList = infoFileService.getInfoFileList(file);
        }


        model.addAttribute("etcFileList", etcFileList);


        List<InfoFileDetailVO> dataFileList = new ArrayList<>();
        if (view.getDataFile() != null || !"".equals(view.getDataFile())) {
            InfoFileDetailVO file = new InfoFileDetailVO();
            file.setAtchFileId(view.getDataFile());
            dataFileList = infoFileService.getInfoFileList(file);
        }

        model.addAttribute("dataFileList", dataFileList);






        return InfoViewUtils.gsisPpageContentView("mdis", "jejuMdisView", layout);
    }


}
