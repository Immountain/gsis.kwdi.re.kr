package wj.com.cms.wj.program.service.impl;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wj.com.cms.wj.program.dao.WjProgramInfoDAO;
import wj.com.cms.wj.program.dao.WjProgramInfoLangDAO;
import wj.com.cms.wj.program.service.WjProgramInfoService;
import wj.com.cms.wj.program.vo.WjProgramInfoLangVO;
import wj.com.cms.wj.program.vo.WjProgramInfoVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjProgramInfoService")

public class WjProgramInfoServiceImpl extends EgovAbstractServiceImpl implements WjProgramInfoService {

    @Resource(name="WjProgramInfoIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="WjProgramInfoDAO")
    private WjProgramInfoDAO wjProgramInfoDAO;

    @Resource(name="WjProgramInfoLangDAO")
    private WjProgramInfoLangDAO wjProgramInfoLangDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;

    @Override
    public WjProgramInfoVO selectProgramInfo(WjProgramInfoVO vo) throws Exception {

        WjProgramInfoVO wjProgramInfoVO = wjProgramInfoDAO.selectProgramInfo(vo);

        String test = wjProgramInfoVO.getProgramDay();

        String year = test.substring(0,4);
        String month = test.substring(4,6);
        String day = test.substring(6,8);

        String programDay = year + "-" + month + "-" + day;

        wjProgramInfoVO.setProgramDay(programDay);

        return wjProgramInfoVO;
    }

    @Override
    public Integer selectProgramInfoTotalCount(WjProgramInfoVO vo) throws Exception {
        return wjProgramInfoDAO.selectProgramInfoTotalCount(vo);
    }

    @Override
    public List<?> selectProgramInfoList(WjProgramInfoVO vo) throws Exception {
        return wjProgramInfoDAO.selectProgramInfoList(vo);
    }

    @Override
    public void insertProgramInfo(WjProgramInfoVO vo) throws Exception {

        String generateId = idgenService.getNextStringId();
        vo.setProgramSno(generateId);

        wjProgramInfoDAO.insertProgramInfo(vo);

        infoFileMngUtil.copyFile(vo.getAtchFileId());

        if(!CollectionUtils.isEmpty(vo.getListLang())) {
            for(WjProgramInfoLangVO langVO : vo.getListLang()) {
                langVO.setProgramSno(generateId);
                langVO.setRegId(vo.getRegId());
                langVO.setModId(vo.getModId());
                langVO.setFestivityId(vo.getFestivityId());
                updateProgramInfoLang(langVO);
            }
        }
    }

    @Override
    public void updateProgramInfo(WjProgramInfoVO vo) throws Exception {
        wjProgramInfoDAO.updateProgramInfo(vo);

        infoFileMngUtil.copyFile(vo.getAtchFileId());

        if(!CollectionUtils.isEmpty(vo.getListLang())){
            for(WjProgramInfoLangVO langVO : vo.getListLang()){
                langVO.setModId(vo.getModId());
                langVO.setFestivityId(vo.getFestivityId());
                langVO.setProgramSno(vo.getProgramSno());
                updateProgramInfoLang(langVO);
            }
        }
    }

    @Override
    public void deleteProgramInfo(WjProgramInfoVO vo) throws Exception {
        wjProgramInfoDAO.deleteProgramInfo(vo);
    }

    private void updateProgramInfoLang(WjProgramInfoLangVO langVO) throws Exception {
        if(wjProgramInfoLangDAO.selectProgramInfoLang(langVO) == null) {
            wjProgramInfoLangDAO.insertProgramInfoLang(langVO);
        } else {
            wjProgramInfoLangDAO.updateProgramInfoLang(langVO);
        }
    }
}
