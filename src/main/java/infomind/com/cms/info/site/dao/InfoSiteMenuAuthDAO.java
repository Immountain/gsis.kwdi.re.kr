package infomind.com.cms.info.site.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.board.vo.InfoBoardAuthVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthConfigVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoSiteMenuAuthDAO")
public class InfoSiteMenuAuthDAO extends EgovComAbstractDAO {

    public List<InfoSiteMenuAuthConfigVO> selectSiteMenuAuthConfigMap(InfoSiteMenuAuthVO vo) {
        return selectList("InfoSiteMenuAuthDAO.selectSiteMenuAuthConfigMap", vo);
    }

    public InfoSiteMenuAuthVO selectSiteMenuAuth(InfoSiteMenuAuthVO vo) throws Exception {
        return selectOne("InfoSiteMenuAuthDAO.selectSiteMenuAuth", vo);
    }

    public Integer selectSiteMenuAuthTotalCount(InfoSiteMenuAuthVO vo) throws Exception {
        return selectOne("InfoSiteMenuAuthDAO.selectSiteMenuAuthTotalCount", vo);
    }

    public List<?> selectSiteMenuAuthList(InfoSiteMenuAuthVO vo) throws Exception {
        return selectList("InfoSiteMenuAuthDAO.selectSiteMenuAuthList", vo);
    }

    public void insertSiteMenuAuth(InfoSiteMenuAuthVO vo) throws Exception {
        insert("InfoSiteMenuAuthDAO.insertSiteMenuAuth", vo);
    }

    public void updateSiteMenuAuth(InfoSiteMenuAuthVO vo) throws Exception {
        insert("InfoSiteMenuAuthDAO.updateSiteMenuAuth", vo);
    }

    public void deleteMenuAuthConfig(String siteMenuId) {
        delete("InfoSiteMenuAuthDAO.deleteMenuAuthConfig", siteMenuId);
    }
}
