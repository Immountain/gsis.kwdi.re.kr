package js.com.cms.stats.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import js.com.cms.stats.dao.JewStatsCategoryDAO;
import js.com.cms.stats.service.JewStatsCategoryService;
import js.com.cms.stats.vo.JewStatsCategoryVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("JewStatsCategoryService")
public class JewStatsCategoryServiceImpl extends EgovAbstractServiceImpl implements JewStatsCategoryService {

    @Resource(name="JewStatsCategoryDAO")
    private JewStatsCategoryDAO jewStatsCategoryDAO;

    @Override
    public JewStatsCategoryVO selectStatsCategory(JewStatsCategoryVO vo) throws Exception {
        return jewStatsCategoryDAO.selectStatsCategory(vo);
    }

    @Override
    public List<?> selectStatsCategoryList(JewStatsCategoryVO vo) throws Exception {
        return jewStatsCategoryDAO.selectStatsCategoryList(vo);
    }

    @Override
    public List<?> selectStatsCategorySearchList(JewStatsCategoryVO vo) throws Exception {
        return jewStatsCategoryDAO.selectStatsCategorySearchList(vo);
    }

    @Override
    public void insertStatsCategory(JewStatsCategoryVO vo) throws Exception {
        jewStatsCategoryDAO.insertStatsCategory(vo);
    }

    @Override
    public void updateStatsCategory(JewStatsCategoryVO vo) throws Exception {
        jewStatsCategoryDAO.updateStatsCategory(vo);
    }

    @Override
    public void deleteStatsCategory(JewStatsCategoryVO vo) throws Exception {
        jewStatsCategoryDAO.deleteStatsCategory(vo);
    }
}
