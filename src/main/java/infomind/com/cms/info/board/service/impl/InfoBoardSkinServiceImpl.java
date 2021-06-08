package infomind.com.cms.info.board.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.board.dao.InfoBoardSkinDAO;
import infomind.com.cms.info.board.service.InfoBoardSkinService;
import infomind.com.cms.info.board.vo.InfoBoardSkinVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoBoardSkinService")
public class InfoBoardSkinServiceImpl extends EgovAbstractServiceImpl implements InfoBoardSkinService {

    @Resource(name="InfoBoardSkinDAO")
    private InfoBoardSkinDAO infoBoardSkinDAO;

    @Override
    public InfoBoardSkinVO selectBoardSkin(InfoBoardSkinVO vo) throws Exception {
        return infoBoardSkinDAO.selectBoardSkin(vo);
    }

    @Override
    public Integer selectBoardSkinTotalCount(InfoBoardSkinVO vo) throws Exception {
        return infoBoardSkinDAO.selectBoardSkinTotalCount(vo);
    }

    @Override
    public List<?> selectBoardSkinList(InfoBoardSkinVO vo) throws Exception {
        return infoBoardSkinDAO.selectBoardSkinList(vo);
    }

    @Override
    public void insertBoardSkin(InfoBoardSkinVO vo) throws Exception {
        infoBoardSkinDAO.insertBoardSkin(vo);
    }

    @Override
    public void updateBoardSkin(InfoBoardSkinVO vo) throws Exception {
        infoBoardSkinDAO.updateBoardSkin(vo);
    }
}
