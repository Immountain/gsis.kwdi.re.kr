package infomind.com.cms.info.mail.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.mail.vo.InfoMailSendVO;
import infomind.com.ext.vo.CmsSearchVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoMailSendDAO")
public class InfoMailSendDAO extends EgovComAbstractDAO {

    public InfoMailSendVO select(InfoMailSendVO vo) {
        return selectOne("InfoMailSendDAO.select", vo);
    }

    public Integer selectTotalCount(InfoMailSendVO vo) {
        return selectOne("InfoMailSendDAO.selectTotalCount", vo);
    }

    public List<?> selectList(InfoMailSendVO vo) {
        return selectList("InfoMailSendDAO.selectList", vo);
    }

    public void insert(InfoMailSendVO vo) {
        insert("InfoMailSendDAO.insert", vo);
    }

    public void update(InfoMailSendVO vo) {
        insert("InfoMailSendDAO.update", vo);
    }

    public void delete(InfoMailSendVO vo) {
        delete("InfoMailSendDAO.delete", vo);
    }

    public List<?> selectComUserList(CmsSearchVO searchVO) {
        return selectList("InfoMailSendDAO.selectComUserList", searchVO);
    }
}
