package infomind.com.cms.uss.umt.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;


import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


import infomind.com.cms.uss.umt.service.InfoDeptManageService;
import infomind.com.cms.uss.umt.vo.InfoDeptManageVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;
import java.util.List;

/**
 * 부서관련 처리를  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * @author 공통서비스 개발팀 조재영
 * @since 2009.00.00
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.02.01    lee.m.j     최초 생성
 *   2015.06.16	 조정국	  서비스 화면 접근시 조회결과를 표시하도록 수정
 * </pre>
 */


@Controller
public class InfoDeptManageController {

	@Resource(name = "infoDeptManageService")
	private InfoDeptManageService infoDeptManageService;

	/** Message ID Generation */
	@Resource(name = "egovDeptManageIdGnrService")
	private EgovIdGnrService egovDeptManageIdGnrService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;


	private String pagePath ="uss/umt/";

	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 부서를 관리하기 위해 등록된 부서목록을 조회한다.
	 * @param loginVO
	 * @param searchVO
	 * @param model
	 * @return "infomind/com/cms/"
	 * @throws Exception
	 */
	@IncludedInfo(name="부서관리", listUrl="/cms/uss/umt/selectDeptManageList.do", order = 461, gid = 50)
	@RequestMapping(value="/cms/uss/umt/selectDeptManageList.do")
	public String selectDeptManageList (@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("searchVO") InfoDeptManageVO searchVO
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

		List<?> deptManageList = infoDeptManageService.selectDeptManageList(searchVO);
		model.addAttribute("resultList", deptManageList);
		model.addAttribute("searchVO", searchVO);

		int totCnt = infoDeptManageService.selectDeptManageListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return InfoViewUtils.adminTilesView(pagePath,"InfoDeptManageList","sub");
	}


	/**
	 * 등록된 부서의 상세정보를 조회한다.

	 * @return String - 리턴 Url
	 */

	@RequestMapping(value = "/cms/uss/umt/selectDeptManage.do")
	public String selectDeptManage (@RequestParam("srcOrgnztId") String srcOrgnztId , @ModelAttribute("loginVO") LoginVO loginVO , InfoDeptManageVO infoDeptManageVO, ModelMap model )	throws Exception {

		infoDeptManageVO.setOrgnztId(srcOrgnztId);
		InfoDeptManageVO result = infoDeptManageService.selectDeptManage(infoDeptManageVO);
		model.addAttribute("infoDeptManageVO", result);

		return InfoViewUtils.adminTilesView(pagePath,"InfoDeptManageUpdt","sub");

	}


	/**
	 * 부서등록 화면으로 이동한다.

	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/cms/uss/umt/addViewDeptManage.do")
	public String insertViewDeptManage(@ModelAttribute("loginVO") LoginVO loginVO, @ModelAttribute("infoDeptManageVO") InfoDeptManageVO infoDeptManageVO, ModelMap model) throws Exception {

		return InfoViewUtils.adminTilesView(pagePath,"InfoDeptManageInsert","sub");
	}


	/**
	 * 부서정보를 신규로 등록한다.

	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/cms/uss/umt/addDeptManage.do")
	public String insertDeptManage(@ModelAttribute("infoDeptManageVO") InfoDeptManageVO infoDeptManageVO, BindingResult bindingResult, ModelMap model) throws Exception {
		System.out.println("insertDeptManage =========> joan1 ==>" + infoDeptManageVO.toString() );
		beanValidator.validate(infoDeptManageVO, bindingResult); //validation 수행

		infoDeptManageVO.setOrgnztId(egovDeptManageIdGnrService.getNextStringId());
		System.out.println("insertDeptManage =========> joan2 ==>" + infoDeptManageVO.toString() );

		if (bindingResult.hasErrors()) {
			return InfoViewUtils.adminTilesView(pagePath,"InfoDeptManageInsert","sub");
		} else {
			System.out.println("insertDeptManage =========> joan1 ==>" + infoDeptManageVO.toString() );
			infoDeptManageService.insertDeptManage(infoDeptManageVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			return "forward:/cms/uss/umt/selectDeptManageList.do";
		}
	}


	/**
	 * 기 등록된 부서정보를 수정한다.

	 * @return String - 리턴 Url
	 */
	@RequestMapping(value = "/cms/uss/umt/updtDeptManage.do")
	public String updateDeptManage(@ModelAttribute("infoDeptManageVO") InfoDeptManageVO infoDeptManageVO, BindingResult bindingResult, ModelMap model) throws Exception {
		beanValidator.validate(infoDeptManageVO, bindingResult); //validation 수행
		//System.out.println("11=========> " + infoDeptManageVO.toString());

		if (bindingResult.hasErrors()){
			//System.out.println("=========> bindingResult.hasErrors");
			return InfoViewUtils.adminTilesView(pagePath,"InfoDeptManageUpdt","sub");
		}

		//System.out.println("22=========> " + infoDeptManageVO.toString());
		infoDeptManageService.updateDeptManage(infoDeptManageVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		return "forward:/cms/uss/umt/selectDeptManageList.do";

	}


	/**
	 * 기 등록된 부서정보를 삭제한다.

	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/uss/umt/removeDeptManage.do")
	public String deleteDeptManage(@ModelAttribute("infoDeptManageVO") InfoDeptManageVO infoDeptManageVO, BindingResult bindingResult, ModelMap model) throws Exception {

		beanValidator.validate(infoDeptManageVO, bindingResult); //validation 수행
		System.out.println("11deleteDeptManage=========> " + infoDeptManageVO.toString());

		if (bindingResult.hasErrors()){
			System.out.println("deleteDeptManage=========> bindingResult.hasErrors");
			return InfoViewUtils.adminTilesView(pagePath,"InfoDeptManageUpdt","sub");
		}


		System.out.println("22deleteDeptManage=========> " + infoDeptManageVO.toString());
		infoDeptManageService.deleteDeptManage(infoDeptManageVO);
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/cms/uss/umt/selectDeptManageList.do";

	}

	/**
	 * 기 등록된 부서정보목록을 일괄 삭제한다.

	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/cms/uss/umt/removeDeptManageList.do")
	public String deleteDeptManageList(@RequestParam("checkOrgnztIds") String checkOrgnztIds, @ModelAttribute("infoDeptManageVO") InfoDeptManageVO infoDeptManageVO, ModelMap model) throws Exception {


		String[] strOrgnztIds = checkOrgnztIds.split(",");
		for (int i = 0; i < strOrgnztIds.length; i++) {
			infoDeptManageVO.setOrgnztId(strOrgnztIds[i]);
			System.out.println("deleteDeptManageList=========> " + strOrgnztIds[i]);
			infoDeptManageService.deleteDeptManage(infoDeptManageVO);
		}

		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/cms/uss/umt/selectDeptManageList.do";
	}



}
