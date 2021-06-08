package wj.com.cms.wj.festivity.service.impl;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wj.com.cms.wj.festivity.dao.WjFestivityLangDAO;
import wj.com.cms.wj.festivity.dao.WjFestivityScheduleDAO;
import wj.com.cms.wj.festivity.dao.WjFestivityScheduleLangDAO;
import wj.com.cms.wj.festivity.service.WjFestivityScheduleService;
import wj.com.cms.wj.festivity.vo.WjFestivityLangVO;
import wj.com.cms.wj.festivity.vo.WjFestivityScheduleLangVO;
import wj.com.cms.wj.festivity.vo.WjFestivityScheduleVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjFestivityScheduleService")

public class WjFestivityScheduleServiceImpl extends EgovAbstractServiceImpl implements WjFestivityScheduleService {

    @Resource(name="WjFestivityScheduleIdGnrService")
    private EgovIdGnrService idgenService;

    @Resource(name="WjFestivityScheduleDAO")
    private WjFestivityScheduleDAO wjFestivityScheduleDAO;

    @Resource(name="WjFestivityScheduleLangDAO")
    private WjFestivityScheduleLangDAO wjFestivityScheduleLangDAO;

    @Override
    public WjFestivityScheduleVO selectFestivitySchedule(WjFestivityScheduleVO vo) throws Exception {

        WjFestivityScheduleVO wjFestivityScheduleVO = wjFestivityScheduleDAO.selectFestivitySchedule(vo);
        String scheduleDay = wjFestivityScheduleVO.getScheduleDay();

        String scheduleDayYear = scheduleDay.substring(0,4);
        String scheduleDayMonth = scheduleDay.substring(4,6);
        String scheduleDayDay = scheduleDay.substring(6,8);

        wjFestivityScheduleVO.setScheduleDay(scheduleDayYear + "-" + scheduleDayMonth + "-" + scheduleDayDay);

        return wjFestivityScheduleVO;
    }

    @Override
    public Integer selectFestivityScheduleTotalCount(WjFestivityScheduleVO vo) throws Exception {
        return wjFestivityScheduleDAO.selectFestivityScheduleTotalCount(vo);
    }

    @Override
    public List<?> selectFestivityScheduleList(WjFestivityScheduleVO vo) throws Exception {
        return wjFestivityScheduleDAO.selectFestivityScheduleList(vo);
    }

    @Override
    public void insertFestivitySchedule(WjFestivityScheduleVO vo) throws Exception {

        String generateId = idgenService.getNextStringId();
        vo.setScheduleSno(generateId);

        wjFestivityScheduleDAO.insertFestivitySchedule(vo);

        if(!CollectionUtils.isEmpty(vo.getListLang())) {
            for(WjFestivityScheduleLangVO langVO : vo.getListLang()) {
                langVO.setModId(vo.getModId());
                langVO.setRegId(vo.getRegId());
                langVO.setScheduleSno(generateId);
                langVO.setFestivityId(vo.getFestivityId());
                updateFestivityScheduleLang(langVO);
            }
        }
    }

    @Override
    public void updateFestivitySchedule(WjFestivityScheduleVO vo) throws Exception {

        wjFestivityScheduleDAO.updateFestivitySchedule(vo);

        if(!CollectionUtils.isEmpty(vo.getListLang())) {
            for(WjFestivityScheduleLangVO langVO : vo.getListLang()) {
                langVO.setFestivityId(vo.getFestivityId());
                langVO.setScheduleSno(vo.getScheduleSno());
                updateFestivityScheduleLang(langVO);
            }
        }
    }

    @Override
    public void deleteFestivitySchedule(WjFestivityScheduleVO vo) throws Exception {
        wjFestivityScheduleDAO.deleteFestivitySchedule(vo);
    }

    private void updateFestivityScheduleLang(WjFestivityScheduleLangVO langVO) throws Exception {

        if(wjFestivityScheduleLangDAO.selectFestivityScheduleLang(langVO) == null) {

            wjFestivityScheduleLangDAO.insertFestivityScheduleLang(langVO);
        } else {

            wjFestivityScheduleLangDAO.updateFestivityScheduleLang(langVO);
        }
    }

}
