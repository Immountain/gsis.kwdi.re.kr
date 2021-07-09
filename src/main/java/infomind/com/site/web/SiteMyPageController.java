package infomind.com.site.web;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.umt.service.MberManageVO;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import infomind.com.cmm.visit.InfoVisitFactory;
import infomind.com.cmm.web.BaseController;
import infomind.com.cms.info.site.service.InfoSiteVisitService;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import infomind.com.cms.uss.umt.vo.InfoStplatVO;
import infomind.com.ext.service.CodeSearchService;
import infomind.com.ext.vo.CodeSearchVO;
import infomind.com.site.service.SiteMyPageService;
import infomind.com.site.vo.SiteJoinVO;
import infomind.com.site.vo.SiteSuccessVO;
import infomind.com.site.vo.SiteUserVO;
import infomind.com.sms.service.InfoSmsInfoService;
import infomind.com.sms.service.ScTranService;
import infomind.com.snsmodule.pcc.service.PccModuleService;
import infomind.com.snsmodule.pcc.vo.PccSmsInfoVO;
import infomind.com.utils.web.InfoWebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static infomind.com.utils.http.InfoHttpResponseUtils.alertAndBackPage;
import static infomind.com.utils.http.InfoHttpResponseUtils.alertAndMovePage;


@Controller
public class SiteMyPageController extends BaseController {


    /**
     * cmmUseService
     */
    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService cmmUseService;


    /**
     * siteMyPageService
     */
    @Resource(name = "SiteMyPageService")
    private SiteMyPageService siteMyPageService;


    @Autowired
    private DefaultBeanValidator beanValidator;


    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


    @Resource(name = "InfoSiteVisitService")
    private InfoSiteVisitService infoSiteVisitService;


    @Resource(name = "CodeSearchService")
    private CodeSearchService codeSearchService;



