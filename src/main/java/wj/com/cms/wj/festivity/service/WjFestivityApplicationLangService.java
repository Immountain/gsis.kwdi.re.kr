package wj.com.cms.wj.festivity.service;

import wj.com.cms.wj.festivity.vo.WjFestivityApplicationLangVO;

import java.util.List;

public interface WjFestivityApplicationLangService {

    WjFestivityApplicationLangVO selectFestivityApplicationLang(WjFestivityApplicationLangVO vo) throws Exception;

    Integer selectFestivityApplicationLangTotalCount(WjFestivityApplicationLangVO vo) throws Exception;

    List<?> selectFestivityApplicationLangList(WjFestivityApplicationLangVO vo) throws Exception;

    void insertFestivityApplicationLang(WjFestivityApplicationLangVO vo) throws Exception;

    void updateFestivityApplicationLang(WjFestivityApplicationLangVO vo) throws Exception;
}
