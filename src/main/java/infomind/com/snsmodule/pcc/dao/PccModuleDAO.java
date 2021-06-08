package infomind.com.snsmodule.pcc.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.snsmodule.pcc.vo.PccSmsInfoVO;
import org.springframework.stereotype.Repository;

@Repository("PccModuleDAO")
public class PccModuleDAO extends EgovComAbstractDAO {



    public PccSmsInfoVO getSelectPccSmsInfo(PccSmsInfoVO vo) throws Exception{
        return selectOne("PccModuleDAO.selectPccSmsInfo", vo);
    }


    public int getUpdatePccSmsInfo(PccSmsInfoVO vo) throws Exception{
       return update("PccModuleDAO.updatePccSmsInfo", vo);
    }


    public void getInsertPccSmsInfo(PccSmsInfoVO vo) throws Exception{
        insert("PccModuleDAO.insertPccSmsInfo", vo);
    }
}
