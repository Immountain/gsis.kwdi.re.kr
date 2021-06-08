package wj.com.site.festivity.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.site.festivity.vo.WjSiteProgramInfoVO;
import wj.com.site.festivity.vo.WjSiteProgramSubInfoVO;

import java.util.List;

@Repository("WjSiteProgramDAO")
public class WjSiteProgramDAO extends EgovComAbstractDAO {




    public WjSiteProgramInfoVO getSelectSiteWJProgramInfo(WjSiteProgramInfoVO vo)throws Exception{
        return selectOne("WjSiteProgramDAO.getSelectSiteWJProgramInfo",vo);
    }



    public List<WjSiteProgramInfoVO> getSelectSiteWJProgramList(WjSiteProgramInfoVO vo)throws Exception{
        return selectList("WjSiteProgramDAO.getSelectSiteWJProgramList",vo);
    }



    public List<WjSiteProgramSubInfoVO> getSelectSiteWJProgramSubList(WjSiteProgramInfoVO vo)throws Exception{
        return selectList("WjSiteProgramDAO.getSelectSiteWJProgramSubList",vo);
    }




}
