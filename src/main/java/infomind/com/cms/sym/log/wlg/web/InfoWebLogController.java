package infomind.com.cms.sym.log.wlg.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.info.site.service.InfoSiteVisitService;
import infomind.com.cms.info.site.vo.InfoSiteVisitVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Class Name : EgovWebLogController.java
 * @Description : 시스템 로그정보를 관리하기 위한 컨트롤러 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.wlg)
 *    2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */

@Controller
public class InfoWebLogController {

	@Resource(name="InfoSiteVisitService")
	private InfoSiteVisitService webLogService;

	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;

	private String pagePath="sym/log/wlg/";
	/**
	 * 웹 로그 목록 조회
	 *
	 * @param webLog
	 * @return sym/log/wlg/EgovWebLogList
	 * @throws Exception
	 */
	@IncludedInfo(name="웹로그관리", listUrl="/cms/sym/log/wlg/InfoSelectWebLogList.do", order = 1070 ,gid = 60)
	@RequestMapping(value="/cms/sym/log/wlg/InfoSelectWebLogList.do")
	public String selectWebLogInf(@ModelAttribute("searchVO") InfoSiteVisitVO webLog,
			ModelMap model) throws Exception{

		webLog.setPageUnit(propertyService.getInt("pageUnit"));
		webLog.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(webLog.getPageIndex());
		paginationInfo.setRecordCountPerPage(webLog.getPageUnit());
		paginationInfo.setPageSize(webLog.getPageSize());

		webLog.setFirstIndex(paginationInfo.getFirstRecordIndex());
		webLog.setLastIndex(paginationInfo.getLastRecordIndex());
		webLog.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> userList = webLogService.selectSiteVisitList(webLog);
		model.addAttribute("resultList", userList);

		int totCnt = webLogService.selectSiteVisitTotalCount(webLog);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);


		return InfoViewUtils.adminTilesView(pagePath,"InfoWebLogList","ax5ui");

	}

	/**
	 * 웹 로그 상세 조회
	 *
	 * @param webLog
	 * @param model
	 * @return sym/log/wlg/EgovWebLogInqire
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/sym/log/wlg/InfoSelectWebLogDetail.do")
	public String selectWebLog(@ModelAttribute("searchVO") InfoSiteVisitVO webLog,
			ModelMap model) throws Exception{

		InfoSiteVisitVO vo = webLogService.selectSiteVisit(webLog);
		model.addAttribute("result", vo);

		return InfoViewUtils.adminTilesView(pagePath,"InfoWebLogDetail","axmodal");
	}

}
