package infomind.com.cms.mnu.auth.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthConfigVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthVO;
import infomind.com.cms.mnu.auth.dao.InfoCmsMenuAuthDAO;
import infomind.com.cms.mnu.auth.service.InfoCmsMenuAuthService;
import infomind.com.cms.mnu.auth.vo.InfoCmsMenuAuthConfigVO;
import infomind.com.cms.mnu.auth.vo.InfoCmsMenuAuthVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("InfoCmsMenuAuthService")
public class InfoCmsMenuAuthServiceImpl extends EgovAbstractServiceImpl implements InfoCmsMenuAuthService {

    @Resource(name = "InfoCmsMenuAuthIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="InfoCmsMenuAuthDAO")
    private InfoCmsMenuAuthDAO infoCmsMenuAuthDAO;

    @Override
    public InfoCmsMenuAuthVO getCmsMenuAuth(InfoCmsMenuAuthVO vo) throws Exception {



        return infoCmsMenuAuthDAO.getCmsMenuAuth(vo);
    }

    @Override
    public void insertSiteMenuAuth(InfoCmsMenuAuthVO vo) throws Exception {
        infoCmsMenuAuthDAO.insertSiteMenuAuth(vo);
    }

    @Override
    public void updateSiteMenuAuth(InfoCmsMenuAuthVO vo) throws Exception {
        infoCmsMenuAuthDAO.updateSiteMenuAuth(vo);
    }

    @Override
    public void deleteMenuAuthConfig(String cmsMemuAuthId) {
        infoCmsMenuAuthDAO.deleteMenuAuthConfig(cmsMemuAuthId);
    }


    @Override
    public List<InfoCmsMenuAuthConfigVO> selectInfoMenuAuthConfigList(InfoCmsMenuAuthVO vo) {
        return infoCmsMenuAuthDAO.selectCmsMenuAuthConfigMap(vo);
    }

    @Override
    public void updateConfigList(String cmsMemuId, List<InfoCmsMenuAuthConfigVO> list) throws Exception {
        infoCmsMenuAuthDAO.deleteMenuAuthConfig(cmsMemuId);

        List<InfoCmsMenuAuthVO> infoBoardAuthVOS = new ArrayList<>();
        for(InfoCmsMenuAuthConfigVO vo : list) {
            if(vo.isList()) infoBoardAuthVOS.add(new InfoCmsMenuAuthVO(vo, idgenService.getNextIntegerId(), "list"));
            if(vo.isRead()) infoBoardAuthVOS.add(new InfoCmsMenuAuthVO(vo, idgenService.getNextIntegerId(), "read"));
            if(vo.isWrite()) infoBoardAuthVOS.add(new InfoCmsMenuAuthVO(vo, idgenService.getNextIntegerId(), "write"));
            if(vo.isModify()) infoBoardAuthVOS.add(new InfoCmsMenuAuthVO(vo, idgenService.getNextIntegerId(), "modify"));
            if(vo.isDelete()) infoBoardAuthVOS.add(new InfoCmsMenuAuthVO(vo, idgenService.getNextIntegerId(), "delete"));
            if(vo.isOrgan()) infoBoardAuthVOS.add(new InfoCmsMenuAuthVO(vo, idgenService.getNextIntegerId(), "organ"));
            if(vo.isCloud()) infoBoardAuthVOS.add(new InfoCmsMenuAuthVO(vo, idgenService.getNextIntegerId(), "cloud"));

        }
        for(InfoCmsMenuAuthVO v : infoBoardAuthVOS) {
            infoCmsMenuAuthDAO.insertSiteMenuAuth(v);
        }
    }
}
