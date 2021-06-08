package infomind.com.cms.sec.ram.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.security.intercept.EgovReloadableFilterInvocationSecurityMetadataSource;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.sec.ram.service.InfoAuthorRoleManageService;
import infomind.com.cms.sec.ram.vo.InfoAuthorRoleManage;
import infomind.com.cms.sec.ram.vo.InfoAuthorRoleManageVO;
import infomind.com.utils.web.InfoViewUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 권한별 롤관리에 관한 controller 클래스를 정의한다.
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
 *   2011.09.07  서준식          롤 등록시 이미 등록된 경우 데이터 중복 에러 발생 문제 수정
 * </pre>
 */
@Controller
public class InfoAuthorRoleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfoAuthorRoleController.class);
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "InfoAuthorRoleManageService")
    private InfoAuthorRoleManageService infoAuthorRoleManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	@Resource(name="databaseSecurityMetadataSource")
	EgovReloadableFilterInvocationSecurityMetadataSource databaseSecurityMetadataSource;


	private String pagePath ="sec/ram/";

	/**
	 * 권한 롤 관계 화면 이동
	 * @return "infomind/com/cms/sec/ram/InfoDeptAuthorList"
	 * @exception Exception
	 */
    @RequestMapping("/cms/sec/ram/InfoAuthorRoleListView.do")
    public String selectInfoAuthorRoleListView() throws Exception {

		return InfoViewUtils.adminTilesView(pagePath,"infoAuthorRoleManage","sub");
    } 

	/**
	 * 권한별 할당된 롤 목록 조회
	 * 
	 * @param infoAuthorRoleManageVO InfoAuthorRoleManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/sec/ram/InfoAuthorRoleList.do")
	public String selectInfoAuthorRoleList(@ModelAttribute("infoAuthorRoleManageVO") InfoAuthorRoleManageVO infoAuthorRoleManageVO,
			                            ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(infoAuthorRoleManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(infoAuthorRoleManageVO.getPageUnit());
		paginationInfo.setPageSize(infoAuthorRoleManageVO.getPageSize());

		infoAuthorRoleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		infoAuthorRoleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		infoAuthorRoleManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		infoAuthorRoleManageVO.setAuthorRoleList(infoAuthorRoleManageService.selectInfoAuthorRoleList(infoAuthorRoleManageVO));
        model.addAttribute("authorRoleList", infoAuthorRoleManageVO.getAuthorRoleList());
        model.addAttribute("searchVO", infoAuthorRoleManageVO);
        
        int totCnt = infoAuthorRoleManageService.selectInfoAuthorRoleListTotCnt(infoAuthorRoleManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return InfoViewUtils.adminTilesView(pagePath,"infoAuthorRoleManage","sub");
	}
    
	/**
	 * 권한정보에 롤을 할당하여 데이터베이스에 등록
	 * @param authorCode String
	 * @param roleCodes String
	 * @param regYns String
	 * @param infoAuthorRoleManage InfoAuthorRoleManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/sec/ram/InfoAuthorRoleInsert.do")
	public String insertAuthorRole(@RequestParam("authorCode") String authorCode,
			                       @RequestParam("roleCodes") String roleCodes,
			                       @RequestParam("regYns") String regYns,
			                       @RequestParam Map<?, ?> commandMap,
			                       @ModelAttribute("authorRoleManage") InfoAuthorRoleManage infoAuthorRoleManage,
			                         ModelMap model) throws Exception {
		
    	String [] strRoleCodes = roleCodes.split(";");
    	String [] strRegYns = regYns.split(";");

		infoAuthorRoleManage.setRoleCode(authorCode);
    	
    	for(int i=0; i<strRoleCodes.length;i++) {

			infoAuthorRoleManage.setRoleCode(strRoleCodes[i]);
			infoAuthorRoleManage.setRegYn(strRegYns[i]);
    		if(strRegYns[i].equals("Y")){
				infoAuthorRoleManageService.deleteInfoAuthorRole(infoAuthorRoleManage);//2011.09.07
				infoAuthorRoleManageService.insertInfoAuthorRole(infoAuthorRoleManage);
    		}else {
				infoAuthorRoleManageService.deleteInfoAuthorRole(infoAuthorRoleManage);
    		}
    	}


    	//롤 재실행
		databaseSecurityMetadataSource.reload();

		return "redirect:/cms/sec/ram/InfoAuthorRoleList.do?searchKeyword="+authorCode+"&menuTargetNo="+infoAuthorRoleManage.getMenuTargetNo();
	}    
}