package gsis.com.site.jejudb.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import gsis.com.site.jejudb.vo.JejuDbVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("JejuDbDAO")
public class JejuDbDAO extends EgovComAbstractDAO {


    public List<JejuDbVO> getSelectJejuDbList(JejuDbVO vo)throws Exception{
        return selectList("JejuDbDAO.getSelectJejuDbList", vo);
    }



}
