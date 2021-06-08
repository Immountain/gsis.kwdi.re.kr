package infomind.com.cms.info.lang.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoLangCodeDAO")
public class InfoLangCodeDAO extends EgovComAbstractDAO {

    public InfoLangCodeVO selectLangCode(InfoLangCodeVO vo) throws Exception{
        return selectOne("InfoLangCodeDAO.selectLangCode", vo);
    }

    public Integer selectLangCodeTotalCount(InfoLangCodeVO vo)throws Exception{
        return selectOne("InfoLangCodeDAO.selectLangCodeTotalCount", vo);
    }
    public List<?> selectLangCodeList(InfoLangCodeVO vo) throws Exception{
        return selectList("InfoLangCodeDAO.selectLangCodeList",vo);
    }
    public void insertLangCode(InfoLangCodeVO vo) throws Exception{
        insert("InfoLangCodeDAO.insertLangCode",vo);
    }
    public void updateLangCode(InfoLangCodeVO vo) throws Exception{
        insert("InfoLangCodeDAO.updateLangCode",vo);
    }
}
