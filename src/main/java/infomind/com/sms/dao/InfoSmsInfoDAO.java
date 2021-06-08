package infomind.com.sms.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.sms.vo.InfoSmsInfoVO;
import org.springframework.stereotype.Repository;

@Repository("InfoSmsInfoDAO")
public class InfoSmsInfoDAO extends EgovComAbstractDAO {

    /**
     * 인증문자 검증
     * @param vo
     * @return
     */
    public int selectSmsCheckCount(InfoSmsInfoVO vo){

        return (Integer)selectOne("InfoSmsInfoDAO.selectSmsCheckCount", vo);
    }


    /**
     * 저장처리
     * @param vo
     */
    public void insert(InfoSmsInfoVO vo){
        insert("InfoSmsInfoDAO.insert",vo);
    }

    /**
     * 완료처리
     * @param vo
     */
    public void update(InfoSmsInfoVO vo){
        update("InfoSmsInfoDAO.update",vo);
    }


}
