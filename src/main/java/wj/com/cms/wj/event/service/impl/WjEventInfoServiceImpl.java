package wj.com.cms.wj.event.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.utils.file.InfoFileMngUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wj.com.cms.wj.event.dao.WjEventInfoDAO;
import wj.com.cms.wj.event.dao.WjEventInfoLangDAO;
import wj.com.cms.wj.event.service.WjEventInfoService;
import wj.com.cms.wj.event.vo.WjEventInfoLangVO;
import wj.com.cms.wj.event.vo.WjEventInfoVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjEventInfoService")
public class WjEventInfoServiceImpl extends EgovAbstractServiceImpl implements WjEventInfoService {

    @Resource(name="WjEventInfoDAO")
    private WjEventInfoDAO wjEventInfoDAO;

    @Resource(name="WjEventInfoLangDAO")
    private WjEventInfoLangDAO wjEventInfoLangDAO;

    @Resource(name = "InfoFileMngUtil")
    private InfoFileMngUtil infoFileMngUtil;

    @Override
    public WjEventInfoVO selectEventInfo(WjEventInfoVO vo) throws Exception {
        return wjEventInfoDAO.selectEventInfo(vo);
    }

    @Override
    public Integer selectEventInfoTotalCount(WjEventInfoVO vo) throws Exception {
        return wjEventInfoDAO.selectEventInfoTotalCount(vo);
    }

    @Override
    public List<?> selectEventInfoList(WjEventInfoVO vo) throws Exception {
        return wjEventInfoDAO.selectEventInfoList(vo);
    }

    @Override
    public void insertEventInfo(WjEventInfoVO vo) throws Exception {

        wjEventInfoDAO.insertEventInfo(vo);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getAtchFileId());

        if(!CollectionUtils.isEmpty(vo.getListLang())){
            for (WjEventInfoLangVO langVO : vo.getListLang()) {
                langVO.setEventId(vo.getEventId());
                langVO.setModId(vo.getModId());
                langVO.setRegId(vo.getRegId());
                updateEventInfoLang(langVO);
            }
        }
    }

    @Override
    public void updateEventInfo(WjEventInfoVO vo) throws Exception {
        wjEventInfoDAO.updateEventInfo(vo);

        //여기서 파일 경로 체인지
        infoFileMngUtil.copyFile(vo.getAtchFileId());

        if(!CollectionUtils.isEmpty(vo.getListLang())){
            for(WjEventInfoLangVO langVO : vo.getListLang()){
                langVO.setEventId(vo.getEventId());
                langVO.setModId(vo.getModId());
                langVO.setRegId(vo.getRegId());
                updateEventInfoLang(langVO);
            }
        }
    }

    @Override
    public void deleteEventInfo(WjEventInfoVO vo) throws Exception {
        wjEventInfoDAO.deleteEventInfo(vo);
    }

    public void updateEventInfoLang(WjEventInfoLangVO langVO) throws Exception {
        if(wjEventInfoLangDAO.selectEventInfoLang(langVO) == null){
            wjEventInfoLangDAO.insertEventInfoLang(langVO);
        }else{
            wjEventInfoLangDAO.updateEventInfoLang(langVO);
        }
    }
}
