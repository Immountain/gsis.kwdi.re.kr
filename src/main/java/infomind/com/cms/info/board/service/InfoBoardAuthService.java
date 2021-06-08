package infomind.com.cms.info.board.service;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import infomind.com.cms.info.board.vo.InfoBoardAuthConfigVO;
import infomind.com.cms.info.board.vo.InfoBoardAuthVO;

import java.util.List;

public interface InfoBoardAuthService {


    //관리자 영역
    List<?> selectInfoBoardAuthConfigList(InfoBoardAuthVO vo) throws Exception ;

    List<?> selectInfoBoardAuthList(InfoBoardAuthVO vo) throws Exception ;

    Integer selectInfoBoardAuthTotalCount(InfoBoardAuthVO vo) throws Exception ;


    void insertBoardAuth(InfoBoardAuthVO vo) throws Exception;

    InfoBoardAuthVO selectBoardAuth(InfoBoardAuthVO vo) throws Exception;

    void updateConfigList(String boardId, List<InfoBoardAuthConfigVO> list) throws Exception;


    InfoBoardAuthVO selectBoardAuthInfo(InfoBoardAuthVO vo) throws Exception ;

}
