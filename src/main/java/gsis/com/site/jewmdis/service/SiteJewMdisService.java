package gsis.com.site.jewmdis.service;


import gsis.com.site.jewmdis.vo.SiteJewFileDownloadVO;
import gsis.com.site.jewmdis.vo.SiteJewMdisVO;

import java.util.List;

public interface SiteJewMdisService {

    Integer getSelectJewMdisTotCnt(SiteJewMdisVO vo) throws Exception ;


    List<?> getSelectJewMdisList(SiteJewMdisVO vo) throws Exception;

    SiteJewMdisVO getSelectJewMdisView(SiteJewMdisVO vo)throws Exception;


    void getInsertMdisFileDownload(SiteJewFileDownloadVO vo)throws Exception;

}
