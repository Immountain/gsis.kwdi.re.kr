package infomind.com.cms.info.board.service;


import infomind.com.cms.info.board.vo.InfoBoardItemVO;
import infomind.com.cms.info.board.vo.InfoBoardVO;

import java.util.List;

public interface InfoBoardService {
    InfoBoardVO selectBoard(InfoBoardVO vo) throws Exception;

    Integer selectBoardTotalCount(InfoBoardVO vo) throws Exception;

    List<?> selectBoardList(InfoBoardVO vo) throws Exception;

    void insertBoard(InfoBoardVO vo) throws Exception;

    void updateBoard(InfoBoardVO vo) throws Exception;


}
