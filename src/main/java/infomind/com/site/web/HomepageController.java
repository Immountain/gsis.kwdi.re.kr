package infomind.com.site.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import gsis.com.cms.data.service.JejuDataService;
import gsis.com.cms.data.vo.JewThemaFileHisVO;
import infomind.com.cmm.visit.InfoVisitFactory;
import infomind.com.cms.info.popup.service.InfoPopupManageService;
import infomind.com.cms.info.popup.vo.InfoPopupManageVO;
import infomind.com.cms.info.site.service.InfoSiteVisitService;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import infomind.com.file.service.InfoFileService;
import infomind.com.utils.web.InfoViewUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


    @Resource(name="JejuDataService")
    private JejuDataService jejuDataService;


    @Resource(name = "InfoFileService")
    private InfoFileService infoFileService;



    private final String MAIN_PAGE_PATH = "page/_mainpage/%s/view.%s";

    @RequestMapping("/index.do")
    public String getIndexPage(ModelMap model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //메인 업데이트 리스트
            List<JewThemaFileHisVO> updateList =jejuDataService.getSelectMainUpdateList(null);
            model.addAttribute("updateList", updateList);

        } catch (Exception e) {
          //  e.printStackTrace();
        }

        //팝업 리스트
        List<InfoPopupManageVO> popupList =infoPopupManageService.getPopList(null);
        model.addAttribute("popupList", popupList);





        //SimpleDateFormat format = new SimpleDateFormat( "yyyy.MM.dd일 HH시");
        //Date time = new Date();
        //String formatTime = format.format(time);

        //model.addAttribute("formatTime", formatTime);

        //request.setAttribute("isIndex", true);


        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);



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
