package wj.com.cms.wj.apply.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.apply.dao.WjApply2019DAO;
import wj.com.cms.wj.apply.service.WjApply2019Service;
import wj.com.cms.wj.apply.vo.WjApply2019VO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjApply2019Service")
public class WjApply2019ServiceImpl extends EgovAbstractServiceImpl implements WjApply2019Service {
    @Resource(name="WjApply2019DAO")
    private WjApply2019DAO wjApply2019DAO;
    @Override
    public WjApply2019VO selectWjApply2019(WjApply2019VO vo) throws Exception {
        return wjApply2019DAO.selectWjApply2019(vo);
    }

    @Override
    public Integer selectWjApply2019TotalCount(WjApply2019VO vo) throws Exception {
        return wjApply2019DAO.selectWjApply2019TotalCount(vo);
    }

    @Override
    public List<?> selectWjApply2019List(WjApply2019VO vo) throws Exception {
        return wjApply2019DAO.selectWjApply2019List(vo);
    }

    @Override
    public void insertWjApply2019(WjApply2019VO vo) throws Exception {
        wjApply2019DAO.insertWjApply2019(vo);
    }

    @Override
    public void updateWjApply2019(WjApply2019VO vo) throws Exception {
        wjApply2019DAO.updateWjApply2019(vo);
    }

}
