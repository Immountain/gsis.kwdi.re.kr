package infomind.com.cms.info.banner.service;

import infomind.com.cms.info.banner.vo.InfoBannerGroupVO;

import java.util.List;

public interface InfoBannerGroupService {
    public InfoBannerGroupVO selectBannerGroup(InfoBannerGroupVO vo) throws Exception;

    public Integer selectBannerGroupTotalCount(InfoBannerGroupVO vo) throws Exception;

    public List<?> selectBannerGroupList(InfoBannerGroupVO vo) throws Exception;

    public void insertBannerGroup(InfoBannerGroupVO vo) throws Exception;

    public void updateBannerGroup(InfoBannerGroupVO vo) throws Exception;


    List<?> getSelectBannerGroup(InfoBannerGroupVO vo) throws Exception ;
}
