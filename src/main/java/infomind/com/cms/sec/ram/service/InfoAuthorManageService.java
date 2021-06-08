package infomind.com.cms.sec.ram.service;


import infomind.com.cms.sec.ram.vo.InfoAuthorManage;
import infomind.com.cms.sec.ram.vo.InfoAuthorManageVO;

import java.util.List;

public interface InfoAuthorManageService {
     List<InfoAuthorManageVO> selectInfoAuthorList(InfoAuthorManageVO infoAuthorManageVO) throws Exception;

     int selectInfoAuthorListTotCnt(InfoAuthorManageVO infoAuthorManageVO)  throws Exception;

     List<InfoAuthorManageVO> selectInfoAuthorAllList(InfoAuthorManageVO infoAuthorManageVO) throws Exception;

     void insertInfoAuthor(InfoAuthorManage infoAuthorManage) throws Exception;

     void updateInfoAuthor(InfoAuthorManage infoAuthorManage) throws Exception;

     void deleteInfoAuthor(InfoAuthorManage infoAuthorManage) throws Exception;

     InfoAuthorManageVO selectInfoAuthor(InfoAuthorManageVO infoAuthorManageVO) throws Exception;


    }
