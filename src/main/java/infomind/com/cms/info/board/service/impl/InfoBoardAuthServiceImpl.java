package infomind.com.cms.info.board.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.board.dao.InfoBoardAuthDAO;
import infomind.com.cms.info.board.service.InfoBoardAuthService;
import infomind.com.cms.info.board.vo.InfoBoardAuthConfigVO;
import infomind.com.cms.info.board.vo.InfoBoardAuthVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("InfoBoardAuthService")
public class InfoBoardAuthServiceImpl extends EgovAbstractServiceImpl implements InfoBoardAuthService {

    @Resource(name = "InfoBoardAuthIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="InfoBoardAuthDAO")
    private InfoBoardAuthDAO infoBoardAuthDAO;

    @Override
    public List<?> selectInfoBoardAuthConfigList(InfoBoardAuthVO vo) throws Exception {
        return infoBoardAuthDAO.selectBoardAuthConfigMap(vo);
    }

    @Override
    public List<?> selectInfoBoardAuthList(InfoBoardAuthVO vo) throws Exception {
        return infoBoardAuthDAO.selectBoardAuthList(vo);
    }

    @Override
    public Integer selectInfoBoardAuthTotalCount(InfoBoardAuthVO vo) throws Exception {
        return infoBoardAuthDAO.selectBoardAuthTotalCount(vo);
    }

    @Override
    public void insertBoardAuth(InfoBoardAuthVO vo) throws Exception {
        infoBoardAuthDAO.insertBoardAuth(vo);
    }

    @Override
    public InfoBoardAuthVO selectBoardAuth(InfoBoardAuthVO vo) throws Exception {
        return infoBoardAuthDAO.selectBoardAuth(vo);
    }

    @Transactional
    @Override
    public void updateConfigList(String boardId, List<InfoBoardAuthConfigVO> list) throws Exception {
        infoBoardAuthDAO.deleteBoardAuthConfig(boardId);

        List<InfoBoardAuthVO> infoBoardAuthVOS = new ArrayList<>();
        for(InfoBoardAuthConfigVO vo : list) {
            if(vo.isList()) {
                infoBoardAuthVOS.add(new InfoBoardAuthVO(vo, idgenService.getNextIntegerId(), "list"));
            }
            if(vo.isRead()) {
                infoBoardAuthVOS.add(new InfoBoardAuthVO(vo, idgenService.getNextIntegerId(), "read"));
            }
            if(vo.isWrite()) {
                infoBoardAuthVOS.add(new InfoBoardAuthVO(vo, idgenService.getNextIntegerId(), "write"));
            }
            if(vo.isComment()) {
                infoBoardAuthVOS.add(new InfoBoardAuthVO(vo, idgenService.getNextIntegerId(), "comment"));
            }
            if(vo.isReply()) {
                infoBoardAuthVOS.add(new InfoBoardAuthVO(vo, idgenService.getNextIntegerId(), "reply"));
            }
            if(vo.isNotice()) {
                infoBoardAuthVOS.add(new InfoBoardAuthVO(vo, idgenService.getNextIntegerId(), "notice"));
            }
        }
        for(InfoBoardAuthVO v : infoBoardAuthVOS) {
            infoBoardAuthDAO.insertBoardAuth(v);
        }
    }

    @Override
    public InfoBoardAuthVO selectBoardAuthInfo(InfoBoardAuthVO vo) throws Exception {
        return infoBoardAuthDAO.selectBoardAuthInfo(vo);
    }
}
