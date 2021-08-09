package gsis.com.site.theme.web;


import gsis.com.cmm.visit.GsisVisitFactory;
import gsis.com.cms.data.service.JejuDataService;
import gsis.com.cms.data.vo.JewThemaFileHisVO;
import gsis.com.cms.thema.service.JewThemaInfoService;
import gsis.com.cms.thema.vo.JewThemaInfoVO;
import gsis.com.site.jejudb.vo.JejuDbVO;
import gsis.com.site.jewmdis.vo.SiteJewMdisVO;
import gsis.com.site.theme.vo.SiteJewThemaInfoVO;
import infomind.com.cmm.visit.InfoVisitFactory;
import infomind.com.cmm.web.BaseController;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.utils.web.InfoViewUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping(value = {"/jejuTheme"})
@Controller
public class SiteThemeController extends BaseController {


    @Resource(name="JewThemaInfoService")
    private JewThemaInfoService jewThemaInfoService;

    @Resource(name="JejuDataService")
    private JejuDataService jejuDataService;




    @RequestMapping("/list.do")
    public String list(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("searchVO") SiteJewThemaInfoVO searchVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);
        String layout = "";
        if (menuInfo != null) {
            layout = menuInfo.getLayout();
        }


        JewThemaInfoVO jewThemaInfoVO = new JewThemaInfoVO();

        //인구와 가족
        jewThemaInfoVO = new JewThemaInfoVO();
        jewThemaInfoVO.setThemaGroupId("J_G_A_TITLE");
        jewThemaInfoVO.setUseYn("Y");
        model.addAttribute("atitleList",jewThemaInfoService.selectThemaInfoList(jewThemaInfoVO));


        //보육과 교육
        jewThemaInfoVO = new JewThemaInfoVO();
        jewThemaInfoVO.setThemaGroupId("J_G_B_TITLE");
        jewThemaInfoVO.setUseYn("Y");
        model.addAttribute("btitleList",jewThemaInfoService.selectThemaInfoList(jewThemaInfoVO));


        //경제 활동
        jewThemaInfoVO = new JewThemaInfoVO();
        jewThemaInfoVO.setThemaGroupId("J_G_C_TITLE");
        jewThemaInfoVO.setUseYn("Y");
        model.addAttribute("ctitleList",jewThemaInfoService.selectThemaInfoList(jewThemaInfoVO));



        //건강과 복지
        jewThemaInfoVO = new JewThemaInfoVO();
        jewThemaInfoVO.setThemaGroupId("J_G_D_TITLE");
        jewThemaInfoVO.setUseYn("Y");
        model.addAttribute("dtitleList",jewThemaInfoService.selectThemaInfoList(jewThemaInfoVO));


        //사회참여와 문화
        jewThemaInfoVO = new JewThemaInfoVO();
        jewThemaInfoVO.setThemaGroupId("J_G_E_TITLE");
        jewThemaInfoVO.setUseYn("Y");
        model.addAttribute("etitleList",jewThemaInfoService.selectThemaInfoList(jewThemaInfoVO));


        //안정과 환경
        jewThemaInfoVO = new JewThemaInfoVO();
        jewThemaInfoVO.setThemaGroupId("J_G_F_TITLE");
        jewThemaInfoVO.setUseYn("Y");
        model.addAttribute("ftitleList",jewThemaInfoService.selectThemaInfoList(jewThemaInfoVO));



        return InfoViewUtils.gsisPpageContentView("theme", "jejuThemeList", layout);
    }





    @RequestMapping("/view.do")
    public String view(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("searchVO") SiteJewThemaInfoVO searchVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);
        String layout = "";
        if (menuInfo != null) {
            layout = menuInfo.getLayout();
        }


        JewThemaInfoVO jewThemaInfoVO = new JewThemaInfoVO();
        jewThemaInfoVO.setThemaId(searchVO.getThemaId());
        jewThemaInfoVO =jewThemaInfoService.selectThemaInfo(jewThemaInfoVO);


        model.addAttribute("view",jewThemaInfoVO);

        JewThemaFileHisVO tempVo =new JewThemaFileHisVO();
        tempVo.setThemaGroupId(jewThemaInfoVO.getThemaGroupId());
        tempVo.setThemaId(jewThemaInfoVO.getThemaId());


        tempVo =jejuDataService.getSelectJewThemaFileHisView(tempVo);
        model.addAttribute("viewFile",tempVo);



        //저장 차리
        JewThemaFileHisVO hisVo = new JewThemaFileHisVO();
        hisVo = GsisVisitFactory.ThemaFileHis(request);
        hisVo.setThemaId(jewThemaInfoVO.getThemaId());
        jejuDataService.getInsertJewThemaVisit(hisVo);




        String url =jewThemaInfoVO.getThemaGroupId()+"/"+jewThemaInfoVO.getThemaId()+"/View";

        System.out.println("url====>"+InfoViewUtils.gsisPpageContentView("datainfo", url, layout));



        return InfoViewUtils.gsisPpageContentView("datainfo", url, layout);
    }
}
