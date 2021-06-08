package infomind.com.cms.info.popup.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.popup.dao.InfoPopupManageDAO;
import infomind.com.cms.info.popup.service.InfoPopupManageService;
import infomind.com.cms.info.popup.vo.InfoPopupManageVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoPopupManageService")
public class InfoPopupManageServiceImpl extends EgovAbstractServiceImpl implements InfoPopupManageService {

    @Resource(name="InfoPopupManageDAO")
    private InfoPopupManageDAO infoPopupManageDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;

    @Override
    public InfoPopupManageVO selectPopupManage(InfoPopupManageVO vo) throws Exception {
        return infoPopupManageDAO.selectPopupManage(vo);
    }

    @Override
    public Integer selectPopupManageTotalCount(InfoPopupManageVO vo) throws Exception {
        return infoPopupManageDAO.selectPopupManageTotalCount(vo);
    }

    @Override
    public List<?> selectPopupManageList(InfoPopupManageVO vo) throws Exception {
        return infoPopupManageDAO.selectPopupManageList(vo);
    }

    @Override
    public void insertPopupManage(InfoPopupManageVO vo) throws Exception {
        infoPopupManageDAO.insertPopupManage(vo);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getPopupImage());
    }

    @Override
    public void updatePopupManage(InfoPopupManageVO vo) throws Exception {
        infoPopupManageDAO.updatePopupManage(vo);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getPopupImage());
    }

    @Override
    public List<InfoPopupManageVO> getPopList(InfoPopupManageVO vo) {
        return infoPopupManageDAO.getPopList(vo);
    }
}
