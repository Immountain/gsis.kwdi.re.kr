package wj.com.cms.wj.jeju.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cmm.service.ComInfoSmtpService;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.stereotype.Service;
import wj.com.cms.wj.festivity.vo.WjFestivityApplicationVO;
import wj.com.cms.wj.jeju.dao.WjJejuPeopleDAO;
import wj.com.cms.wj.jeju.service.WjJejuPeopleService;
import wj.com.cms.wj.jeju.vo.WjJejuPeoplePicVO;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleSendMailVO;
import wj.com.cms.wj.jeju.vo.WjJejuPeopleVO;

import javax.annotation.Resource;
import java.util.List;

@Service("WjJejuPeopleService")
public class WjJejuPeopleServiceImpl extends EgovAbstractServiceImpl implements WjJejuPeopleService{

    @Resource(name="WjJejuPeopleDAO")
    private WjJejuPeopleDAO wjJejuPeopleDAO;

    @Resource(name="comInfoSmtpService")
    private ComInfoSmtpService comInfoSmtpService;



    @Override
    public WjJejuPeopleVO selectJejuPeople(WjJejuPeopleVO vo) throws Exception {
        return wjJejuPeopleDAO.selectJejuPeople(vo);
    }

    @Override
    public Integer selectJejuPeopleTotalCount(WjJejuPeopleVO vo) throws Exception {
        return wjJejuPeopleDAO.selectJejuPeopleTotalCount(vo);
    }

    @Override
    public List<?> selectJejuPeopleList(WjJejuPeopleVO vo) throws Exception {
        return wjJejuPeopleDAO.selectJejuPeopleList(vo);
    }

    @Override
    public void insertJejuPeople(WjJejuPeopleVO vo) throws Exception {
        wjJejuPeopleDAO.insertJejuPeople(vo);
    }

    @Override
    public void updateJejuPeople(WjJejuPeopleVO vo) throws Exception {
        wjJejuPeopleDAO.updateJejuPeople(vo);
    }

    @Override
    public WjJejuPeoplePicVO getSelectWjJejuPeoplePic(String id) throws Exception {
        return wjJejuPeopleDAO.getSelectWjJejuPeoplePic(id);
    }

    @Override
    public void sendMail(WjJejuPeopleSendMailVO wjJejuPeopleSendMail) {

        for (WjJejuPeopleVO people : wjJejuPeopleSendMail.getSendUserList()) {
            Email email = EmailBuilder.startingBlank()
                    .from("조동국", "jodongguk@gmail.com")
                    .to(people.getKorName(), people.getEmaill())
                    .withSubject(wjJejuPeopleSendMail.getSubject())
                    .withHTMLText(wjJejuPeopleSendMail.getText())
                    .buildEmail();

            comInfoSmtpService.sendMail(wjJejuPeopleSendMail.getSendAgentKey(), email);
        }
    }
}
