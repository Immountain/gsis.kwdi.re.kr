package infomind.com.cms.sym.log.ulg.web;

import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import infomind.com.cms.sym.log.ulg.service.InfoUserLogService;
import infomind.com.cms.sym.log.ulg.vo.InfoUserLogVO;
import infomind.com.utils.web.InfoViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Class Name : EgovUserLogController.java
 * @Description : 사용로그정보를 관리하기 위한 컨트롤러 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.ulg)
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
public class InfoUserLogController {

	@Resource(name="InfoUserLogService")
	private InfoUserLogService userLogService;

	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;

	private String pagePath ="sym/log/ulg/";

	/**
	 * 사용자 로그 목록 조회
	 *
	 * @param UserLog
	 * @return sym/log/ulg/EgovUserLogList
	 * @throws Exception
	 */
	@IncludedInfo(name="사용로그관리", listUrl= "/cms/sym/log/ulg/InfoSelectUserLogList.do", order = 1040 ,gid = 60)
	@RequestMapping(value="/cms/sym/log/ulg/InfoSelectUserLogList.do")
	public String selectUserLogInf(@ModelAttribute("searchVO") InfoUserLogVO userLog,
			ModelMap model) throws Exception{

		/** EgovPropertyService.sample */
		userLog.setPageUnit(propertyService.getInt("pageUnit"));
		userLog.setPageSize(propertyService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userLog.getPageIndex());
		paginationInfo.setRecordCountPerPage(userLog.getPageUnit());
		paginationInfo.setPageSize(userLog.getPageSize());

		userLog.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userLog.setLastIndex(paginationInfo.getLastRecordIndex());
		userLog.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		HashMap<?, ?> _map = (HashMap<?, ?>)userLogService.selectUserLogInf(userLog);
		int totCnt = Integer.parseInt((String)_map.get("resultCnt"));

		model.addAttribute("resultList", _map.get("resultList"));
		model.addAttribute("resultCnt", _map.get("resultCnt"));

		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		return InfoViewUtils.adminTilesView(pagePath,"InfoUserLogList","ax5ui");

	}

	/**
	 * 사용자 로그 상세 조회
	 *
	 * @param userLog
	 * @param model
	 * @return sym/log/ulg/EgovUserLogInqire
	 * @throws Exception
	 */
//	@RequestMapping(value="/cms/sym/log/ulg/InfoSelectUserLogDetail.do")
//	public String selectUserLog(@ModelAttribute("searchVO") InfoUserLogVO userLog,
//			@RequestParam("occrrncDe") String occrrncDe,
//			@RequestParam("rqesterId") String rqesterId,
//			@RequestParam("srvcNm") String srvcNm,
//			@RequestParam("methodNm") String methodNm,
//			ModelMap model) throws Exception{
//
//		userLog.setOccrrncDe(occrrncDe.trim());
//		userLog.setRqesterId(rqesterId.trim());
//		userLog.setSrvcNm(srvcNm.trim());
//		userLog.setMethodNm(methodNm.trim());
//
//		InfoUserLogVO vo = userLogService.selectUserLog(userLog);
//		model.addAttribute("result", vo);
//
//		return InfoViewUtils.adminTilesView(pagePath,"InfoUserLogDetail","axmodal");
//
//	}

}
