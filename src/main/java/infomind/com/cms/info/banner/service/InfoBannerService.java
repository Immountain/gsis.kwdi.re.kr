package infomind.com.cms.info.banner.service;

import infomind.com.cms.info.banner.vo.InfoBannerGroupVO;
import infomind.com.cms.info.banner.vo.InfoBannerVO;

import java.util.List;

public interface InfoBannerService {
    public InfoBannerVO selectBanner(InfoBannerVO vo) throws Exception;

    public Integer selectBannerTotalCount(InfoBannerVO vo) throws Exception;

    public List<?> selectBannerList(InfoBannerVO vo) throws Exception;

    public void insertBanner(InfoBannerVO vo) throws Exception;

    public void updateBanner(InfoBannerVO vo) throws Exception;
}
