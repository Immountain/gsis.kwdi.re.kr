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
import infomind.com.cms.uss.umt.service.InfoMberManageService;
import infomind.com.cms.uss.umt.vo.InfoMberManageVO;
import infomind.com.cms.uss.umt.vo.InfoMberValidator;
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
 * 일반회원관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2011.08.26	 정진오			IncludedInfo annotation 추가
 *   2014.12.08	 이기하			암호화방식 변경(EgovFileScrty.encryptPassword)
 *   2015.06.16	 조정국			수정시 유효성체크 후 에러발생 시 목록으로 이동하여 에러메시지 표시
 *   2015.06.19	 조정국			미인증 사용자에 대한 보안처리 기준 수정 (!isAuthenticated)
 *   2017.07.21  장동한 			로그인인증제한 작업
 *
 * </pre>
 */

@Controller
public class InfoMberManageController extends BaseController {

	/** mberManageService */
	@Resource(name = "InfoMberManageService")
	private InfoMberManageService InfoMberManageService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name = "SiteMyPageService")
	private SiteMyPageService siteMyPageService;



	/** DefaultBeanValidator beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	private final String pagePath ="uss/umt/";


	/**
	 * 일반회원목록을 조회한다. (pageing)
	 * @param userSearchVO 검색조건정보
	 * @param model 화면모델
	 * @return /cms/uss/umt/InfoMberManage
	 * @throws Exception
	 */
	@IncludedInfo(name = "일반회원관리", order = 470, gid = 50)
	@RequestMapping(value = "/cms/uss/umt/InfoMberManage.do")
	public String selectMberList(@ModelAttribute("userSearchVO") InfoMberManageVO userSearchVO, ModelMap model
			,@RequestAttribute(value ="menuInfo" ,required = false) InfoMenuManageVO menuInfo
			,HttpServletRequest request) throws Exception {

		// 미인증 사용자에 대한 보안처리
		LoginVO user = (LoginVO) request.getSession().getAttribute("loginVO");
		String searchUsrYn ="N";

		if(menuInfo.getAuthVO().getListYn()==0) {
			return "redirect:/cms/accessDenied.do";
		}else{
			if("USR".equals(user.getUserSe())){
				searchUsrYn ="Y";
			}else{
				searchUsrYn ="N";
				//사용자 기관 정보 가져온다
			}
		}

		/** EgovPropertyService */
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

		List<?> mberList = InfoMberManageService.selectMberList(userSearchVO);
		model.addAttribute("resultList", mberList);

		int totCnt = InfoMberManageService.selectMberListTotCnt(userSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		//일반회원 상태코드를 코드정보로부터 조회
		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId("COM013");
		List<?> mberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
		model.addAttribute("mberSttus_result", mberSttus_result);//기업회원상태코드목록

		return InfoViewUtils.adminTilesView(pagePath,"InfoMberManage","sub");
		//	return "egovframework/com/uss/umt/EgovMberManage";
	}

	/**
	 * 일반회원등록화면으로 이동한다.
	 * @param userSearchVO 검색조건정보
	 * @param mberManageVO 일반회원초기화정보
	 * @param model 화면모델
	 * @return /cms/uss/umt/InfoMberInsert
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoMberInsertView.do")
	public String insertMberView(@ModelAttribute("userSearchVO") CmsSearchVO userSearchVO, @ModelAttribute("mberManageVO") InfoMberManageVO mberManageVO, Model model)
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
		List<?> mberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
		//그룹정보를 조회 - GROUP_ID정보
		vo.setTableNm("COMTNORGNZTINFO");
		List<?> groupId_result = cmmUseService.selectGroupIdDetail(vo);

		model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
		model.addAttribute("mberSttus_result", mberSttus_result); //사용자상태코드목록
		model.addAttribute("groupId_result", groupId_result); //그룹정보 목록

		return InfoViewUtils.adminTilesView(pagePath,"InfoMberInsert","ax5ui");
		//	return "egovframework/com/uss/umt/EgovMberInsert";
	}

	/**
	 * 일반회원등록처리후 목록화면으로 이동한다.
	 * @param mberManageVO 일반회원등록정보
	 * @param bindingResult 입력값검증용 bindingResult
	 * @param model 화면모델
	 * @return forward:/cms/uss/umt/InfoMberManage.do
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoMberInsert.do")
	public String insertMber(@ModelAttribute("mberManageVO") InfoMberManageVO mberManageVO, BindingResult bindingResult, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		beanValidator.validate(mberManageVO, bindingResult);

		if (bindingResult.hasErrors()) {
			return InfoViewUtils.adminTilesView(pagePath,"InfoMberInsert","sub");
			//return "egovframework/com/uss/umt/EgovMberInsert";
		}

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		mberManageVO.setUniqId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		InfoMberManageService.insertMber(mberManageVO);
		//Exception 없이 진행시 등록 성공메시지
		model.addAttribute("resultMsg", "success.common.insert");

		return "forward:/cms/uss/umt/InfoMberManage.do";
	}


	/**
	 * 일반회원정보 수정을 위해 일반회원정보를 상세조회한다.
	 * @param mberId 상세조회대상 일반회원아이디
	 * @param userSearchVO 검색조건
	 * @param model 화면모델
	 * @return uss/umt/EgovMberSelectUpdt
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoMberSelectUpdtView.do")
	public String updateMberView(@RequestParam("selectedId") String mberId, @ModelAttribute("searchVO") CmsSearchVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		InfoMberManageVO infoMberManageVO = new InfoMberManageVO();
		infoMberManageVO = InfoMberManageService.selectMber(mberId);

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		//패스워드힌트목록을 코드정보로부터 조회
		vo.setCodeId("COM022");
		List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
		//성별구분코드를 코드정보로부터 조회
		vo.setCodeId("COM014");
		List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//사용자상태코드를 코드정보로부터 조회
		vo.setCodeId("COM013");
		List<?> mberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
		//그룹정보를 조회 - GROUP_ID정보
		vo.setTableNm("COMTNORGNZTINFO");
		List<?> groupId_result = cmmUseService.selectGroupIdDetail(vo);

		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("mberManageVO", infoMberManageVO);
		model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
		model.addAttribute("mberSttus_result", mberSttus_result); //사용자상태코드목록
		model.addAttribute("groupId_result", groupId_result); //그룹정보 목록

		return InfoViewUtils.adminTilesView(pagePath,"InfoMberSelectUpdt","ax5ui");
	}


	/**
	 * 일반회원정보 수정후 목록조회 화면으로 이동한다.
	 * @param mberManageVO 일반회원수정정보
	 * @param bindingResult 입력값검증용 bindingResult
	 * @param model 화면모델
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoMberSelectUpdt.do")
	public String updateMber(@ModelAttribute("mberManageVO") InfoMberManageVO mberManageVO, BindingResult bindingResult, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		InfoMberValidator validator = new InfoMberValidator();
		validator.validate(mberManageVO, bindingResult);

		if (bindingResult.hasErrors()) {
			CmsSearchVO userSearchVO = new CmsSearchVO();
			userSearchVO.setSearchCondition(mberManageVO.getSearchCondition());
			userSearchVO.setSearchKeyword(mberManageVO.getSearchKeyword());
			userSearchVO.setSbscrbSttus(mberManageVO.getSbscrbSttus());
			userSearchVO.setPageIndex(mberManageVO.getPageIndex());

			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM013");
			List<?> mberSttus_result = cmmUseService.selectCmmCodeDetail(vo);

			model.addAttribute("userSearchVO", userSearchVO);
			model.addAttribute("mberSttus_result", mberSttus_result); //사용자상태코드목록
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return InfoViewUtils.adminTilesView(pagePath,"InfoMberSelectUpdt","ax5ui");
		}

		if ("".equals(mberManageVO.getGroupId())) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
			mberManageVO.setGroupId(null);
		}

		InfoMberManageService.updateMber(mberManageVO);

		//Exception 없이 진행시 수정성공메시지
		model.addAttribute("resultMsg", "success.common.update");
		return "forward:/cms/uss/umt/InfoMberManage.do";
	}


	@RequestMapping("/cms/uss/umt/InfoMberUpdtUserView.do")
	public String InfoMberUpdtUserView(@RequestParam("selectedId") String mberId
			  , @ModelAttribute("searchVO") CmsSearchVO userSearchVO, Model model
			  , @RequestAttribute(value ="menuInfo" ,required = false) InfoMenuManageVO menuInfo
			   , HttpServletRequest request

	    ) throws Exception {

		if(menuInfo.getAuthVO().getReadYn()==0){

			return "redirect:/cms/accessDenied.do";
		}



		InfoMberManageVO mberManageVO = new InfoMberManageVO();
		mberManageVO = InfoMberManageService.selectMber(mberId);
		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("mberManageVO", mberManageVO);

		/*JtpMberReqVO jtpMberReqVO = new JtpMberReqVO();
		jtpMberReqVO.setChngGb("0");
		jtpMberReqVO.setEsntlId(mberManageVO.getUniqId());

		jtpMberReqVO.setEsntlId(mberManageVO.getUniqId());
		jtpMberReqVO.setRegId(mberManageVO.getUniqId());
		jtpMberReqVO.setModId(mberManageVO.getUniqId());
		List<JtpMberReqVO> list = jtpMberReqService.getSelect(jtpMberReqVO);
		model.addAttribute("chngGb", 0);
		model.addAttribute("count", list.size());

		jtpMberReqVO = new JtpMberReqVO();
		if(list.size()>0){
			jtpMberReqVO =list.get(0);
		}

		model.addAttribute("jtpMberReqVO", jtpMberReqVO);*/

		return InfoViewUtils.adminTilesView(pagePath,"InfoMberUpdt","ax5ui");
	}

	// TODO 수정 필요
	@RequestMapping("/cms/uss/umt/InfoMberUpdtUser.do")
	public String InfoMberUpdtUser(HttpServletRequest request, Model model) throws Exception {
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		/*
		jtpMberReqVO.setModId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		jtpMberReqVO.setRegId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());

		jtpMberReqVO.setRegId(user.getUniqId());
		jtpMberReqVO.setModId(user.getUniqId());

		int cnt = jtpMberReqService.getChangeUser(jtpMberReqVO);

		if(cnt!=0){
			model.addAttribute("resultMsg", "success.common.update");
		}else{
			model.addAttribute("resultMsg", "처리중 오류가 발생했습니다.");
		}*/

		return "forward:/jtp/cms/jtpmberreq/selectUserReqChngGb0List.do";
	}


	/**
	 * 로그인인증제한 해제
	 * @param mberManageVO 일반회원등록정보
	 * @param model 화면모델
	 * @return /cms/uss/umt/InfoMberSelectUpdtView.do
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoMberLockIncorrect.do")
	public String updateLockIncorrect(InfoMberManageVO mberManageVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		InfoMberManageService.updateLockIncorrect(mberManageVO);

		//Exception 없이 진행시 수정성공메시지
		model.addAttribute("resultMsg", "success.common.update");
		return "forward:/cms/uss/umt/InfoMberManage.do";
	}


	/**
	 * 일반회원정보삭제후 목록조회 화면으로 이동한다.
	 * @param checkedIdForDel 삭제대상 아이디 정보
	 * @param userSearchVO 검색조건정보
	 * @param model 화면모델
	 * @return forward:/cms/uss/umt/InfoMberManage.do
	 * @throws Exception
	 */
	@RequestMapping("/cms/uss/umt/InfoMberDelete.do")
	public String deleteMber(@RequestParam("checkedIdForDel") String checkedIdForDel, @ModelAttribute("searchVO") CmsSearchVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		InfoMberManageService.deleteMber(checkedIdForDel);
		//Exception 없이 진행시 삭제성공메시지
		model.addAttribute("resultMsg", "success.common.delete");
		return "forward:/cms/uss/umt/InfoMberManage.do";
	}


	/**
	 * @param model 화면모델
	 * @param commandMap 파라메터전달용 commandMap
	 * @param userSearchVO 검색조건
	 * @param mberManageVO 일반회원수정정보(비밀번호)
	 * @return /cms/uss/umt/InfoMberPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/uss/umt/InfoMberPasswordUpdt.do")
	public String updatePassword(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
								 @ModelAttribute("mberManageVO") InfoMberManageVO mberManageVO) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String oldPassword = (String) commandMap.get("oldPassword");
		String newPassword = (String) commandMap.get("newPassword");
		String newPassword2 = (String) commandMap.get("newPassword2");
		String uniqId = (String) commandMap.get("uniqId");

		boolean isCorrectPassword = false;
		InfoMberManageVO resultVO = new InfoMberManageVO();
		mberManageVO.setPassword(newPassword);
		mberManageVO.setOldPassword(oldPassword);
		mberManageVO.setUniqId(uniqId);

		String resultMsg = "";
		resultVO = InfoMberManageService.selectPassword(mberManageVO);
		//패스워드 암호화
		String encryptPass = EgovFileScrty.encryptPassword(oldPassword, mberManageVO.getMberId());
		if (encryptPass.equals(resultVO.getPassword())) {
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
			mberManageVO.setPassword(EgovFileScrty.encryptPassword(newPassword, mberManageVO.getMberId()));
			InfoMberManageService.updatePassword(mberManageVO);
			model.addAttribute("mberManageVO", mberManageVO);
			resultMsg = "success.common.update";
		} else {
			model.addAttribute("mberManageVO", mberManageVO);
		}

		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("resultMsg", resultMsg);

		return InfoViewUtils.adminTilesView(pagePath,"InfoMberPasswordUpdt","sub");
		//return "egovframework/com/uss/umt/EgovMberPasswordUpdt";
	}


//	일반회원 비밀번호 변경
	@RequestMapping(value = "/cms/uss/umt/InfoMberPasswordUpdtObject.do")
	public ModelAndView InfoMberPasswordUpdtObject(@RequestBody Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
												   @ModelAttribute("mberManageVO") InfoMberManageVO mberManageVO) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");

		String newPassword = (String) commandMap.get("newPassword");
		String newPassword2 = (String) commandMap.get("newPassword2");
		String uniqId = (String) commandMap.get("uniqId");

		boolean isCorrectPassword = false;
		mberManageVO.setPassword(newPassword);
		mberManageVO.setMberId(uniqId);

		String resultMsg = "";

		//패스워드 암호화
			if (newPassword.equals(newPassword2)) {
				isCorrectPassword = true;
			} else {
				isCorrectPassword = false;
				resultMsg = "비밀번호, 비밀번호확인 다릅니다.";
			}

		if (isCorrectPassword) {
			mberManageVO.setPassword(EgovFileScrty.encryptPassword(newPassword, mberManageVO.getMberId()));
			InfoMberManageService.updatePassword(mberManageVO);

			resultMsg = "수정되었습니다.";
		}

		modelAndView.addObject("userSearchVO", userSearchVO);
		modelAndView.addObject("resultMsg", resultMsg);

		return modelAndView;
	}

	/**
	 * 일반회원 암호 수정 화면 이동
	 * @param model 화면모델
	 * @param commandMap 파라메터전달용 commandMap
	 * @param userSearchVO 검색조건
	 * @param mberManageVO 일반회원수정정보(비밀번호)
	 * @return /cms/uss/umt/InfoMberPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/uss/umt/InfoMberPasswordUpdtView.do")
	public String updatePasswordView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") CmsSearchVO userSearchVO,
									 @ModelAttribute("mberManageVO") InfoMberManageVO mberManageVO) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String userTyForPassword = (String) commandMap.get("userTyForPassword");
		mberManageVO.setUserTy(userTyForPassword);

		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("mberManageVO", mberManageVO);

		return InfoViewUtils.adminTilesView(pagePath,"InfoMberPasswordUpdt","sub");
		//return "egovframework/com/uss/umt/EgovMberPasswordUpdt";
	}

}
