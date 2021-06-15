package infomind.com.cms.info.mail.web;


import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cmm.ax5ui.model.Ax5ModalFormList;
import infomind.com.cms.info.mail.service.InfoMailSendService;
import infomind.com.cms.info.mail.vo.InfoMailSendFormVO;
import infomind.com.ext.vo.ComvUserMasterVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class InfoMailSendController {

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name = "InfoMailSendService")
    private InfoMailSendService infoMailSendService;

    private final String pagePath = "info/mail/";

//    @IncludedInfo(name = "메일 발송 대상", listUrl = "/cms/info/mail/selectMailSendList.do", order = 1111, gid = 60)
//    @RequestMapping(value = "/cms/info/mail/selectMailSendList.do", method = {RequestMethod.GET})
//    public String selectMailSendList(@ModelAttribute("searchVO") WjJejuPeopleVO searchVO, ModelMap model) throws Exception {
//
//        return InfoViewUtils.adminTilesView(pagePath, "selectMailSendList", "ax5ui");
//    }
//
//    @RequestMapping(value = "/cms/info/mail/selectMailSendListObject.do", method = {RequestMethod.POST})
//    @ResponseBody
//    public ModelAndView selectMailSendListObject(@RequestBody CmsSearchVO searchVO) throws Exception {
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("jsonView");
//        modelAndView.addObject("list", infoMailSendService.selectComUserList(searchVO));
//
//        return modelAndView;
//    }
//
//    @IncludedInfo(name = "메일 발송 대상 이력", listUrl = "/cms/info/mail/selectMailSendHistory.do", order = 1111, gid = 60)
//    @RequestMapping(value = "/cms/info/mail/selectMailSendHistory.do")
//    public String selectMailSendHistory(@ModelAttribute("searchVO") WjJejuPeopleVO searchVO, ModelMap model) throws Exception {
//        return InfoViewUtils.adminTilesView(pagePath, "selectMailSendHistory", "ax5ui");
//    }
//
//    @RequestMapping(value = "/cms/info/mail/selectMailSendHistoryObject.do", method = {RequestMethod.POST})
//    @ResponseBody
//    public ModelAndView selectMailSendHistoryObject(@RequestBody InfoMailSendVO searchVO) throws Exception {
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("jsonView");
//        modelAndView.addObject("list", infoMailSendService.selectList(searchVO));
//
//        return modelAndView;
//    }

    @RequestMapping(value = "/cms/info/mail/selectMailSendForm.do")
    public String wjJejuPeopleMailSendPage(HttpServletRequest request, ModelMap model,
                                           Ax5ModalFormList<ComvUserMasterVO> modalData,
                                           @ModelAttribute("infoMailSendFormVO") InfoMailSendFormVO infoMailSendFormVO) throws Exception {

        model.addAttribute("list", modalData.getList());

        return InfoViewUtils.adminTilesView(pagePath, "selectMailSendForm", "axmodal");
    }

    @RequestMapping(value = "/cms/info/mail/selectMailSend.do", method = RequestMethod.POST)
    public ModelAndView selectMailSend(HttpServletRequest request, ModelMap model,
                                       InfoMailSendFormVO infoMailSendFormVO) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        infoMailSendService.sendMail(infoMailSendFormVO);
        modelAndView.addObject("message", "메일 발송 처리되었습니다.");
        return modelAndView;
    }

}