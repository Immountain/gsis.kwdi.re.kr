

package infomind.com.cms.sec.ram.dao;


import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.sec.ram.vo.InfoAuthorManage;
import infomind.com.cms.sec.ram.vo.InfoAuthorManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoAuthorManageDAO")
public class InfoAuthorManageDAO extends EgovComAbstractDAO {
    @SuppressWarnings("unchecked")
    public List<InfoAuthorManageVO> selectInfoAuthorList(InfoAuthorManageVO infoAuthorManageVO) throws Exception {
        return (List<InfoAuthorManageVO>) list("InfoAuthorManageDAO.selectInfoAuthorList", infoAuthorManageVO);
    }

    /**
     * 권한을 등록한다.
     * @param infoAuthorManage InfoAuthorManage
     * @exception Exception
     */
    public void insertInfoAuthor(InfoAuthorManage infoAuthorManage) throws Exception {
        insert("InfoAuthorManageDAO.insertInfoAuthor", infoAuthorManage);
    }

    /**
     * 권한목록 총 갯수를 조회한다.
     * @param infoAuthorManageVO InfoAuthorManageVO
     * @return int
     * @exception Exception
     */
    public int selectInfoAuthorListTotCnt(InfoAuthorManageVO infoAuthorManageVO)  throws Exception {
        return (Integer) selectOne("InfoAuthorManageDAO.selectInfoAuthorListTotCnt", infoAuthorManageVO);
    }

    /**
     * 권한을 수정한다.
     * @param infoAuthorManage InfoAuthorManage
     * @exception Exception
     */
    public void updateInfoAuthor(InfoAuthorManage infoAuthorManage) throws Exception {
        update("InfoAuthorManageDAO.updateInfoAuthor", infoAuthorManage);
    }

    /**
     * 권한을 삭제한다.
     * @param infoAuthorManage InfoAuthorManage
     * @exception Exception
     */
    public void deleteInfoAuthor(InfoAuthorManage infoAuthorManage) throws Exception {
        delete("InfoAuthorManageDAO.deleteInfoAuthor", infoAuthorManage);
    }

    /**
     * 권한을 조회한다.
     * @param infoAuthorManageVO InfoAuthorManageVO
     * @return InfoAuthorManageVO
     * @exception Exception
     */
    public InfoAuthorManageVO selectInfoAuthor(InfoAuthorManageVO infoAuthorManageVO) throws Exception {
        return (InfoAuthorManageVO) selectOne("InfoAuthorManageDAO.selectInfoAuthor", infoAuthorManageVO);
    }

    @SuppressWarnings("unchecked")
    public List<InfoAuthorManageVO> selectInfoAuthorAllList(InfoAuthorManageVO infoAuthorManageVO) throws Exception {
        return (List<InfoAuthorManageVO>) list("InfoAuthorManageDAO.selectInfoAuthorAllList", infoAuthorManageVO);

    }
}
