package wj.com.site.jejupeople.service;

import org.springframework.web.multipart.MultipartFile;
import wj.com.cms.wj.jeju.vo.WjJejuPeoplePicVO;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleVO;

public interface WjSiteJejuPeopleService {


    String saveMber(WjJejuPeopleVO mberManageVO, MultipartFile file)throws Exception;

    WjJejuPeopleVO selectJejuPeople(String id) throws Exception;

    WjJejuPeoplePicVO getSelectWjJejuPeoplePic(String userId)throws Exception;
}
