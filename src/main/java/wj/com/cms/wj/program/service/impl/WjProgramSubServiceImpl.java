package wj.com.cms.wj.program.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wj.com.cms.wj.program.dao.WjProgramSubDAO;
import wj.com.cms.wj.program.dao.WjProgramSubLangDAO;
import wj.com.cms.wj.program.service.WjProgramSubService;
import wj.com.cms.wj.program.vo.WjProgramSubLangVO;
import wj.com.cms.wj.program.vo.WjProgramSubVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjProgramSubService")
public class WjProgramSubServiceImpl extends EgovAbstractServiceImpl implements WjProgramSubService {

    @Resource(name="WjProgramSubDAO")
    private WjProgramSubDAO wjProgramSubDAO;

    @Resource(name="WjProgramSubLangDAO")
    private WjProgramSubLangDAO wjProgramSubLangDAO;

    @Resource(name="WjProgramSubIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;

    @Override
    public WjProgramSubVO selectProgramSub(WjProgramSubVO vo) throws Exception {
        return wjProgramSubDAO.selectProgramSub(vo);
    }

    @Override
    public Integer selectProgramSubTotalCount(WjProgramSubVO vo) throws Exception {
        return wjProgramSubDAO.selectProgramSubTotalCount(vo);
    }

    @Override
    public List<?> selectProgramSubList(WjProgramSubVO vo) throws Exception {
        return wjProgramSubDAO.selectProgramSubList(vo);
    }

    @Override
    public void insertProgramSub(WjProgramSubVO vo) throws Exception {

        String generateId = idgenService.getNextStringId();
        vo.setProgramSubSno(generateId);

        wjProgramSubDAO.insertProgramSub(vo);

        //파일 경로체인지
        infoFileMngUtil.copyFile(vo.getAtchFileId());

        if(!CollectionUtils.isEmpty(vo.getListLang())) {
            for(WjProgramSubLangVO langVO : vo.getListLang()) {
                langVO.setProgramSubSno(generateId);
                langVO.setProgramSno(vo.getProgramSno());
                langVO.setRegId(vo.getRegId());
                langVO.setFestivityId(vo.getFestivityId());
                updateProgramSubLang(langVO);
            }
        }

    }

    @Override
    public void updateProgramSub(WjProgramSubVO vo) throws Exception {
        wjProgramSubDAO.updateProgramSub(vo);

        infoFileMngUtil.copyFile(vo.getAtchFileId());

        if(CollectionUtils.isEmpty(vo.getListLang())){
            for(WjProgramSubLangVO langVO : vo.getListLang()){
                updateProgramSubLang(langVO);
            }
        }

    }

    @Override
    public void deleteProgramSub(WjProgramSubVO vo) throws Exception {
        wjProgramSubDAO.deleteProgramSub(vo);
    }

    private void updateProgramSubLang(WjProgramSubLangVO vo) throws Exception{
        if(wjProgramSubLangDAO.selectProgramSubLang(vo) == null){
            wjProgramSubLangDAO.insertProgramSubLang(vo);
        }else{
            wjProgramSubLangDAO.updateProgramSubLang(vo);
        }
    }
}
