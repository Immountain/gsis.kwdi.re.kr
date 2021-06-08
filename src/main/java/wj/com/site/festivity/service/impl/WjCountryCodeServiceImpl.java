package wj.com.site.festivity.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import wj.com.site.festivity.dao.WjCountryCodeDAO;
import wj.com.site.festivity.service.WjCountryCodeService;
import wj.com.site.festivity.vo.WjCountryCodeVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjCountryCodeService")
public class WjCountryCodeServiceImpl extends EgovAbstractServiceImpl implements WjCountryCodeService {


    @Resource(name="WjCountryCodeDAO")
    private WjCountryCodeDAO wjCountryCodeDAO;


    @Override
    public List<WjCountryCodeVO> getSelectCountryCodeList(WjCountryCodeVO vo) throws Exception {
        return wjCountryCodeDAO.getSelectCountryCodeList(vo);
    }
}
