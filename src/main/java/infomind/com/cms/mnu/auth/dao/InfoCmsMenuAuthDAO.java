package infomind.com.cms.mnu.auth.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthConfigVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthVO;
import infomind.com.cms.mnu.auth.vo.InfoCmsMenuAuthConfigVO;
import infomind.com.cms.mnu.auth.vo.InfoCmsMenuAuthVO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("InfoCmsMenuAuthDAO")
public class InfoCmsMenuAuthDAO extends EgovComAbstractDAO {

    public List<InfoCmsMenuAuthConfigVO> selectCmsMenuAuthConfigMap(InfoCmsMenuAuthVO vo) {
        return selectList("InfoCmsMenuAuthDAO.selectCmsMenuAuthConfigMap", vo);
    }

    public InfoCmsMenuAuthVO getCmsMenuAuth(InfoCmsMenuAuthVO vo) throws Exception {
        return selectOne("InfoCmsMenuAuthDAO.selectCmsMenuAuthInfo", vo);
    }

    public void insertSiteMenuAuth(InfoCmsMenuAuthVO vo) throws Exception {
        insert("InfoCmsMenuAuthDAO.insertCmsMenuAuth", vo);
    }

    public void updateSiteMenuAuth(InfoCmsMenuAuthVO vo) throws Exception {
        insert("InfoCmsMenuAuthDAO.updateCmsMenuAuth", vo);
    }

    public void deleteMenuAuthConfig(String cmsMemuAuthId) {
        delete("InfoCmsMenuAuthDAO.deleteCmsMenuAuthConfig", cmsMemuAuthId);
    }




}
