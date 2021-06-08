package wj.com.cms.wj.jeju.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cmm.ax5ui.model.Ax5ModalFormList;
import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.ext.service.LangPackService;
import infomind.com.utils.web.InfoViewUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.jeju.service.WjJejuPeopleService;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleSendMailVO;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WjJejuPeopleController {

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "WjJejuPeopleService")
    private WjJejuPeopleService wjJejuPeopleService;

    @Resource(name = "LangPackService")
    private LangPackService langPackService;

    private final String pagePath="wj/jeju/";

    @IncludedInfo(name="제주인 등록", listUrl = "/cms/wj/jeju/wjJejuPeopleList.do", order= 1111, gid = 60)
    @RequestMapping(value = "/cms/wj/jeju/wjJejuPeopleList.do")
    public String wjJejuPeopleList(@ModelAttribute("searchVO") WjJejuPeopleVO searchVO,
                                   ModelMap model)throws Exception{

        return InfoViewUtils.adminWjView(pagePath, "wjJejuPeopleList", "ax5ui");
    }

    @RequestMapping(value = "/cms/wj/jeju/wjJejuPeopleObject.do")
    @ResponseBody
    public ModelAndView wjJejuPeopleObject(@RequestBody WjJejuPeopleVO searchVO) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");
        modelAndView.addObject("list", wjJejuPeopleService.selectJejuPeopleList(searchVO));

        return modelAndView;
    }

    @RequestMapping(value="/cms/wj/jeju/wjJejuPeopleDetail.do")
    public String wjJejuPeopleDetail(HttpServletRequest request,
                                     ModelMap model, @ModelAttribute("resultVO")WjJejuPeopleVO resultVO)throws Exception{

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);

        WjJejuPeopleVO result = wjJejuPeopleService.selectJejuPeople(resultVO);

        //학력
        InfoLangCodeVO infoLangCodeVO = new InfoLangCodeVO();
        infoLangCodeVO.setLangCode(infoSite.getLangCd());
        infoLangCodeVO.setCodeId("WJ_10");
        List<InfoLangCodeVO> wjAlist = langPackService.getInfoLangCode(infoLangCodeVO);
        model.addAttribute("wjAlist", wjAlist);

        //활동분야
        //로그인 정보가 있으면 넣어준다
        infoLangCodeVO = new InfoLangCodeVO();
        infoLangCodeVO.setLangCode(infoSite.getLangCd());
        infoLangCodeVO.setCodeId("WJ_20");
        infoLangCodeVO.setUserId(resultVO.getUserId());
        List<InfoLangCodeVO> wjBlist = langPackService.getInfoActLangCode(infoLangCodeVO);
        model.addAttribute("wjBlist", wjBlist);

        //종교
        infoLangCodeVO = new InfoLangCodeVO();
        infoLangCodeVO.setLangCode(infoSite.getLangCd());
        infoLangCodeVO.setCodeId("WJ_30");
        List<InfoLangCodeVO> wjClist = langPackService.getInfoLangCode(infoLangCodeVO);
        model.addAttribute("wjClist", wjClist);

        //국가 코드 리스트
        infoLangCodeVO = new InfoLangCodeVO();
        infoLangCodeVO.setLangCode(infoSite.getLangCd());
        infoLangCodeVO.setCodeId("NATION");
        List<InfoLangCodeVO> nationlist = langPackService.getInfoLangCode(infoLangCodeVO);
        model.addAttribute("nationlist", nationlist);

        model.addAttribute("resultVO", result);
        return InfoViewUtils.adminWjView(pagePath, "wjJejuPeopleDetail", "axmodal");
    }

    @RequestMapping(value = "/cms/wj/jeju/wjJejuPeopleMailSendForm.do")
    public String wjJejuPeopleMailSendPage(HttpServletRequest request, ModelMap model,
                                       Ax5ModalFormList<WjJejuPeopleVO> modalData,
                                       @ModelAttribute("wjJejuPeopleSendMail") WjJejuPeopleSendMailVO wjJejuPeopleSendMail) throws Exception {


        model.addAttribute("list", modalData.getList());

        return InfoViewUtils.adminWjView(pagePath, "wjJejuPeopleMailSend", "axmodal");
    }

    @RequestMapping(value = "/cms/wj/jeju/wjJejuPeopleMailSend.do", method = RequestMethod.POST)
    public ModelAndView wjJejuPeopleMailSend(HttpServletRequest request, ModelMap model,
                                       WjJejuPeopleSendMailVO wjJejuPeopleSendMail) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        wjJejuPeopleService.sendMail(wjJejuPeopleSendMail);
        modelAndView.addObject("message", "메일 발송 처리되었습니다.");
        return modelAndView;
    }

}


