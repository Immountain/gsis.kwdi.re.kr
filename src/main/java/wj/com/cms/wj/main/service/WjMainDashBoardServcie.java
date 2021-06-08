package wj.com.cms.wj.main.service;

import wj.com.cms.wj.main.vo.WjMainDashBoardVO;

import java.util.List;

public interface WjMainDashBoardServcie {

    /**
     * 일주일 써이트 방문 기록
     * @param vo
     * @return
     * @throws Exception
     */
    List<WjMainDashBoardVO> getWeekDaySiteMain(WjMainDashBoardVO vo) throws Exception;

    /**
     * 가입현황
     * @param vo
     * @return
     * @throws Exception
     */
    List<WjMainDashBoardVO> getPeopleCountSiteMain(WjMainDashBoardVO vo) throws Exception;

}
