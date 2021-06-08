package infomind.com.cms.ccm.cde.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;

import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.com.sym.ccm.ccc.service.CmmnClCodeVO;
import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.ccm.cca.service.InfoCcmCmmnCodeManageService;
import infomind.com.cms.ccm.cca.vo.InfoCmmnCodeVO;
import infomind.com.cms.ccm.ccc.service.InfoCcmCmmnClCodeManageService;
import infomind.com.cms.ccm.ccc.vo.InfoCmmnClCode;
import infomind.com.cms.ccm.cde.service.InfoCcmCmmnDetailCodeManageService;
import infomind.com.cms.ccm.cde.vo.InfoCmmnDetailCodeVO;
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
* 공통상세코드에 관한 요청을 받아 서비스 클래스로 요청을 전달하고 서비스클래스에서 처리한 결과를 웹 화면으로 전달을 위한 Controller를 정의한다
* @author 인포마인 개발팀 양진혁
* @since 2020.10.19
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2020.10.19  양진혁       최초 생성
 *
* </pre>
*/

@Controller
public class InfoCcmCmmnDetailCodeManageController {

	@Resource(name = "InfoCcmCmmnDetailCodeManageService")
   private InfoCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;

	@Resource(name = "InfoCcmCmmnClCodeManageService")
	private InfoCcmCmmnClCodeManageService infoCcmCmmnClCodeManageService;


	@Resource(name = "InfoCcmCmmnCodeManageService")
	private InfoCcmCmmnCodeManageService infoCcmCmmnCodeManageService;


    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;


	private String pagePath ="ccm/cde/";

	@Autowired
	private DefaultBeanValidator beanValidator;
	
	   /**
		 * 공통상세코드 목록을 조회한다.
	     * @param loginVO
	     * @param searchVO
	     * @param model
	     * @return "infomind/com/cms/"
	     * @throws Exception
	     */
		@IncludedInfo(name="공통상세코드", listUrl="/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do", order = 970 ,gid = 60)
	    @RequestMapping(value="/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do")
		public String selectCmmnDetailCodeList (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("searchVO") InfoCmmnDetailCodeVO searchVO
				, ModelMap model
				) throws Exception {
			
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

	        List<?> CmmnCodeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(searchVO);
	        model.addAttribute("resultList", CmmnCodeList);

	        int totCnt = cmmnDetailCodeManageService.selectCmmnDetailCodeListTotCnt(searchVO);
			paginationInfo.setTotalRecordCount(totCnt);
	        model.addAttribute("paginationInfo", paginationInfo);



			return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnDetailCodeList","ax5ui");



		}

	/**
	 * 공통상세코드 상세항목을 조회한다.
	 * @param loginVO
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return "infomind/com/cms/"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/ccm/cde/SelectCcmCmmnDetailCodeDetail.do")
	public String selectCmmnDetailCodeDetail (@ModelAttribute("loginVO") LoginVO loginVO
			, InfoCmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model
	)	throws Exception {
		InfoCmmnDetailCodeVO vo = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
		model.addAttribute("result", vo);



		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnDetailCodeDetail","sub");
	}


	/**
	 * 공통상세코드 등록을 위한 등록페이지로 이동한다.
	 *
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/cde/RegistCcmCmmnDetailCodeView.do")
	public String insertCmmnDetailCodeView(@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("cmmnCodeVO") InfoCmmnCodeVO cmmnCodeVO,
										   @ModelAttribute("cmmnDetailCodeVO") InfoCmmnDetailCodeVO cmmnDetailCodeVO
			,ModelMap model) throws Exception {

//		InfoCmmnClCode searchClCodeVO = new InfoCmmnClCode();
//		searchClCodeVO.setFirstIndex(0);
//
//		List<?> CmmnClCodeList = infoCcmCmmnClCodeManageService.selectCmmnClCodeList(searchClCodeVO);
//		model.addAttribute("clCodeList", CmmnClCodeList);
		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnDetailCodeRegist","sub");
	}


	/**
	 * 공통상세코드를 등록한다.
	 * @param cmmnDetailCodeVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/cde/RegistCcmCmmnDetailCode.do")
	public String insertCmmnDetailCode(@ModelAttribute("cmmnDetailCodeVO") InfoCmmnDetailCodeVO cmmnDetailCodeVO,
									   BindingResult bindingResult, ModelMap model) throws Exception {

		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		beanValidator.validate(cmmnDetailCodeVO, bindingResult);

		if (bindingResult.hasErrors()) {
			System.out.println("진혁  11111111111");

			return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnDetailCodeRegist","sub");
		}

		if(cmmnDetailCodeVO.getCodeId() != null){

			System.out.println("진혁  2222222222");

			InfoCmmnDetailCodeVO vo = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
			if(vo != null){
				System.out.println("진혓  3333333");
				model.addAttribute("message", egovMessageSource.getMessage("comSymCcmCde.validate.codeCheck"));
				return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnDetailCodeRegist","sub");
			}
		}

		cmmnDetailCodeVO.setFrstRegisterId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		cmmnDetailCodeManageService.insertCmmnDetailCode(cmmnDetailCodeVO);

		return "forward:/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do";
	}


	/**
	 * 공통상세코드를 삭제한다.
	 * @param loginVO
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return "forward:/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do"
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/ccm/cde/RemoveCcmCmmnDetailCode.do")
	public String deleteCmmnDetailCode (@ModelAttribute("loginVO") LoginVO loginVO
			, InfoCmmnDetailCodeVO cmmnDetailCodeVO
			, ModelMap model
	) throws Exception {
		cmmnDetailCodeManageService.deleteCmmnDetailCode(cmmnDetailCodeVO);

		return "forward:/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do";
	}


	/**
	 * 공통상세코드 수정을 위한 수정페이지로 이동한다.
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return "InfoCcmCmmnDetailCodeUpdt"
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/cde/UpdateCcmCmmnDetailCodeView.do")
	public String updateCmmnDetailCodeView(@ModelAttribute("loginVO") LoginVO loginVO,
										   @ModelAttribute("cmmnDetailCodeVO") InfoCmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model)
			throws Exception {

		InfoCmmnDetailCodeVO result = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
		model.addAttribute("cmmnDetailCodeVO", result);

		return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnDetailCodeUpdt","sub");
	}


	/**
	 * 공통상세코드를 수정한다.
	 *
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return "forward:/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do"
	 * @throws Exception
	 */
	@RequestMapping("/cms/ccm/cde/UpdateCcmCmmnDetailCode.do")
	public String updateCmmnDetailCode(@ModelAttribute("cmmnDetailCodeVO") InfoCmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model, BindingResult bindingResult )
			throws Exception {

		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		beanValidator.validate(cmmnDetailCodeVO, bindingResult);

		if (bindingResult.hasErrors()){
			InfoCmmnDetailCodeVO result = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
			model.addAttribute("cmmnDetailCodeVO", result);

			return InfoViewUtils.adminTilesView(pagePath,"InfoCcmCmmnDetailCodeUpdt","sub");
		}

		cmmnDetailCodeVO.setLastUpdusrId((user == null || user.getUniqId() == null) ? "" : user.getUniqId());
		cmmnDetailCodeManageService.updateCmmnDetailCode(cmmnDetailCodeVO);

		return "forward:/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do";
	}


}
