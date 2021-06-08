package infomind.com.cms.info.banner.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.banner.vo.InfoPageBannerVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoPageBannerDAO")
public class InfoPageBannerDAO extends EgovComAbstractDAO {

    public InfoPageBannerVO selectPageBanner(InfoPageBannerVO vo) throws Exception {
        return selectOne("InfoPageBannerDAO.selectPageBanner", vo);
    }

    public Integer selectPageBannerTotalCount(InfoPageBannerVO vo) throws Exception {
        return selectOne("InfoPageBannerDAO.selectPageBannerTotalCount", vo);
    }

    public List<?> selectPageBannerList(InfoPageBannerVO vo) throws Exception {
        return selectList("InfoPageBannerDAO.selectPageBannerList", vo);
    }

    public void insertPageBanner(InfoPageBannerVO vo) throws Exception {
        insert("InfoPageBannerDAO.insertPageBanner", vo);
    }

    public void updatePageBanner(InfoPageBannerVO vo) throws Exception {
        insert("InfoPageBannerDAO.updatePageBanner", vo);
    }

}
