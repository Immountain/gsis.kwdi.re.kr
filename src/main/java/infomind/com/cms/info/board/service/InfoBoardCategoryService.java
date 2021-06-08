package infomind.com.cms.info.board.service;

import infomind.com.cms.info.board.vo.InfoBoardCategoryVO;

import java.util.List;

public interface InfoBoardCategoryService {


    //관리자 영역
    List<?> selectInfoBoardCategoryList(InfoBoardCategoryVO vo) throws Exception ;

    Integer selectInfoBoardCategoryTotalCount(InfoBoardCategoryVO vo) throws Exception ;


    void insertBoardCategory(InfoBoardCategoryVO vo) throws Exception;

    InfoBoardCategoryVO selectBoardCategory(InfoBoardCategoryVO vo) throws Exception;

    void updateList(List<InfoBoardCategoryVO> list) throws Exception;


    List<?> selectBoardCategoryDepthList(InfoBoardCategoryVO vo) throws Exception ;

}
