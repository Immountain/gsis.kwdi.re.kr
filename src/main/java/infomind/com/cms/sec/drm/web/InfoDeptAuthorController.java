package infomind.com.cms.sec.drm.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.sec.drm.service.InfoDeptAuthorService;
import infomind.com.cms.sec.drm.vo.InfoDeptAuthor;
import infomind.com.cms.sec.drm.vo.InfoDeptAuthorVO;
import infomind.com.cms.sec.ram.service.InfoAuthorManageService;
import infomind.com.cms.sec.ram.vo.InfoAuthorManageVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;

/**
 * 부서권한에 관한 controller 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * </pre>
 */


@Controller
@SessionAttributes(types=SessionVO.class)
public class InfoDeptAuthorController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "InfoDeptAuthorService")
    private InfoDeptAuthorService infoDeptAuthorService;
    
    @Resource(name = "InfoAuthorManageService")
    private InfoAuthorManageService infoAuthorManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	private String pagePath = "sec/drm/";

	/**
	 * 권한 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/cms/sec/drm/InfoDeptAuthorListView.do")
    public String selectDeptAuthorListView() throws Exception {
		return InfoViewUtils.adminTilesView(pagePath, "infoDeptAuthorManage", "sub");
    }

	/**
	 * 부서별 할당된 권한목록 조회
	 * 
	 * @param infoDeptAuthorVO InfoDeptAuthorVO
	 * @param infoAuthorManageVO InfoAuthorManageVO
	 * @return String
	 * @exception Exception
	 */
    @IncludedInfo(name="부서권한관리", listUrl="/cms/sec/drm/InfoDeptAuthorList.do", order = 100,gid = 20)
    @RequestMapping(value="/cms/sec/drm/InfoDeptAuthorList.do")
	public String selectInfoDeptAuthorList(@ModelAttribute("infoDeptAuthorVO") InfoDeptAuthorVO infoDeptAuthorVO,
			                            @ModelAttribute("infoAuthorManageVO") InfoAuthorManageVO infoAuthorManageVO,
			                             ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(infoDeptAuthorVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(infoDeptAuthorVO.getPageUnit());
		paginationInfo.setPageSize(infoDeptAuthorVO.getPageSize());
		
		infoDeptAuthorVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		infoDeptAuthorVO.setLastIndex(paginationInfo.getLastRecordIndex());
		infoDeptAuthorVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		infoDeptAuthorVO.setDeptAuthorList(infoDeptAuthorService.selectInfoDeptAuthorList(infoDeptAuthorVO));
        model.addAttribute("deptAuthorList", infoDeptAuthorVO.getDeptAuthorList());
        
        int totCnt = infoDeptAuthorService.selectInfoDeptAuthorListTotCnt(infoDeptAuthorVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

		infoAuthorManageVO.setAuthorManageList(infoAuthorManageService.selectInfoAuthorAllList(infoAuthorManageVO));
        model.addAttribute("authorManageList", infoAuthorManageVO.getAuthorManageList());

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return InfoViewUtils.adminTilesView(pagePath, "infoDeptAuthorManage", "sub");
	}
	
	/**
	 * 부서에 권한정보를 할당하여 데이터베이스에 등록
	 * 
	 * @param userIds String
	 * @param authorCodes String
	 * @param regYns StringinfoDeptSearch.jsp
	 * @param infoDeptAuthor InfoDeptAuthor
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/sec/drm/InfoDeptAuthorInsert.do")
	public String insertInfoDeptAuthor(@RequestParam("userIds") String userIds,
			                       @RequestParam("authorCodes") String authorCodes,
			                       @RequestParam("regYns") String regYns,
			                       @ModelAttribute("infoDeptAuthor") InfoDeptAuthor infoDeptAuthor,
			                         ModelMap model) throws Exception {
		
    	String [] strUserIds = userIds.split(";");
    	String [] strAuthorCodes = authorCodes.split(";");
    	String [] strRegYns = regYns.split(";");
    	
    	for(int i=0; i<strUserIds.length;i++) {
			infoDeptAuthor.setUniqId(strUserIds[i]);
			infoDeptAuthor.setAuthorCode(strAuthorCodes[i]);
    		if(strRegYns[i].equals("N"))
    			infoDeptAuthorService.insertInfoDeptAuthor(infoDeptAuthor);
    		else
    			infoDeptAuthorService.updateInfoDeptAuthor(infoDeptAuthor);
    	}

        model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));		
		return "forward:/cms/sec/drm/InfoDeptAuthorList.do";
	}

	/**
	 * 부서별 할당된 시스템 메뉴 접근권한을 삭제
	 * @param userIds String
	 * @param infoDeptAuthor InfoDeptAuthor
	 * @return String
	 * @exception Exception
	 */ 
	@RequestMapping(value="/cms/sec/drm/InfoDeptAuthorDelete.do")
	public String deleteDeptAuthor (@RequestParam("userIds") String userIds,
			                        @ModelAttribute("infoDeptAuthor") InfoDeptAuthor infoDeptAuthor,
                                     ModelMap model) throws Exception {
		
    	String [] strUserIds = userIds.split(";");
    	for(int i=0; i<strUserIds.length;i++) {
			infoDeptAuthor.setUniqId(strUserIds[i]);
    		infoDeptAuthorService.deleteInfoDeptAuthor(infoDeptAuthor);
    	}
    	
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/cms/sec/drm/InfoDeptAuthorList.do";
	}
	
    /**
	 * 부서조회 팝업 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/cms/sec/drm/InfoDeptSearchView.do")
    public String selectInfoDeptListView() throws Exception {

		return InfoViewUtils.adminTilesView(pagePath, "infoDeptSearch", "sub");

    }
	
	/**
	 * 부서별 할당된 권한목록 조회
	 * @param infoDeptAuthorVO InfoDeptAuthorVO
	 * @return String
	 * @exception Exception
	 */
    //@IncludedInfo(name="부서목록관리", order = 101)
    @RequestMapping(value="/cms/sec/drm/InfoDeptSearchList.do")
	public String selectDeptList(@ModelAttribute("infoDeptAuthorVO") InfoDeptAuthorVO infoDeptAuthorVO,
			                             ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(infoDeptAuthorVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(infoDeptAuthorVO.getPageUnit());
		paginationInfo.setPageSize(infoDeptAuthorVO.getPageSize());

		infoDeptAuthorVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		infoDeptAuthorVO.setLastIndex(paginationInfo.getLastRecordIndex());
		infoDeptAuthorVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		infoDeptAuthorVO.setDeptList(infoDeptAuthorService.selectInfoDeptList(infoDeptAuthorVO));
        model.addAttribute("deptList", infoDeptAuthorVO.getDeptList());
        
        int totCnt = infoDeptAuthorService.selectInfoDeptListTotCnt(infoDeptAuthorVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return InfoViewUtils.adminTilesView(pagePath, "infoDeptSearch", "sub");

	}


	/**
	 * 부서별 할당된 권한목록 조회 테스트 입니다....
	 * @param infoDeptAuthorVO InfoDeptAuthorVO
	 * @return String
	 * @exception Exception
	 */
	//@IncludedInfo(name="부서목록관리", order = 101)
	@RequestMapping(value="/cms/sec/drm/InfoDeptSearchListTest.do")
	public String InfoDeptSearchListTest(@ModelAttribute("infoDeptAuthorVO") InfoDeptAuthorVO infoDeptAuthorVO,
								 ModelMap model) throws Exception {

		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(infoDeptAuthorVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(infoDeptAuthorVO.getPageUnit());
		paginationInfo.setPageSize(infoDeptAuthorVO.getPageSize());

		infoDeptAuthorVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		infoDeptAuthorVO.setLastIndex(paginationInfo.getLastRecordIndex());
		infoDeptAuthorVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		infoDeptAuthorVO.setDeptList(infoDeptAuthorService.selectInfoDeptList(infoDeptAuthorVO));
		model.addAttribute("deptList", infoDeptAuthorVO.getDeptList());

		int totCnt = infoDeptAuthorService.selectInfoDeptListTotCnt(infoDeptAuthorVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return InfoViewUtils.adminTilesView(pagePath, "infoDeptSearch_test", "sub");

	}
}