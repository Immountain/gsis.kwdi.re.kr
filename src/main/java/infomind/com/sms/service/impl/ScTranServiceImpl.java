package infomind.com.sms.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.sms.dao.ScTranDAO;
import infomind.com.sms.service.ScTranService;
import infomind.com.sms.vo.ScTranVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("ScTranService")
public class ScTranServiceImpl extends EgovAbstractServiceImpl implements ScTranService {


    @Resource(name="ScTranDAO")
    private ScTranDAO scTranDAO;

    @Override
    public void insert(ScTranVO vo) {

        scTranDAO.insert(vo);
    }
}
