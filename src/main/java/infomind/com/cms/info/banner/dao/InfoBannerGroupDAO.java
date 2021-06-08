package infomind.com.cms.info.banner.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.banner.vo.InfoBannerGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoBannerGroupDAO")
public class InfoBannerGroupDAO extends EgovComAbstractDAO {
    
    public InfoBannerGroupVO selectBannerGroup(InfoBannerGroupVO vo) throws Exception {
        return selectOne("InfoBannerGroupDAO.selectBannerGroup", vo);
    }

    public Integer selectBannerGroupTotalCount(InfoBannerGroupVO vo) throws Exception {
        return selectOne("InfoBannerGroupDAO.selectBannerGroupTotalCount", vo);
    }

    public List<?> selectBannerGroupList(InfoBannerGroupVO vo) throws Exception {
        return selectList("InfoBannerGroupDAO.selectBannerGroupList", vo);
    }

    public void insertBannerGroup(InfoBannerGroupVO vo) throws Exception {
        insert("InfoBannerGroupDAO.insertBannerGroup", vo);
    }

    public void updateBannerGroup(InfoBannerGroupVO vo) throws Exception {
        insert("InfoBannerGroupDAO.updateBannerGroup", vo);
    }


    public List<?> getSelectBannerGroup(InfoBannerGroupVO vo) throws Exception {
        return selectList("InfoBannerGroupDAO.getSelectBannerGroup", vo);
    }

}
