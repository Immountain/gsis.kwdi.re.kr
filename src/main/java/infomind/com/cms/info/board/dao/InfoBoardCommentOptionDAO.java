package infomind.com.cms.info.board.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.board.vo.InfoBoardCommentOptionVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoBoardCommentOptionDAO")
public class InfoBoardCommentOptionDAO extends EgovComAbstractDAO {

    public InfoBoardCommentOptionVO selectBoardCommentOption(InfoBoardCommentOptionVO vo) throws Exception {
        return selectOne("InfoBoardCommentOptionDAO.selectBoardCommentOption", vo);
    }

    public Integer selectBoardCommentOptionTotalCount(InfoBoardCommentOptionVO vo) throws Exception {
        return selectOne("InfoBoardCommentOptionDAO.selectBoardCommentOptionTotalCount", vo);
    }

    public List<?> selectBoardCommentOptionList(InfoBoardCommentOptionVO vo) throws Exception {
        return selectList("InfoBoardCommentOptionDAO.selectBoardCommentOptionList", vo);
    }

    public void insertBoardCommentOption(InfoBoardCommentOptionVO vo) throws Exception {
        insert("InfoBoardCommentOptionDAO.insertBoardCommentOption", vo);
    }

    public void updateBoardCommentOption(InfoBoardCommentOptionVO vo) throws Exception {
        insert("InfoBoardCommentOptionDAO.updateBoardCommentOption", vo);
    }
}
