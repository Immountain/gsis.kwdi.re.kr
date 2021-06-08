package wj.com.cms.wj.stats.service;

import wj.com.cms.wj.stats.vo.WjStatsReportVO;

import java.util.List;

public interface WjStatsReportService {

    public WjStatsReportVO selectStatsReport(WjStatsReportVO vo) throws Exception;

    public Integer selectStatsReportTotalCount(WjStatsReportVO vo) throws Exception;

    public List<?> selectStatsReportList(WjStatsReportVO vo) throws Exception;

    public void insertStatsReport(WjStatsReportVO vo) throws Exception;

    public void updateStatsReport(WjStatsReportVO vo) throws Exception;

    public void deleteStatsReport(WjStatsReportVO vo) throws Exception;
}