package infomind.com.cms.info.popup.dao;


import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.popup.vo.InfoPopupGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoPopupGroupDAO")
public class InfoPopupGroupDAO extends EgovComAbstractDAO {

    public InfoPopupGroupVO selectPopupGroup(InfoPopupGroupVO vo) throws Exception {
        return selectOne("InfoPopupGroupDAO.selectPopupGroup", vo);
    }

    public Integer selectPopupGroupTotalCount(InfoPopupGroupVO vo) throws Exception {
        return selectOne("InfoPopupGroupDAO.selectPopupGroupTotalCount", vo);
    }

    public List<?> selectPopupGroupList(InfoPopupGroupVO vo) throws Exception {
        return selectList("InfoPopupGroupDAO.selectPopupGroupList", vo);
    }

    public void insertPopupGroup(InfoPopupGroupVO vo) throws Exception {
        insert("InfoPopupGroupDAO.insertPopupGroup", vo);
    }

    public void updatePopupGroup(InfoPopupGroupVO vo) throws Exception {
        insert("InfoPopupGroupDAO.updatePopupGroup", vo);
    }


}
