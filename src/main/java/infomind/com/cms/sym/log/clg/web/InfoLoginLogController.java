package infomind.com.cms.sym.log.clg.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.sym.log.clg.service.InfoLoginLogService;
import infomind.com.cms.sym.log.clg.vo.InfoLoginLogVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Class Name : EgovLoginLogController.java
 * @Description : 접속로그정보를 관리하기 위한 컨트롤러 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------      -------     -------------------
 *    2009. 3. 11. 이삼섭        최초생성
 *    2011. 7. 01. 이기하        패키지 분리(sym.log -> sym.log.clg)
 *    2011.8.26	정진오			IncludedInfo annotation 추가
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */

@Controller
public class InfoLoginLogController {

	@Resource(name="InfoLoginLogService")
	private InfoLoginLogService loginLogService;

	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;

	private String pagePath="sym/log/clg/";
	/**
	 * 로그인 로그 목록 조회
	 *
	 * @param loginLog
	 * @return sym/log/clg/EgovLoginLogList
	 * @throws Exception
	 */
	@IncludedInfo(name="접속로그관리", order = 1080 ,gid = 60)
	@RequestMapping(value="/cms/sym/log/clg/InfoSelectLoginLogList.do")
	public String selectLoginLogInf(@ModelAttribute("searchVO") InfoLoginLogVO loginLog,
			ModelMap model) throws Exception{

		loginLog.setPageUnit(propertyService.getInt("pageUnit"));
		loginLog.setPageSize(propertyService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(loginLog.getPageIndex());
		paginationInfo.setRecordCountPerPage(loginLog.getPageUnit());
		paginationInfo.setPageSize(loginLog.getPageSize());

		loginLog.setFirstIndex(paginationInfo.getFirstRecordIndex());
		loginLog.setLastIndex(paginationInfo.getLastRecordIndex());
		loginLog.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		HashMap<?, ?> _map = (HashMap<?, ?>)loginLogService.selectLoginLogInf(loginLog);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));

		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return InfoViewUtils.adminTilesView(pagePath,"InfoLoginLogList","ax5ui");

	}

	/**
	 * 로그인 로그 상세 조회
	 *
	 * @param loginLog
	 * @param model
	 * @return sym/log/clg/EgovLoginLogInqire
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/sym/log/clg/InfoSelectLoginLogDetail.do")
	public String selectLoginLog(@ModelAttribute("searchVO") InfoLoginLogVO loginLog,
			@RequestParam("logId") String logId,
			ModelMap model) throws Exception{
		

		loginLog.setLogId(logId.trim());

		InfoLoginLogVO vo = loginLogService.selectLoginLog(loginLog);
		model.addAttribute("result", vo);
		return InfoViewUtils.adminTilesView(pagePath,"InfoLoginLogDetail","axmodal");

	}

}
