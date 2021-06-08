package infomind.com.sms.service;

import infomind.com.sms.vo.InfoSmsInfoVO;

public interface InfoSmsInfoService {

    /**
     * 인증문자 검증
     * @param vo
     * @return
     */
    int selectSmsCheckCount(InfoSmsInfoVO vo);

    /**
     * 저장처리
     * @param vo
     */
    String insert(InfoSmsInfoVO vo);
    /**
     * 완료처리
     * @param vo
     */
    void update(InfoSmsInfoVO vo);

}
