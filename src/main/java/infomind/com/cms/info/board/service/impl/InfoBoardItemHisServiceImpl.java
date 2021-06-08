package infomind.com.cms.info.board.service.impl;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.board.dao.InfoBoardItemHisDAO;
import infomind.com.cms.info.board.service.InfoBoardItemHisService;
import infomind.com.cms.info.board.vo.InfoBoardItemHisVO;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service("InfoBoardItemHisService")
public class InfoBoardItemHisServiceImpl extends EgovAbstractServiceImpl implements InfoBoardItemHisService {

    @Resource(name="InfoBoardItemHisDAO")
    private InfoBoardItemHisDAO infoBoardItemHisDAO;


    @Resource(name = "ItemIdHisSnoIdService")
    private EgovIdGnrService idgenService;




    @Override
    public InfoBoardItemHisVO selectBoardItemHis(InfoBoardItemHisVO vo) throws Exception {
        return infoBoardItemHisDAO.selectBoardItemHis(vo);
    }

    @Override
    public Integer selectBoardItemHisTotalCount(InfoBoardItemHisVO vo) throws Exception {
        return infoBoardItemHisDAO.selectBoardItemHisTotalCount(vo);
    }

    @Override
    public List<?> selectBoardItemHisList(InfoBoardItemHisVO vo) throws Exception {
        return infoBoardItemHisDAO.selectBoardItemHisList(vo);
    }

    @Override
    public void insertBoardItemHis(InfoBoardItemHisVO vo) throws Exception {
        infoBoardItemHisDAO.insertBoardItemHis(vo);
    }

    @Override
    public void getInsertBoardItemHis(InfoBoardItemVO vo) throws Exception {

        String sno =idgenService.getNextStringId();
        vo.setItemIdHis(sno);
        infoBoardItemHisDAO.getInsertBoardItemHis(vo);
    }
}
