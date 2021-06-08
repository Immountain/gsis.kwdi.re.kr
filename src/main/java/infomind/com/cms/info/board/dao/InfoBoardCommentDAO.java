package infomind.com.cms.info.board.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.board.vo.InfoBoardCommentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoBoardCommentDAO")
public class InfoBoardCommentDAO extends EgovComAbstractDAO {



    public List<?> selectInfoBoardComment(InfoBoardCommentVO vo) throws Exception {
        return selectList("InfoBoardCommentDAO.selectInfoBoardComment", vo);
    }


    public void insertInfoBoardComment(InfoBoardCommentVO vo) throws Exception {
        insert("InfoBoardCommentDAO.insertInfoBoardComment", vo);
    }


    public void deleteInfoBoardComment(InfoBoardCommentVO vo) throws Exception {
        delete("InfoBoardCommentDAO.deleteInfoBoardComment", vo);
    }

}
