package infomind.com.snsmodule.pcc.service;


import infomind.com.snsmodule.pcc.vo.PccSmsInfoVO;

public interface PccModuleService {


    PccSmsInfoVO setPcc(String phoneCertId, String srvNo, String returnUrl);

    PccSmsInfoVO getSelectPccSmsInfo(PccSmsInfoVO vo) throws Exception;


     int getUpdatePccSmsInfo(PccSmsInfoVO vo) throws Exception;


    void getInsertPccSmsInfo(PccSmsInfoVO vo) throws Exception;
}
