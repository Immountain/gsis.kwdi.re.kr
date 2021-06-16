package gsis.com.cms.mdis.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import gsis.com.cms.mdis.dao.JewMdisDAO;
import gsis.com.cms.mdis.service.JewMdisService;
import gsis.com.cms.mdis.vo.JewMdisVO;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("JewMdisService")
public class JewMdisServiceImpl extends EgovAbstractServiceImpl implements JewMdisService {

    @Resource(name = "jewMdisIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="JewMdisDAO")
    private JewMdisDAO jewMdisDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;

    @Override
    public JewMdisVO selectMdis(JewMdisVO vo) throws Exception {
        return jewMdisDAO.selectMdis(vo);
    }

    @Override
    public List<?> selectMdisList(JewMdisVO vo) throws Exception {
        return jewMdisDAO.selectMdisList(vo);
    }

    @Override
    public void insertMdis(JewMdisVO vo) throws Exception {

        String key = idgenService.getNextStringId();
        vo.setJewMdisSno(key);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getEtc());
        infoFileMngUtil.copyFile(vo.getDataFile());
        jewMdisDAO.insertMdis(vo);
    }

    @Override
    public void updateMdis(JewMdisVO vo) throws Exception {
        jewMdisDAO.updateMdis(vo);
    }

    @Override
    public void deleteMdis(JewMdisVO vo) throws Exception {
        jewMdisDAO.deleteMdis(vo);
    }
}
