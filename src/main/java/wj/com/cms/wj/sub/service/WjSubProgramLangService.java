package wj.com.cms.wj.sub.service;

import wj.com.cms.wj.sub.vo.WjSubProgramLangVO;
import wj.com.cms.wj.sub.vo.WjSubProgramVO;

import java.util.List;

public interface WjSubProgramLangService {

    WjSubProgramLangVO selectSubProgramLang(WjSubProgramLangVO vo) throws Exception;

    Integer selectSubProgramLangTotalCount(WjSubProgramLangVO vo) throws Exception;

    List<?> selectSubProgramLangList(WjSubProgramLangVO vo) throws Exception;

    void insertSubProgramLang(WjSubProgramLangVO vo) throws Exception;

    void updateSubProgramLang(WjSubProgramLangVO vo) throws Exception;

    void updateSubProgramContentLang(WjSubProgramVO vo)throws Exception;


    }