    /**
     * 마이페이지 로그인
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/mypage/login.do")
    public String mypage2(ModelMap model, HttpServletRequest request) {

        InfoSiteVO infoSite = InfoWebUtils.getCurrentSiteInfo(request);
        LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");

        StringBuilder sb = new StringBuilder();
        sb.append("redirect:/");

        if (StringUtils.isNotEmpty(infoSite.getSubPath())) {
            sb.append(infoSite.getSubPath());
        }

        //   LOGGER.debug("User Id : {}", user == null ? "" : EgovStringUtil.isNullToString(user.getId()));
        String url = "page/mypage/siteLoginUsr.mypage";
        return url;
    }

    /**
     * 마이페이지 입구
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mypage/view.do")
    public String page(ModelMap model, HttpServletRequest request,
                       @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo) throws Exception {

        //로그인 권한 체크
        LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
        if (user == null || user.getUniqId() == null) {
            return "redirect:/mypage/login.do";
        } else {
            //메뉴 통계
            InfoSiteVisitVO visit = InfoVisitFactory.fromRequest(request);
            visit.setPageType("MY_PAGE");
            visit.setVisitType("MYPAGE");
            infoSiteVisitService.insertSiteVisit(visit);


            return "page/mypage/view.mypage";
        }
    }

    /**
     * 회원가입 약관동의
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mypage/joinStep1.do")
    public String joinStep1(ModelMap model, HttpServletRequest request,
                            @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo,
                            @ModelAttribute("mberManageVO") MberManageVO mberManageVO) throws Exception {

        InfoStplatVO stplatVO = new InfoStplatVO();
        String id = "STPLAT_0000000000001";
        stplatVO = siteMyPageService.selectStplat(id);
        model.addAttribute("stplatVO", stplatVO);

        return "page/mypage/joinStep1.mypage";
    }


    /**
     * 회원가입
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mypage/join.do")
    public String join(ModelMap model, HttpServletRequest request,
                       @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo,
                       @ModelAttribute("checkYn") String checkYn,
                       @ModelAttribute(value = "di") String di,
                       @ModelAttribute("reqNum") String reqNum) throws Exception {

//        System.out.println("checkYn===>" + checkYn);
//        System.out.println("di===>" + di);
//        System.out.println("reqNum===>" + reqNum);


//       return "page/mypage/join.mypage";


//        if ("Y".equals(checkYn) && !"".equals(di) && !"".equals(reqNum)) {
//
//            PccSmsInfoVO pccVo = new PccSmsInfoVO();
//            pccVo.setDi(di);
//            pccVo.setReqNum(reqNum);
//
//
//            pccVo = pccModuleService.getSelectPccSmsInfo(pccVo);
//
//            if (pccVo != null && !"".equals(pccVo.getPccSmsSno())) {
//                model.addAttribute("pccVo", pccVo);
//                return "page/mypage/join.mypage";
//            } else {
//                model.addAttribute("msg", "잘못된 접근입니다.");
//                return "forward:/mypage/joinStep1.do";
//            }
//
//        } else {
//            model.addAttribute("msg", "잘못된 접근입니다.");
//            return "forward:/mypage/joinStep1.do";
//        }


        CodeSearchVO codeSearchVO = new CodeSearchVO();
        codeSearchVO.setCodeId("COM022");

        List<CodeSearchVO> codeList = codeSearchService.selectComtccmmndetailcodeList(codeSearchVO) ;
        model.addAttribute("codeList", codeList);


        return "page/mypage/join.mypage";
    }

    /**
     * 회원가입 완료 페이지
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mypage/joinStep3.do")
    public String joinStep3(ModelMap model
            , HttpServletRequest request) throws Exception {

//        resultMsg

        String msg = request.getParameter("resultMsg");
        //    System.out.println("msg==>" + msg);

        return "page/mypage/joinStep3.mypage";
    }

    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     *
     * @param commandMap 파라메터전달용 commandMap
     * @return uss/umt/EgovIdDplctCnfirm
     * @throws Exception
     */
    @RequestMapping(value = "/mypage/checkUserId.do")
    public ModelAndView checkIdDplctAjax(@RequestParam Map<String, Object> commandMap) throws Exception {

        //  System.out.println("checkUserId==>");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String checkId = (String) commandMap.get("checkId");
        //checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

        int usedCnt = siteMyPageService.checkUserId(checkId);
        modelAndView.addObject("usedCnt", usedCnt);
        modelAndView.addObject("checkId", checkId);

        return modelAndView;
    }


    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     *
     * @param commandMap 파라메터전달용 commandMap
     * @return uss/umt/EgovIdDplctCnfirm
     * @throws Exception
     */
    @RequestMapping(value = "/mypage/checkEmail.do")
    public ModelAndView checkEmail(@RequestParam Map<String, Object> commandMap) throws Exception {

        //  System.out.println("checkUserId==>");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        String email = (String) commandMap.get("email");
        //checkId = new String(checkId.getBytes("ISO-8859-1"), "UTF-8");

        int emailCnt = siteMyPageService.checkEmail(email);
        modelAndView.addObject("usedCnt", emailCnt);
        modelAndView.addObject("checkId", email);

        return modelAndView;
    }


    /**
     * 싸이트에서 회원 가입
     * @param siteJoinVO
     * @param response
     * @param model
     * @throws Exception
     */
    @RequestMapping("/mypage/mberInsert.do")
    public void insertMber(@ModelAttribute("siteJoinVO") SiteJoinVO siteJoinVO,  HttpServletResponse response, Model model) throws Exception {


        try {

            siteMyPageService.insertMber(siteJoinVO);
            alertAndMovePage(response, "회원가입 되셨습니다.","/");

        }catch (Exception e){

            alertAndMovePage(response, "처리중 오류가 발생하였습니다.","/mypage/join.do");

        }




       // model.addAttribute("resultMsg", "success");





      //  return "redirect:/mypage/login.do";
    }

    /**
     * 일반회원 암호 수정 화면 이동
     *
     * @param model 화면모델
     * @throws Exception
     */
    @RequestMapping(value = "/mypage/mberPasswordUpdtView.do")
    public String updatePasswordView(ModelMap model) throws Exception {
        return "page/mypage/passwordUpdtView";
    }


