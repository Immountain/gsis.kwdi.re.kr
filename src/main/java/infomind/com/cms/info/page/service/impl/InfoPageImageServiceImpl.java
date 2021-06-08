package infomind.com.cms.info.page.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.page.dao.InfoPageImageDAO;
import infomind.com.cms.info.page.service.InfoPageImageService;
import infomind.com.cms.info.page.vo.InfoPageImageGroupVO;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("InfoPageImageService")
public class InfoPageImageServiceImpl extends EgovAbstractServiceImpl implements InfoPageImageService {

    @Resource(name="InfoPageImageDAO")
    private InfoPageImageDAO infoPageImageDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;


    @Override
    public int selectPageImageListTotCnt(InfoPageImageGroupVO searchVO) throws Exception {
        return infoPageImageDAO.selectPageImageListTotCnt(searchVO);
    }

    @Override
    public List<?> selectPageImageList(InfoPageImageGroupVO searchVO) throws Exception {
        return infoPageImageDAO.selectPageImageList(searchVO);
    }

    @Override
    public void insertPageImage(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception {


        infoPageImageDAO.insertPageImage(infoPageImageGroupVO);
        //여기서 경로 체인지
        infoFileMngUtil.copyFile(infoPageImageGroupVO.getAtchFileId(),"Y");
    }

    @Override
    public InfoPageImageGroupVO selecPageImageDetail(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception {
        return infoPageImageDAO.selecPageImageDetail(infoPageImageGroupVO);
    }

    @Override
    public void updatePageImage(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception {

        infoPageImageDAO.updatePageImage(infoPageImageGroupVO);
        //여기서 경로 체인지
        infoFileMngUtil.copyFile(infoPageImageGroupVO.getAtchFileId(),"Y");
    }

    @Override
    public InfoPageImageGroupVO selecPageImageThumbnail(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception {
        return infoPageImageDAO.selecPageImageThumbnail(infoPageImageGroupVO);
    }
}
