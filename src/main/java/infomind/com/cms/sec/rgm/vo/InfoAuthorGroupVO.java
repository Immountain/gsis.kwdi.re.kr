package infomind.com.cms.sec.rgm.vo;

import egovframework.com.sec.rgm.service.AuthorGroup;

import java.util.List;

/**
 * 권한그룹에 대한 Vo 클래스를 정의한다.
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
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */

public class InfoAuthorGroupVO extends InfoAuthorGroup {

	private static final long serialVersionUID = 1L;

	List <InfoAuthorGroupVO> authorGroupList;

	/**
	 * authorGroupList attribute 를 리턴한다.
	 * @return List<AuthorGroupVO>
	 */
	public List<InfoAuthorGroupVO> getAuthorGroupList() {
		return authorGroupList;
	}
	/**
	 * authorGroupList attribute 값을 설정한다.
	 * @param authorGroupList List<AuthorGroupVO> 
	 */
	public void setAuthorGroupList(List<InfoAuthorGroupVO> authorGroupList) {
		this.authorGroupList = authorGroupList;
	}
	

}