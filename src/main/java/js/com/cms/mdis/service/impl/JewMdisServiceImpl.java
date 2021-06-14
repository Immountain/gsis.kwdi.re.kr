package js.com.cms.mdis.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import js.com.cms.mdis.dao.JewMdisDAO;
import js.com.cms.mdis.service.JewMdisService;
import js.com.cms.mdis.vo.JewMdisVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("JewMdisService")
public class JewMdisServiceImpl extends EgovAbstractServiceImpl implements JewMdisService {

    @Resource(name="JewMdisDAO")
    private JewMdisDAO jewMdisDAO;

    @Override
    public JewMdisVO selectMdis(JewMdisVO vo) throws Exception {
        return jewMdisDAO.selectMdis(vo);
    }

    @Override
    public List<?> selectMdisList(JewMdisVO vo) throws Exception {
        return jewMdisDAO.selectMdisList(vo);
    }

    @Override
    public void insertMdis(JewMdisVO vo) throws Exception {
        jewMdisDAO.insertMdis(vo);
    }

    @Override
    public void updateMdis(JewMdisVO vo) throws Exception {
        jewMdisDAO.updateMdis(vo);
    }
}
