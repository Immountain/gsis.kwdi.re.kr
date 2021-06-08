package wj.com.cms.wj.festivity.service;


import wj.com.cms.wj.festivity.vo.WjFestivityLangVO;

import java.util.List;

public interface WjFestivityLangService {

     WjFestivityLangVO selectFestivityLang(WjFestivityLangVO vo) throws Exception;

     Integer selectFestivityLangTotalCount(WjFestivityLangVO vo)throws Exception;

     List<?> selectFestivityLangList(WjFestivityLangVO vo) throws Exception;

     void insertFestivityLang(WjFestivityLangVO vo)throws Exception;

     void updateFestivityLang(WjFestivityLangVO vo)throws Exception;
}
