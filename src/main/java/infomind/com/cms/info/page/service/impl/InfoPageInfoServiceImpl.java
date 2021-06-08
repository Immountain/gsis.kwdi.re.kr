package infomind.com.cms.info.page.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.page.dao.InfoPageInfoDAO;
import infomind.com.cms.info.page.service.InfoPageInfoService;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoPageInfoService")
public class InfoPageInfoServiceImpl extends EgovAbstractServiceImpl implements InfoPageInfoService {


    @Resource(name="InfoPageInfoDAO")
    private InfoPageInfoDAO infoPageInfoDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;


    @Resource(name = "InfoPageContentHisIdGnrService")
    private EgovIdGnrService idgenService;

    @Override
    public int selectPageListTotCnt(InfoPageInfoVO searchVO) throws Exception {
        return infoPageInfoDAO.selectPageListTotCnt(searchVO);
    }

    @Override
    public List<?> selectPageInfoList(InfoPageInfoVO searchVO) throws Exception {
        return infoPageInfoDAO.selectPageInfoList(searchVO);
    }

    @Override
    public void insertPageInfo(InfoPageInfoVO infoPageInfoVO) throws Exception {
        infoPageInfoDAO.insertPageInfo(infoPageInfoVO);

        infoPageInfoDAO.insertPageContent(infoPageInfoVO);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(infoPageInfoVO.getPageImage());


        infoPageInfoVO.setModGb("I");
        infoPageInfoVO.setPageHisSno(idgenService.getNextIntegerId());

        //ids 로 변경
        insertPageInfoHis(infoPageInfoVO);

    }

    @Override
    public void insertPageContent(InfoPageInfoVO infoPageInfoVO) throws Exception {
        infoPageInfoDAO.insertPageContent(infoPageInfoVO);

    }

    @Override
    public InfoPageInfoVO selecPageInfoDetail(InfoPageInfoVO infoPageInfoVO) throws Exception {
        return infoPageInfoDAO.selecPageInfoDetail(infoPageInfoVO);
    }

    @Override
    public void updatePageInfo(InfoPageInfoVO infoPageInfoVO) throws Exception {
        infoPageInfoDAO.updatePageInfo(infoPageInfoVO);
        infoPageInfoDAO.updatePageInfoDetail(infoPageInfoVO);



        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(infoPageInfoVO.getPageImage());

        infoPageInfoVO.setModGb("U");
        infoPageInfoVO.setPageHisSno(idgenService.getNextIntegerId());
        insertPageInfoHis(infoPageInfoVO);

    }

    @Override
    public void updatePageInfoDetail(InfoPageInfoVO infoPageInfoVO) throws Exception {
        infoPageInfoDAO.updatePageInfoDetail(infoPageInfoVO);
    }

    @Override
    public InfoPageInfoVO selectPageInfoHis(InfoPageInfoVO vo) throws Exception {
        return infoPageInfoDAO.selectPageInfoHis(vo);
    }

    @Override
    public void updatePageInfoHis(InfoPageInfoVO vo) throws Exception {
        infoPageInfoDAO.updatePageInfoHis(vo);
    }

    @Override
    public void insertPageInfoHis(InfoPageInfoVO infoPageInfoVO) throws Exception {
        infoPageInfoDAO.insertPageInfoHis(infoPageInfoVO);
    }

    @Override
    public List<?> selectPageInfoHisList(InfoPageInfoVO searchVO) throws Exception {
        return infoPageInfoDAO.selectPageInfoHisList(searchVO);
    }

    @Override
    public int selectPageListHisTotCnt(InfoPageInfoVO searchVO) throws Exception {
        return infoPageInfoDAO.selectPageListHisTotCnt(searchVO);
    }

    @Override
    public List<?> getSelectPageInfoAllList(InfoPageInfoVO searchVO) throws Exception {
        return infoPageInfoDAO.getSelectPageInfoAllList(searchVO);
    }
}
