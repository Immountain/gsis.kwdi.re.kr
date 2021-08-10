package gsis.com.cms.banner.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.banner.dao.JewBannerDAO;
import gsis.com.cms.banner.service.JewBannerService;
import gsis.com.cms.banner.vo.JewBannerVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("JewBannerService")
public class JewBannerServiceImpl extends EgovAbstractServiceImpl implements JewBannerService {


    @Resource(name="JewBannerDAO")
    private JewBannerDAO jewBannerDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;


    @Override
    public List<JewBannerVO> selectJewBannerList(JewBannerVO vo) throws Exception {
        return jewBannerDAO.selectJewBannerList(vo);
    }

    @Override
    public void insertJewBanner(JewBannerVO vo) throws Exception {






        jewBannerDAO.insertJewBanner(vo);

        infoFileMngUtil.copyFile(vo.getBannerImage(),"Y");

    }

    @Override
    public JewBannerVO selectJewBannerView(JewBannerVO vo) throws Exception {
        return jewBannerDAO.selectJewBannerView(vo);
    }

    @Override
    public void updateJewBanner(JewBannerVO vo) throws Exception {
        jewBannerDAO.updateJewBanner(vo);

        infoFileMngUtil.copyFile(vo.getBannerImage(),"Y");

    }

    @Override
    public void deleteJewBanner(JewBannerVO vo) throws Exception {
        jewBannerDAO.deleteJewBanner(vo);

    }

    @Override
    public List<JewBannerVO> getSelectMainJewBannerList(JewBannerVO vo) throws Exception {
        return jewBannerDAO.getSelectMainJewBannerList(vo);
    }
}
