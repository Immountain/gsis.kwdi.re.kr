package infomind.com.cms.info.page.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.page.vo.InfoPageSatisfactionVO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("InfoPageSatisfactionDAO")
public class InfoPageSatisfactionDAO extends EgovComAbstractDAO {

    public InfoPageSatisfactionVO selectPageSatisfaction(InfoPageSatisfactionVO vo) throws Exception {
        return selectOne("InfoPageSatisfactionDAO.selectPageSatisfaction", vo);
    }

    public Integer selectPageSatisfactionTotalCount(InfoPageSatisfactionVO vo) throws Exception {
        return selectOne("InfoPageSatisfactionDAO.selectPageSatisfaction", vo);
    }

    public List<?> selectPageSatisfactionList(InfoPageSatisfactionVO vo) throws Exception {
        return selectList("InfoPageSatisfactionDAO.selectPageSatisfaction", vo);
    }

    public void insertPageSatisfaction(InfoPageSatisfactionVO vo) throws Exception {
        insert("InfoPageSatisfactionDAO.selectPageSatisfaction", vo);
    }
}
