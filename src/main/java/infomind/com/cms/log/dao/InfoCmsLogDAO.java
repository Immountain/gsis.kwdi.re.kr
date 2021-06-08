package infomind.com.cms.log.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.log.vo.InfoCmsLogVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoCmsLogDAO")
public class InfoCmsLogDAO extends EgovComAbstractDAO {

    public void getLogInfoCmsLog(InfoCmsLogVO vo) throws Exception {
        insert("InfoCmsLogDAO.insertInfoCmsLog", vo);
    }

    public InfoCmsLogVO selectInfoCmsLog(InfoCmsLogVO vo) throws Exception {
        return selectOne("InfoCmsLogDAO.selectInfoCmsLog", vo);
    }

    public Integer selectInfoCmsLogTotalCount(InfoCmsLogVO vo) throws Exception {
        return selectOne("InfoCmsLogDAO.selectInfoCmsLogTotalCount", vo);
    }

    public List<?> selectInfoCmsLogList(InfoCmsLogVO vo) throws Exception {
        return selectList("InfoCmsLogDAO.selectInfoCmsLogList", vo);
    }

}
