package gsis.com.cms.mdis.service;

import gsis.com.cms.mdis.vo.JewMdisVO;

import java.util.List;

public interface JewMdisService {

     JewMdisVO selectMdis(JewMdisVO vo)throws Exception;

     List<?> selectMdisList(JewMdisVO vo)throws Exception;

     void insertMdis(JewMdisVO vo)throws Exception;

     void updateMdis(JewMdisVO vo)throws Exception;

     void deleteMdis(JewMdisVO vo)throws Exception;


     List<?> getSelectDashboardMdis(JewMdisVO vo)throws Exception;

     }
