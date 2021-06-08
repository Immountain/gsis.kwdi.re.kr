package infomind.com.cms.info.board.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.board.dao.InfoBoardDAO;
import infomind.com.cms.info.board.service.InfoBoardService;
import infomind.com.cms.info.board.vo.InfoBoardVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoBoardService")
public class InfoBoardServiceImpl extends EgovAbstractServiceImpl  implements InfoBoardService {

    @Resource(name="InfoBoardDAO")
    private InfoBoardDAO infoBoardDAO;

    @Override
    public InfoBoardVO selectBoard(InfoBoardVO vo) throws Exception {
        return infoBoardDAO.selectBoard(vo);
    }

    @Override
    public Integer selectBoardTotalCount(InfoBoardVO vo) throws Exception {
        return infoBoardDAO.selectBoardTotalCount(vo);
    }

    @Override
    public List<?> selectBoardList(InfoBoardVO vo) throws Exception {
        return infoBoardDAO.selectBoardList(vo);
    }

    @Override
    public void insertBoard(InfoBoardVO vo) throws Exception {
        infoBoardDAO.insertBoard(vo);
    }

    @Override
    public void updateBoard(InfoBoardVO vo) throws Exception {
        infoBoardDAO.updateBoard(vo);
    }
}
