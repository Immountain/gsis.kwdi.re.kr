package infomind.com.cms.info.site.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("InfoSiteDAO")
public class InfoSiteDAO extends EgovComAbstractDAO {

    public InfoSiteVO selectInfoSite(InfoSiteVO vo) throws Exception{
        return selectOne("InfoSiteDAO.selectInfoSite",vo);
    }

    public Integer selectInfoSiteTotalCount(InfoSiteVO vo) throws Exception{
        return selectOne("InfoSiteDAO.selectInfoSiteTotalCount",vo);
    }

    public List<?> selectSiteList(InfoSiteVO vo) throws Exception{
        return selectList("InfoSiteDAO.selectSiteList",vo);

    }

    public void insertSite(InfoSiteVO vo) throws Exception{
        insert("InfoSiteDAO.insertSite",vo);
    }

    public void updateSite(InfoSiteVO vo) throws Exception{
        insert("InfoSiteDAO.updateSite",vo);

    }

}
