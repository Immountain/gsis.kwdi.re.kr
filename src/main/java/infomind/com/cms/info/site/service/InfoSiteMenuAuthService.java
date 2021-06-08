package infomind.com.cms.info.site.service;

import infomind.com.cms.info.board.vo.InfoBoardAuthVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthConfigVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthVO;

import java.util.List;

public interface InfoSiteMenuAuthService {

    //관리자 영역
    public List<InfoSiteMenuAuthConfigVO> selectInfoMenuAuthConfigList(InfoSiteMenuAuthVO vo) throws Exception ;
    void updateConfigList(String siteMenuId, List<InfoSiteMenuAuthConfigVO> list) throws Exception;

    public InfoSiteMenuAuthVO selectSiteMenuAuth(InfoSiteMenuAuthVO vo) throws Exception;

    public Integer selectSiteMenuAuthTotalCount(InfoSiteMenuAuthVO vo) throws Exception;

    public List<?> selectSiteMenuAuthList(InfoSiteMenuAuthVO vo) throws Exception;

    public void insertSiteMenuAuth(InfoSiteMenuAuthVO vo) throws Exception ;

    public void updateSiteMenuAuth(InfoSiteMenuAuthVO vo) throws Exception;
}
