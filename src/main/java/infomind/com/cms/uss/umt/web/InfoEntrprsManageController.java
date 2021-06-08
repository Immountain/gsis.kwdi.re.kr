package infomind.com.cms.uss.umt.web;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cmm.web.BaseController;
import infomind.com.cms.mnu.mpm.vo.InfoMenuManageVO;
import infomind.com.cms.sec.gmt.service.InfoGroupManageService;
import infomind.com.cms.sec.gmt.vo.InfoGroupManageVO;
import infomind.com.cms.uss.umt.service.InfoEntrprsManageService;
import infomind.com.cms.uss.umt.vo.InfoEntrprsManageVO;
import infomind.com.cms.uss.umt.vo.InfoEntrprsValidator;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.site.service.SiteMyPageService;
import infomind.com.utils.web.InfoViewUtils;
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
import java.util.List;
import java.util.Map;

/**
 * 기업회원관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * @  수정일             수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2020.11.25   이기선              최초 생성
 *  @author 인포마인드 개발팀
 *  @since 2020.11.25
 *  @version 1.0
 *  @see
 *
 */

@Controller
public class InfoEntrprsManageController extends BaseController {

	/** InfoEntrprsManageService */
	@Resource(name = "InfoEntrprsManageService")
	private InfoEntrprsManageService entrprsManageService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** DefaultBeanValidator beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	@Resource(name = "SiteMyPageService")
	private SiteMyPageService siteMyPageService;


	@Resource(name = "InfoGroupManageService")
	private InfoGroupManageService infoGroupManageService;




	private final String pagePath ="uss/umt/";

