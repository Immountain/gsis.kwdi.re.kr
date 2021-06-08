package infomind.com.cms.sec.rmt.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.sec.rmt.dao.InfoRoleManageDAO;
import infomind.com.cms.sec.rmt.service.InfoRoleManageService;
import infomind.com.cms.sec.rmt.vo.InfoRoleManage;
import infomind.com.cms.sec.rmt.vo.InfoRoleManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoRoleManageService")
public class InfoRoleManageServiceImpl extends EgovAbstractServiceImpl implements InfoRoleManageService {

    @Resource(name="InfoRoleManageDAO")
    private InfoRoleManageDAO infoRoleManageDAO ;

    @Override
    public InfoRoleManageVO selectInfoRole(InfoRoleManageVO infoRoleManageVO) throws Exception {
        return infoRoleManageDAO.selectInfoRole(infoRoleManageVO);
    }

    @Override
    public List<InfoRoleManageVO> selectInfoRoleList(InfoRoleManageVO infoRoleManageVO) throws Exception {
        return infoRoleManageDAO.selectInfoRoleList(infoRoleManageVO);
    }

    @Override
    public InfoRoleManageVO insertInfoRole(InfoRoleManage infoRoleManage, InfoRoleManageVO infoRoleManageVO) throws Exception {
        infoRoleManageDAO.insertInfoRole(infoRoleManage);
        infoRoleManageVO.setRoleCode(infoRoleManage.getRoleCode());
        return infoRoleManageDAO.selectInfoRole(infoRoleManageVO);
    }

    @Override
    public void updateInfoRole(InfoRoleManage infoRoleManage) throws Exception {
        infoRoleManageDAO.updateInfoRole(infoRoleManage);
    }

    @Override
    public void deleteInfoRole(InfoRoleManage infoRoleManage) throws Exception {
        infoRoleManageDAO.deleteInfoRole(infoRoleManage);
    }

    @Override
    public int selectInfoRoleListTotCnt(InfoRoleManageVO infoRoleManageVO) throws Exception {
        return infoRoleManageDAO.selectInfoRoleListTotCnt(infoRoleManageVO);
    }

    @Override
    public List<InfoRoleManageVO> selectInfoRoleAllList(InfoRoleManageVO infoRoleManageVO) throws Exception {
        return infoRoleManageDAO.selectInfoRoleAllList(infoRoleManageVO);
    }
}
