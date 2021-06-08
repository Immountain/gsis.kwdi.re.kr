package infomind.com.cms.info.lang.service;

import infomind.com.cms.info.lang.vo.InfoLangCodeVO;

import java.util.List;

public interface InfoLangCodeService {

    InfoLangCodeVO selectLangCode(InfoLangCodeVO vo) throws Exception;

    Integer selectLangCodeTotalCount(InfoLangCodeVO vo)throws Exception;

    List<?> selectLangCodeList(InfoLangCodeVO vo) throws Exception;

    void insertLangCode(InfoLangCodeVO vo) throws Exception;

    void updateLangCode(InfoLangCodeVO vo) throws Exception;
}
