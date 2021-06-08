package infomind.com.cms.uss.stp.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.sam.stp.service.StplatManageDefaultVO;
import egovframework.com.uss.sam.stp.service.StplatManageVO;
import infomind.com.cms.uss.stp.vo.InfoStplatManageVO;
import infomind.com.ext.vo.CmsSearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * 약관내용을 처리하는 DAO 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *   2016.06.13  장동한          표준프레임워크 v3.6 개선
 *
 * </pre>
 */
@Repository("InfoStplatManageDAO")
public class InfoStplatManageDAO extends EgovComAbstractDAO {


    /**
	 * 약관정보 글 목록에 대한 상세내용을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
    public InfoStplatManageVO selectStplatDetail(InfoStplatManageVO vo) throws Exception {

        return (InfoStplatManageVO) selectOne("InfoStplatManageDAO.selectStplatDetail", vo);

    }

    /**
	 * 약관정보 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    public List<?> selectStplatList(CmsSearchVO searchVO) throws Exception {

        return list("InfoStplatManageDAO.selectStplatList", searchVO);

    }

    /**
	 * 약관정보 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    public int selectStplatListTotCnt(CmsSearchVO searchVO) {

        return (Integer)selectOne("InfoStplatManageDAO.selectStplatListTotCnt", searchVO);

    }

	/**
	 * 약관정보 글을 등록한다.
	 * @param vo
	 * @exception Exception
	 */
    public void insertStplatCn(InfoStplatManageVO vo) throws Exception {

        insert("InfoStplatManageDAO.insertStplatCn", vo);

    }

	/**
	 * 약관정보 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    public void updateStplatCn(InfoStplatManageVO vo) throws Exception {

        update("InfoStplatManageDAO.updateStplatCn", vo);

    }

	/**
	 * 약관정보 글을 삭제한다.
	 * @param vo
	 * @exception Exception
	 */
    public void deleteStplatCn(InfoStplatManageVO vo) throws Exception {

        delete("InfoStplatManageDAO.deleteStplatCn", vo);

    }

}
