package infomind.com.cms.info.site.service;

import infomind.com.cms.info.site.vo.InfoSiteMenuVO;


import java.util.List;

public interface InfoSiteMenuService {


     /**
      * 메뉴 단건 조회
      * @param searchVO
      * @return
      * @throws Exception
      */
     InfoSiteMenuVO selectSiteMenuInfo(InfoSiteMenuVO searchVO) throws Exception;

     /**
      * 메뉴 단건 저장 처리
      * @param vo
      * @throws Exception
      */
     void insertSiteMenu(InfoSiteMenuVO vo) throws Exception ;

     /**
      * 메뉴 단건 업데이트 처리
      * @param vo
      * @throws Exception
      */
     void updateSiteMenu(InfoSiteMenuVO vo) throws Exception;

     /**
      * 메뉴 아이디 중복 체크
      * @param vo
      * @return
      * @throws Exception
      */
     Integer selectSiteMemuIdCount(InfoSiteMenuVO vo) throws Exception ;

     /**
      * 슬러거 중복 체크
      * @param vo
      * @return
      * @throws Exception
      */
     Integer selectSiteSlugCount(InfoSiteMenuVO vo) throws Exception ;

     /**
      * 아빠 메뉴 리스트 가져오기
      * @param searchVO
      * @return
      * @throws Exception
      */
     List<?> selectSiteParentMenuList(InfoSiteMenuVO searchVO) throws Exception;


     public List<InfoSiteMenuVO> selectSiteAllMenuList(InfoSiteMenuVO searchVO) throws Exception;
     public List<InfoSiteMenuVO> getSiteAllMenuList(InfoSiteMenuVO searchVO) throws Exception;


    void updateList(List<InfoSiteMenuVO> list, String pId) throws Exception;

    String saveSiteMenu(InfoSiteMenuVO vo) throws Exception;
}
