package infomind.com.cms.info.board.dao;


import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;
import infomind.com.cms.info.board.vo.InfoBoardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoBoardItemDAO")
public class InfoBoardItemDAO extends EgovComAbstractDAO {



    //관리자 영역
    public List<?> selectInfoBoardItemList(InfoBoardItemVO vo) throws Exception {
        return selectList("InfoBoardItemDAO.selectInfoBoardItemList", vo);
    }

    public Integer selectInfoBoardItemTotalCount(InfoBoardItemVO vo) throws Exception {
        return selectOne("InfoBoardItemDAO.selectInfoBoardItemTotalCount", vo);
    }

    public void insertBoardItem(InfoBoardItemVO vo) throws Exception {
        insert("InfoBoardItemDAO.insertBoardItem", vo);
    }



    public InfoBoardItemVO selectBoardItem(InfoBoardItemVO vo) throws Exception {
        return selectOne("InfoBoardItemDAO.selectBoardItem", vo);
    }

    //공지
    public List<?> selectInfoBoardItemNoticeList(InfoBoardItemVO vo) throws Exception {
        return selectList("InfoBoardItemDAO.selectInfoBoardItemNoticeList", vo);
    }

    //공지 공지여부 관련
    public List<?> getInfoBoardItemNoticeYnList(InfoBoardItemVO vo) throws Exception {
        return selectList("InfoBoardItemDAO.selectInfoBoardItemNoticeYnList", vo);
    }





    public void updateBoardReadCnt(InfoBoardItemVO vo) throws Exception {
        update("InfoBoardItemDAO.updateBoardReadCnt", vo);
    }


    public void updateBoardItem(InfoBoardItemVO vo) throws Exception {
        update("InfoBoardItemDAO.updateBoardItem", vo);
    }


    public void deleteBoardItemId(InfoBoardItemVO vo) {
        update("InfoBoardItemDAO.deleteBoardItemId", vo);
    }

    public void restoreBoardItemId(InfoBoardItemVO vo) {
        update("InfoBoardItemDAO.restoreBoardItemId", vo);
    }
}
