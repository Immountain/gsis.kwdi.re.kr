package infomind.com.cms.sec.rgm.dao;


import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sec.rgm.service.AuthorGroup;
import infomind.com.cms.sec.rgm.vo.InfoAuthorGroup;
import infomind.com.cms.sec.rgm.vo.InfoAuthorGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoAuthorGroupDAO")
public class InfoAuthorGroupDAO extends EgovComAbstractDAO {

    @SuppressWarnings("unchecked")
    public List<InfoAuthorGroupVO> selectInfoAuthorGroupList(InfoAuthorGroupVO infoAuthorGroupVO) throws Exception {
        return (List<InfoAuthorGroupVO>) list("InfoAuthorGroupDAO.selectInfoAuthorGroupList", infoAuthorGroupVO);
    }

    public int selectInfoAuthorGroupListTotCnt(InfoAuthorGroupVO infoAuthorGroupVO) throws Exception {
        return (Integer) selectOne("InfoAuthorGroupDAO.selectInfoAuthorGroupListTotCnt", infoAuthorGroupVO);

    }

    /**
     * 그룹에 권한정보를 할당하여 데이터베이스에 등록
     *
     * @param infoAuthorGroup InfoAuthorGroup
     * @throws Exception
     */
    public void insertInfoAuthorGroup(InfoAuthorGroup infoAuthorGroup) throws Exception {
        insert("InfoAuthorGroupDAO.insertInfoAuthorGroup", infoAuthorGroup);
    }

    public void updateInfoAuthorGroup(InfoAuthorGroup infoAuthorGroup) throws Exception {
        update("InfoAuthorGroupDAO.updateInfoAuthorGroup", infoAuthorGroup);
    }

    public void deleteInfoAuthorGroup(InfoAuthorGroup infoAuthorGroup) throws Exception {
        delete("InfoAuthorGroupDAO.deleteInfoAuthorGroup", infoAuthorGroup);

    }
}
