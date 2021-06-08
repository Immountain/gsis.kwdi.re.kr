package infomind.com.cms.info.board.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.board.vo.InfoBoardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoBoardDAO")
public class InfoBoardDAO extends EgovComAbstractDAO {

    public InfoBoardVO selectBoard(InfoBoardVO vo) throws Exception {
        return selectOne("InfoBoardDAO.selectBoard", vo);
    }

    public Integer selectBoardTotalCount(InfoBoardVO vo) throws Exception {
        return selectOne("InfoBoardDAO.selectBoardTotalCount", vo);
    }

    public List<?> selectBoardList(InfoBoardVO vo) throws Exception {
        return selectList("InfoBoardDAO.selectBoardList", vo);
    }

    public void insertBoard(InfoBoardVO vo) throws Exception {
        insert("InfoBoardDAO.insertBoard", vo);
    }

    public void updateBoard(InfoBoardVO vo) throws Exception {
        insert("InfoBoardDAO.updateBoard", vo);
    }

}
