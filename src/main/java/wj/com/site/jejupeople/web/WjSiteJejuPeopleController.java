package wj.com.site.jejupeople.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.ext.service.LangPackService;
import infomind.com.file.vo.InfoFileDetailVO;
import infomind.com.utils.web.InfoViewUtils;
import infomind.com.utils.web.InfoWebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wj.com.cms.wj.jeju.service.WjJejuPeopleService;
import wj.com.cms.wj.jeju.vo.WjJejuPeoplePicVO;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleVO;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleValidator;
import wj.com.site.jejupeople.service.WjSiteJejuPeopleService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;


@RequestMapping(value = {"/jeju"})
@Controller
public class WjSiteJejuPeopleController {

    /**
     * EgovPropertyService
     */
    @Resource(name = "propertiesService")
    private EgovPropertyService propertiesService;

    /** EgovMessageSource */
    @Resource(name="egovMessageSource")
    private EgovMessageSource egovMessageSource;

    @Resource(name = "WjJejuPeopleService")
    private WjJejuPeopleService wjJejuPeopleService;

    @Resource(name = "WjSiteJejuPeopleService")
    private WjSiteJejuPeopleService wjSiteJejuPeopleService;


    @Resource(name = "LangPackService")
    private LangPackService langPackService;


    @RequestMapping(value = "/people/clause.do", method = RequestMethod.GET)
    public String clause(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("wjJejuPeopleVO") WjJejuPeopleVO wjJejuPeopleVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);

        String layout = "";
        if (menuInfo != null) {
            layout = menuInfo.getLayout();
        }


        return InfoViewUtils.wjPpageContentView("people", "clause", layout);
    }


    @RequestMapping(value = "/people/register.do", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("wjJejuPeopleVO") WjJejuPeopleVO wjJejuPeopleVO
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);

        String layout = "";
        if (menuInfo != null) {
            layout = menuInfo.getLayout();
        }

        //학력
        InfoLangCodeVO infoLangCodeVO = new InfoLangCodeVO();
        infoLangCodeVO.setLangCode(infoSite.getLangCd());
        infoLangCodeVO.setCodeId("WJ_10");
        List<InfoLangCodeVO> wjAlist = langPackService.getInfoLangCode(infoLangCodeVO);
        model.addAttribute("wjAlist", wjAlist);



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

        LoginVO user = (LoginVO) InfoWebUtils.getSessionUser(request);
        String userId="";
        if(user != null) {
            wjJejuPeopleVO = wjSiteJejuPeopleService.selectJejuPeople(user.getId());
            userId =user.getId();

            model.addAttribute("mode", "update");
            model.addAttribute("wjJejuPeopleVO", wjJejuPeopleVO);
        }



        //활동분야
        //로그인 정보가 있으면 넣어준다
        infoLangCodeVO = new InfoLangCodeVO();
        infoLangCodeVO.setLangCode(infoSite.getLangCd());
        infoLangCodeVO.setCodeId("WJ_20");
        infoLangCodeVO.setUserId(userId);
        List<InfoLangCodeVO> wjBlist = langPackService.getInfoActLangCode(infoLangCodeVO);
        model.addAttribute("wjBlist", wjBlist);


        return InfoViewUtils.wjPpageContentView("people", "register", layout);
    }

    @RequestMapping(value = "/people/insert.do", method = RequestMethod.POST)
    public String insert(HttpServletRequest request, ModelMap model
            , @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo
            , @ModelAttribute("wjJejuPeopleVO") WjJejuPeopleVO wjJejuPeopleVO
            , MultipartFile file
            , BindingResult bindingResult
            , RedirectAttributes attr
    ) throws Exception {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);

        String layout = "";
        if (menuInfo != null) {
            layout = menuInfo.getLayout();
        }

        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        wjJejuPeopleVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        wjJejuPeopleVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
        wjJejuPeopleVO.setLangCode(infoSite.getLangCd());

        //체크
        WjJejuPeopleValidator mValidator = new WjJejuPeopleValidator();
        mValidator.validate(wjJejuPeopleVO, bindingResult);

        // 오류여부 확인
        if (bindingResult.hasErrors()) {
            String url = "";
            if (StringUtils.isNotEmpty(infoSite.getSubPath())) {
                url += "/" + infoSite.getSubPath();
            }
            url += "/jeju/people/register.do";


            attr.addFlashAttribute("org.springframework.validation.BindingResult.wjJejuPeopleVO", bindingResult);
            attr.addFlashAttribute("errors", bindingResult.getFieldErrors());
            attr.addFlashAttribute("wjJejuPeopleVO", wjJejuPeopleVO);
            return String.format("redirect:%s", url);
        }

        //System.out.println("파일 이름: {}" + file.getOriginalFilename());
        //System.out.println("파일 크기: {}" + file.getSize());

        String url = "";
        if (StringUtils.isNotEmpty(infoSite.getSubPath())) {
            url += "/" + infoSite.getSubPath();
        }

        if("update".equals(wjSiteJejuPeopleService.saveMber(wjJejuPeopleVO, file))) {
            attr.addFlashAttribute("message", egovMessageSource.getMessage("success.common.update"));
            url += "/jeju/people/register.do";
        }else {
            attr.addFlashAttribute("message", egovMessageSource.getMessage("success.common.insert"));
            url += "/mypage/login.do";
        }

        return String.format("redirect:%s", url);
    }



    @RequestMapping(value="/people/preview.do", produces="text/plain;charset=UTF-8")
    public void preview(HttpServletRequest request, HttpServletResponse response ,String userId){
        WjJejuPeopleVO fileInfo = null;
        InputStream is = null;
        try {


            WjJejuPeoplePicVO wjJejuPeoplePicVO = wjSiteJejuPeopleService.getSelectWjJejuPeoplePic(userId);



            String contentType = "image/jpeg";
            response.setContentType(contentType);
            is = new ByteArrayInputStream(wjJejuPeoplePicVO.getPic());

            ServletOutputStream os = response.getOutputStream();
            int binaryRead;

            while ((binaryRead = is.read()) != -1)    {
                os.write(binaryRead);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{

        }
    }

}
