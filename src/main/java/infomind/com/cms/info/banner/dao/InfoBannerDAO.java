package infomind.com.cms.info.banner.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.banner.vo.InfoBannerVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoBannerDAO")
public class InfoBannerDAO extends EgovComAbstractDAO {

    public InfoBannerVO selectBanner(InfoBannerVO vo) throws Exception {
        return selectOne("InfoBannerDAO.selectBanner", vo);
    }

    public Integer selectBannerTotalCount(InfoBannerVO vo) throws Exception {
        return selectOne("InfoBannerDAO.selectBannerTotalCount", vo);
    }

    public List<?> selectBannerList(InfoBannerVO vo) throws Exception {
        return selectList("InfoBannerDAO.selectBannerList", vo);
    }

    public void insertBanner(InfoBannerVO vo) throws Exception {
        insert("InfoBannerDAO.insertBanner", vo);
    }

    public void updateBanner(InfoBannerVO vo) throws Exception {
        insert("InfoBannerDAO.updateBanner", vo);
    }

}