	/**
	 * 기업회원목록을 조회한다. (pageing)
	 * @param userSearchVO 검색조건정보
	 * @param model 화면모델
	 * @return /cms/uss/umt/InfoEntrprsMberManage
	 * @throws Exception
	 */
	@IncludedInfo(name = "기업회원관리", order = 450, gid = 50)
	@RequestMapping(value = "/cms/uss/umt/InfoEntrprsMberManage.do")
	public String selectEntrprsMberList(@ModelAttribute("userSearchVO") InfoEntrprsManageVO userSearchVO
			, ModelMap model
			,@RequestAttribute(value ="menuInfo" ,required = false) InfoMenuManageVO menuInfo
			,HttpServletRequest request
		) throws Exception {

		LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
		String searchUsrYn ="N";

		if(menuInfo.getAuthVO().getListYn()==0) {
			return "redirect:/cms/accessDenied.do";
		}else{

			if("USR".equals(user.getUserSe())){
				searchUsrYn ="Y";
			}else{
				searchUsrYn ="N";
			}
		}

		model.addAttribute("searchUsrYn", searchUsrYn);
		/** EgovPropertyService.sample */
		userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
		paginationInfo.setPageSize(userSearchVO.getPageSize());

		userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> entrprsList = entrprsManageService.selectEntrprsMberList(userSearchVO);
		model.addAttribute("resultList", entrprsList);

		int totCnt = entrprsManageService.selectEntrprsMberListTotCnt(userSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		//사용자상태코드를 코드정보로부터 조회
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM013");
		List<?> entrprsMberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("entrprsMberSttus_result", entrprsMberSttus_result);//기업회원상태코드목록

		return InfoViewUtils.adminTilesView(pagePath,"InfoEntrprsMberManage","sub");
		//	return "egovframework/com/uss/umt/EgovEntrprsMberManage";
	}

	/**
	 * 기업회원정보 수정을 위해기업회원정보를 상세조회한다.
	 * @param entrprsmberId 상세조회 대상 기업회원아이디
	 * @param userSearchVO 조회조건정보
	 * @param model 화면모델
	 * @return /cms/uss/umt/InfoEntrprsMberSelectUpdt
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoEntrprsMberSelectUpdtView.do")

	public String updateEntrprsMberView(@RequestParam("selectedId") String entrprsmberId
			 , @ModelAttribute("searchVO") CmsSearchVO userSearchVO, Model model
			 , @RequestAttribute(value ="menuInfo" ,required = false) InfoMenuManageVO menuInfo
			 , HttpServletRequest request
	) throws Exception {

		// 미인증 사용자에 대한 보안처리
		if(menuInfo.getAuthVO().getReadYn()==0) {

			return "redirect:/cms/accessDenied.do";
		}else{

			String searchUsrYn ="";
			boolean searchUsrCheck=true;

			LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");

			if("USR".equals(user.getUserSe())){
				searchUsrYn ="Y";
				searchUsrCheck=false;

			}else {
				searchUsrYn = "N";
				searchUsrCheck=true;
			}


			InfoEntrprsManageVO entrprsManageVO = new InfoEntrprsManageVO();
			entrprsManageVO = entrprsManageService.selectEntrprsmber(entrprsmberId);

			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			//패스워드힌트목록을 코드정보로부터 조회


			//사용자상태코드를 코드정보로부터 조회
			vo.setCodeId("COM013");
			List<?> entrprsMberSttus_result = cmmUseService.selectCmmCodeDetail(vo);

			InfoGroupManageVO infoGroupManageVO = new InfoGroupManageVO();
			String groupOrganYn ="";

			if(menuInfo.getAuthVO().getOrganAllYn()==0){

				groupOrganYn="Y";
			}

			infoGroupManageVO.setGroupOrganYn(groupOrganYn);



			List<?> groupId_result = infoGroupManageService.getSelectInfoGroupList(infoGroupManageVO);


			model.addAttribute("entrprsManageVO", entrprsManageVO);
			model.addAttribute("userSearchVO", userSearchVO);
			model.addAttribute("entrprsMberSttus_result", entrprsMberSttus_result);//사용자상태코드목록
			model.addAttribute("groupIdList", groupId_result); //그룹정보 목록

			model.addAttribute("searchUsrYn", searchUsrYn);
			model.addAttribute("searchUsrCheck", searchUsrCheck);


			return InfoViewUtils.adminTilesView(pagePath,"InfoEntrprsMberSelectUpdt","ax5ui");


		}






	}

	/**
	 * 기업회원정보 수정을 위해기업회원정보를 상세조회한다.
	 * @param entrprsmberId 상세조회 대상 기업회원아이디
	 * @param userSearchVO 조회조건정보
	 * @param model 화면모델
	 * @return /cms/uss/umt/InfoEntrprsMberSelectUpdt
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoEntrprsMberUpdtUserView.do")
	public String InfoEntrprsMberUpdtUserView(@RequestParam("selectedId") String entrprsmberId, @ModelAttribute("searchVO") CmsSearchVO userSearchVO, Model model) throws Exception {
		InfoEntrprsManageVO entrprsManageVO = new InfoEntrprsManageVO();
		entrprsManageVO = entrprsManageService.selectEntrprsmber(entrprsmberId);
		model.addAttribute("entrprsManageVO", entrprsManageVO);
		model.addAttribute("userSearchVO", userSearchVO);

		/*
		JtpMberReqVO jtpMberReqVO = new JtpMberReqVO();
		jtpMberReqVO.setChngGb("1");
		jtpMberReqVO.setEsntlId(entrprsManageVO.getUniqId());

		jtpMberReqVO.setEsntlId(entrprsManageVO.getUniqId());
		jtpMberReqVO.setRegId(entrprsManageVO.getUniqId());
		jtpMberReqVO.setModId(entrprsManageVO.getUniqId());
		List<JtpMberReqVO> list  = jtpMberReqService.getSelect(jtpMberReqVO);
		model.addAttribute("chngGb", 1);
		model.addAttribute("count", list.size());

		jtpMberReqVO = new JtpMberReqVO();
		if(list.size()>0){
			jtpMberReqVO =list.get(0);
		}
		model.addAttribute("jtpMberReqVO", jtpMberReqVO);
		*/


		return InfoViewUtils.adminTilesView(pagePath,"InfoEntrprsMberUserUpdt","ax5ui");
		//	return "egovframework/com/uss/umt/EgovEntrprsMberSelectUpdt";
	}

