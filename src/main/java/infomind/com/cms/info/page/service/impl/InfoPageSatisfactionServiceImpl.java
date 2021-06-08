package infomind.com.cms.info.page.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.info.page.dao.InfoPageSatisfactionDAO;
import infomind.com.cms.info.page.service.InfoPageSatisfactionService;
import infomind.com.cms.info.page.vo.InfoPageSatisfactionVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoPageSatisfactionService")
public class InfoPageSatisfactionServiceImpl extends EgovAbstractServiceImpl implements InfoPageSatisfactionService {


    @Resource(name="InfoPageSatisfactionDAO")
    private InfoPageSatisfactionDAO infoPageSatisfactionDAO;


    @Override
    public InfoPageSatisfactionVO selectPageSatisfaction(InfoPageSatisfactionVO vo) throws Exception {
        return infoPageSatisfactionDAO.selectPageSatisfaction(vo);
    }

    @Override
    public Integer selectPageSatisfactionTotalCount(InfoPageSatisfactionVO vo) throws Exception {
        return infoPageSatisfactionDAO.selectPageSatisfactionTotalCount(vo);
    }

    @Override
    public List<?> selectPageSatisfactionList(InfoPageSatisfactionVO vo) throws Exception {
        return infoPageSatisfactionDAO.selectPageSatisfactionList(vo);
    }

    @Override
    public void insertPageSatisfaction(InfoPageSatisfactionVO vo) throws Exception {
        infoPageSatisfactionDAO.insertPageSatisfaction(vo);
    }
}
