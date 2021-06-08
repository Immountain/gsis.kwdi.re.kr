package wj.com.cms.wj.event.service;

import wj.com.cms.wj.event.vo.WjEventInfoLangVO;

import java.util.List;

public interface WjEventInfoLangService {

     WjEventInfoLangVO selectEventInfoLang(WjEventInfoLangVO vo)throws Exception;

     Integer selectEventInfoLangTotalCount(WjEventInfoLangVO vo)throws Exception;

     List<?> selectEventInfoLangList(WjEventInfoLangVO vo)throws Exception;

     void insertEventInfoLang(WjEventInfoLangVO vo) throws Exception;

     void updateEventInfoLang(WjEventInfoLangVO vo) throws Exception;

}
