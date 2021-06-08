package infomind.com.cms.info.board.dao;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.board.vo.InfoBoardItemHisVO;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("InfoBoardItemHisDAO")
public class InfoBoardItemHisDAO extends EgovComAbstractDAO {

            //총카운터수 
    public InfoBoardItemHisVO selectBoardItemHis(InfoBoardItemHisVO vo) throws Exception {
        return selectOne("InfoBoardItemHisDAO.selectBoardItemHis", vo);
    }

    public Integer selectBoardItemHisTotalCount(InfoBoardItemHisVO vo) throws Exception {
        return selectOne("InfoBoardItemHisDAO.selectBoardItemHisTotalCount", vo);
    }

    public List<?> selectBoardItemHisList(InfoBoardItemHisVO vo) throws Exception {
        return selectList("InfoBoardItemHisDAO.selectBoardItemHisList", vo);
    }

    public void insertBoardItemHis(InfoBoardItemHisVO vo) throws Exception {
        insert("InfoBoardItemHisDAO.insertBoardItemHis", vo);
    }

    public void getInsertBoardItemHis(InfoBoardItemVO vo) throws Exception {
        insert("InfoBoardItemHisDAO.insertBoardItemHis", vo);
    }
}
