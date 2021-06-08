package infomind.com.cms.info.layout.service;

import infomind.com.cms.info.layout.vo.InfoLayoutInfoVO;

import java.util.List;

public interface InfoLayoutInfoService {



    int selectLayoutInfoListTotCnt(InfoLayoutInfoVO searchVO) throws Exception;
    List<?> selectLayoutInfoList(InfoLayoutInfoVO searchVO) throws Exception;
    void insertLayoutInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception;
    InfoLayoutInfoVO selecLayoutInfoDetail(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception;
    void updateLayoutInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception;


    InfoLayoutInfoVO selecLayoutContentInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception;
    InfoLayoutInfoVO getLayoutContentInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception;


}
