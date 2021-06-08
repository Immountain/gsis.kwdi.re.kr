package wj.com.cms.wj.sub.service.impl;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wj.com.cms.wj.sub.dao.WjSubProgramDAO;
import wj.com.cms.wj.sub.dao.WjSubProgramLangDAO;
import wj.com.cms.wj.sub.service.WjSubProgramService;
import wj.com.cms.wj.sub.vo.WjSubProgramLangVO;
import wj.com.cms.wj.sub.vo.WjSubProgramVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjSubProgramService")

public class WjSubProgramServiceImpl extends EgovAbstractServiceImpl implements WjSubProgramService {

    @Resource(name="WjSubProgramIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="WjSubProgramDAO")
    private WjSubProgramDAO wjSubProgramDAO;

    @Resource(name="WjSubProgramLangDAO")
    private WjSubProgramLangDAO wjSubProgramLangDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;

    @Override
    public WjSubProgramVO selectSubProgram(WjSubProgramVO vo) throws Exception {
        return wjSubProgramDAO.selectSubProgram(vo);
    }

    @Override
    public Integer selectSubProgramTotalCount(WjSubProgramVO vo) throws Exception {
        return wjSubProgramDAO.selectSubProgramTotalCount(vo);
    }

    @Override
    public List<?> selectSubProgramList(WjSubProgramVO vo) throws Exception {
        return wjSubProgramDAO.selectSubProgramList(vo);
    }

    @Override
    public void insertSubProgram(WjSubProgramVO vo) throws Exception {

        String generateId = idgenService.getNextStringId();

        vo.setFestivityProgramSno(generateId);

        wjSubProgramDAO.insertSubProgram(vo);
        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getAtchFileId());

        if(!CollectionUtils.isEmpty(vo.getListLang())) {
            for(WjSubProgramLangVO langVO : vo.getListLang()) {
                langVO.setFestivityProgramSno(generateId);
                langVO.setRegId(vo.getRegId());
                langVO.setFestivityId(vo.getFestivityId());
                updateSubProgramLang(langVO);
            }
        }
    }

    @Override
    public void updateSubProgram(WjSubProgramVO vo) throws Exception {

        wjSubProgramDAO.updateSubProgram(vo);
        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getAtchFileId());

        if(!CollectionUtils.isEmpty(vo.getListLang())){
            for(WjSubProgramLangVO langVO : vo.getListLang()){
                langVO.setFestivityProgramSno(vo.getFestivityProgramSno());
                langVO.setModId(vo.getModId());
                langVO.setFestivityId(vo.getFestivityId());
                updateSubProgramLang(langVO);
            }
        }
    }

    private void updateSubProgramLang(WjSubProgramLangVO langVO) throws Exception {
        if(wjSubProgramLangDAO.selectSubProgramLang(langVO) == null) {
            wjSubProgramLangDAO.insertSubProgramLang(langVO);
        } else {
            wjSubProgramLangDAO.updateSubProgramLang(langVO);
        }
    }
}
