package wj.com.cms.wj.festivity.service;

import wj.com.cms.wj.festivity.vo.WjFestivityScheduleLangVO;

import java.util.List;

public interface WjFestivityScheduleLangService {

    public WjFestivityScheduleLangVO selectFestivityScheduleLang(WjFestivityScheduleLangVO vo) throws Exception;

    public Integer selectFestivityScheduleLangTotalCount(WjFestivityScheduleLangVO vo) throws Exception;

    public List<?> selectFestivityScheduleLangList(WjFestivityScheduleLangVO vo) throws Exception;

    public void insertFestivityScheduleLang(WjFestivityScheduleLangVO vo) throws Exception;

    public void updateFestivityScheduleLang(WjFestivityScheduleLangVO vo) throws Exception;
}
