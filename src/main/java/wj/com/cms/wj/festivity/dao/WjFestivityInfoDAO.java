package wj.com.cms.wj.festivity.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.festivity.vo.WjFestivityInfoVO;

import java.util.List;

@Repository("WjFestivityInfoDAO")
public class WjFestivityInfoDAO extends EgovComAbstractDAO {

    public WjFestivityInfoVO selectFestivityInfo(WjFestivityInfoVO vo) throws Exception{
            return selectOne("WjFestivityInfoDAO.selectFestivityInfo", vo);
    }

    public Integer selectFestivityInfoTotalCount(WjFestivityInfoVO vo)throws Exception{
        return selectOne("WjFestivityInfoDAO.selectFestivityInfoTotalCount", vo);
    }
    public List<?> selectFestivityInfoList(WjFestivityInfoVO vo) throws Exception{
        return selectList("WjFestivityInfoDAO.selectFestivityInfoList",vo);
    }
    public void insertFestivityInfo(WjFestivityInfoVO vo) throws Exception{
        insert("WjFestivityInfoDAO.insertFestivityInfo",vo);
    }
    public void updateFestivityInfo(WjFestivityInfoVO vo) throws Exception{
        insert("WjFestivityInfoDAO.updateFestivityInfo",vo);
    }

    public void deleteFestivityInfo(WjFestivityInfoVO vo) throws Exception{
        insert("WjFestivityInfoDAO.deleteFestivityInfo",vo);
    }

    public List<?> selectFestivityInfoSearchList(WjFestivityInfoVO vo)throws Exception{
       return selectList("WjFestivityInfoDAO.selectFestivityInfoSearchList",vo);
    }
}
