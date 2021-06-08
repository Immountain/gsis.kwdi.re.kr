package infomind.com.cms.sec.rgm.service;


import infomind.com.cms.sec.rgm.vo.InfoAuthorGroup;
import infomind.com.cms.sec.rgm.vo.InfoAuthorGroupVO;

import java.util.List;

public interface InfoAuthorGroupService {

     int selectInfoAuthorGroupListTotCnt(InfoAuthorGroupVO infoAuthorGroupVO) throws Exception;

     List<InfoAuthorGroupVO> selectInfoAuthorGroupList(InfoAuthorGroupVO infoAuthorGroupVO) throws Exception;

     void insertInfoAuthorGroup(InfoAuthorGroup infoAuthorGroup) throws Exception;

     void updateInfoAuthorGroup(InfoAuthorGroup infoAuthorGroup) throws Exception;

     void deleteInfoAuthorGroup(InfoAuthorGroup infoAuthorGroup) throws Exception;

    }