    /**
     * 아이디/비번찾기
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mypage/find.do")
    public String find(ModelMap model, HttpServletRequest request,
                       @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo) throws Exception {



        CodeSearchVO codeSearchVO = new CodeSearchVO();
        codeSearchVO.setCodeId("COM022");

        List<CodeSearchVO> codeList = codeSearchService.selectComtccmmndetailcodeList(codeSearchVO) ;
        model.addAttribute("codeList", codeList);




        return "page/mypage/find.mypage";
    }

    /**
     * 비밀번호 찾기
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mypage/findPassword.do")
    public String findPassword(ModelMap model, HttpServletRequest request,
                               @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo) throws Exception {

        return "page/mypage/findPassword";
    }

    // 탈퇴 처리 기능에 대한 예시
    @RequestMapping("/mypage/mberWithdraw.do")
    public String withdrawMber(Model model) throws Exception {
        LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

        String returnPage = "/"; // 탈퇴 처리 후 화면 지정

        if (!isAuthenticated) {
            model.addAttribute("resultMsg", "fail.common.delete");
            return "redirect:" + returnPage;
        }

        //  mberManageService.deleteMber(user == null ? "" : EgovStringUtil.isNullToString(user.getUniqId()));
        //Exception 없이 진행시 삭제성공메시지
        model.addAttribute("resultMsg", "success.common.delete");

        return "redirect:" + returnPage;
    }


    /**
     * 회원수정
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/mypage/modify.do")
    public String modify(ModelMap model, HttpServletRequest request,
                         @ModelAttribute("mberManageVO") SiteJoinVO mberManageVO,
                         @RequestAttribute(value = "menuInfo", required = false) InfoSiteMenuVO menuInfo) throws Exception {

        LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
        if (user == null || user.getUniqId() == null || "".equals(user.getUniqId())) {
            return "redirect:/mypage/login.do";
        }

        if ("USR".equals(user.getUserSe())) {
            model.addAttribute("message", "업무사용자는 관리자 페이지에서만 가능 합니다");
            return "page/mypage/blank.mypage";
        }

        mberManageVO.setUniqId(user.getUniqId());
        mberManageVO.setType(user.getUserSe());
        mberManageVO = siteMyPageService.selectMber(mberManageVO);

        if (mberManageVO == null) {
            model.addAttribute("message", "사용자 정보가 존재하지 않습니다.");
            return "page/mypage/blank.mypage";
        }
        model.addAttribute("userManageVO", mberManageVO);
        return "page/mypage/modify.mypage";
    }

    @RequestMapping("/mypage/mberUpdt.do")
    public String mberUpdt(@ModelAttribute("siteJoinVO") SiteJoinVO siteJoinVO, Model model, HttpServletRequest request) throws Exception {

        LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
        if (user == null || user.getUniqId() == null) {
            return "redirect:/mypage/login.do";
        }


        //ㅇㅇ
        siteJoinVO.setUniqId(user.getUniqId());
        siteMyPageService.updateMber(siteJoinVO);

        return "redirect:/mypage/view.do";
    }


    @RequestMapping(value = "/mypage/getSiteUserIdFind.do")
    @ResponseBody
    public ModelAndView getSiteUserIdFind(@ModelAttribute("searchVO") SiteUserVO searchVO, ModelMap model, HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        List<SiteUserVO> list = siteMyPageService.getSiteUserIdFind(searchVO);
        SiteSuccessVO sVo = new SiteSuccessVO();
        if (list.size() == 1) {

            sVo.setSuccessCode("0000");
            sVo.setMsg("성공");
            sVo.setInfo(list.get(0).getUserId());


        } else if (list.size() == 0) {

            sVo.setSuccessCode("9999");
            sVo.setMsg("없는 사용자 정보입니다");
            sVo.setInfo("");

        } else {

            sVo.setSuccessCode("8888");
            sVo.setMsg("관리자 문의 바랍니다.");
            sVo.setInfo("");

        }
        modelAndView.addObject("info", sVo);
        return modelAndView;
    }


    @RequestMapping(value = "/mypage/getSiteUserPwFind.do")
    @ResponseBody
    public ModelAndView getSiteUserPwFind(@ModelAttribute("searchVO") SiteUserVO searchVO, ModelMap model, HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        List<SiteUserVO> list = siteMyPageService.getSiteUserPwFind(searchVO);
        SiteSuccessVO sVo = new SiteSuccessVO();
        if (list.size() == 1) {

            String userId = list.get(0).getUserId();
            String ESNTL_ID =list.get(0).getEsntlId();

//
//            Random rnd = new Random();
//            StringBuffer buf = new StringBuffer();
//
//            for (int i = 0; i < 8; i++) {
//                if (rnd.nextBoolean()) {
//                    buf.append((char) (rnd.nextInt(26) + 97));
//
//                } else {
//                    buf.append((rnd.nextInt(10)));
//                }
//            }
//
//            String encryptPass = EgovFileScrty.encryptPassword(buf.toString(), userId);
//            list.get(0).setPassword(encryptPass);
//            list.get(0).setType("USR");
//            siteMyPageService.getUserPw(list.get(0));
//            String msg = "세계제주인대회조직위원회 임시 패시워드는[" + buf + "] 입니다.";
            sVo.setSuccessCode("0000");
            sVo.setMsg("성공");
            sVo.setInfo(ESNTL_ID);

        } else if (list.size() == 0) {

            sVo.setSuccessCode("9999");
            sVo.setMsg("실패");
            sVo.setInfo("");

        } else {
            sVo.setSuccessCode("8888");
            sVo.setMsg("관리자 문의 바랍니다.");
            sVo.setInfo("");

        }


        modelAndView.addObject("info", sVo);
        return modelAndView;
    }


    /**
     * 일반회원 암호 수정
     *
     * @throws Exception
     */
    @RequestMapping(value = "/mypage/mberPasswordUpdt.do")
    public ModelAndView updatePassword(@ModelAttribute("searchVO") SiteUserVO searchVO, ModelMap model, HttpServletRequest request) throws Exception {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");

        SiteSuccessVO sVo = new SiteSuccessVO();
        LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
        if (user == null || user.getUniqId() == null) {

            sVo.setSuccessCode("7777");
            sVo.setMsg("다시 로그인 하셔야합니다.");
            sVo.setInfo("");
        }

        String oldPassword = searchVO.getOldPassword();
        String encryptPass = EgovFileScrty.encryptPassword(oldPassword, user.getId());
        searchVO.setPassword(encryptPass);
        searchVO.setEsntlId(user.getUniqId());


        List<SiteUserVO> list = siteMyPageService.getSiteUserPwChangeFind(searchVO);


        if (list.size() == 1) {
            String newPassword = EgovFileScrty.encryptPassword(searchVO.getNewPassword(), user.getId());
            list.get(0).setPassword(newPassword);
            siteMyPageService.getUserPw(list.get(0));

            sVo.setSuccessCode("0000");
            sVo.setMsg("성공");
            sVo.setInfo(searchVO.getNewPassword());


        } else if (list.size() == 0) {

            sVo.setSuccessCode("9999");
            sVo.setMsg("패스워드 확인 바랍니다.");
            sVo.setInfo(searchVO.getNewPassword());

        } else {

            sVo.setSuccessCode("8888");
            sVo.setMsg("관리자 문의 바랍니다.");
            sVo.setInfo("");


        }


        modelAndView.addObject("info", sVo);
        return modelAndView;
    }





