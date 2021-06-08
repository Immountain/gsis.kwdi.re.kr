package infomind.com.cms.info.board.service;
import infomind.com.cms.info.board.vo.InfoBoardItemHisVO;
import infomind.com.cms.info.board.vo.InfoBoardItemVO;

import java.util.List;

public interface InfoBoardItemHisService {
      InfoBoardItemHisVO selectBoardItemHis(InfoBoardItemHisVO vo) throws Exception;

      Integer selectBoardItemHisTotalCount(InfoBoardItemHisVO vo) throws Exception ;

      List<?> selectBoardItemHisList(InfoBoardItemHisVO vo) throws Exception ;

      void insertBoardItemHis(InfoBoardItemHisVO vo) throws Exception ;
      void getInsertBoardItemHis(InfoBoardItemVO vo) throws Exception ;

}
