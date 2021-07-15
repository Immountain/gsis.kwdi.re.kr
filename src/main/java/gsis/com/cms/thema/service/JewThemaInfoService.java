package gsis.com.cms.thema.service;

import gsis.com.cms.thema.vo.JewThemaInfoVO;

import java.util.List;

public interface JewThemaInfoService {

    JewThemaInfoVO selectThemaInfo(JewThemaInfoVO vo)throws Exception;

    List<?> selectThemaInfoList(JewThemaInfoVO vo)throws Exception;

    void insertThemaInfo(JewThemaInfoVO vo)throws Exception;

    void updateThemaInfo(JewThemaInfoVO vo)throws Exception;

    void deleteThemaInfo(JewThemaInfoVO vo)throws Exception;

}
