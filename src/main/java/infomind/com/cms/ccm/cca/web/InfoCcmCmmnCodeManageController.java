package infomind.com.cms.ccm.cca.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;

import egovframework.com.sym.ccm.cca.service.CmmnCode;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.ccm.cca.service.InfoCcmCmmnCodeManageService;
import infomind.com.cms.ccm.cca.vo.InfoCmmnCodeVO;
import infomind.com.cms.ccm.ccc.service.InfoCcmCmmnClCodeManageService;
import infomind.com.cms.ccm.ccc.vo.InfoCmmnClCode;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;
import java.util.List;

/**
*
* 공통코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
* @author 인포마인드 개발팀 양진혁
* @since 2020.10.19
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2020.10.18  양진혁          최초 생성
 *
* </pre>
*/

@Controller
public class InfoCcmCmmnCodeManageController {
	
	@Resource(name = "InfoCcmCmmnCodeManageService")
    private InfoCcmCmmnCodeManageService cmmnCodeManageService;
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


	private String pagePath ="ccm/cca/";
	
	/**
	 * 공통분류코드 목록을 조회한다.
	 * 
	 * @param searchVO
	 * @param model
	 * @return "/ccm/ccm/InfoCcmCmmnCodeList"
	 * @throws Exception
	 */
	@IncludedInfo(name = "공통코드", listUrl = "/ccm/cca/SelectCcmCmmnCodeList.do", order = 980, gid = 60)
	@RequestMapping(value = "/cms/ccm/cca/SelectCcmCmmnCodeList.do")
	public String selectCmmnCodeList(@ModelAttribute("searchVO") InfoCmmnCodeVO searchVO, ModelMap model)
			throws Exception {
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> CmmnCodeList = cmmnCodeManageService.selectCmmnCodeList(searchVO);
		model.addAttribute("resultList", CmmnCodeList);

		int totCnt = cmmnCodeManageService.selectCmmnCodeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);


		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnCodeList","sub");
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 *
	 * @param loginVO
	 * @param cmmnCodeVO
	 * @param model
	 * @return "/ccm/ccm/InfoCcmCmmnCodeDetail"
	 * @throws Exception
	 */
	@RequestMapping(value = "/cms/ccm/cca/SelectCcmCmmnCodeDetail.do")
	public String selectCmmnCodeDetail(@ModelAttribute("loginVO") LoginVO loginVO, InfoCmmnCodeVO cmmnCodeVO, ModelMap model) throws Exception {

		InfoCmmnCodeVO vo = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCodeVO);

		model.addAttribute("result", vo);

		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnCodeDetail","sub");
	}

	/**
	 * 공통코드 등록을 위한 등록페이지로 이동한다.
	 *
	 * @param cmmnCodeVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/cca/RegistCcmCmmnCodeView.do")
	public String insertCmmnCodeView(@ModelAttribute("cmmnCodeVO")InfoCmmnCodeVO cmmnCodeVO, ModelMap model) throws Exception {

		InfoCmmnClCode searchVO = new InfoCmmnClCode();
		searchVO.setFirstIndex(0);


		List<?> CmmnCodeList = infoCcmCmmnClCodeManageService.selectCmmnClCodeList(searchVO);
		model.addAttribute("clCodeList", CmmnCodeList);

		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnCodeRegist","sub");
	}

	/**
	 * 공통코드를 등록한다.
	 *
	 * @param cmmnCode
	 * @param cmmnCodeVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/cca/RegistCcmCmmnCode.do")
	public String insertCmmnCode(@ModelAttribute("searchVO") InfoCmmnCodeVO cmmnCode, @ModelAttribute("cmmnCodeVO") InfoCmmnCodeVO cmmnCodeVO,
								 BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		InfoCmmnClCode searchVO = new InfoCmmnClCode();

		beanValidator.validate(cmmnCodeVO, bindingResult);

		if (bindingResult.hasErrors()) {

			List<?> CmmnCodeList = infoCcmCmmnClCodeManageService.selectCmmnClCodeList(searchVO);
			model.addAttribute("clCodeList", CmmnCodeList);

			return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnCodeRegist","sub");
		}

		if(cmmnCode.getCodeId() != null){
			InfoCmmnCodeVO vo = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
			if(vo != null){
				model.addAttribute("message", egovMessageSource.getMessage("comSymCcmCca.validate.codeCheck"));

				searchVO.setFirstIndex(0);
				List<?> CmmnCodeList = infoCcmCmmnClCodeManageService.selectCmmnClCodeList(searchVO);
				model.addAttribute("clCodeList", CmmnCodeList);

				return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnCodeRegist","sub");
			}
		}

		cmmnCodeVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		cmmnCodeManageService.insertCmmnCode(cmmnCodeVO);

		return "forward:/cms/ccm/cca/SelectCcmCmmnCodeList.do";
	}


	/**
	 * 공통코드 수정을 위한 수정페이지로 이동한다.
	 *
	 * @param cmmnCodeVO
	 * @param model
	 * @return "/ccm/ccm/InfoCcmCmmnCodeList"
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/cca/UpdateCcmCmmnCodeView.do")
	public String updateCmmnCodeView(@ModelAttribute("cmmnCodeVO") InfoCmmnCodeVO cmmnCodeVO, ModelMap model)
			throws Exception {

		InfoCmmnCodeVO result = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCodeVO);
		model.addAttribute("cmmnCodeVO", result);
		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnCodeUpdt","sub");

	}


	/**
	 * 공통코드를 수정한다.
	 *
	 * @param cmmnCodeVO
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/cca/UpdateCcmCmmnCode.do")
	public String updateCmmnCode(@ModelAttribute("searchVO") InfoCmmnCodeVO cmmnCode, @ModelAttribute("cmmnCodeVO") InfoCmmnCodeVO cmmnCodeVO,
								 BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(cmmnCodeVO, bindingResult);
		if (bindingResult.hasErrors()) {

			InfoCmmnCodeVO result = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCode);
			model.addAttribute("cmmnCodeVO", result);

			return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnCodeUpdt","sub");
		}

		cmmnCodeVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		cmmnCodeManageService.updateCmmnCode(cmmnCodeVO);

		return "forward:/cms/ccm/cca/SelectCcmCmmnCodeList.do";
	}


	/**
	 * 공통코드를 삭제한다.
	 *
	 * @param cmmnCodeVO
	 * @param status
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/cca/RemoveCcmCmmnCode.do")
	public String deleteCmmnCode(@ModelAttribute("cmmnCodeVO") InfoCmmnCodeVO cmmnCodeVO,
								 BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		cmmnCodeVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		cmmnCodeManageService.deleteCmmnCode(cmmnCodeVO);

		return "forward:/cms/ccm/cca/SelectCcmCmmnCodeList.do";
	}


}