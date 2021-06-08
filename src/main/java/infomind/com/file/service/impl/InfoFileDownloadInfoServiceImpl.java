package infomind.com.file.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.file.dao.InfoFileDownloadInfoDAO;
import infomind.com.file.service.InfoFileDownloadInfoService;
import infomind.com.file.vo.InfoFileDownloadInfoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoFileDownloadInfoService")
public class InfoFileDownloadInfoServiceImpl extends EgovAbstractServiceImpl implements InfoFileDownloadInfoService {

    @Resource(name="InfoFileDownloadInfoDAO")
    private InfoFileDownloadInfoDAO infoFileDownloadInfoDAO;



    @Resource(name = "fileDownloadSnoIdGnrService")
    private EgovIdGnrService idgenService;


    @Override
    public InfoFileDownloadInfoVO selectInfoFileDownloadInfo(InfoFileDownloadInfoVO vo) throws Exception {
        return infoFileDownloadInfoDAO.selectInfoFileDownloadInfo(vo);
    }

    @Override
    public Integer selectInfoFileDownloadInfoTotalCount(InfoFileDownloadInfoVO vo) throws Exception {
        return infoFileDownloadInfoDAO.selectInfoFileDownloadInfoTotalCount(vo);
    }

    @Override
    public List<?> selectInfoFileDownloadInfoList(InfoFileDownloadInfoVO vo) throws Exception {
        return infoFileDownloadInfoDAO.selectInfoFileDownloadInfoList(vo);
    }
    @Override
    public void getInfoFileDownloadInfo(InfoFileDownloadInfoVO vo) throws Exception {
        vo.setFileDownloadSno(idgenService.getNextIntegerId());
        infoFileDownloadInfoDAO.insertInfoFileDownloadInfo(vo);
    }
}
