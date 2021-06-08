package wj.com.site.festivity.service;

import wj.com.site.festivity.vo.WjSiteFestivityApplicationVO;
import wj.com.site.festivity.vo.WjSiteFestivityInfoVO;
import wj.com.site.festivity.vo.WjSiteFestivityScheduleVO;

import java.util.List;

public interface WjSiteFestivityService {


    /**
     * 행사 정보
     * @param vo
     * @return
     * @throws Exception
     */
    WjSiteFestivityInfoVO getSelectSiteFestivityInfo(WjSiteFestivityInfoVO vo)throws Exception;

    /**
     * 행사 일정 정보
     * @param vo
     * @return
     * @throws Exception
     */
    List<WjSiteFestivityScheduleVO> getSelectSiteWjFestivitySchedule(WjSiteFestivityInfoVO vo)throws Exception;

    String getLastFesitivityId() throws Exception;
    String getLastFesitivityYear() throws Exception;

    /**
     * 행사 카테고리 리스트
     * @param vo
     * @return
     * @throws Exception
     */
    List<WjSiteFestivityInfoVO> getSelectSiteFestivityYearList(WjSiteFestivityInfoVO vo) throws Exception ;
    /**
     * 행사 리스트
     * @param vo
     * @return
     * @throws Exception
     */
    List<WjSiteFestivityInfoVO> getSelectSiteFestivityInfoList(WjSiteFestivityInfoVO vo) throws Exception ;


    /**
     * 참가신청 리스트
     * @param vo
     * @return
     * @throws Exception
     */
    List<WjSiteFestivityApplicationVO> getSelectSiteWjSiteFestivityApplicationList(WjSiteFestivityInfoVO vo) throws Exception;

    /**
     * 메인 행사 리스트
     * @param vo
     * @return
     * @throws Exception
     */
    List<WjSiteFestivityInfoVO> getMainFesitivityList(WjSiteFestivityInfoVO vo) throws Exception;


}
