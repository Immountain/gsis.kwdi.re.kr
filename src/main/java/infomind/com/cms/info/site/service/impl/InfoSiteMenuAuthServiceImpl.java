package infomind.com.cms.info.site.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.board.vo.InfoBoardAuthConfigVO;
import infomind.com.cms.info.board.vo.InfoBoardAuthVO;
import infomind.com.cms.info.site.dao.InfoSiteMenuAuthDAO;
import infomind.com.cms.info.site.service.InfoSiteMenuAuthService;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthConfigVO;
import infomind.com.cms.info.site.vo.InfoSiteMenuAuthVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("InfoSiteMenuAuthService")
public class InfoSiteMenuAuthServiceImpl extends EgovAbstractServiceImpl implements InfoSiteMenuAuthService {

    @Resource(name = "InfoMenuAuthIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="InfoSiteMenuAuthDAO")
    private InfoSiteMenuAuthDAO infoSiteMenuAuthDAO;

    @Override
    public List<InfoSiteMenuAuthConfigVO> selectInfoMenuAuthConfigList(InfoSiteMenuAuthVO vo) throws Exception {
        return infoSiteMenuAuthDAO.selectSiteMenuAuthConfigMap(vo);
    }

    @Override
    public void updateConfigList(String siteMenuId, List<InfoSiteMenuAuthConfigVO> list) throws Exception {
        infoSiteMenuAuthDAO.deleteMenuAuthConfig(siteMenuId);

        List<InfoSiteMenuAuthVO> infoBoardAuthVOS = new ArrayList<>();
        for(InfoSiteMenuAuthConfigVO vo : list) {
            if(vo.isList()) {
                infoBoardAuthVOS.add(new InfoSiteMenuAuthVO(vo, idgenService.getNextIntegerId(), "list"));
            }
            if(vo.isRead()) {
                infoBoardAuthVOS.add(new InfoSiteMenuAuthVO(vo, idgenService.getNextIntegerId(), "read"));
            }
            if(vo.isWrite()) {
                infoBoardAuthVOS.add(new InfoSiteMenuAuthVO(vo, idgenService.getNextIntegerId(), "write"));
            }
            if(vo.isComment()) {
                infoBoardAuthVOS.add(new InfoSiteMenuAuthVO(vo, idgenService.getNextIntegerId(), "comment"));
            }
            if(vo.isReply()) {
                infoBoardAuthVOS.add(new InfoSiteMenuAuthVO(vo, idgenService.getNextIntegerId(), "reply"));
            }
            if(vo.isNotice()) {
                infoBoardAuthVOS.add(new InfoSiteMenuAuthVO(vo, idgenService.getNextIntegerId(), "notice"));
            }
        }
        for(InfoSiteMenuAuthVO v : infoBoardAuthVOS) {
            infoSiteMenuAuthDAO.insertSiteMenuAuth(v);
        }
    }

    @Override
    public InfoSiteMenuAuthVO selectSiteMenuAuth(InfoSiteMenuAuthVO vo) throws Exception {
        return infoSiteMenuAuthDAO.selectSiteMenuAuth(vo);
    }

    @Override
    public Integer selectSiteMenuAuthTotalCount(InfoSiteMenuAuthVO vo) throws Exception {
        return infoSiteMenuAuthDAO.selectSiteMenuAuthTotalCount(vo);
    }

    @Override
    public List<?> selectSiteMenuAuthList(InfoSiteMenuAuthVO vo) throws Exception {
        return infoSiteMenuAuthDAO.selectSiteMenuAuthList(vo);
    }

    @Override
    public void insertSiteMenuAuth(InfoSiteMenuAuthVO vo) throws Exception {
        infoSiteMenuAuthDAO.insertSiteMenuAuth(vo);
    }

    @Override
    public void updateSiteMenuAuth(InfoSiteMenuAuthVO vo) throws Exception {
        infoSiteMenuAuthDAO.updateSiteMenuAuth(vo);
    }
}
