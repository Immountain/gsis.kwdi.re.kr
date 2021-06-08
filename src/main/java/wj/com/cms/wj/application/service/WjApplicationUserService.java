package wj.com.cms.wj.application.service;

import wj.com.cms.wj.application.vo.WjApplicationUserVO;

import java.util.List;

public interface WjApplicationUserService {

     WjApplicationUserVO selectApplicationUser(WjApplicationUserVO vo)throws Exception;

     Integer selectApplicationUserTotalCount(WjApplicationUserVO vo)throws Exception;

     List<?> selectApplicationUserList(WjApplicationUserVO vo)throws Exception;

     void insertApplicationUser(WjApplicationUserVO vo)throws Exception;

     void updateApplicationUser(WjApplicationUserVO vo) throws Exception;
}
