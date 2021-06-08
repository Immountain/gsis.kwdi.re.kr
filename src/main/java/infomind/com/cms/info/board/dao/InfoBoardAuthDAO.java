package infomind.com.cms.info.board.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.board.vo.InfoBoardAuthConfigVO;
import infomind.com.cms.info.board.vo.InfoBoardAuthVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoBoardAuthDAO")
public class InfoBoardAuthDAO extends EgovComAbstractDAO {

    public List<InfoBoardAuthConfigVO> selectBoardAuthConfigMap(InfoBoardAuthVO vo) throws Exception {
        return selectList("selectBoardAuthConfigMap", vo);
    }
    public InfoBoardAuthVO selectBoardAuth(InfoBoardAuthVO vo) throws Exception {
        return selectOne("InfoBoardAuthDAO.selectBoardAuth", vo);
    }

    public Integer selectBoardAuthTotalCount(InfoBoardAuthVO vo) throws Exception {
        return selectOne("InfoBoardAuthDAO.selectBoardAuthTotalCount", vo);
    }

    public List<?> selectBoardAuthList(InfoBoardAuthVO vo) throws Exception {
        return selectList("InfoBoardAuthDAO.selectBoardAuthList", vo);
    }

    public void insertBoardAuth(InfoBoardAuthVO vo) throws Exception {
        insert("InfoBoardAuthDAO.insertBoardAuth", vo);
    }

    public void updateBoardAuth(InfoBoardAuthVO vo) throws Exception {
        insert("InfoBoardAuthDAO.updateBoardAuth", vo);
    }

    public void deleteBoardAuthConfig(String boardId) {
        delete("InfoBoardAuthDAO.deleteBoardAuthConfig", boardId);
    }


    public InfoBoardAuthVO selectBoardAuthInfo(InfoBoardAuthVO vo) throws Exception {
        return selectOne("InfoBoardAuthDAO.selectBoardAuthInfo", vo);
    }



}