	/**
	 * 기업회원정보 수정후 목록조회 화면으로 이동한다.
	 * @param entrprsManageVO 수정할 기업회원정보
	 * @param bindingResult 입력값 검증용 bindingResult
	 * @param model 화면모델
	 * @return forward:/cms/uss/umt/InfoEntrprsMberManage.do
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoEntrprsMberUpdtUser.do")
	public String InfoEntrprsMberUpdtUser(@ModelAttribute("entrprsManageVO")InfoEntrprsManageVO entrprsManageVO, BindingResult bindingResult, Model model) throws Exception {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		/*
		jtpMberReqVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		jtpMberReqVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		jtpMberReqVO.setRegId(user.getUniqId());
		jtpMberReqVO.setModId(user.getUniqId());


		jtpMberReqService.getUpdateOrg(jtpMberReqVO);
		*/
		//Exception 없이 진행시 수정성공메시지
		model.addAttribute("resultMsg", "success.common.update");
		return "forward:/jtp/cms/jtpmberreq/selectUserReqChngGb1List.do";
	}



	/**
	 * 기업회원정보 수정후 목록조회 화면으로 이동한다.
	 * @param entrprsManageVO 수정할 기업회원정보
	 * @param bindingResult 입력값 검증용 bindingResult
	 * @param model 화면모델
	 * @return forward:/cms/uss/umt/InfoEntrprsMberManage.do
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoEntrprsMberSelectUpdt.do")
	public String updateEntrprsMber(@ModelAttribute("entrprsManageVO") InfoEntrprsManageVO entrprsManageVO, BindingResult bindingResult, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		InfoEntrprsValidator validator = new InfoEntrprsValidator();
		validator.validate(entrprsManageVO, bindingResult);

		if (bindingResult.hasErrors()) {
			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM013");
			List<?> entrprsMberSttus_result = cmmUseService.selectCmmCodeDetail(vo);

			CmsSearchVO userSearchVO = new CmsSearchVO();
			userSearchVO.setSearchCondition(entrprsManageVO.getSearchCondition());
			userSearchVO.setSearchKeyword(entrprsManageVO.getSearchKeyword());
			userSearchVO.setSbscrbSttus(entrprsManageVO.getSbscrbSttus());
			userSearchVO.setPageIndex(entrprsManageVO.getPageIndex());

			model.addAttribute("userSearchVO", userSearchVO);
			model.addAttribute("entrprsMberSttus_result", entrprsMberSttus_result);//기업회원상태코드목록
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return InfoViewUtils.adminTilesView(pagePath,"InfoEntrprsMberSelectUpdt","ax5ui");
		} else {

			if ("".equals(entrprsManageVO.getGroupId())) {
				entrprsManageVO.setGroupId(null);
			}

			entrprsManageService.updateEntrprsmber(entrprsManageVO);





			//Exception 없이 진행시 수정성공메시지
			model.addAttribute("resultMsg", "success.common.update");
			return "forward:/cms/uss/umt/InfoEntrprsMberManage.do";
		}
	}


	/**
	 * 기업회원 등록화면으로 이동한다.
	 * @param userSearchVO 검색조건정보
	 * @param entrprsManageVO 기업회원 초기화정보
	 * @param model 화면모델
	 * @return /cms/uss/umt/InfoEntrprsMberInsert
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoEntrprsMberInsertView.do")
	public String insertEntrprsMberView(@ModelAttribute("userSearchVO") CmsSearchVO userSearchVO, @ModelAttribute("entrprsManageVO") InfoEntrprsManageVO entrprsManageVO, Model model)
			throws Exception {


		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}



		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		//패스워드힌트목록을 코드정보로부터 조회
		vo.setCodeId("COM022");
		List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
		//성별구분코드를 코드정보로부터 조회
		vo.setCodeId("COM014");
		List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//사용자상태코드를 코드정보로부터 조회
		vo.setCodeId("COM013");
		List<?> entrprsMberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
		//그룹정보를 조회 - GROUP_ID정보
		vo.setTableNm("COMTNORGNZTINFO");
		List<?> groupId_result = cmmUseService.selectGroupIdDetail(vo);
		//기업구분코드를 코드정보로부터 조회 - COM026
		vo.setCodeId("COM026");
		List<?> entrprsSeCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//업종코드를 코드정보로부터 조회 - COM027
		vo.setCodeId("COM027");
		List<?> indutyCode_result = cmmUseService.selectCmmCodeDetail(vo);

		model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
		model.addAttribute("entrprsMberSttus_result", entrprsMberSttus_result);//사용자상태코드목록
		model.addAttribute("groupId_result", groupId_result); //그룹정보 목록
		model.addAttribute("entrprsSeCode_result", entrprsSeCode_result); //기업구분코드 목록
		model.addAttribute("indutyCode_result", indutyCode_result); //업종코드목록

		return InfoViewUtils.adminTilesView(pagePath,"InfoEntrprsMberInsert","ax5ui");
		//return "egovframework/com/uss/umt/EgovEntrprsMberInsert";
	}

	/**
	 * 기업회원등록처리후 목록화면으로 이동한다.
	 * @param entrprsManageVO 신규기업회원정보
	 * @param bindingResult   입력값검증용  bindingResult
	 * @param model           화면모델
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoEntrprsMberInsert.do")
	public String insertEntrprsMber(@ModelAttribute("entrprsManageVO") InfoEntrprsManageVO entrprsManageVO, BindingResult bindingResult, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		beanValidator.validate(entrprsManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return InfoViewUtils.adminTilesView(pagePath,"InfoEntrprsMberInsert","sub");
			//return "infomind/com/cms/uss/umt/InfoEntrprsMberInsert";
		} else {
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			entrprsManageVO.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

			entrprsManageService.insertEntrprsmber(entrprsManageVO);
			//Exception 없이 진행시 등록성공메시지
			model.addAttribute("resultMsg", "success.common.insert");
		}
		return "forward:/cms/uss/umt/InfoEntrprsMberManage.do";
	}

	/**
	 * 기업회원 암호 수정처리 후 화면 이동한다.
	 * @param model 화면모델
	 * @param commandMap 파라메터전달용 commandMap
	 * @param userSearchVO 검색조건정보
	 * @param entrprsManageVO 기업회원수정정보
	 * @return css/uss/umt/InfoEntrprsPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/uss/umt/InfoEntrprsPasswordUpdt.do")
	public String updatePassword(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
								 @ModelAttribute("entrprsManageVO") InfoEntrprsManageVO entrprsManageVO) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		//System.out.println("1a1a1a1a1a1a1a");

		String oldPassword = (String) commandMap.get("oldPassword");
		String newPassword = (String) commandMap.get("newPassword");
		String newPassword2 = (String) commandMap.get("newPassword2");
		String uniqId = (String) commandMap.get("uniqId");

		boolean isCorrectPassword = false;
		InfoEntrprsManageVO resultVO = new InfoEntrprsManageVO();
		entrprsManageVO.setEntrprsMberPassword(newPassword);
		entrprsManageVO.setOldPassword(oldPassword);
		entrprsManageVO.setUniqId(uniqId);

		String resultMsg = "";
		resultVO = entrprsManageService.selectPassword(entrprsManageVO);
		//패스워드 암호화
		String encryptPass = EgovFileScrty.encryptPassword(oldPassword, entrprsManageVO.getEntrprsmberId());
		if (encryptPass.equals(resultVO.getEntrprsMberPassword())) {
			if (newPassword.equals(newPassword2)) {
				isCorrectPassword = true;
			} else {
				isCorrectPassword = false;
				resultMsg = "fail.user.passwordUpdate2";
			}
		} else {
			isCorrectPassword = false;
			resultMsg = "fail.user.passwordUpdate1";
		}

		if (isCorrectPassword) {
			entrprsManageVO.setEntrprsMberPassword(EgovFileScrty.encryptPassword(newPassword, entrprsManageVO.getEntrprsmberId()));
			entrprsManageService.updatePassword(entrprsManageVO);
			model.addAttribute("entrprsManageVO", entrprsManageVO);
			resultMsg = "success.common.update";
		} else {
			model.addAttribute("entrprsManageVO", entrprsManageVO);
		}
		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("resultMsg", resultMsg);

		//System.out.println("1b1b1b1b1b1b1b");

		return InfoViewUtils.adminTilesView(pagePath,"InfoEntrprsPasswordUpdt","sub");
		//return "egovframework/com/uss/umt/EgovEntrprsPasswordUpdt";
	}

	@RequestMapping(value="/cms/uss/umt/InfoEntrprsPasswordUpdtObject.do")
	@ResponseBody
	public ModelAndView InfoUserPasswordUpdtObject(@RequestBody Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
												   @ModelAttribute("userManageVO") InfoEntrprsManageVO entrprsManageVO) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");

		String oldPassword = (String) commandMap.get("oldPassword");
		String newPassword = (String) commandMap.get("newPassword");
		String newPassword2 = (String) commandMap.get("newPassword2");
		String uniqId = (String) commandMap.get("uniqId");
		String entrprsmberId = (String) commandMap.get("entrprsmberId");

		boolean isCorrectPassword = false;
		InfoEntrprsManageVO resultVO = new InfoEntrprsManageVO();
		entrprsManageVO.setEntrprsMberPassword(newPassword);
		entrprsManageVO.setOldPassword(oldPassword);
		entrprsManageVO.setUniqId(uniqId);

		String resultMsg = "";
		resultVO = entrprsManageService.selectPassword(entrprsManageVO);
		//패스워드 암호화
		String encryptPass = EgovFileScrty.encryptPassword(oldPassword, entrprsmberId);
		if (encryptPass.equals(resultVO.getEntrprsMberPassword())) {
			if (newPassword.equals(newPassword2)) {
				isCorrectPassword = true;
			} else {
				isCorrectPassword = false;
				resultMsg = "fail.user.passwordUpdate2";
			}
		} else {
			isCorrectPassword = false;
			resultMsg = "fail.user.passwordUpdate1";
		}

		if (isCorrectPassword) {
			entrprsManageVO.setEntrprsMberPassword(EgovFileScrty.encryptPassword(newPassword, entrprsmberId));
			entrprsManageService.updatePassword(entrprsManageVO);
			resultMsg = "success.common.update";
		}

		modelAndView.addObject("isCorrectPassword", isCorrectPassword);
		modelAndView.addObject("masage", resultMsg);

		return modelAndView;
	}

	/**
	 * 기업회원암호 수정 화면 이동
	 * @param model 화면모델
	 * @param commandMap 파라메터전송용 commandMap
	 * @param userSearchVO 검색조건정보
	 * @param entrprsManageVO 기업회원수정정보
	 * @return cms/uss/umt/InfoEntrprsPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/uss/umt/InfoEntrprsPasswordUpdtView.do")
	public String updatePasswordView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
									 @ModelAttribute("entrprsManageVO") InfoEntrprsManageVO entrprsManageVO) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String userTyForPassword = (String) commandMap.get("userTyForPassword");
		entrprsManageVO.setUserTy(userTyForPassword);

		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("entrprsManageVO", entrprsManageVO);
		return InfoViewUtils.adminTilesView(pagePath,"InfoEntrprsPasswordUpdt","axmodal");
		//return "egovframework/com/uss/umt/EgovEntrprsPasswordUpdt";
	}

	/**
	 * 로그인인증제한 해제
	 * @param entrprsManageVO 기업회원정보
	 * @param model 화면모델
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoEntrprsMberLockIncorrect.do")
	public String updateLockIncorrect(InfoEntrprsManageVO entrprsManageVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		entrprsManageService.updateLockIncorrect(entrprsManageVO);
		model.addAttribute("resultMsg", "success.common.update");
		return "forward:/cms/uss/umt/InfoEntrprsMberManage.do";
	}


	/**
	 * 기업회원정보삭제후 목록조회 화면으로 이동한다.
	 * @param checkedIdForDel 삭제대상아이디 정보
	 * @param userSearchVO 조회조건정보
	 * @param model 화면모델
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoEntrprsMberDelete.do")
	public String deleteEntrprsMber(@RequestParam("checkedIdForDel") String checkedIdForDel, @ModelAttribute("searchVO") CmsSearchVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		entrprsManageService.deleteEntrprsmber(checkedIdForDel);
		//Exception 없이 진행시 삭제성공메시지
		model.addAttribute("resultMsg", "success.common.delete");
		return "forward:/cms/uss/umt/InfoEntrprsMberManage.do";
	}


	@IncludedInfo(name = "기업회원팝업", order = 450, gid = 50)
	@RequestMapping(value = "/cms/uss/umt/popEntrprsMberList.do")
	public String popEntrprsMberList(@ModelAttribute("userSearchVO") InfoEntrprsManageVO userSearchVO, ModelMap model, HttpServletRequest request) throws Exception {
		String searchAllYn = "N";

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		// 팝업인데 어디로 보내지?
		if (!isAuthenticated) {
			//return "index";
		}

		LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");

		//관리자 일때만...
		if("USR".equals(user.getUserSe())){
			searchAllYn ="Y";
		}else{
			searchAllYn ="N";
		}

		/** EgovPropertyService.sample */
		userSearchVO.setPageUnit(7);
		userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
		paginationInfo.setPageSize(userSearchVO.getPageSize());

		userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> entrprsList = entrprsManageService.selectEntrprsMberPopupList(userSearchVO);
		int totCnt = entrprsManageService.selectEntrprsMberPopupListTotCnt(userSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);

		model.addAttribute("resultList", entrprsList);
		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("searchAllYn", searchAllYn);

		return InfoViewUtils.adminTilesView(pagePath,"popEntrprsMberList","axmodal");
	}

	@RequestMapping(value = "/cms/uss/umt/PopInfoEntrprsMberManage.do")
	public String PopInfoEntrprsMberManage(@ModelAttribute("userSearchVO") InfoEntrprsManageVO userSearchVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		/** EgovPropertyService.sample */
		userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
		paginationInfo.setPageSize(userSearchVO.getPageSize());

		userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> entrprsList = entrprsManageService.selectEntrprsMberList(userSearchVO);
		model.addAttribute("resultList", entrprsList);

		int totCnt = entrprsManageService.selectEntrprsMberListTotCnt(userSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		//사용자상태코드를 코드정보로부터 조회
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM013");
		List<?> entrprsMberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("entrprsMberSttus_result", entrprsMberSttus_result);//기업회원상태코드목록

		return InfoViewUtils.adminTilesView(pagePath,"PopInfoEntrprsMberManage","sub");
		//	return "egovframework/com/uss/umt/EgovEntrprsMberManage";
	}

}




