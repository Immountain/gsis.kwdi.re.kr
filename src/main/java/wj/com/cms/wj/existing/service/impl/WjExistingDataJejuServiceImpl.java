package wj.com.cms.wj.existing.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.existing.dao.WjExistingDataJejuDAO;
import wj.com.cms.wj.existing.service.WjExistingDataJejuService;
import wj.com.cms.wj.existing.vo.WjExistingDataJejuVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjExistingDataJejuService")
public class WjExistingDataJejuServiceImpl extends EgovAbstractServiceImpl implements WjExistingDataJejuService {
    @Resource(name="WjExistingDataJejuDAO")
    private WjExistingDataJejuDAO wjExistingDataJejuDAO;
    @Override
    public WjExistingDataJejuVO selectWjExistingDataJeju(WjExistingDataJejuVO vo) throws Exception {
        return wjExistingDataJejuDAO.selectWjExistingDataJeju(vo);
    }

    @Override
    public Integer selectWjExistingDataJejuTotalCount(WjExistingDataJejuVO vo) throws Exception {
        return wjExistingDataJejuDAO.selectWjExistingDataJejuTotalCount(vo);
    }

    @Override
    public List<?> selectWjExistingDataJejuList(WjExistingDataJejuVO vo) throws Exception {
        return wjExistingDataJejuDAO.selectWjExistingDataJejuList(vo);
    }

    @Override
    public void insertWjExistingDataJeju(WjExistingDataJejuVO vo) throws Exception {
        wjExistingDataJejuDAO.insertWjExistingDataJeju(vo);
    }

    @Override
    public void updateWjExistingDataJeju(WjExistingDataJejuVO vo) throws Exception {
        wjExistingDataJejuDAO.updateWjExistingDataJeju(vo);
    }

}
