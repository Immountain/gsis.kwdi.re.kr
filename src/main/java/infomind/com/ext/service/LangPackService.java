package infomind.com.ext.service;

import infomind.com.cms.info.lang.vo.InfoLangCodeVO;
import infomind.com.ext.vo.LangPackVO;
import wj.com.cms.wj.event.vo.WjEventInfoLangVO;
import wj.com.cms.wj.event.vo.WjEventInfoVO;
import wj.com.cms.wj.festivity.vo.*;
import wj.com.cms.wj.program.vo.WjProgramInfoLangVO;
import wj.com.cms.wj.program.vo.WjProgramInfoVO;
import wj.com.cms.wj.program.vo.WjProgramSubLangVO;
import wj.com.cms.wj.program.vo.WjProgramSubVO;
import wj.com.cms.wj.stats.vo.WjStatsReportLangVO;
import wj.com.cms.wj.stats.vo.WjStatsReportVO;
import wj.com.cms.wj.sub.vo.WjSubProgramLangVO;
import wj.com.cms.wj.sub.vo.WjSubProgramVO;

import java.util.List;

public interface LangPackService {

    /**
     * 총언어 리스트
    * @return
     * @throws Exception
     */
    List<LangPackVO> getSelectLangPackList() throws Exception;

    /**
     * 대회 다국어 언어팻
     * @param searchVO
     * @return
     * @throws Exception
     */
    List<WjFestivityLangVO> getSelectWjFestivityLang(WjFestivityInfoVO searchVO) throws Exception ;

    /**
     * 일정 다국어 언어팩
     * @param searchVO
     * @return
     * @throws Exception
     */
    List<WjFestivityScheduleLangVO> getSelectWjFestivityScheduleLang(WjFestivityScheduleVO searchVO) throws Exception ;


    /**
     * 주요프로그램 언어팩
     * @param searchVO
     * @return
     * @throws Exception
     */
    List<WjProgramInfoLangVO> getSelectWjProgramInfoLang(WjProgramInfoVO searchVO) throws Exception ;

    /**
     * 프로그램서브내용 언어팩
     * @param searchVO
     * @return
     * @throws Exception
     */
    List<WjProgramSubLangVO> getSelectWjProgramSubLang(WjProgramSubVO searchVO) throws Exception ;

    /**
     * 제주도민현황 언어팩
     * @param searchVO
     * @return
     * @throws Exception
     */
     List<WjStatsReportLangVO> getSelectWjStatsReportLang(WjStatsReportVO searchVO) throws Exception;

    /**
     * 부대프로그램 언어팩
     * @param searchVO
     * @return
     * @throws Exception
     */
     List<WjSubProgramLangVO> getWjSubProgramLang(WjSubProgramVO searchVO)throws Exception;


    /**
     * 참가신청설정 언어팩
     * @param searchVO
     * @return
     * @throws Exception
     */
     List<WjFestivityApplicationLangVO> getWjFestivityApplicationLang(WjFestivityApplicationVO searchVO)throws Exception;

    /**
     * 이벤트 언어팩
     * @param searchVO
     * @return
     * @throws Exception
     */
     List<WjEventInfoLangVO> getWjEventLang(WjEventInfoVO searchVO)throws Exception;

    /**
     * 인포마인드 코드 언어팩
      * @param searchVO
     * @return
     * @throws Exception
     */
     List<InfoLangCodeVO> getInfoLangCode(InfoLangCodeVO searchVO)throws Exception;



    /**
     * 제주인 활동분야 코드
     * @param searchVO
     * @return
     * @throws Exception
     */
    List<InfoLangCodeVO> getInfoActLangCode(InfoLangCodeVO searchVO)throws Exception;




}
