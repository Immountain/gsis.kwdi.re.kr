package infomind.com.cms.info.page.dao;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.page.vo.InfoPageGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("InfoPageGroupDAO")
public class InfoPageGroupDAO extends EgovComAbstractDAO {

    public InfoPageGroupVO selectPageGroup(InfoPageGroupVO vo) throws Exception{
        return selectOne("InfoPageGroupDAO.selectPageGroup", vo);

    }

    public List<?> selectPageGroupList(InfoPageGroupVO vo) throws Exception{
        return  list("InfoPageGroupDAO.selectPageGroupList", vo);
    }

    public int selectPageGroupTotalCount(InfoPageGroupVO vo) throws Exception{
        return (Integer)selectOne("InfoPageGroupDAO.selectPageGroupTotalCount", vo);
    }

    public void insertPageGroup(InfoPageGroupVO vo) throws Exception{
        insert("InfoPageGroupDAO.insertPageGroup", vo);
    }

    public void updatePageGroup(InfoPageGroupVO vo) throws Exception {
        insert("InfoPageGroupDAO.updatePageGroup", vo);
    }

    public List<?> getSelectPageGroupAll(InfoPageGroupVO vo) throws Exception{
        return  list("InfoPageGroupDAO.getSelectPageGroupAll", vo);
    }


}
