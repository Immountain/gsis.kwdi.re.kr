package wj.com.cms.wj.existing.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.cms.wj.existing.vo.WjExistingDataJejuVO;

import java.util.List;

@Repository("WjExistingDataJejuDAO")
public class WjExistingDataJejuDAO extends EgovComAbstractDAO {

    public WjExistingDataJejuVO selectWjExistingDataJeju(WjExistingDataJejuVO vo) throws Exception {
        return selectOne("WjExistingDataJejuDAO.selectWjExistingDataJeju", vo);
    }

    public Integer selectWjExistingDataJejuTotalCount(WjExistingDataJejuVO vo) throws Exception {
        return selectOne("WjExistingDataJejuDAO.selectWjExistingDataJejuTotalCount", vo);
    }

    public List<?> selectWjExistingDataJejuList(WjExistingDataJejuVO vo) throws Exception {
        return selectList("WjExistingDataJejuDAO.selectWjExistingDataJejuList", vo);
    }

    public void insertWjExistingDataJeju(WjExistingDataJejuVO vo) throws Exception {
        insert("WjExistingDataJejuDAO.insertWjExistingDataJeju", vo);
    }

    public void updateWjExistingDataJeju(WjExistingDataJejuVO vo) throws Exception {
        insert("WjExistingDataJejuDAO.updateWjExistingDataJeju", vo);
    }

}
