package infomind.com.sms.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.sms.vo.ScTranVO;
import org.springframework.stereotype.Repository;

@Repository("ScTranDAO")
public class ScTranDAO extends EgovComAbstractDAO {

    /**
     * 문자전송
     * @param vo
     */
    public void insert(ScTranVO vo){
        insert("ScTranDAO.insert",vo);
    }
}
