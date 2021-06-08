package infomind.com.cms.info.site.service;

import infomind.com.cms.info.site.vo.InfoSiteVO;

import java.util.List;

public interface InfoSiteService {

    public InfoSiteVO selectInfoSite(InfoSiteVO vo) throws Exception;

    public Integer selectInfoSiteTotalCount(InfoSiteVO vo) throws Exception;

    public List<?> selectSiteList(InfoSiteVO vo) throws Exception;

    public void insertSite(InfoSiteVO vo) throws Exception;

    public void updateSite(InfoSiteVO vo) throws Exception;
}
