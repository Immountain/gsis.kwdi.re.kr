package infomind.com.cms.sec.gmt.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.sec.gmt.dao.InfoGroupManageDAO;
import infomind.com.cms.sec.gmt.service.InfoGroupManageService;
import infomind.com.cms.sec.gmt.vo.InfoGroupManage;
import infomind.com.cms.sec.gmt.vo.InfoGroupManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoGroupManageService")
public class InfoGroupManageServiceImpl extends EgovAbstractServiceImpl implements InfoGroupManageService {

    @Resource(name="InfoGroupManageDAO")
    private InfoGroupManageDAO infoGroupManageDAO;

    @Override
    public InfoGroupManageVO selectInfoGroup(InfoGroupManageVO infoGroupManageVO) throws Exception {
        return infoGroupManageDAO.selectInfoGroup(infoGroupManageVO);
    }

    @Override
    public List<InfoGroupManageVO> selectInfoGroupList(InfoGroupManageVO infoGroupManageVO) throws Exception {
        return infoGroupManageDAO.selectInfoGroupList(infoGroupManageVO);
    }

    @Override
        public InfoGroupManageVO insertInfoGroup(InfoGroupManage infoGroupManage, InfoGroupManageVO infoGroupManageVO) throws Exception {
        infoGroupManageDAO.insertInfoGroup(infoGroupManage);
        infoGroupManageVO.setGroupId(infoGroupManage.getGroupId());
         return infoGroupManageDAO.selectInfoGroup(infoGroupManageVO);

    }

    @Override
    public void updateInfoGroup(InfoGroupManage infoGroupManage) throws Exception {
        infoGroupManageDAO.updateInfoGroup(infoGroupManage);
    }

    @Override
    public void deleteInfoGroup(InfoGroupManage infoGroupManage) throws Exception {
        infoGroupManageDAO.deleteInfoGroup(infoGroupManage);
    }

    @Override
    public int selectInfoGroupListTotCnt(InfoGroupManageVO infoGroupManageVO) throws Exception {
        return infoGroupManageDAO.selectInfoGroupListTotCnt(infoGroupManageVO);
    }

    @Override
    public List<InfoGroupManageVO> getSelectInfoGroupList(InfoGroupManageVO infoGroupManageVO) throws Exception {
        return infoGroupManageDAO.getSelectInfoGroupList(infoGroupManageVO);
    }
}
