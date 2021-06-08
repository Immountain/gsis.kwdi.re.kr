package infomind.com.cms.mnu.auth.service;

import infomind.com.cms.info.site.vo.InfoSiteMenuAuthConfigVO;
import infomind.com.cms.mnu.auth.vo.InfoCmsMenuAuthConfigVO;
import infomind.com.cms.mnu.auth.vo.InfoCmsMenuAuthVO;

import java.util.List;

public interface InfoCmsMenuAuthService {

    InfoCmsMenuAuthVO getCmsMenuAuth(InfoCmsMenuAuthVO vo) throws Exception ;

    void insertSiteMenuAuth(InfoCmsMenuAuthVO vo) throws Exception ;

    void updateSiteMenuAuth(InfoCmsMenuAuthVO vo) throws Exception ;

    void deleteMenuAuthConfig(String cmsMemuAuthId);
    
    void updateConfigList(String cmsMemuId, List<InfoCmsMenuAuthConfigVO> list) throws Exception;

    List<InfoCmsMenuAuthConfigVO> selectInfoMenuAuthConfigList(InfoCmsMenuAuthVO searchVO);
}
