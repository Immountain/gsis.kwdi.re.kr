package infomind.com.cms.sym.prm.web;


import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;


import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ems.service.EgovSndngMailRegistService;

import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cmm.bean.InfoUserMenuCacheManager;

import infomind.com.cms.sym.prm.service.InfoProgrmManageService;
import infomind.com.cms.sym.prm.vo.InfoProgrmManageVO;
import infomind.com.ext.vo.CmsSearchVO;

import infomind.com.utils.page.InfoPageUtils;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 프로그램목록 관리및 변경을 처리하는 비즈니스 구현 클래스
 * @author 인포마인드 개발팀 양진혁
 * @since 2020.10.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2020.10.20  양진혁          최초 생성
 * </pre>
 */

@Controller
public class InfoProgrmManageController {

	/** Validator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** InfoProgrmManageService */
	@Resource(name = "InfoProgrmManageService")
    private InfoProgrmManageService progrmManageService;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /** EgovSndngMailRegistService */
	@Resource(name = "sndngMailRegistService")
    private EgovSndngMailRegistService sndngMailRegistService;

	private final String pagePath ="sym/prm/";


	@Resource
	private InfoUserMenuCacheManager cacheAdminMenu;

	@Value("${Globals.DbType}")
	private String file_Path;


    /**
     * 프로그램목록 리스트조회한다.
     * @param searchVO ComDefaultVO
     * @return 출력페이지정보 "sym/prm/InfoProgramListManage"
     * @exception Exception
     */
    @IncludedInfo(name="프로그램관리",listUrl="/cms/sym/prm/programListManageSelect.do", order = 1111 ,gid = 60)
    @RequestMapping(value="/cms/sym/prm/programListManageSelect.do")
    public String selectProgrmList(@ModelAttribute("searchVO") CmsSearchVO searchVO,ModelMap model) throws Exception {



	//	System.out.println("진혁 =======>"+cacheAdminMenu.getMenuByCode("USER"));

//		List<MenuManageVO> va2= cacheAdminMenu.getMenuByCode("USER");
//		System.out.println("진혁 va2=======>"+va2.size());
//		System.out.println("진혁 va2=======>"+file_Path);
//
//

    	// 내역 조회
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

        List<?> list_progrmmanage = progrmManageService.selectProgrmList(searchVO);

        model.addAttribute("list_progrmmanage", list_progrmmanage);
        model.addAttribute("searchVO", searchVO);

        int totCnt = progrmManageService.selectProgrmListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
		return InfoViewUtils.adminTilesView(pagePath,"InfoProgramListManage","sub");
    }



