package infomind.com.cms.info.popup.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.popup.dao.InfoPopupGroupDAO;
import infomind.com.cms.info.popup.dao.InfoPopupManageDAO;
import infomind.com.cms.info.popup.service.InfoPopupGroupService;
import infomind.com.cms.info.popup.vo.InfoPopupGroupVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoPopupGroupService")
public class InfoPopupGroupServiceImpl extends EgovAbstractServiceImpl implements InfoPopupGroupService {

    @Resource(name="InfoPopupGroupDAO")
    private InfoPopupGroupDAO infoPopupGroupDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;

    @Override
    public InfoPopupGroupVO selectPopupGroup(InfoPopupGroupVO vo) throws Exception {
        return infoPopupGroupDAO.selectPopupGroup(vo);
    }

    @Override
    public Integer selectPopupGroupTotalCount(InfoPopupGroupVO vo) throws Exception {
        return infoPopupGroupDAO.selectPopupGroupTotalCount(vo);
    }

    @Override
    public List<?> selectPopupGroupList(InfoPopupGroupVO vo) throws Exception {
        return infoPopupGroupDAO.selectPopupGroupList(vo);
    }

    @Override
    public void insertPopupGroup(InfoPopupGroupVO vo) throws Exception {
        infoPopupGroupDAO.insertPopupGroup(vo);
    }

    @Override
    public void updatePopupGroup(InfoPopupGroupVO vo) throws Exception {
        infoPopupGroupDAO.updatePopupGroup(vo);
    }
}
