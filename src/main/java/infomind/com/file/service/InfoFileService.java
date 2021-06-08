package infomind.com.file.service;


import infomind.com.file.vo.InfoFileDetailVO;

import java.util.List;

public interface InfoFileService {


    int getMaxFileSN(InfoFileDetailVO fvo) throws Exception;

    int getOneFileSN(InfoFileDetailVO fvo) throws Exception;


    void insertInfoFileDetail(InfoFileDetailVO infoFileDetailVO) throws Exception;


    List<InfoFileDetailVO> getInfoFileList(InfoFileDetailVO infoFileDetailVO) throws Exception;


    InfoFileDetailVO getFileInf(InfoFileDetailVO fvo) throws Exception ;

    void deleteInfoFileDetail(InfoFileDetailVO infoFileDetailVO) throws Exception;


    void updateInfoFileDetail(InfoFileDetailVO infoFileDetailVO) throws Exception;



    void insertInfoFileThumbnail( List<InfoFileDetailVO> filelist) throws Exception;


    void deleteInfoFileThumbnail(InfoFileDetailVO infoFileDetailVO) throws Exception;


    List<InfoFileDetailVO> getInfoFileTempList(InfoFileDetailVO infoFileDetailVO) throws Exception;


    InfoFileDetailVO getFileImageId(InfoFileDetailVO fvo) throws Exception ;


    InfoFileDetailVO getFileThumbnailImageId(InfoFileDetailVO fvo) throws Exception ;


    InfoFileDetailVO getFileThumbnailInf(InfoFileDetailVO fvo) throws Exception;



    void insertInfoFileByte(InfoFileDetailVO vo) throws Exception;



    InfoFileDetailVO selectInfoFileByte(InfoFileDetailVO fvo) throws Exception;

}
