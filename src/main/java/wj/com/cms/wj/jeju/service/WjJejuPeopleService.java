package wj.com.cms.wj.jeju.service;

import wj.com.cms.wj.jeju.vo.WjJejuPeoplePicVO;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleSendMailVO;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleVO;

import java.util.List;

public interface WjJejuPeopleService {

    WjJejuPeopleVO selectJejuPeople(WjJejuPeopleVO vo) throws Exception;

    Integer selectJejuPeopleTotalCount(WjJejuPeopleVO vo) throws Exception;

    List<?> selectJejuPeopleList(WjJejuPeopleVO vo) throws Exception;

    void insertJejuPeople(WjJejuPeopleVO vo) throws Exception;

    void updateJejuPeople(WjJejuPeopleVO vo) throws Exception;

    WjJejuPeoplePicVO getSelectWjJejuPeoplePic(String id)throws Exception;

    void sendMail(WjJejuPeopleSendMailVO wjJejuPeopleSendMail);
}
