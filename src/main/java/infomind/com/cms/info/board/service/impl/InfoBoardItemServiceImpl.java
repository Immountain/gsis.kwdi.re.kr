package infomind.com.cms.info.board.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.board.dao.InfoBoardItemDAO;
import infomind.com.cms.info.board.service.InfoBoardItemHisService;
import infomind.com.cms.info.board.service.InfoBoardItemService;
import infomind.com.cms.info.board.vo.InfoBoardItemHisVO;
import infomind.com.cms.info.board.vo.InfoBoardItemProcDto;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("InfoBoardItemService")
public class InfoBoardItemServiceImpl extends EgovAbstractServiceImpl implements InfoBoardItemService {


    @Resource(name = "egovBBSMstrIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="InfoBoardItemDAO")
    private InfoBoardItemDAO infoBoardItemDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;


    @Resource(name="InfoBoardItemHisService")
    private InfoBoardItemHisService infoBoardItemHisService;


    @Override
    public List<?> selectInfoBoardItemList(InfoBoardItemVO vo) throws Exception {
        return infoBoardItemDAO.selectInfoBoardItemList(vo);
    }

    @Override
    public Integer selectInfoBoardItemTotalCount(InfoBoardItemVO vo) throws Exception {
        return infoBoardItemDAO.selectInfoBoardItemTotalCount(vo);
    }

    @Override
    public void insertBoardItem(InfoBoardItemVO vo) throws Exception {

        vo.setItemId(idgenService.getNextStringId());
        vo.setIdCode(vo.getItemId());

        infoBoardItemDAO.insertBoardItem(vo);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getBoardFile());
        infoFileMngUtil.copyFile(vo.getFile01(),"Y");

        vo.setModType("I");

        //히스토리 저장
        infoBoardItemHisService.getInsertBoardItemHis(vo);

    }

    @Override
    public InfoBoardItemVO selectBoardItem(InfoBoardItemVO vo) throws Exception {

        InfoBoardItemVO boardItemVO = new InfoBoardItemVO();

        boardItemVO =infoBoardItemDAO.selectBoardItem(vo);

//        if(boardItemVO.getBoardFile()!=null || !"".equals(boardItemVO.getBoardFile())){
//
//            InfoFileDetailVO file = new InfoFileDetailVO();
//            file.setAtchFileId(boardItemVO.getBoardFile());
//
//            List<InfoFileDetailVO> fileList = new ArrayList<>();
//
//            fileList =infoFileService.getInfoFileList(file);
//            boardItemVO.setBoardFileList(fileList);
//        }




        return boardItemVO;
    }

    @Override
    public List<?> selectInfoBoardItemNoticeList(InfoBoardItemVO vo) throws Exception {
        return infoBoardItemDAO.selectInfoBoardItemNoticeList(vo);
    }

    @Override
    public List<?> getInfoBoardItemNoticeYnList(InfoBoardItemVO vo) throws Exception {
        return infoBoardItemDAO.getInfoBoardItemNoticeYnList(vo);
    }

    @Override
    public void updateBoardReadCnt(InfoBoardItemVO vo) throws Exception {

        infoBoardItemDAO.updateBoardReadCnt(vo);
    }

    @Override
    public void updateBoardItem(InfoBoardItemVO vo) throws Exception {
        infoBoardItemDAO.updateBoardItem(vo);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getBoardFile());

        infoFileMngUtil.copyFile(vo.getFile01(),"Y");

        vo.setModType("U");
        //히스토리 저장
        infoBoardItemHisService.getInsertBoardItemHis(vo);
    }

    @Override
    public void deleteBoardItem(InfoBoardItemVO vo) throws Exception {
        infoBoardItemDAO.deleteBoardItemId(vo);

    }

    @Override
    public void boardItemProc(InfoBoardItemProcDto dto) throws Exception {
        List<InfoBoardItemVO> list = dto.getList();
        switch (dto.getCmd()) {
            case "delete":
                for (InfoBoardItemVO v : list) {
                    v = infoBoardItemDAO.selectBoardItem(v);
                    v.setDeleteType("A");
                    v.setDeleteYn("Y");
                    infoBoardItemDAO.deleteBoardItemId(v);

                    v.setModType("delete");
                    //히스토리 저장
                    infoBoardItemHisService.getInsertBoardItemHis(v);

                }
                break;
            case "restore":
                for (InfoBoardItemVO v : list) {
                    v = infoBoardItemDAO.selectBoardItem(v);
                    v.setDeleteYn("N");
                    infoBoardItemDAO.restoreBoardItemId(v);

                    v.setModType("restore");
                    //히스토리 저장
                    infoBoardItemHisService.getInsertBoardItemHis(v);

                }
                break;
            case "deleteReal":
                throw new Exception("준비되지 않은 이벤트입니다.");
            case "copy":
                for (InfoBoardItemVO v : list) {
                    v = infoBoardItemDAO.selectBoardItem(v);
                    v.setBoardId(dto.getTargetBoardId());
                    this.insertBoardItem(v);

                    v.setModType("copy");
                    //히스토리 저장
                    infoBoardItemHisService.getInsertBoardItemHis(v);
                }
                break;
            case "move":
                for (InfoBoardItemVO v : list) {
                    v = infoBoardItemDAO.selectBoardItem(v);
                    v.setBoardId(dto.getTargetBoardId());
                    this.updateBoardItem(v);

                    v.setModType("move");
                    //히스토리 저장
                    infoBoardItemHisService.getInsertBoardItemHis(v);


                }
                break;
        }
    }
}
