package infomind.com.cms.info.site.dao;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.site.vo.InfoSiteMenuGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoSiteMenuGroupDAO")
public class InfoSiteMenuGroupDAO extends EgovComAbstractDAO {

    public InfoSiteMenuGroupVO selectInfoSiteMenuGroup(InfoSiteMenuGroupVO vo) throws Exception {
        return selectOne("InfoSiteMenuGroupDAO.selectInfoSiteMenuGroup", vo);
    }

    public Integer selectInfoSiteMenuGroupTotalCount(InfoSiteMenuGroupVO vo) throws Exception {
        return selectOne("InfoSiteMenuGroupDAO.selectInfoSiteMenuGroupTotalCount", vo);
    }

    public List<?> selectSiteMenuGroupList(InfoSiteMenuGroupVO vo) throws Exception {
        return selectList("InfoSiteMenuGroupDAO.selectSiteMenuGroupList", vo);
    }

    public void insertSiteMenuGroup(InfoSiteMenuGroupVO vo) throws Exception {
        insert("InfoSiteMenuGroupDAO.insertSiteMenuGroup", vo);
    }
    public void updateSiteMenuGroup(InfoSiteMenuGroupVO vo) throws Exception {
        insert("InfoSiteMenuGroupDAO.updateSiteMenuGroup", vo);
    }


}
