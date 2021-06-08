package infomind.com.cms.info.board.service;

import infomind.com.cms.info.banner.vo.InfoBannerVO;
import infomind.com.cms.info.board.vo.InfoBoardItemProcDto;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;

import java.util.List;

public interface InfoBoardItemService {


    //관리자 영역
    List<?> selectInfoBoardItemList(InfoBoardItemVO vo) throws Exception ;

    Integer selectInfoBoardItemTotalCount(InfoBoardItemVO vo) throws Exception ;


    void insertBoardItem(InfoBoardItemVO vo) throws Exception;

    InfoBoardItemVO selectBoardItem(InfoBoardItemVO vo) throws Exception;


    //공지
    List<?> selectInfoBoardItemNoticeList(InfoBoardItemVO vo) throws Exception ;

    //공지 공지여부 관련
    List<?> getInfoBoardItemNoticeYnList(InfoBoardItemVO vo) throws Exception ;




    void updateBoardReadCnt(InfoBoardItemVO vo) throws Exception ;


    void updateBoardItem(InfoBoardItemVO vo) throws Exception ;
    void deleteBoardItem(InfoBoardItemVO vo) throws Exception ;


    void boardItemProc(InfoBoardItemProcDto dto) throws Exception;
}
