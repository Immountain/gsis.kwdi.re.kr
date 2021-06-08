package infomind.com.cms.info.page.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.page.dao.InfoPageGroupDAO;
import infomind.com.cms.info.page.service.InfoPageGroupService;
import infomind.com.cms.info.page.vo.InfoPageGroupVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoPageGroupService")
public class InfoPageGroupServiceImpl extends EgovAbstractServiceImpl implements InfoPageGroupService {
    @Resource(name="InfoPageGroupDAO")
    private InfoPageGroupDAO infoPageGroupDAO;

    @Override
    public InfoPageGroupVO selectPageGroup(InfoPageGroupVO vo) throws Exception {
        return infoPageGroupDAO.selectPageGroup(vo);
    }

    @Override
    public List<?> selectPageGroupList(InfoPageGroupVO vo) throws Exception {
        return infoPageGroupDAO.selectPageGroupList(vo);
    }

    @Override
    public int selectPageGroupTotalCount(InfoPageGroupVO vo) throws Exception {
        return infoPageGroupDAO.selectPageGroupTotalCount(vo);
    }

    @Override
    public void insertPageGroup(InfoPageGroupVO vo) throws Exception {
        infoPageGroupDAO.insertPageGroup(vo);
    }

    @Override
    public void updatePageGroup(InfoPageGroupVO vo) throws Exception {
        infoPageGroupDAO.updatePageGroup(vo);
    }

    @Override
    public List<?> getSelectPageGroupAll(InfoPageGroupVO vo) throws Exception {
        return infoPageGroupDAO.getSelectPageGroupAll(vo);
    }
}
