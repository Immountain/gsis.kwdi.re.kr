package wj.com.cms.wj.festivity.service.impl;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import infomind.com.cms.info.banner.vo.InfoPageBannerLangVO;
import infomind.com.ext.service.LangPackService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wj.com.cms.wj.festivity.dao.WjFestivityInfoDAO;
import wj.com.cms.wj.festivity.dao.WjFestivityLangDAO;
import wj.com.cms.wj.festivity.service.WjFestivityInfoService;
import wj.com.cms.wj.festivity.vo.WjFestivityInfoVO;
import wj.com.cms.wj.festivity.vo.WjFestivityLangVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("WjFestivityInfoService")
public class WjFestivityInfoServiceImpl extends EgovAbstractServiceImpl implements WjFestivityInfoService {

    @Resource(name = "EgovCmmUseService")
    private EgovCmmUseService cmmUseService;

    @Resource(name="WjFestivityInfoDAO")
    private WjFestivityInfoDAO wjFestivityInfoDAO;

    @Resource(name="WjFestivityLangDAO")
    private WjFestivityLangDAO wjFestivityLangDAO;

    @Resource(name = "LangPackService")
    private LangPackService langPackService;

    @Override
    public WjFestivityInfoVO selectFestivityInfo(WjFestivityInfoVO vo) throws Exception {

        WjFestivityInfoVO wjFestivityInfoVO = wjFestivityInfoDAO.selectFestivityInfo(vo);

        wjFestivityInfoVO.setListLang(langPackService.getSelectWjFestivityLang(wjFestivityInfoVO));

        String pageStrDay = wjFestivityInfoVO.getPageStrDay();
        String pageEndDay = wjFestivityInfoVO.getPageEndDay();
        String festivityStrDay = wjFestivityInfoVO.getFestivityStrDay();
        String festivityEndDay = wjFestivityInfoVO.getFestivityEndDay();

        String pageStrDayYear = pageStrDay.substring(0,4);
        String pageEndDayYear = pageEndDay.substring(0,4);
        String festivityStrYear = festivityStrDay.substring(0,4);
        String festivityEndYear = festivityEndDay.substring(0,4);

        String pageStrDayMonth = pageStrDay.substring(4,6);
        String pageEndDayMonth = pageEndDay.substring(4,6);
        String festivityStrMonth = festivityStrDay.substring(4,6);
        String festivityEndMonth = festivityEndDay.substring(4,6);

        String pageStrDayDay = pageStrDay.substring(6,8);
        String pageEndDayDay = pageEndDay.substring(6,8);
        String festivityInfoStrDay = festivityStrDay.substring(6,8);
        String festivityInfoEndDay = festivityEndDay.substring(6,8);


        wjFestivityInfoVO.setPageStrDay(pageStrDayYear + "-" + pageStrDayMonth + "-"+ pageStrDayDay);
        wjFestivityInfoVO.setPageEndDay(pageEndDayYear + "-" + pageEndDayMonth + "-"+ pageEndDayDay);
        wjFestivityInfoVO.setFestivityStrDay(festivityStrYear + "-" + festivityStrMonth + "-"+ festivityInfoStrDay);
        wjFestivityInfoVO.setFestivityEndDay(festivityEndYear + "-" + festivityEndMonth + "-"+ festivityInfoEndDay);

        return wjFestivityInfoVO;
    }

    @Override
    public Integer selectFestivityInfoTotalCount(WjFestivityInfoVO vo) throws Exception {
        return wjFestivityInfoDAO.selectFestivityInfoTotalCount(vo);
    }

    @Override
    public List<?> selectFestivityInfoList(WjFestivityInfoVO vo) throws Exception {
        return wjFestivityInfoDAO.selectFestivityInfoList(vo);
    }

    @Override

    public void insertFestivityInfo(WjFestivityInfoVO vo) throws Exception {

        wjFestivityInfoDAO.insertFestivityInfo(vo);

        if(!CollectionUtils.isEmpty(vo.getListLang())) {
            for(WjFestivityLangVO langVO : vo.getListLang()) {
                langVO.setModId(vo.getModId());
                langVO.setRegId(vo.getRegId());
                langVO.setFestivityId(vo.getFestivityId());
                updateFestivityInfoLang(langVO);
            }
        }

    }

    @Override
    public void updateFestivityInfo(WjFestivityInfoVO vo) throws Exception {

        String generateId = vo.getFestivityId();
        wjFestivityInfoDAO.updateFestivityInfo(vo);

        if(!CollectionUtils.isEmpty(vo.getListLang())) {
            for(WjFestivityLangVO langVO : vo.getListLang()) {
                langVO.setFestivityId(generateId);
                updateFestivityInfoLang(langVO);
            }
        }
    }

    @Override
    public void deleteFestivityInfo(WjFestivityInfoVO vo) throws Exception {
        wjFestivityInfoDAO.deleteFestivityInfo(vo);
    }

    @Override
    public List<?> selectFestivityInfoSearchList(WjFestivityInfoVO vo) throws Exception {
        return wjFestivityInfoDAO.selectFestivityInfoSearchList(vo);
    }


    private void updateFestivityInfoLang(WjFestivityLangVO langVO) throws Exception {
        if(wjFestivityLangDAO.selectFestivityLang(langVO) == null) {
            wjFestivityLangDAO.insertFestivityLang(langVO);
        } else {
            wjFestivityLangDAO.updateFestivityLang(langVO);
        }
    }
}
