package infomind.com.cms.info.page.service;

import infomind.com.cms.info.page.vo.InfoPageInfoVO;

import java.util.List;

public interface InfoPageInfoService {


    int selectPageListTotCnt(InfoPageInfoVO searchVO) throws Exception;


    List<?> selectPageInfoList(InfoPageInfoVO searchVO) throws Exception;


    void insertPageInfo(InfoPageInfoVO infoPageInfoVO) throws Exception;

    void insertPageContent(InfoPageInfoVO infoPageInfoVO) throws Exception;

    InfoPageInfoVO selecPageInfoDetail(InfoPageInfoVO infoPageInfoVO) throws Exception;


    void updatePageInfo(InfoPageInfoVO infoPageInfoVO) throws Exception;

    void updatePageInfoDetail(InfoPageInfoVO infoPageInfoVO) throws Exception;

    InfoPageInfoVO selectPageInfoHis(InfoPageInfoVO vo)throws Exception;

    void updatePageInfoHis(InfoPageInfoVO vo)throws Exception;

    void insertPageInfoHis(InfoPageInfoVO infoPageInfoVO) throws Exception;

    List<?> selectPageInfoHisList(InfoPageInfoVO searchVO) throws Exception;

    int selectPageListHisTotCnt(InfoPageInfoVO searchVO) throws Exception;



    List<?> getSelectPageInfoAllList(InfoPageInfoVO searchVO) throws Exception;


}
