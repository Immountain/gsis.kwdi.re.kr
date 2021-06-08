package infomind.com.sms.service;

import infomind.com.sms.vo.ScTranVO;

public interface ScTranService {

    /**
     * 문자전송
     * @param vo
     */
    void insert(ScTranVO vo);
}
