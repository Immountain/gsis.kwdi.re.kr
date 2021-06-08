package wj.com.cms.wj.program.service;

import wj.com.cms.wj.program.vo.WjProgramInfoLangVO;
import wj.com.cms.wj.program.vo.WjProgramInfoVO;

import java.util.List;

public interface WjProgramInfoLangService {

     WjProgramInfoLangVO selectProgramInfoLang(WjProgramInfoLangVO vo) throws Exception;

     Integer selectProgramInfoLangTotalCount(WjProgramInfoLangVO vo) throws Exception;

     List<?> selectProgramInfoLangList(WjProgramInfoLangVO vo) throws Exception;

     void insertProgramInfoLang(WjProgramInfoLangVO vo) throws Exception;

     void updateProgramInfoLang(WjProgramInfoLangVO vo) throws Exception;

     void updateProgramInfoContentLang(WjProgramInfoVO vo)throws Exception;
}
