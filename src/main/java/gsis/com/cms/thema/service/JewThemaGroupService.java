package gsis.com.cms.thema.service;

import gsis.com.cms.thema.vo.JewThemaGroupVO;

import java.util.List;

public interface JewThemaGroupService {

    public JewThemaGroupVO selectThemaGroup(JewThemaGroupVO vo)throws Exception;

    public List<?> selectThemaGroupList(JewThemaGroupVO vo)throws Exception;

    public void insertThemaGroup(JewThemaGroupVO vo)throws Exception;

    public void updateThemaGroup(JewThemaGroupVO vo)throws Exception;

    public void deleteThemaGroup(JewThemaGroupVO vo)throws Exception;

}
