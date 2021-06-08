package infomind.com.cms.ccm.ccc.service.impl;




import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.ccm.ccc.dao.InfoCmmnClCodeManageDAO;
import infomind.com.cms.ccm.ccc.service.InfoCcmCmmnClCodeManageService;
import infomind.com.cms.ccm.ccc.vo.InfoCmmnClCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
*
* 공통분류코드에 대한 서비스 구현클래스를 정의한다
* 
* @author 인포마인드 개발팀 양진혁
* @since 2020.10.07
* @version 1.0
* @see
*
*      <pre>
* << 개정이력(Modification Information) >>
* 
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2020.10.07  양진혁          최초 생성
*
* </pre>
*/
@Service("InfoCcmCmmnClCodeManageService")
public class InfoCcmCmmnClCodeManageServiceImpl extends EgovAbstractServiceImpl implements InfoCcmCmmnClCodeManageService {
	
	@Resource(name = "InfoCmmnClCodeManageDAO")
	private InfoCmmnClCodeManageDAO infoCmmnClCodeManageDAO;
	
	/**
	 * 공통분류코드 총 갯수를 조회한다.
	 */
	@Override
	public int selectCmmnClCodeListTotCnt(InfoCmmnClCode searchVO) throws Exception {
        return infoCmmnClCodeManageDAO.selectCmmnClCodeListTotCnt(searchVO);
	}
	
	/**
	 * 공통분류코드 목록을 조회한다.
	 */
	@Override
	public List<?> selectCmmnClCodeList(InfoCmmnClCode searchVO) throws Exception {
        return infoCmmnClCodeManageDAO.selectCmmnClCodeList(searchVO);
	}
	
	/**
	 * 공통분류코드 상세항목을 조회한다.
	 */
	@Override
	public InfoCmmnClCode selectCmmnClCodeDetail(InfoCmmnClCode cmmnClCodeVO) throws Exception {
		InfoCmmnClCode ret = infoCmmnClCodeManageDAO.selectCmmnClCodeDetail(cmmnClCodeVO);
    	return ret;
	}
	
	/**
	 * 공통분류코드를 등록한다.
	 */
	@Override
	public void insertCmmnClCode(InfoCmmnClCode cmmnClCodeVO) throws Exception {
		System.out.println("TEST4 : 등록 Serviceimpl");


		infoCmmnClCodeManageDAO.insertCmmnClCode(cmmnClCodeVO);





	}
	
	/**
	 * 공통분류코드를 삭제한다.
	 */
	@Override
	public void deleteCmmnClCode(InfoCmmnClCode cmmnClCodeVO) throws Exception {
		infoCmmnClCodeManageDAO.deleteCmmnClCode(cmmnClCodeVO);
	}
	
	/**
	 * 공통분류코드를 수정한다.
	 */
	@Override
	public void updateCmmnClCode(InfoCmmnClCode cmmnClCodeVO) throws Exception {
		infoCmmnClCodeManageDAO.updateCmmnClCode(cmmnClCodeVO);
		
	}

	@Override
	public List<InfoCmmnClCode> selectCmmnClCodeAllList(InfoCmmnClCode searchVO) throws Exception {
		return infoCmmnClCodeManageDAO.selectCmmnClCodeAllList(searchVO);
	}

}
