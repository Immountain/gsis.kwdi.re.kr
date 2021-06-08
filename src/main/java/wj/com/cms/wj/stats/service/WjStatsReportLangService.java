package wj.com.cms.wj.stats.service;

import wj.com.cms.wj.stats.vo.WjStatsReportLangVO;

import java.util.List;

public interface WjStatsReportLangService {

     WjStatsReportLangVO selectStatsReportLang(WjStatsReportLangVO vo) throws Exception;

     Integer selectStatsReportLangTotalCount(WjStatsReportLangVO vo) throws Exception;

     List<?> selectStatsReportLangList(WjStatsReportLangVO vo) throws Exception;

     void insertStatsReportLang(WjStatsReportLangVO vo) throws Exception;

     void updateStatsReportLang(WjStatsReportLangVO vo) throws Exception;
}
