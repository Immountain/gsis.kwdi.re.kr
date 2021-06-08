package infomind.com.cms.info.board.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.board.vo.InfoBoardCategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoBoardCategoryDAO")
public class InfoBoardCategoryDAO extends EgovComAbstractDAO {

    public InfoBoardCategoryVO selectBoardCategory(InfoBoardCategoryVO vo) throws Exception {
        return selectOne("InfoBoardCategoryDAO.selectBoardCategory", vo);
    }

    public Integer selectBoardCategoryTotalCount(InfoBoardCategoryVO vo) throws Exception {
        return selectOne("InfoBoardCategoryDAO.selectBoardCategoryTotalCount", vo);
    }

    public List<?> selectBoardCategoryList(InfoBoardCategoryVO vo) throws Exception {
        return selectList("InfoBoardCategoryDAO.selectBoardCategoryList", vo);
    }

    public void insertBoardCategory(InfoBoardCategoryVO vo) throws Exception {
        insert("InfoBoardCategoryDAO.insertBoardCategory", vo);
    }

    public void updateBoardCategory(InfoBoardCategoryVO vo) throws Exception {
        insert("InfoBoardCategoryDAO.updateBoardCategory", vo);
    }


    public List<?> selectBoardCategoryDepthList(InfoBoardCategoryVO vo) throws Exception {
        return selectList("InfoBoardCategoryDAO.selectBoardCategoryDepthList", vo);
    }


}
