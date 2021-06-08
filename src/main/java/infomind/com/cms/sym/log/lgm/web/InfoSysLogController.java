package infomind.com.cms.sym.log.lgm.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.log.service.InfoCmsLogService;
import infomind.com.cms.log.vo.InfoCmsLogVO;
import infomind.com.cms.sym.log.lgm.service.InfoSysLogService;
import infomind.com.cms.sym.log.lgm.vo.InfoSysLogVO;
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
 * @Class Name : EgovSysLogController.java
 * @Description : 시스템 로그정보를 관리하기 위한 컨트롤러 클래스
 * @Modification Information
 *
 *    수정일        수정자         수정내용
 *    -------       -------     -------------------
 *    2009. 3. 11.  이삼섭         최초생성
 *    2011. 7. 01.  이기하         패키지 분리(sym.log -> sym.log.lgm)
 *    2011.8.26	정진오			IncludedInfo annotation 추가
 *    2017.09.14	이정은			표준프레임워크 v3.7 개선
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */

@Controller
public class InfoSysLogController {
	
	@Resource(name="InfoSysLogService")
	private InfoSysLogService sysLogService;

	@Resource(name="InfoCmsLogService")
	private InfoCmsLogService infoCmsLogService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	private String pagePath ="sym/log/lgm/";
	/**
	 * 시스템 로그 목록 조회
	 *
	 * @param sysLog
	 * @return sym/log/lgm/EgovSysLogList
	 * @throws Exception
	 */
	@IncludedInfo(name="로그관리", listUrl="/cms/sym/log/lgm/InfoSelectSysLogList.do", order = 1030 ,gid = 60)
	@RequestMapping(value="/cms/sym/log/lgm/InfoSelectSysLogList.do")
	public String selectSysLogInf(@ModelAttribute("searchVO") InfoSysLogVO sysLog,
			ModelMap model) throws Exception{
		
    	/** EgovPropertyService.sample */
		sysLog.setPageUnit(propertiesService.getInt("pageUnit"));
		sysLog.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(sysLog.getPageIndex());
		paginationInfo.setRecordCountPerPage(sysLog.getPageUnit());
		paginationInfo.setPageSize(sysLog.getPageSize());

		sysLog.setFirstIndex(paginationInfo.getFirstRecordIndex());
		sysLog.setLastIndex(paginationInfo.getLastRecordIndex());
		sysLog.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		HashMap<?, ?> _map = (HashMap<?, ?>)sysLogService.selectSysLogInf(sysLog);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));

		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));
		model.addAttribute("frm", sysLog);

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return InfoViewUtils.adminTilesView(pagePath,"InfoSysLogList","ax5ui");


	}
	/**
	 * 시스템 로그 상세 조회
	 *
	 * @param sysLog
	 * @param model
	 * @return sym/log/lgm/EgovSysLogInqire
	 * @throws Exception
	 */
	@RequestMapping(value="/cms/sym/log/lgm/InfoSelectSysLogDetail.do")
	public String selectSysLog(@ModelAttribute("searchVO") InfoSysLogVO sysLog,
			@RequestParam("requstId") String requstId,
			ModelMap model) throws Exception{

		sysLog.setRequstId(requstId.trim());

		InfoSysLogVO vo = sysLogService.selectSysLog(sysLog);
		model.addAttribute("result", vo);

		return InfoViewUtils.adminTilesView(pagePath,"InfoSysLogDetail","axmodal");

	}



	@RequestMapping(value="/cms/sym/log/lgm/selectInfoCmsLogList.do")
	public String selectListView(@ModelAttribute("searchVO") InfoCmsLogVO searchVO, ModelMap model) throws Exception {

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

		List<?> list = infoCmsLogService.selectInfoCmsLogList(searchVO);

		model.addAttribute("list", list);
		model.addAttribute("searchVO", searchVO);

		int totalCnt = infoCmsLogService.selectInfoCmsLogTotalCount(searchVO);
		paginationInfo.setTotalRecordCount(totalCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return InfoViewUtils.adminTilesView(pagePath,"InfoCmsLogList","ax5ui");
	}

	@RequestMapping(value="/cms/sym/log/lgm/InfoCmsLogDetail.do")
	public String InfoCmsLogDetail(@ModelAttribute("searchVO") InfoCmsLogVO sysLog,
							   @RequestParam("cmsLogSno") String cmsLogSno,
							   ModelMap model) throws Exception{

		sysLog.setCmsLogSno(cmsLogSno.trim());

		InfoCmsLogVO vo = infoCmsLogService.selectInfoCmsLog(sysLog);
		model.addAttribute("result", vo);

		return InfoViewUtils.adminTilesView(pagePath,"InfoCmsLogDetail","axmodal");

	}
}
