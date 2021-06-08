package infomind.com.cms.info.board.service;


import infomind.com.cms.info.board.vo.InfoBoardSkinVO;

import java.util.List;

public interface InfoBoardSkinService {
    public InfoBoardSkinVO selectBoardSkin(InfoBoardSkinVO vo) throws Exception;

    public Integer selectBoardSkinTotalCount(InfoBoardSkinVO vo) throws Exception;

    public List<?> selectBoardSkinList(InfoBoardSkinVO vo) throws Exception;

    public void insertBoardSkin(InfoBoardSkinVO vo) throws Exception;

    public void updateBoardSkin(InfoBoardSkinVO vo) throws Exception;
}
