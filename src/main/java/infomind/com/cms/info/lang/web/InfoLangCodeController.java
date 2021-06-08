package infomind.com.cms.info.lang.web;
import com.google.common.collect.ImmutableMap;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cms.ccm.cde.service.InfoCcmCmmnDetailCodeManageService;
import infomind.com.cms.ccm.cde.vo.InfoCmmnDetailCodeVO;
import infomind.com.cms.info.lang.service.InfoLangCodeService;
import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import infomind.com.ext.service.LangPackService;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import wj.com.cms.wj.festivity.vo.WjFestivityInfoVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class InfoLangCodeController {

//    @ModelAttribute("lang")
//    private List<Map<String, String>> getLangData() {
//        List<Map<String, String>> list = new ArrayList<>();
//
//        list.add(ImmutableMap.<String, String>builder().put("langCode", "KR").put("langNm", "한국어").build());
//        list.add(ImmutableMap.<String, String>builder().put("langCode", "EN").put("langNm", "영어").build());
//        list.add(ImmutableMap.<String, String>builder().put("langCode", "JP").put("langNm", "일본어").build());
//        list.add(ImmutableMap.<String, String>builder().put("langCode", "CN").put("langNm", "중국어").build());
//
//        return list;
//    }

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource(name="InfoCcmCmmnDetailCodeManageService")
    private InfoCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;

    @Resource(name="LangPackService")
    private LangPackService langPackService;

    @Resource(name = "InfoLangCodeService")
    private InfoLangCodeService infoLangCodeService;

    private String pagePath = "info/langCode/";



    @RequestMapping(value="/cms/info/langCode/RegistLangCodeView.do")
    public String registView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
                             @ModelAttribute("resultVO") InfoLangCodeVO infoLangCodeVO, InfoCmmnDetailCodeVO cmmnDetailCodeVO) throws Exception {

        InfoCmmnDetailCodeVO result = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
        InfoLangCodeVO resultLang = new InfoLangCodeVO();

        resultLang.setListLang(langPackService.getInfoLangCode(infoLangCodeVO));

        model.addAttribute("resultVO",resultLang);
        model.addAttribute("result", result);


        return InfoViewUtils.adminTilesView(pagePath,"infoLangCodeRegist","axmodal");
    }



    //	다국어 코드언어팩 ajax
    @RequestMapping(value="/cms/info/langCode/RegistLangCode.do")
    @ResponseBody
    public ModelAndView InfoUserPasswordUpdtObject(@ModelAttribute("resultVO") InfoLangCodeVO resultVO
    ) throws Exception{

        String resultMessage="";

        if(infoLangCodeService.selectLangCode(resultVO) == null){

            resultMessage = "등록되었습니다";

        }else {

            resultMessage = "수정되었습니다";
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        resultVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        resultVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

        modelAndView.addObject("message", resultMessage);

        infoLangCodeService.insertLangCode(resultVO);

        return modelAndView;
    }
}
