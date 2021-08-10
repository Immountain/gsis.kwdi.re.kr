package gsis.com.cms.banner.service;

import gsis.com.cms.banner.vo.JewBannerVO;

import java.util.List;

public interface JewBannerService {


    List<JewBannerVO> selectJewBannerList(JewBannerVO vo)throws Exception;

    void insertJewBanner(JewBannerVO vo)throws Exception;


    JewBannerVO selectJewBannerView(JewBannerVO vo)throws Exception;

    void updateJewBanner(JewBannerVO vo)throws Exception;

    void deleteJewBanner(JewBannerVO vo)throws Exception;

    List<JewBannerVO> getSelectMainJewBannerList(JewBannerVO vo)throws Exception;


}
