package gsis.com.site.jejudb.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.site.jejudb.dao.JejuDbDAO;
import gsis.com.site.jejudb.service.JejuDbService;
import gsis.com.site.jejudb.vo.JejuDbVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("JejuDbService")
public class JejuDbServiceImpl extends EgovAbstractServiceImpl implements JejuDbService {

    @Resource(name="JejuDbDAO")
    private JejuDbDAO jejuDbDAO;


    @Override
    public List<JejuDbVO> getSelectJejuDbList(JejuDbVO vo) throws Exception {
        return jejuDbDAO.getSelectJejuDbList(vo);
    }
}
