package infomind.com.cms.info.page.service;

import infomind.com.cms.info.page.vo.InfoPageImageGroupVO;

import java.util.List;

public interface InfoPageImageService {


    int selectPageImageListTotCnt(InfoPageImageGroupVO searchVO) throws Exception;
    List<?> selectPageImageList(InfoPageImageGroupVO searchVO) throws Exception;
    void insertPageImage(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception;
    InfoPageImageGroupVO selecPageImageDetail(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception;
    void updatePageImage(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception;


    InfoPageImageGroupVO selecPageImageThumbnail(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception;


}
