package infomind.com.cms.info.layout.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.layout.dao.InfoLayoutInfoDAO;
import infomind.com.cms.info.layout.service.InfoLayoutInfoService;
import infomind.com.cms.info.layout.vo.InfoLayoutInfoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoLayoutInfoService")
public class InfoLayoutInfoServiceImpl extends EgovAbstractServiceImpl implements InfoLayoutInfoService {

    @Resource(name="InfoLayoutInfoDAO")
    private InfoLayoutInfoDAO infoLayoutInfoDAO;

    @Resource(name = "InfoLayoutContentHisIdGnrService")
    private EgovIdGnrService idgenService;




    @Override
    public int selectLayoutInfoListTotCnt(InfoLayoutInfoVO searchVO) throws Exception {
        return infoLayoutInfoDAO.selectLayoutInfoListTotCnt(searchVO);
    }

    @Override
    public List<?> selectLayoutInfoList(InfoLayoutInfoVO searchVO) throws Exception {
        return infoLayoutInfoDAO.selectLayoutInfoList(searchVO);
    }

    @Override
    public void insertLayoutInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception {
        infoLayoutInfoDAO.insertLayoutInfo(infoLayoutInfoVO);

        infoLayoutInfoDAO.insertLayoutContent(infoLayoutInfoVO);
        infoLayoutInfoVO.setModType("I");
        infoLayoutInfoVO.setLayoutHisSno(idgenService.getNextStringId());
        infoLayoutInfoDAO.insertLayoutContentHis(infoLayoutInfoVO);


    }

    @Override
    public InfoLayoutInfoVO selecLayoutInfoDetail(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception {
        return infoLayoutInfoDAO.selecLayoutInfoDetail(infoLayoutInfoVO);
    }

    @Override
    public void updateLayoutInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception {
        infoLayoutInfoDAO.updateLayoutInfo(infoLayoutInfoVO);

        infoLayoutInfoDAO.updateLayoutContent(infoLayoutInfoVO);
        infoLayoutInfoVO.setModType("U");
        infoLayoutInfoVO.setLayoutHisSno(idgenService.getNextStringId());
        infoLayoutInfoDAO.insertLayoutContentHis(infoLayoutInfoVO);

    }

    @Override
    public InfoLayoutInfoVO selecLayoutContentInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception {
        return infoLayoutInfoDAO.selecLayoutContentInfo(infoLayoutInfoVO);
    }

    @Override
    public InfoLayoutInfoVO getLayoutContentInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception {
        return infoLayoutInfoDAO.selecLayoutContentInfo(infoLayoutInfoVO);
    }
}
