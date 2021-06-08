package infomind.com.site.web;

import egovframework.com.cmm.LoginVO;
import infomind.com.cmm.InfoConstants;
import infomind.com.cmm.visit.InfoVisitFactory;
import infomind.com.cms.info.layout.service.InfoLayoutInfoService;
import infomind.com.cms.info.layout.vo.InfoLayoutInfoVO;
import infomind.com.cms.info.page.service.InfoPageInfoService;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import infomind.com.cms.info.site.service.InfoSiteVisitService;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import infomind.com.utils.page.InfoPageUtils;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;


@Controller
public class SitePageController {

    @Resource(name = "InfoPageInfoService")
    private InfoPageInfoService infoPageInfoService;

    @Resource(name = "InfoSiteVisitService")
    private InfoSiteVisitService infoSiteVisitService;

    @Resource(name = "InfoSiteVisitService")
    private InfoSiteVisitService InfoLayoutInfoService;

    @RequestMapping("/page/{slug}")
    public String page(HttpServletRequest request, ModelMap model
            , @PathVariable("slug") String slug
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo) throws Exception {

        System.out.println(String.format("[INFO-%s] %s", this.getClass().getName(), "Started"));

        if ("Y".equals(menuInfo.getLoginYn())) {
            LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
            if (user == null || user.getUniqId() == null) {
                return "redirect:/mypage/login.do";
            }
        }

        String layout = menuInfo.getLayout();
        InfoPageInfoVO pageInfoVO = new InfoPageInfoVO();

        pageInfoVO.setPageId(menuInfo.getMenuPage());

        try {
            pageInfoVO = infoPageInfoService.selecPageInfoDetail(pageInfoVO);

            if (pageInfoVO != null) {

                //메뉴 통계
                InfoSiteVisitVO visit = InfoVisitFactory.fromRequest(request);
                visit.setPageType(slug);
                visit.setTargetId(pageInfoVO.getPageId());
                visit.setVisitType("PAGE");
                infoSiteVisitService.insertSiteVisit(visit);

                //디비 타입 일때 디비 내용 그대로 보여준다.
                if ("DB".equals(pageInfoVO.getModType())) {

                    model.addAttribute("contentInfo", pageInfoVO.getContentInfoDecode());
                    return InfoViewUtils.pageContentView("", "default", layout);
                } else if ("JSP".equals(pageInfoVO.getModType())) {

                    //디레토리 체크
                    File newFile = new File(InfoConstants.DIR_PATH() + "/WEB-INF/jsp/page/" + pageInfoVO.getPageGroupId() + "/" + pageInfoVO.getPageId() + "/info", "info.jsp");
                    if (newFile.isFile()) {
                        System.out.println("페이지 파일 있습니다.");
                    } else {
                        InfoPageUtils.createPageFile(pageInfoVO.getContentInfoDecode(), pageInfoVO.getPageId(), pageInfoVO.getPageGroupId());
                    }
                    return InfoViewUtils.pageContentView(pageInfoVO.getPageGroupId() + "/" + pageInfoVO.getPageId() + "/info", "info", layout);
                } else {
                    return InfoViewUtils.pageContentView(pageInfoVO.getPageGroupId() + "/" + pageInfoVO.getPageId(), "view", layout);
                }
            } else {
                System.out.println("pageInfoVO==>" + InfoViewUtils.pageContentView("blank", "", layout));
                return InfoViewUtils.pageContentView("blank", "", layout);
            }
        } catch (Exception e) {
            return InfoViewUtils.pageContentView("blank", "", layout);
        }
    }


}
