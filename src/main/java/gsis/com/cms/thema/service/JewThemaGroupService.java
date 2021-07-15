package gsis.com.cms.thema.service;

import gsis.com.cms.thema.vo.JewThemaGroupVO;

import java.util.List;

public interface JewThemaGroupService {

    JewThemaGroupVO selectThemaGroup(JewThemaGroupVO vo)throws Exception;

    List<?> selectThemaGroupList(JewThemaGroupVO vo)throws Exception;

    void insertThemaGroup(JewThemaGroupVO vo)throws Exception;

    void updateThemaGroup(JewThemaGroupVO vo)throws Exception;

    void deleteThemaGroup(JewThemaGroupVO vo)throws Exception;

}
