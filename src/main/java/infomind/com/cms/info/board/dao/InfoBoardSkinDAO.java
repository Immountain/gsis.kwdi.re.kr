package infomind.com.cms.info.board.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.board.vo.InfoBoardSkinVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoBoardSkinDAO")
public class InfoBoardSkinDAO extends EgovComAbstractDAO {

    public InfoBoardSkinVO selectBoardSkin(InfoBoardSkinVO vo) throws Exception {
        return selectOne("InfoBoardSkinDAO.selectBoardSkin", vo);
    }

    public Integer selectBoardSkinTotalCount(InfoBoardSkinVO vo) throws Exception {
        return selectOne("InfoBoardSkinDAO.selectBoardSkinTotalCount", vo);
    }

    public List<?> selectBoardSkinList(InfoBoardSkinVO vo) throws Exception {
        return selectList("InfoBoardSkinDAO.selectBoardSkinList", vo);
    }

    public void insertBoardSkin(InfoBoardSkinVO vo) throws Exception {
        insert("InfoBoardSkinDAO.insertBoardSkin", vo);
    }

    public void updateBoardSkin(InfoBoardSkinVO vo) throws Exception {
        insert("InfoBoardSkinDAO.updateBoardSkin", vo);
    }

}
