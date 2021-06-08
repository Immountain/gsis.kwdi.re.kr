package wj.com.site.festivity.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import org.springframework.stereotype.Repository;
import wj.com.site.festivity.vo.WjCountryCodeVO;
import java.util.List;

@Repository("WjCountryCodeDAO")
public class WjCountryCodeDAO extends EgovComAbstractDAO {

    public List<WjCountryCodeVO> getSelectCountryCodeList(WjCountryCodeVO vo) throws Exception {
        return selectList("WjCountryCodeDAO.getSelectCountryCodeList", vo);
    }



}
