package infomind.com.cms.log.service;

import infomind.com.cms.log.vo.InfoCmsLogVO;

import java.util.List;


public interface InfoCmsLogService {
     void getLogInfoCmsLog(InfoCmsLogVO vo) throws Exception;

     public InfoCmsLogVO selectInfoCmsLog(InfoCmsLogVO vo) throws Exception;

     public Integer selectInfoCmsLogTotalCount(InfoCmsLogVO vo) throws Exception;

     public List<?> selectInfoCmsLogList(InfoCmsLogVO vo) throws Exception;
}
