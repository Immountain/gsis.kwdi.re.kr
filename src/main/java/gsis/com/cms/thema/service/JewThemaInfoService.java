package gsis.com.cms.thema.service;

import gsis.com.cms.thema.vo.JewThemaInfoVO;

import java.util.List;

public interface JewThemaInfoService {

    public JewThemaInfoVO selectThemaInfo(JewThemaInfoVO vo)throws Exception;

    public List<?> selectThemaInfoList(JewThemaInfoVO vo)throws Exception;

    public void insertThemaInfo(JewThemaInfoVO vo)throws Exception;

    public void updateThemaInfo(JewThemaInfoVO vo)throws Exception;

    public void deleteThemaInfo(JewThemaInfoVO vo)throws Exception;

}
