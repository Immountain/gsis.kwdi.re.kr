package gsis.com.cms.thema.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.thema.dao.JewThemaGroupDAO;
import gsis.com.cms.thema.service.JewThemaGroupService;
import gsis.com.cms.thema.vo.JewThemaGroupVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("JewThemaGroupService")
public class JewThemaGroupServiceImpl extends EgovAbstractServiceImpl implements JewThemaGroupService {

    @Resource(name="JewThemaGroupDAO")
    private JewThemaGroupDAO jewThemaGroupDAO;

    @Override
    public JewThemaGroupVO selectThemaGroup(JewThemaGroupVO vo) throws Exception {
        return jewThemaGroupDAO.selectThemaGroup(vo);
    }

    @Override
    public List<?> selectThemaGroupList(JewThemaGroupVO vo) throws Exception {
        return jewThemaGroupDAO.selectThemaGroupList(vo);
    }

    @Override
    public void insertThemaGroup(JewThemaGroupVO vo) throws Exception {
        jewThemaGroupDAO.insertThemaGroup(vo);
    }

    @Override
    public void updateThemaGroup(JewThemaGroupVO vo) throws Exception {
        jewThemaGroupDAO.updateThemaGroup(vo);
    }

    @Override
    public void deleteThemaGroup(JewThemaGroupVO vo) throws Exception {
        jewThemaGroupDAO.deleteThemaGroup(vo);
    }
}
