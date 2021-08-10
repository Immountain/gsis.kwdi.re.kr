package gsis.com.cms.banner.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.cms.banner.vo.JewBannerVO;
import gsis.com.cms.data.vo.JewThemaFileHisVO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("JewBannerDAO")
public class JewBannerDAO extends EgovComAbstractDAO {



    public List<JewBannerVO> selectJewBannerList(JewBannerVO vo)throws Exception{
        return selectList("JewBannerDAO.selectJewBannerList",vo);
    }

    public void insertJewBanner(JewBannerVO vo)throws Exception{
        insert("JewBannerDAO.insertJewBanner",vo);
    }


    public JewBannerVO selectJewBannerView(JewBannerVO vo)throws Exception{
        return   selectOne("JewBannerDAO.selectJewBannerView",vo);
    }

    public void updateJewBanner(JewBannerVO vo)throws Exception{
        update("JewBannerDAO.updateJewBanner",vo);
    }


    public void deleteJewBanner(JewBannerVO vo)throws Exception{
        update("JewBannerDAO.deleteJewBanner",vo);
    }
}

