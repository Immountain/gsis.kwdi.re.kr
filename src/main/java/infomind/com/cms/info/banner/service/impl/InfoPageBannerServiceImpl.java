package infomind.com.cms.info.banner.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.banner.dao.InfoPageBannerDAO;
import infomind.com.cms.info.banner.dao.InfoPageBannerLangDAO;
import infomind.com.cms.info.banner.service.InfoPageBannerService;
import infomind.com.cms.info.banner.vo.InfoPageBannerLangVO;
import infomind.com.cms.info.banner.vo.InfoPageBannerVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoPageBannerService")
public class InfoPageBannerServiceImpl extends EgovAbstractServiceImpl implements InfoPageBannerService {

    @Resource(name = "InfoPageBannerIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="InfoPageBannerDAO")
    private InfoPageBannerDAO infoPageBannerDAO;

    @Resource(name="InfoPageBannerLangDAO")
    private InfoPageBannerLangDAO infoPageBannerLangDAO;

    @Override
    public InfoPageBannerVO selectPageBanner(InfoPageBannerVO vo) throws Exception {
        return infoPageBannerDAO.selectPageBanner(vo);
    }

    @Override
    public Integer selectPageBannerTotalCount(InfoPageBannerVO vo) throws Exception {
        return infoPageBannerDAO.selectPageBannerTotalCount(vo);
    }

    @Override
    public List<?> selectPageBannerList(InfoPageBannerVO vo) throws Exception {
        return infoPageBannerDAO.selectPageBannerList(vo);
    }

    @Override
    public void insertPageBanner(InfoPageBannerVO vo) throws Exception {

        String generateId = idgenService.getNextStringId();

        vo.setPageBannerId(idgenService.getNextStringId());

        infoPageBannerDAO.insertPageBanner(vo);

        if(!CollectionUtils.isEmpty(vo.getBannerLangList())) {
            for(InfoPageBannerLangVO langVO : vo.getBannerLangList()) {
                langVO.setPageBannerId(generateId);
                updatePageBannerLang(langVO);
            }
        }
    }

    @Override
    public void updatePageBanner(InfoPageBannerVO vo) throws Exception {
        infoPageBannerDAO.updatePageBanner(vo);

        if(!CollectionUtils.isEmpty(vo.getBannerLangList())) {
            for(InfoPageBannerLangVO langVO : vo.getBannerLangList()) {
                updatePageBannerLang(langVO);
            }
        }
    }

    private void updatePageBannerLang(InfoPageBannerLangVO langVO) throws Exception {
        if(infoPageBannerLangDAO.selectPageBannerLang(langVO) == null) {
            infoPageBannerLangDAO.insertPageBannerLang(langVO);
        } else {
            infoPageBannerLangDAO.updatePageBannerLang(langVO);
        }
    }

}
