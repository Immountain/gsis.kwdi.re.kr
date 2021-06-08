package infomind.com.cms.info.site.service;

import infomind.com.cms.info.site.vo.InfoSiteMenuGroupVO;

import java.util.List;




public interface InfoSiteMenuGroupService {
      InfoSiteMenuGroupVO selectInfoSiteMenuGroup(InfoSiteMenuGroupVO vo) throws Exception;

      Integer selectInfoSiteMenuGroupTotalCount(InfoSiteMenuGroupVO vo) throws Exception;

      List<?> selectSiteMenuGroupList(InfoSiteMenuGroupVO vo) throws Exception ;

      void insertSiteMenuGroup(InfoSiteMenuGroupVO vo) throws Exception ;

       void updateSiteMenuGroup(InfoSiteMenuGroupVO vo) throws Exception;

}
