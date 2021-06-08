package infomind.com.cms.info.board.service;

import infomind.com.cms.info.board.vo.InfoBoardCommentVO;

import java.util.List;

public interface InfoBoardCommentService {


     List<?> selectInfoBoardComment(InfoBoardCommentVO vo) throws Exception ;
     void insertInfoBoardComment(InfoBoardCommentVO vo) throws Exception ;
     void deleteInfoBoardComment(InfoBoardCommentVO vo) throws Exception ;

}
