package infomind.com.cms.log.service.impl;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.log.dao.InfoCmsLogDAO;
import infomind.com.cms.log.service.InfoCmsLogService;
import infomind.com.cms.log.vo.InfoCmsLogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoCmsLogService")
public class InfoCmsLogServiceImpl extends EgovAbstractServiceImpl implements InfoCmsLogService {

    @Resource(name="InfoCmsLogDAO")
    private InfoCmsLogDAO infoCmsLogDAO;

    @Override
    public void getLogInfoCmsLog(InfoCmsLogVO vo) throws Exception {
        infoCmsLogDAO.getLogInfoCmsLog(vo);
    }

    @Override
    public InfoCmsLogVO selectInfoCmsLog(InfoCmsLogVO vo) throws Exception {
        return infoCmsLogDAO.selectInfoCmsLog(vo);
    }

    @Override
    public Integer selectInfoCmsLogTotalCount(InfoCmsLogVO vo) throws Exception {
        return infoCmsLogDAO.selectInfoCmsLogTotalCount(vo);
    }

    @Override
    public List<?> selectInfoCmsLogList(InfoCmsLogVO vo) throws Exception {
        return infoCmsLogDAO.selectInfoCmsLogList(vo);
    }
}
