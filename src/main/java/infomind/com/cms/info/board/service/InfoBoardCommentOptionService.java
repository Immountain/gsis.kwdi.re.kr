package infomind.com.cms.info.board.service;

import infomind.com.cms.info.board.vo.InfoBoardCommentOptionVO;

import java.util.List;

public interface InfoBoardCommentOptionService {

    List<?> selectInfoBoardCommentOptionList(InfoBoardCommentOptionVO vo) throws Exception ;

    Integer selectInfoBoardCommentOptionTotalCount(InfoBoardCommentOptionVO vo) throws Exception ;

    void insertBoardCommentOption(InfoBoardCommentOptionVO vo) throws Exception;

    void updateBoardCommentOption(InfoBoardCommentOptionVO vo) throws Exception;

    InfoBoardCommentOptionVO selectBoardCommentOption(InfoBoardCommentOptionVO vo) throws Exception;

    void save(InfoBoardCommentOptionVO vo) throws Exception;
}