	/**
	 * 프로그램목록을 등록화면으로 이동
	 * @param progrmManageVO ProgrmManageVO
	 * @param commandMap     Map
	 * @return 출력페이지정보 등록화면 호출시 "sym/prm/EgovProgramListRegist",
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/sym/prm/RegistProgramListView.do")
	public String insertProgrmListView(
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("progrmManageVO") InfoProgrmManageVO progrmManageVO,BindingResult bindingResult,ModelMap model)throws Exception {



		return InfoViewUtils.adminTilesView(pagePath,"InfoProgramListRegist","sub");
	}



	/**
	 * 프로그램목록을 등록화면으로 이동 및 등록 한다.
	 * @param progrmManageVO ProgrmManageVO
	 * @param commandMap     Map
	 *         출력페이지정보 등록처리시 "forward:/cms/sym/prm/programListManageSelect.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/sym/prm/RegistProgramList.do")
	public String insertProgrmList(@RequestParam Map<?, ?> commandMap,
								   @ModelAttribute("progrmManageVO") InfoProgrmManageVO progrmManageVO,BindingResult bindingResult,ModelMap model)throws Exception {

		String resultMsg = "";
		beanValidator.validate(progrmManageVO, bindingResult);
		if (bindingResult.hasErrors()){
			return InfoViewUtils.adminTilesView(pagePath,"InfoProgramListRegist","sub");
		}


		if(progrmManageVO.getProgrmFileNm() != null){

			InfoProgrmManageVO vo = progrmManageService.selectProgrm(progrmManageVO);
			if(vo != null){
				model.addAttribute("resultMsg", "프로그램파일명이 중복 입니다.");
				return InfoViewUtils.adminTilesView(pagePath,"InfoProgramListRegist","sub");
			}
		}

		if(progrmManageVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){
			progrmManageVO.setProgrmDc(" ");
		}

		progrmManageService.insertProgrm(progrmManageVO);
		resultMsg = egovMessageSource.getMessage("success.common.insert");




		model.addAttribute("resultMsg", resultMsg);
		return "forward:/cms/sym/prm/programListManageSelect.do";
	}


	/**
	 * 프로그램목록을 상세화면 호출 및 상세조회한다.
	 * @param tmp_progrmNm  String
	 * @return 출력페이지정보 "sym/prm/EgovProgramListDetailSelectUpdt"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/sym/prm/ProgramListDetailSelect.do")
	public String selectProgrm(@RequestParam("tmp_progrmNm") String tmp_progrmNm ,@ModelAttribute("searchVO") CmsSearchVO searchVO,ModelMap model)
			throws Exception {


		InfoProgrmManageVO vo = new InfoProgrmManageVO();
		vo.setProgrmFileNm(tmp_progrmNm);
		InfoProgrmManageVO progrmManageVO = progrmManageService.selectProgrm(vo);
		model.addAttribute("progrmManageVO", progrmManageVO);

		return InfoViewUtils.adminTilesView(pagePath,"InfoProgramListDetailSelectUpdt","sub");
	}

	/**
	 * 프로그램목록을 삭제 한다.
	 * @param progrmManageVO ProgrmManageVO
	 * @return 출력페이지정보 "forward:/cms/sym/prm/programListManageSelect.do"
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/sym/prm/ProgramListManageDelete.do")
	public String deleteProgrmList(@ModelAttribute("progrmManageVO")InfoProgrmManageVO progrmManageVO,ModelMap model)throws Exception {
		String resultMsg = "";

		progrmManageService.deleteProgrm(progrmManageVO);
		resultMsg = egovMessageSource.getMessage("success.common.delete");
		model.addAttribute("resultMsg", resultMsg);

		return "forward:/cms/sym/prm/programListManageSelect.do";
	}

	/**
	 * 프로그램목록을 수정 한다.
	 * @param progrmManageVO ProgrmManageVO
	 * @return 출력페이지정보 "forward:/sym/prm/EgovProgramListManageSelect.do"
	 * @exception Exception
	 */
	/*프로그램목록수정*/
	@RequestMapping(value="/cms/sym/prm/ProgramListDetailSelectUpdt.do")
	public String updateProgrmList(@ModelAttribute("progrmManageVO") InfoProgrmManageVO progrmManageVO,
			BindingResult bindingResult,
			ModelMap model)
			throws Exception {
		String resultMsg = "";



		beanValidator.validate(progrmManageVO, bindingResult);
		if (bindingResult.hasErrors()){
			return InfoViewUtils.adminTilesView(pagePath,"InfoProgramListRegist","sub");
		}

		if(progrmManageVO.getProgrmDc()==null || progrmManageVO.getProgrmDc().equals("")){progrmManageVO.setProgrmDc(" ");}
		progrmManageService.updateProgrm(progrmManageVO);
		resultMsg = egovMessageSource.getMessage("success.common.update");
		model.addAttribute("resultMsg", resultMsg);

		return "forward:/cms/sym/prm/programListManageSelect.do";
	}

	/**
	 * 프로그램파일명을 조회한다.
	 * @param searchVO ComDefaultVO
	 * @return 출력페이지정보 "sym/prm/EgovFileNmSearch"
	 * @exception Exception
	 */
	@RequestMapping(value="/sym/prm/InfoProgramListSearch.do")
	public String selectProgrmListSearch(
			@ModelAttribute("searchVO") CmsSearchVO searchVO,
			ModelMap model)
			throws Exception {
		// 0. Spring Security 사용자권한 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "egovframework/com/uat/uia/EgovLoginUsr";
		}
		// 내역 조회
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

		List<?> list_progrmmanage = progrmManageService.selectProgrmList(searchVO);
		model.addAttribute("list_progrmmanage", list_progrmmanage);

		int totCnt = progrmManageService.selectProgrmListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return InfoViewUtils.adminTilesView(pagePath,"InfoFileNmSearch","axmodal");

	}
}