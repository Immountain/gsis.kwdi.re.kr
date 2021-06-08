package infomind.com.cms.info.site.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.site.dao.InfoSiteMenuGroupDAO;
import infomind.com.cms.info.site.service.InfoSiteMenuGroupService;
import infomind.com.cms.info.site.vo.InfoSiteMenuGroupVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoSiteMenuGroupService")
public class InfoSiteMenuGroupServiceImpl extends EgovAbstractServiceImpl implements InfoSiteMenuGroupService {

     @Resource(name = "InfoSiteMenuGroupDAO")
    private InfoSiteMenuGroupDAO infoSiteMenuGroupDAO;

    @Override
    public InfoSiteMenuGroupVO selectInfoSiteMenuGroup(InfoSiteMenuGroupVO vo) throws Exception {
        return infoSiteMenuGroupDAO.selectInfoSiteMenuGroup(vo);
    }

    @Override
    public Integer selectInfoSiteMenuGroupTotalCount(InfoSiteMenuGroupVO vo) throws Exception {
        return infoSiteMenuGroupDAO.selectInfoSiteMenuGroupTotalCount(vo);
    }

    @Override
    public List<?> selectSiteMenuGroupList(InfoSiteMenuGroupVO vo) throws Exception {
        return infoSiteMenuGroupDAO.selectSiteMenuGroupList(vo);
    }

    @Override
    public void insertSiteMenuGroup(InfoSiteMenuGroupVO vo) throws Exception {
        infoSiteMenuGroupDAO.insertSiteMenuGroup(vo);
    }

    @Override
    public void updateSiteMenuGroup(InfoSiteMenuGroupVO vo) throws Exception {
        infoSiteMenuGroupDAO.updateSiteMenuGroup(vo);
    }
}






























