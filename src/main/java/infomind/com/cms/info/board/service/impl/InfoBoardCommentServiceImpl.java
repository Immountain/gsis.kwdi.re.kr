package infomind.com.cms.info.board.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.board.dao.InfoBoardCommentDAO;
import infomind.com.cms.info.board.service.InfoBoardCommentService;
import infomind.com.cms.info.board.vo.InfoBoardCommentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoBoardCommentService")
public class InfoBoardCommentServiceImpl extends EgovAbstractServiceImpl implements InfoBoardCommentService {


    @Resource(name="InfoBoardCommentDAO")
    private InfoBoardCommentDAO infoBoardCommentDAO;


    @Override
    public List<?> selectInfoBoardComment(InfoBoardCommentVO vo) throws Exception {
        return infoBoardCommentDAO.selectInfoBoardComment(vo);
    }

    @Override
    public void insertInfoBoardComment(InfoBoardCommentVO vo) throws Exception {
        infoBoardCommentDAO.insertInfoBoardComment(vo);

    }

    @Override
    public void deleteInfoBoardComment(InfoBoardCommentVO vo) throws Exception {
        infoBoardCommentDAO.deleteInfoBoardComment(vo);

    }
}
