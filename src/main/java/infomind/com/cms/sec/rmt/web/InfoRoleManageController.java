package infomind.com.cms.sec.rmt.web;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.sec.ram.service.InfoAuthorManageService;
import infomind.com.cms.sec.ram.vo.InfoAuthorManageVO;
import infomind.com.cms.sec.rmt.service.InfoRoleManageService;
import infomind.com.cms.sec.rmt.vo.InfoRoleManage;
import infomind.com.cms.sec.rmt.vo.InfoRoleManageVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springmodules.validation.commons.DefaultBeanValidator;

import javax.annotation.Resource;
import java.util.List;

/**
 * 롤관리에 관한 controller 클래스를 정의한다.
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
public class InfoRoleManageController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    @Resource(name = "InfoRoleManageService")
    private InfoRoleManageService infoRoleManageService;

    @Resource(name = "EgovCmmUseService")
    EgovCmmUseService egovCmmUseService;

    @Resource(name = "InfoAuthorManageService")
    private InfoAuthorManageService infoAuthorManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** Message ID Generation */
    @Resource(name="egovRoleIdGnrService")
    private EgovIdGnrService egovRoleIdGnrService;

	private String pagePath = "sec/rmt/";

    @Autowired
	private DefaultBeanValidator beanValidator;

    /**
	 * 롤 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/cms/sec/rmt/InfoRoleListView.do")
    public String selectRoleListView()
            throws Exception {
		return InfoViewUtils.adminTilesView(pagePath, "infoRoleManage", "sub");
    }

	/**
	 * 등록된 롤 정보 목록 조회
	 * @param infoRoleManageVO InfoRoleManageVO
	 * @return String
	 * @exception Exception
	 */
    @IncludedInfo(name="롤관리", listUrl="/cms/sec/rmt/InfoRoleList.do", order = 90,gid = 20)
    @RequestMapping(value="/cms/sec/rmt/InfoRoleList.do")
	public String selectInfoRoleList(@ModelAttribute("infoRoleManageVO") InfoRoleManageVO infoRoleManageVO,
			                      ModelMap model) throws Exception {

    	/** paging */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(infoRoleManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(infoRoleManageVO.getPageUnit());
		paginationInfo.setPageSize(infoRoleManageVO.getPageSize());

		infoRoleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		infoRoleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		infoRoleManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		infoRoleManageVO.setRoleManageList(infoRoleManageService.selectInfoRoleList(infoRoleManageVO));
        model.addAttribute("roleList", infoRoleManageVO.getRoleManageList());

        int totCnt = infoRoleManageService.selectInfoRoleListTotCnt(infoRoleManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("message", egovMessageSource.getMessage("success.common.select"));

		return InfoViewUtils.adminTilesView(pagePath, "infoRoleManage", "sub");

	}

	/**
	 * 등록된 롤 정보 조회
	 * @param roleCode String
	 * @param infoRoleManageVO InfoRoleManageVO
	 * @param infoAuthorManageVO InfoAuthorManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/sec/rmt/InfoRole.do")
	public String selectRole(@RequestParam("roleCode") String roleCode,
	                         @ModelAttribute("infoRoleManageVO") InfoRoleManageVO infoRoleManageVO,
	                         @ModelAttribute("infoAuthorManageVO") InfoAuthorManageVO infoAuthorManageVO,
		                      ModelMap model) throws Exception {

		infoRoleManageVO.setRoleCode(roleCode);

		infoAuthorManageVO.setAuthorManageList(infoAuthorManageService.selectInfoAuthorAllList(infoAuthorManageVO));

    	model.addAttribute("infoRoleManage", infoRoleManageService.selectInfoRole(infoRoleManageVO));
        model.addAttribute("authorManageList", infoAuthorManageVO.getAuthorManageList());
        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM029"));

		return InfoViewUtils.adminTilesView(pagePath, "infoRoleUpdate", "sub");

	}

    /**
	 * 롤 등록화면 이동
	 * @param infoAuthorManageVO InfoAuthorManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping("/cms/sec/rmt/InfoRoleInsertView.do")
    public String insertRoleView(@ModelAttribute("infoAuthorManageVO") InfoAuthorManageVO infoAuthorManageVO,
    								@ModelAttribute("infoRoleManage") InfoRoleManage infoRoleManage,
    									ModelMap model) throws Exception {

		infoAuthorManageVO.setAuthorManageList(infoAuthorManageService.selectInfoAuthorAllList(infoAuthorManageVO));
        model.addAttribute("authorManageList", infoAuthorManageVO.getAuthorManageList());
        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM029"));


		return InfoViewUtils.adminTilesView(pagePath, "infoRoleInsert", "sub");

    }

    /**
	 * 공통코드 호출
	 * @param comDefaultCodeVO ComDefaultCodeVO
	 * @param codeId String
	 * @return List
	 * @exception Exception
	 */
    public List<?> getCmmCodeDetailList(ComDefaultCodeVO comDefaultCodeVO, String codeId)  throws Exception {
    	comDefaultCodeVO.setCodeId(codeId);
    	return egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO);
    }

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 등록
	 * @param infoRoleManage InfoRoleManage
	 * @param infoRoleManageVO InfoRoleManageVO
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/sec/rmt/InfoRoleInsert.do")
	public String insertRole(@ModelAttribute("infoRoleManage") InfoRoleManage infoRoleManage,
			                 @ModelAttribute("infoRoleManageVO") InfoRoleManageVO infoRoleManageVO,
			                  BindingResult bindingResult,
                              ModelMap model) throws Exception {

    	beanValidator.validate(infoRoleManage, bindingResult); //validation 수행

    	if (bindingResult.hasErrors()) {
			return InfoViewUtils.adminTilesView(pagePath, "infoRoleInsert", "sub");
		} else {
    	    String roleTyp = infoRoleManage.getRoleTyp();
	    	if("method".equals(roleTyp))//KISA 보안약점 조치 (2018-10-29, 윤창원)
	    		roleTyp = "mtd";
	    	else if("pointcut".equals(roleTyp))//KISA 보안약점 조치 (2018-10-29, 윤창원)
	    		roleTyp = "pct";
	    	else roleTyp = "web";

	    	infoRoleManage.setRoleCode(roleTyp.concat("-").concat(egovRoleIdGnrService.getNextStringId()));
	    	infoRoleManageVO.setRoleCode(infoRoleManage.getRoleCode());

	        model.addAttribute("cmmCodeDetailList", getCmmCodeDetailList(new ComDefaultCodeVO(),"COM029"));
	    	model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	        model.addAttribute("infoRoleManage", infoRoleManageService.insertInfoRole(infoRoleManage, infoRoleManageVO));

	        //return "egovframework/com/sec/rmt/EgovRoleUpdate";
	        return "forward:/cms/sec/rmt/InfoRoleList.do";
		}
	}

	/**
	 * 시스템 메뉴에 따른 접근권한, 데이터 입력, 수정, 삭제의 권한 롤을 수정
	 * @param infoRoleManage InfoRoleManage
	 * @param bindingResult BindingResult
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/sec/rmt/InfoRoleUpdate.do")
	public String updateRole(@ModelAttribute("infoRoleManage") InfoRoleManage infoRoleManage,
			BindingResult bindingResult,
            ModelMap model) throws Exception {

    	beanValidator.validate(infoRoleManage, bindingResult); //validation 수행
    	if (bindingResult.hasErrors()) {


					System.out.println("dddddddddddddd");


			return InfoViewUtils.adminTilesView(pagePath, "infoRoleUpdate", "sub");
		} else {


			System.out.println("aaaaaaaa");

    	infoRoleManageService.updateInfoRole(infoRoleManage);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
    	//return "forward:/sec/rmt/EgovRole.do";
    	return "forward:/cms/sec/rmt/InfoRoleList.do";
		}
	}

	/**
	 * 불필요한 롤정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param infoRoleManage InfoRoleManage
	 * @return String
	 * @exception Exception
	 */
    @RequestMapping(value="/cms/sec/rmt/InfoRoleDelete.do")
	public String deleteRole(@ModelAttribute("infoRoleManage") InfoRoleManage infoRoleManage,
            ModelMap model) throws Exception {
    	infoRoleManageService.deleteInfoRole(infoRoleManage);
    	model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/cms/sec/rmt/InfoRoleList.do";

	}

	/**
	 * 불필요한 그룹정보 목록을 화면에 조회하여 데이터베이스에서 삭제
	 * @param roleCodes String
	 * @param infoRoleManage InfoRoleManage
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value="/cms/sec/rmt/InfoRoleListDelete.do")
	public String deleteRoleList(@RequestParam("roleCodes") String roleCodes,
			                     @ModelAttribute("infoRoleManage") InfoRoleManage infoRoleManage,
	                              Model model) throws Exception {
    	String [] strRoleCodes = roleCodes.split(";");
    	for(int i=0; i<strRoleCodes.length;i++) {
    		infoRoleManage.setRoleCode(strRoleCodes[i]);
    		infoRoleManageService.deleteInfoRole(infoRoleManage);
    	}

		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		return "forward:/cms/sec/rmt/InfoRoleList.do";
	}

}