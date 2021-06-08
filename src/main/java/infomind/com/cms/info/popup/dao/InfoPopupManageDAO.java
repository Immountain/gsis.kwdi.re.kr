package infomind.com.cms.info.popup.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.popup.vo.InfoPopupManageVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoPopupManageDAO")
public class InfoPopupManageDAO extends EgovComAbstractDAO {

    public InfoPopupManageVO selectPopupManage(InfoPopupManageVO vo) throws Exception {
        return selectOne("InfoPopupManageDAO.selectPopupManage", vo);
    }

    public Integer selectPopupManageTotalCount(InfoPopupManageVO vo) throws Exception {
        return selectOne("InfoPopupManageDAO.selectPopupManageTotalCount", vo);
    }

    public List<?> selectPopupManageList(InfoPopupManageVO vo) throws Exception {
        return selectList("InfoPopupManageDAO.selectPopupManageList", vo);
    }

    public void insertPopupManage(InfoPopupManageVO vo) throws Exception {
        insert("InfoPopupManageDAO.insertPopupManage", vo);
    }

    public void updatePopupManage(InfoPopupManageVO vo) throws Exception {
        insert("InfoPopupManageDAO.updatePopupManage", vo);
    }



    public List<InfoPopupManageVO> getPopList(InfoPopupManageVO vo) {
        return selectList("InfoPopupManageDAO.getPopList", vo);
    }


}
