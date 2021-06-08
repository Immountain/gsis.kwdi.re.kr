package infomind.com.cms.info.mail.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cmm.service.ComInfoSmtpService;
import infomind.com.cms.info.mail.dao.InfoMailSendDAO;
import infomind.com.cms.info.mail.service.InfoMailSendService;
import infomind.com.cms.info.mail.vo.InfoMailSendFormVO;
import infomind.com.cms.info.mail.vo.InfoMailSendVO;
import infomind.com.ext.vo.CmsSearchVO;
import infomind.com.ext.vo.ComvUserMasterVO;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoMailSendService")
public class InfoMailSendServiceImpl extends EgovAbstractServiceImpl implements InfoMailSendService {

    @Resource(name="comInfoSmtpService")
    private ComInfoSmtpService comInfoSmtpService;

    @Resource(name="InfoMailSendDAO")
    private InfoMailSendDAO infoMailSendDAO;

    @Override
    public InfoMailSendVO select(InfoMailSendVO vo) throws Exception {
        return infoMailSendDAO.select(vo);
    }

    @Override
    public Integer selectTotalCount(InfoMailSendVO vo) throws Exception {
        return infoMailSendDAO.selectTotalCount(vo);
    }

    @Override
    public List<?> selectList(InfoMailSendVO vo) throws Exception {
        return infoMailSendDAO.selectList(vo);
    }

    @Override
    public void insert(InfoMailSendVO vo) throws Exception {
        infoMailSendDAO.insert(vo);
    }

    @Override
    public void update(InfoMailSendVO vo) throws Exception {
        infoMailSendDAO.update(vo);
    }

    @Override
    public void delete(InfoMailSendVO vo) throws Exception {
        infoMailSendDAO.delete(vo);
    }

    @Override
    public List<?> selectComUserList(CmsSearchVO searchVO) {
        return infoMailSendDAO.selectComUserList(searchVO);
    }

    @Override
    public void sendMail(InfoMailSendFormVO infoMailSendFormVO) {
        for (ComvUserMasterVO vo : infoMailSendFormVO.getSendUserList()) {
            Email email = EmailBuilder.startingBlank()
                    .from("조동국", "jodongguk@gmail.com")
                    .to(vo.getUserNm(), vo.getUserEmail())
                    .withSubject(infoMailSendFormVO.getSubject())
                    .withHTMLText(infoMailSendFormVO.getText())
                    .buildEmail();

            comInfoSmtpService.sendMail(infoMailSendFormVO.getSendAgentKey(), email);
        }
    }
}
