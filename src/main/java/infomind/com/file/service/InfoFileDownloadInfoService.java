package infomind.com.file.service;


import infomind.com.file.vo.InfoFileDownloadInfoVO;

import java.util.List;


public interface InfoFileDownloadInfoService {

      InfoFileDownloadInfoVO selectInfoFileDownloadInfo(InfoFileDownloadInfoVO vo) throws Exception ;

      Integer selectInfoFileDownloadInfoTotalCount(InfoFileDownloadInfoVO vo) throws Exception;

      List<?> selectInfoFileDownloadInfoList(InfoFileDownloadInfoVO vo) throws Exception ;

      void getInfoFileDownloadInfo(InfoFileDownloadInfoVO vo) throws Exception;
}
