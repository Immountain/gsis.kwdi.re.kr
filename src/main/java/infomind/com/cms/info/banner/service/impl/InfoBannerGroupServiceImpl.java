package infomind.com.cms.info.banner.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.banner.dao.InfoBannerGroupDAO;
import infomind.com.cms.info.banner.service.InfoBannerGroupService;
import infomind.com.cms.info.banner.vo.InfoBannerGroupVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoBannerGroupService")
public class InfoBannerGroupServiceImpl extends EgovAbstractServiceImpl implements InfoBannerGroupService {

    @Resource(name="InfoBannerGroupDAO")
    private InfoBannerGroupDAO infoBannerGroupDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;

    @Override
    public InfoBannerGroupVO selectBannerGroup(InfoBannerGroupVO vo) throws Exception {
        return infoBannerGroupDAO.selectBannerGroup(vo);
    }

    @Override
    public Integer selectBannerGroupTotalCount(InfoBannerGroupVO vo) throws Exception {
        return infoBannerGroupDAO.selectBannerGroupTotalCount(vo);
    }

    @Override
    public List<?> selectBannerGroupList(InfoBannerGroupVO vo) throws Exception {
        return infoBannerGroupDAO.selectBannerGroupList(vo);
    }

    @Override
    public void insertBannerGroup(InfoBannerGroupVO vo) throws Exception {
        infoBannerGroupDAO.insertBannerGroup(vo);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getBannerGroupImage());
    }

    @Override
    public void updateBannerGroup(InfoBannerGroupVO vo) throws Exception {
        infoBannerGroupDAO.updateBannerGroup(vo);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getBannerGroupImage());
    }

    @Override
    public List<?> getSelectBannerGroup(InfoBannerGroupVO vo) throws Exception {
        return infoBannerGroupDAO.getSelectBannerGroup(vo);
    }
}
