package gsis.com.cms.data.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.data.dao.JejuDataDAO;
import gsis.com.cms.data.service.JejuDataService;
import gsis.com.cms.data.vo.JewThemaFileHisVO;
import gsis.com.cms.thema.vo.JewThemaGroupVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("JejuDataService")
public class JejuDataServiceImpl extends EgovAbstractServiceImpl implements JejuDataService {


    @Resource(name="JejuDataDAO")
    private JejuDataDAO jejuDataDAO;


    @Override
    public List<JewThemaFileHisVO> selectJewThemaFileHis(JewThemaFileHisVO vo) throws Exception {
        return jejuDataDAO.selectJewThemaFileHis(vo);
    }

    @Override
    public void insertJewThemaFileHis(JewThemaGroupVO vo) throws Exception {
        jejuDataDAO.insertJewThemaFileHis(vo);
        

    }
}


