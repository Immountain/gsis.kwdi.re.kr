package infomind.com.cms.info.banner.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.banner.dao.InfoBannerDAO;
import infomind.com.cms.info.banner.service.InfoBannerService;
import infomind.com.cms.info.banner.vo.InfoBannerVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoBannerService")
public class InfoBannerServiceImpl extends EgovAbstractServiceImpl implements InfoBannerService {

    @Resource(name="InfoBannerDAO")
    private InfoBannerDAO infoBannerDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;

    @Override
    public InfoBannerVO selectBanner(InfoBannerVO vo) throws Exception {
        return infoBannerDAO.selectBanner(vo);
    }

    @Override
    public Integer selectBannerTotalCount(InfoBannerVO vo) throws Exception {
        return infoBannerDAO.selectBannerTotalCount(vo);
    }

    @Override
    public List<?> selectBannerList(InfoBannerVO vo) throws Exception {
        return infoBannerDAO.selectBannerList(vo);
    }

    @Override
    public void insertBanner(InfoBannerVO vo) throws Exception {
        infoBannerDAO.insertBanner(vo);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getBannerImage());
    }

    @Override
    public void updateBanner(InfoBannerVO vo) throws Exception {
        infoBannerDAO.updateBanner(vo);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getBannerImage());
    }
}
