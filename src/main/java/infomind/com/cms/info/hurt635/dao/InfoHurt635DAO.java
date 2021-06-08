package infomind.com.cms.info.hurt635.dao;


import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.hurt635.vo.InfoHurt635VO;
import infomind.com.cms.info.site.vo.InfoSiteVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoHurt635DAO")
public class InfoHurt635DAO extends EgovComAbstractDAO {

    public InfoHurt635VO selectHurt635(InfoHurt635VO vo) throws Exception {
        return selectOne("InfoHurt635DAO.selectHurt635", vo);
    }

    public Integer selectHurt635TotalCount(InfoHurt635VO vo) throws Exception {
        return selectOne("InfoHurt635DAO.selectHurt635TotalCount", vo);
    }

    public List<?> selectHurt635List(InfoHurt635VO vo) throws Exception {
        return selectList("InfoHurt635DAO.selectHurt635List", vo);
    }

    public void insertHurt635(InfoHurt635VO vo) throws Exception{
        insert("InfoHurt635DAO.insertHurt635",vo);
    }

    public void updateHurt635(InfoHurt635VO vo) throws Exception{
        insert("InfoHurt635DAO.updateHurt635",vo);

    }

}
