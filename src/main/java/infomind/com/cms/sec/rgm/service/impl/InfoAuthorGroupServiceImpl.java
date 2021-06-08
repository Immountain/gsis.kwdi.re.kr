package infomind.com.cms.sec.rgm.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.sec.rgm.dao.InfoAuthorGroupDAO;
import infomind.com.cms.sec.rgm.service.InfoAuthorGroupService;
import infomind.com.cms.sec.rgm.vo.InfoAuthorGroup;
import infomind.com.cms.sec.rgm.vo.InfoAuthorGroupVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoAuthorGroupService")
public class InfoAuthorGroupServiceImpl extends EgovAbstractServiceImpl implements InfoAuthorGroupService {

    @Resource(name="InfoAuthorGroupDAO")
    private InfoAuthorGroupDAO infoAuthorGroupDAO;

    @Override
    public int selectInfoAuthorGroupListTotCnt(InfoAuthorGroupVO infoAuthorGroupVO) throws Exception {
        return infoAuthorGroupDAO.selectInfoAuthorGroupListTotCnt(infoAuthorGroupVO);
    }

    @Override
    public List<InfoAuthorGroupVO> selectInfoAuthorGroupList(InfoAuthorGroupVO infoAuthorGroupVO) throws Exception {
        return infoAuthorGroupDAO.selectInfoAuthorGroupList(infoAuthorGroupVO);
    }

    @Override
    public void insertInfoAuthorGroup(InfoAuthorGroup infoAuthorGroup) throws Exception {
        infoAuthorGroupDAO.insertInfoAuthorGroup(infoAuthorGroup);
    }

    @Override
    public void updateInfoAuthorGroup(InfoAuthorGroup infoAuthorGroup) throws Exception {
        infoAuthorGroupDAO.updateInfoAuthorGroup(infoAuthorGroup);
    }

    @Override
    public void deleteInfoAuthorGroup(InfoAuthorGroup infoAuthorGroup) throws Exception {
        infoAuthorGroupDAO.deleteInfoAuthorGroup(infoAuthorGroup);
    }
}
