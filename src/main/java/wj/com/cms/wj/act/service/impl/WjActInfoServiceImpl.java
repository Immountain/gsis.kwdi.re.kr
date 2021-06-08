package wj.com.cms.wj.act.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.act.dao.WjActInfoDAO;
import wj.com.cms.wj.act.service.WjActInfoService;
import wj.com.cms.wj.act.vo.WjActInfoVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjActInfoService")
public class WjActInfoServiceImpl extends EgovAbstractServiceImpl implements WjActInfoService {

    @Resource(name="WjActInfoDAO")
    private WjActInfoDAO wjActInfoDAO;

    @Override
    public WjActInfoVO selectActInfo(WjActInfoVO vo) throws Exception {
        return wjActInfoDAO.selectActInfo(vo);
    }

    @Override
    public Integer selectActInfoTotalCount(WjActInfoVO vo) throws Exception {
        return wjActInfoDAO.selectActInfoTotalCount(vo);
    }

    @Override
    public List<?> selectActInfoList(WjActInfoVO vo) throws Exception {
        return wjActInfoDAO.selectActInfoList(vo);
    }

    @Override
    public void insertActInfo(WjActInfoVO vo) throws Exception {
        wjActInfoDAO.insertActInfo(vo);
    }

    @Override
    public void updateActInfo(WjActInfoVO vo) throws Exception {
        wjActInfoDAO.updateActInfo(vo);
    }
}
