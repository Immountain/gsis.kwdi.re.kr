package infomind.com.cms.info.banner.service;

import infomind.com.cms.info.banner.vo.InfoBannerGroupVO;
import infomind.com.cms.info.banner.vo.InfoPageBannerVO;

import java.util.List;

public interface InfoPageBannerService {
    public InfoPageBannerVO selectPageBanner(InfoPageBannerVO vo) throws Exception;

    public Integer selectPageBannerTotalCount(InfoPageBannerVO vo) throws Exception;

    public List<?> selectPageBannerList(InfoPageBannerVO vo) throws Exception;

    public void insertPageBanner(InfoPageBannerVO vo) throws Exception;

    public void updatePageBanner(InfoPageBannerVO vo) throws Exception;




}
