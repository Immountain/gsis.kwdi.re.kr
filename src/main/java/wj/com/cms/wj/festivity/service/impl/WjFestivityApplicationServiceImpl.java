package wj.com.cms.wj.festivity.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wj.com.cms.wj.festivity.dao.WjFestivityApplicationDAO;
import wj.com.cms.wj.festivity.dao.WjFestivityApplicationLangDAO;
import wj.com.cms.wj.festivity.service.WjFestivityApplicationService;
import wj.com.cms.wj.festivity.vo.WjFestivityApplicationLangVO;
import wj.com.cms.wj.festivity.vo.WjFestivityApplicationVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjFestivityApplicationService")
public class WjFestivityApplicationServiceImpl extends EgovAbstractServiceImpl implements WjFestivityApplicationService {

    @Resource(name="WjFestivityApplicationDAO")
    private WjFestivityApplicationDAO wjFestivityApplicationDAO;

    @Resource(name="WjFestivityApplicationLangDAO")
    private WjFestivityApplicationLangDAO wjFestivityApplicationLangDAO;

    @Override
    public WjFestivityApplicationVO selectFestivityApplication(WjFestivityApplicationVO vo) throws Exception {

        WjFestivityApplicationVO wjFestivityApplicationVO = wjFestivityApplicationDAO.selectFestivityApplication(vo);
        String applicationStrDay = wjFestivityApplicationVO.getApplicationStrDt();
        String applicationEntDt = wjFestivityApplicationVO.getApplicationStrDt();

        String applicationStrDayYear = applicationStrDay.substring(0,4);
        String applicationStrDayMonth = applicationStrDay.substring(4,6);
        String applicationStrDayDay = applicationStrDay.substring(6,8);

        String applicationEntDayYear = applicationEntDt.substring(0,4);
        String applicationEntDayMonth = applicationEntDt.substring(4,6);
        String applicationEntDayDay = applicationEntDt.substring(6,8);

        wjFestivityApplicationVO.setApplicationStrDt(applicationStrDayYear + "-" + applicationStrDayMonth + "-" + applicationStrDayDay);
        wjFestivityApplicationVO.setApplicationEntDt(applicationEntDayYear + "-" + applicationEntDayMonth + "-" + applicationEntDayDay);

        return wjFestivityApplicationVO;
    }

    @Override
    public Integer selectFestivityApplicationTotalCount(WjFestivityApplicationVO vo) throws Exception {
        return wjFestivityApplicationDAO.selectFestivityApplicationTotalCount(vo);
    }

    @Override
    public List<?> selectFestivityApplicationList(WjFestivityApplicationVO vo) throws Exception {
        return wjFestivityApplicationDAO.selectFestivityApplicationList(vo);
    }

    @Override

    public void insertFestivityApplication(WjFestivityApplicationVO vo) throws Exception {

        wjFestivityApplicationDAO.insertFestivityApplication(vo);

        if(!CollectionUtils.isEmpty(vo.getListLang())){
            for(WjFestivityApplicationLangVO langVO: vo.getListLang()){
                langVO.setFestivityId(vo.getFestivityId());
                langVO.setApplicationId(vo.getApplicationId());
                updateFestivityApplicationLang(langVO);
            }
        }
    }

    @Override
    public void updateFestivityApplication(WjFestivityApplicationVO vo) throws Exception {

        wjFestivityApplicationDAO.updateFestivityApplication(vo);

        if(!CollectionUtils.isEmpty(vo.getListLang())){
            for(WjFestivityApplicationLangVO langVO : vo.getListLang()){
                langVO.setFestivityId(vo.getFestivityId());
                langVO.setApplicationId(vo.getApplicationId());
                updateFestivityApplicationLang(langVO);
            }
        }
    }

    public void updateFestivityApplicationLang(WjFestivityApplicationLangVO vo) throws Exception{

        if(wjFestivityApplicationLangDAO.selectFestivityApplicationLang(vo) == null){
            wjFestivityApplicationLangDAO.insertFestivityApplicationLang(vo);
        }else{
            wjFestivityApplicationLangDAO.updateFestivityApplicationLang(vo);
        }
    }

}
