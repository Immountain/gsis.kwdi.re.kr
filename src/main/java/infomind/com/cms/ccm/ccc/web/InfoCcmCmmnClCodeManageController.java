package infomind.com.cms.ccm.ccc.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.ccm.ccc.service.CmmnClCode;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.rte.fdl.property.EgovPropertyService;

import infomind.com.cmm.api.ApiResponse;
import infomind.com.cmm.web.BaseAjaxController;
import infomind.com.cmm.web.BaseController;
import infomind.com.cms.ccm.ccc.service.InfoCcmCmmnClCodeManageService;
import infomind.com.cms.ccm.ccc.vo.InfoCmmnClCode;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;


/**
 *
 * 공통분류코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
 * 
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *	     수정일		수정자          			 수정내용
 *  ---------  -------   ---------------------------
 *  2009.04.01	이중호         최초 생성
 *  2011.8.26	정진오	 IncludedInfo annotation 추가
 *  2017.06.08	이정은	 표준프레임워크 v3.7 개선
 *
 *      </pre>
 */

@Controller
public class InfoCcmCmmnClCodeManageController extends BaseAjaxController {
	@Resource(name = "InfoCcmCmmnClCodeManageService")
	private InfoCcmCmmnClCodeManageService infoCcmCmmnClCodeManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Autowired
	private DefaultBeanValidator beanValidator;

	private String pagePath ="ccm/ccc/";

	/**
	 * 공통분류코드 그리드 화면
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@IncludedInfo(name = "공통분류코드", listUrl = "/cms/ccc/InitCcmCmmnClCodeList.do", order = 960, gid = 60)
	@RequestMapping(value = "/cms/ccm/ccc/InitCcmCmmnClCodeList.do")
	public String init(@ModelAttribute("searchVO") CmsSearchVO searchVO, ModelMap model) throws Exception {

		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnClCodeList","ax5ui");

	}


	/**
	 * 공통분류코드 데이터 셋
	 * @param searchVO
	 * @return modelAndView 또는 Object 가능 하게 설정 완료
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/ccm/ccc/SelectCcmCmmnClCodeObject.do")
	@ResponseBody
	public ModelAndView SelectCcmCmmnClCodeObject(@RequestBody InfoCmmnClCode searchVO) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jsonView");
		modelAndView.addObject("list", infoCcmCmmnClCodeManageService.selectCmmnClCodeAllList(searchVO));


		return modelAndView;
	}

	/**
	 * 공통분류코드 상세 페이지
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/ccc/view.do")
	public String InfoCcmCmmnClCodeView(@ModelAttribute("searchVO") InfoCmmnClCode searchVO, ModelMap model) throws Exception {

		InfoCmmnClCode vo = infoCcmCmmnClCodeManageService.selectCmmnClCodeDetail(searchVO);
		model.addAttribute("result", vo);


		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnClCodeView","axmodal");
	}

	/**
	 * 공통분류코드 등록을 위한 등록페이지로 이동한다.
	 *
	 * @param cmmnClCode
	 * @param model
	 * @return "infomind/com/cms/ccm/ccc/InfoCcmCmmnClCodeRegist.sub";
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/ccc/InfoCcmCmmnClCodeRegist.do")
	public String InfoCcmCmmnClCodeRegist(@ModelAttribute("editVO") InfoCmmnClCode cmmnClCode, ModelMap model) throws Exception {
		model.addAttribute("cmmnClCodeVO", new InfoCmmnClCode());


		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		System.out.println("user===>"+user);
		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnClCodeRegist","axmodal");

	}


	/**
	 * 공통분류코드를 등록한다.
	 *
	 * @param cmmnClCode
	 * @param model
	 * @return /sym/ccm/ccc/SelectCcmCmmnClCodeList.do";
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/ccc/RegistCcmCmmnClCode.do")
	@ResponseBody
	public ApiResponse insertCmmnClCode(InfoCmmnClCode cmmnClCode, ModelMap model) throws Exception {

		// 로그인VO에서  사용자 정보 가져오기
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		if(cmmnClCode.getClCode() != null){
			InfoCmmnClCode vo = new InfoCmmnClCode();
			vo = infoCcmCmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCode);
			if(vo == null){
				cmmnClCode.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
				infoCcmCmmnClCodeManageService.insertCmmnClCode(cmmnClCode);
			}else{
				return ok(egovMessageSource.getMessage("comSymCcmCcc.validate.codeCheck"));


			}
		}

		return ok();
	}

	/**
	 * 공통분류코드를 삭제한다.
	 * @param cmmnClCode
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/ccc/RemoveCcmCmmnClCode.do")
	@ResponseBody
	public ApiResponse deleteCmmnClCode(InfoCmmnClCode cmmnClCode, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		cmmnClCode.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		infoCcmCmmnClCodeManageService.deleteCmmnClCode(cmmnClCode);
		return ok();
	}
	/**
	 * 공통분류코드 수정을 위한 수정페이지로 이동한다.
	 *
	 * @param cmmnClCode
	 * @param model
	 * @return "egovframework/com/sym/ccm/ccc/EgovCcmCmmnClCodeUpdt";
	 * @throws Exception
	 */

	@RequestMapping("/cms/ccm/ccc/InfoCcmCmmnClCodeUpdt.do")
	public String InfoCcmCmmnClCodeUpdt(InfoCmmnClCode cmmnClCode, ModelMap model)	throws Exception {

		InfoCmmnClCode result = infoCcmCmmnClCodeManageService.selectCmmnClCodeDetail(cmmnClCode);


		//System.out.println("InfoCcmCmmnClCodeUpdt");

		model.addAttribute("cmmnClCodeVO", result);


		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnClCodeUpdt","axmodal");
	}



	/**
	 * 공통분류코드를 수정한다.
	 * @param cmmnClCode
	 * @param model
	 * @return /sym/ccm/ccc/SelectCcmCmmnClCodeList.do"
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/ccc/InfoUpdateCcmCmmnClCode.do")
	@ResponseBody
	public ApiResponse updateCmmnClCode(InfoCmmnClCode cmmnClCode,  ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();


		cmmnClCode.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		infoCcmCmmnClCodeManageService.updateCmmnClCode(cmmnClCode);

		return  ok();
	}

}