package infomind.com.site.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cms.info.popup.service.InfoPopupManageService;
import infomind.com.cms.info.site.service.InfoSiteVisitService;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;
import infomind.com.utils.web.InfoViewUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import wj.com.site.festivity.service.WjSiteFestivityService;
import wj.com.site.festivity.vo.WjSiteFestivityInfoVO;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomepageController {

    /**
     * log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HomepageController.class);

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /**
     * InfoPopupManageService
     */
    @Resource(name = "InfoPopupManageService")
    protected InfoPopupManageService infoPopupManageService;

    @Resource(name = "InfoSiteVisitService")
    private InfoSiteVisitService infoSiteVisitService;



    @Resource(name = "WjSiteFestivityService")
    private WjSiteFestivityService wjSiteFestivityService;



    @Resource(name = "InfoFileService")
    private InfoFileService infoFileService;



    private final String MAIN_PAGE_PATH = "page/_mainpage/%s/view.%s";

    @RequestMapping("/index.do")
    public String getIndexPage(ModelMap model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //try {
        //    InfoSiteVisitVO visit = InfoVisitFactory.fromRequest(request);
        //    visit.setPageType("INDEX");
        //    visit.setVisitType("MAIN");
        //    infoSiteVisitService.insertSiteVisit(visit);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}

        //팝업 리스트
        //List<InfoPopupManageVO> popupList =infoPopupManageService.getPopList(null);
        //model.addAttribute("popupList", popupList);

        //SimpleDateFormat format = new SimpleDateFormat( "yyyy.MM.dd일 HH시");
        //Date time = new Date();
        //String formatTime = format.format(time);

        //model.addAttribute("formatTime", formatTime);

        //request.setAttribute("isIndex", true);


        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);


        List<InfoFileDetailVO> fileList = new ArrayList<>();
        WjSiteFestivityInfoVO wjSiteFestivityInfoVO = new WjSiteFestivityInfoVO();
        wjSiteFestivityInfoVO.setLangCode(infoSite.getLangCd());


        WjSiteFestivityInfoVO festivityInfo = new WjSiteFestivityInfoVO();
        int festivitySize =0;

        try {

            List<WjSiteFestivityInfoVO> festivityList =wjSiteFestivityService.getMainFesitivityList(wjSiteFestivityInfoVO);

            festivitySize =festivityList.size();
            if(festivitySize>0){
                festivityInfo =festivityList.get(0);
                if (festivityInfo.getFestivityImage() != null || !"".equals(festivityInfo.getFestivityImage())) {
                    InfoFileDetailVO file = new InfoFileDetailVO();
                    file.setAtchFileId(festivityInfo.getFestivityImage());
                    fileList = infoFileService.getInfoFileList(file);
                }

            }
        }catch (Exception e){


        }

        model.addAttribute("festivitySize", festivitySize);
        model.addAttribute("festivityInfo", festivityInfo);
        model.addAttribute("fileList", fileList);

        System.out.println(String.format("[INFO-%s] %s", this.getClass().getName(), "Started"));
        System.out.println(String.format("[INFO-%s] %s", this.getClass().getName(), InfoViewUtils.forwardSiteMainPage(infoSite)));

        if(StringUtils.isNotEmpty(infoSite.getIndexPage())) {
            return InfoViewUtils.forwardSiteMainPage(infoSite);
        }

        return getIndexPagePath(infoSite.getSiteId(), infoSite.getLayout());
    }

    private String getIndexPagePath(String siteId, String layout) {
        return String.format(MAIN_PAGE_PATH, siteId, layout);
    }

}
