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
    public void insertJewThemaFileHis(JewThemaFileHisVO vo) throws Exception {



        vo.setUpdateKeepId(vo.getThemaId());
        vo.setTitle(vo.getThemaTitle());
        vo.setUpdateKeepType("T");

        jejuDataDAO.getInsertJewUpdateKeep(vo);
        jejuDataDAO.insertJewThemaFileHis(vo);
        

    }

    @Override
    public JewThemaFileHisVO getSelectJewThemaFileHisView(JewThemaFileHisVO vo) throws Exception {
        return  jejuDataDAO.selectJewThemaFileHisView(vo);
    }

    @Override
    public void updateJewThemaFileHis(JewThemaFileHisVO vo) throws Exception {
        jejuDataDAO.updateJewThemaFileHis(vo);
    }

    @Override
    public void getInsertJewUpdateKeep(JewThemaFileHisVO vo) throws Exception {
        jejuDataDAO.getInsertJewUpdateKeep(vo);
    }

    @Override
    public void getInsertJewThemaVisit(JewThemaFileHisVO vo) throws Exception {
        jejuDataDAO.getInsertJewThemaVisit(vo);
    }
}


