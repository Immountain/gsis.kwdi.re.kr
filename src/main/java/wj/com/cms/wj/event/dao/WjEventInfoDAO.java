package wj.com.cms.wj.event.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.event.vo.WjEventInfoVO;

import java.util.List;

@Repository("WjEventInfoDAO")
public class WjEventInfoDAO extends EgovComAbstractDAO {

    public WjEventInfoVO selectEventInfo(WjEventInfoVO vo)throws Exception{
        return selectOne("WjEventInfoDAO.selectEventInfo",vo);
    }

    public Integer selectEventInfoTotalCount(WjEventInfoVO vo)throws Exception{
        return selectOne("WjEventInfoDAO.selectEventInfoTotalCount",vo);
    }

    public List<?> selectEventInfoList(WjEventInfoVO vo)throws Exception{
        return selectList("WjEventInfoDAO.selectEventInfoList", vo);
    }

    public void insertEventInfo(WjEventInfoVO vo)throws Exception{
        insert("WjEventInfoDAO.insertEventInfo",vo);
    }

    public void updateEventInfo(WjEventInfoVO vo)throws Exception{
        insert("WjEventInfoDAO.updateEventInfo",vo);
    }

    public void deleteEventInfo(WjEventInfoVO vo)throws Exception{
        insert("WjEventInfoDAO.deleteEventInfo",vo);
    }

}
