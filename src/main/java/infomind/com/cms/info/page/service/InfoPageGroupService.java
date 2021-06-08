package infomind.com.cms.info.page.service;

import infomind.com.cms.info.page.vo.InfoPageGroupVO;

import java.util.List;

public interface InfoPageGroupService {

        InfoPageGroupVO selectPageGroup(InfoPageGroupVO vo) throws Exception;

        List<?> selectPageGroupList(InfoPageGroupVO vo) throws Exception;

        int selectPageGroupTotalCount(InfoPageGroupVO vo) throws Exception;

        void insertPageGroup(InfoPageGroupVO vo) throws Exception;

        void updatePageGroup(InfoPageGroupVO vo) throws Exception ;


        List<?> getSelectPageGroupAll(InfoPageGroupVO vo) throws Exception;


}
