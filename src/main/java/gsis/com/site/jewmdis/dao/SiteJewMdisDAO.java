package gsis.com.site.jewmdis.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.site.jewmdis.vo.SiteJewFileDownloadVO;
import gsis.com.site.jewmdis.vo.SiteJewMdisVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SiteJewMdisDAO")
public class SiteJewMdisDAO extends EgovComAbstractDAO {


    public Integer getSelectJewMdisTotCnt(SiteJewMdisVO vo) throws Exception {
        return selectOne("SiteJewMdisDAO.getSelectJewMdisTotCnt", vo);
    }

    public List<?> getSelectJewMdisList(SiteJewMdisVO vo) throws Exception {
        return selectList("SiteJewMdisDAO.getSelectJewMdisList", vo);
    }

    public SiteJewMdisVO getSelectJewMdisView(SiteJewMdisVO vo)throws Exception{
        return selectOne("SiteJewMdisDAO.getSelectJewMdisView",vo);
    }


    public void getInsertMdisFileDownload(SiteJewFileDownloadVO vo)throws Exception{
        insert("SiteJewMdisDAO.getInsertMdisFileDownload",vo);
    }





}
