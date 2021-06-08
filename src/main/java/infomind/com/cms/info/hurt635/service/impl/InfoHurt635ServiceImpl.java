package infomind.com.cms.info.hurt635.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.hurt635.dao.InfoHurt635DAO;
import infomind.com.cms.info.hurt635.service.InfoHurt635Service;
import infomind.com.cms.info.hurt635.vo.InfoHurt635VO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("InfoHurt635Service")
public class InfoHurt635ServiceImpl extends EgovAbstractServiceImpl implements InfoHurt635Service {

    @Resource(name = "InfoHurt635KeySeqService")
    private EgovIdGnrService idgenService;

    @Resource(name="InfoHurt635DAO")
        private InfoHurt635DAO infoHurt635DAO;

    @Resource(name="InfoHurt635Service")
        private InfoHurt635Service infoHurt635Service;




    @Override
    public InfoHurt635VO selectHurt635(InfoHurt635VO vo) throws Exception {
        return infoHurt635DAO.selectHurt635(vo);
    }

    @Override
    public Integer selectHurt635TotalCount(InfoHurt635VO vo) throws Exception {
        return infoHurt635DAO.selectHurt635TotalCount(vo);
    }

    @Override
    public List<?> selectHurt635List(InfoHurt635VO vo) throws Exception {
        return infoHurt635DAO.selectHurt635List(vo);
    }

    @Override
    public void insertHurt635(InfoHurt635VO vo) throws Exception {
        String key =idgenService.getNextStringId();
        vo.setKeySeq(key);
        infoHurt635DAO.insertHurt635(vo);
    }

    @Override
    public void updateHurt635(InfoHurt635VO vo) throws Exception {
        infoHurt635DAO.updateHurt635(vo);
    }
}
