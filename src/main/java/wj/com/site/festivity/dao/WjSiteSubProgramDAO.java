package wj.com.site.festivity.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.site.festivity.vo.WjSiteSubProgramVO;

import java.util.List;

@Repository("WjSiteSubProgramDAO")
public class WjSiteSubProgramDAO extends EgovComAbstractDAO {



    public WjSiteSubProgramVO getSelectSiteWjSubProgramInfo(WjSiteSubProgramVO vo)throws Exception{
        return selectOne("WjSiteSubProgramDAO.getSelectSiteWjSubProgramInfo",vo);
    }



    public List<WjSiteSubProgramVO> getSelectSiteWjSubProgramList(WjSiteSubProgramVO vo)throws Exception{
        return selectList("WjSiteSubProgramDAO.getSelectSiteWjSubProgramList",vo);
    }


}