    /**
     * 일반회원  새로운 비빌번호 암호 수정
     *
     * @throws Exception
     */
    @RequestMapping(value = "/mypage/mberNewPassword.do")
    public ModelAndView mberNewPassword(@ModelAttribute("searchVO") SiteUserVO searchVO, ModelMap model, HttpServletRequest request) throws Exception {


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jsonView");


        String oldPassword = searchVO.getOldPassword();





        List<SiteUserVO> list = siteMyPageService.getSiteUserPwFind(searchVO);
        SiteSuccessVO sVo = new SiteSuccessVO();
        if (list.size() == 1) {

            String userId = list.get(0).getUserId();
            String ESNTL_ID =list.get(0).getEsntlId();



            String newPassword = EgovFileScrty.encryptPassword(searchVO.getPassword(), searchVO.getUserId());
            searchVO.setPassword(newPassword);



            siteMyPageService.getUserPw(searchVO);
            sVo.setSuccessCode("0000");
            sVo.setMsg("성공");
            sVo.setInfo("");


        } else if (list.size() == 0) {

            sVo.setSuccessCode("9999");
            sVo.setMsg("정보을 확인 바랍니다.");
            sVo.setInfo(searchVO.getNewPassword());

        } else {

            sVo.setSuccessCode("8888");
            sVo.setMsg("관리자 문의 바랍니다.");
            sVo.setInfo("");


        }


        modelAndView.addObject("info", sVo);
        return modelAndView;
    }





    public String smsNum() {

        //문자 랜던
        java.util.Random ran = new Random();
        //랜덤 문자 길이
        int numLength = 6;
        String smsNum = "";
        for (int i = 0; i < numLength; i++) {
            //0 ~ 9 랜덤 숫자 생성
            smsNum += ran.nextInt(10);
        }

        return smsNum;
    }

}
