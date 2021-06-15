package gsis.com.cms.thema.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.thema.dao.JewThemaInfoDAO;
import gsis.com.cms.thema.service.JewThemaInfoService;
import gsis.com.cms.thema.vo.JewThemaInfoVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("JewThemaInfoService")
public class JewThemaInfoServiceImpl extends EgovAbstractServiceImpl implements JewThemaInfoService {

    @Resource(name="JewThemaInfoDAO")
    private JewThemaInfoDAO jewThemaInfoDAO;

    @Override
    public JewThemaInfoVO selectThemaInfo(JewThemaInfoVO vo) throws Exception {
        return jewThemaInfoDAO.selectThemaInfo(vo);
    }

    @Override
    public List<?> selectThemaInfoList(JewThemaInfoVO vo) throws Exception {
        return jewThemaInfoDAO.selectThemaInfoList(vo);
    }

    @Override
    public void insertThemaInfo(JewThemaInfoVO vo) throws Exception {
        jewThemaInfoDAO.insertThemaInfo(vo);
    }

    @Override
    public void updateThemaInfo(JewThemaInfoVO vo) throws Exception {
        jewThemaInfoDAO.updateThemaInfo(vo);
    }

    @Override
    public void deleteThemaInfo(JewThemaInfoVO vo) throws Exception {
        jewThemaInfoDAO.deleteThemaInfo(vo);
    }
}
