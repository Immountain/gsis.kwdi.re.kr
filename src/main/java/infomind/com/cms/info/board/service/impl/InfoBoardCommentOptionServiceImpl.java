package infomind.com.cms.info.board.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.board.dao.InfoBoardCommentOptionDAO;
import infomind.com.cms.info.board.service.InfoBoardCommentOptionService;
import infomind.com.cms.info.board.vo.InfoBoardCommentOptionVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoBoardCommentOptionService")
public class InfoBoardCommentOptionServiceImpl extends EgovAbstractServiceImpl implements InfoBoardCommentOptionService {

    @Resource(name="InfoBoardCommentOptionDAO")
    private InfoBoardCommentOptionDAO infoBoardCommentOptionDAO;

    @Override
    public List<?> selectInfoBoardCommentOptionList(InfoBoardCommentOptionVO vo) throws Exception {
        return infoBoardCommentOptionDAO.selectBoardCommentOptionList(vo);
    }

    @Override
    public Integer selectInfoBoardCommentOptionTotalCount(InfoBoardCommentOptionVO vo) throws Exception {
        return infoBoardCommentOptionDAO.selectBoardCommentOptionTotalCount(vo);
    }

    @Override
    public void insertBoardCommentOption(InfoBoardCommentOptionVO vo) throws Exception {
        infoBoardCommentOptionDAO.insertBoardCommentOption(vo);
    }

    @Override
    public void updateBoardCommentOption(InfoBoardCommentOptionVO vo) throws Exception {
        infoBoardCommentOptionDAO.updateBoardCommentOption(vo);
    }

    @Override
    public InfoBoardCommentOptionVO selectBoardCommentOption(InfoBoardCommentOptionVO vo) throws Exception {
        return infoBoardCommentOptionDAO.selectBoardCommentOption(vo);
    }

    @Override
    public void save(InfoBoardCommentOptionVO vo) throws Exception {
        if(vo.is__modified__()) {
            infoBoardCommentOptionDAO.updateBoardCommentOption(vo);
        }else{
            infoBoardCommentOptionDAO.insertBoardCommentOption(vo);
        }
    }

}
