package gsis.com.site.jewmdis.web;


import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import gsis.com.site.jewmdis.service.SiteJewMdisService;
import gsis.com.site.jewmdis.vo.SiteJewFileDownloadVO;
import gsis.com.site.jewmdis.vo.SiteJewMdisVO;

import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.cmm.web.BaseController;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;

import infomind.com.utils.file.InfoFileMngUtil;
import infomind.com.utils.web.InfoViewUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static infomind.com.utils.http.InfoHttpResponseUtils.alert;
import static infomind.com.utils.http.InfoHttpResponseUtils.alertAndBackPage;
import static org.bouncycastle.crypto.tls.ContentType.alert;

@RequestMapping(value = {"/mdis"})
@Controller
public class SiteJewMdisController extends BaseAjaxController {


    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


    @Resource(name = "InfoFileService")
    private InfoFileService infoFileService;


    @Resource(name = "SiteJewMdisService")
    private SiteJewMdisService siteJewMdisService;

    @Resource(name="InfoFileMngUtil")
    private InfoFileMngUtil fileUtil;



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
        searchVO.setRecordCountPerPage(5);




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



    /** ajax */
    @RequestMapping(value="/fileDown.do")
    public void fileDown2(SiteJewFileDownloadVO searchVO, HttpServletRequest request, HttpServletResponse response) throws Exception {



        LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
        if (user == null || user.getUniqId() == null) {
            alertAndBackPage(response, "권한이 없습니다.");
        }else{

            SiteJewMdisVO siteJewMdisVO = new SiteJewMdisVO();
            siteJewMdisVO.setJewMdisSno(searchVO.getJewMdisSno());


            SiteJewMdisVO view =siteJewMdisService.getSelectJewMdisView(siteJewMdisVO);

            if (view == null) {
                alertAndBackPage(response, "자료가 없습니다.");
            }else{

                List<InfoFileDetailVO> dataFileList = new ArrayList<>();
                if (view.getDataFile() != null || !"".equals(view.getDataFile())) {
                    InfoFileDetailVO file = new InfoFileDetailVO();
                    file.setAtchFileId(view.getDataFile());
                    dataFileList = infoFileService.getInfoFileList(file);
                }
                if(dataFileList.size()>0){

                    for(InfoFileDetailVO fileVo :dataFileList){


                        SiteJewFileDownloadVO siteJewFileDownloadVO = new SiteJewFileDownloadVO();

                        siteJewFileDownloadVO.setDownloadType(searchVO.getDownloadType());
                        siteJewFileDownloadVO.setJewMdisSno(view.getJewMdisSno());
                        siteJewFileDownloadVO.setDataFile(fileVo.getAtchFileId());
                        siteJewFileDownloadVO.setFileSn(fileVo.getFileSn());
                        siteJewFileDownloadVO.setUserId(user.getId());

                        siteJewMdisService.getInsertMdisFileDownload(siteJewFileDownloadVO);



                        fileUtil.fileDown(fileVo,request,response);
                    }
                }else{
                    alertAndBackPage(response, "자료가 없습니다.");
                }
            }

        }


    }




    @RequestMapping(value = "/fileDown2.do")
    public void fileDown(SiteJewMdisVO view, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

//
//        InfoFileDownloadInfoVO file = InfoVisitFactory.downliadRequest(request);
//        file.setAtchFileId(view.getAtchFileId());
//        file.setFileSn(fileVo.getFileSn());





    }


}
