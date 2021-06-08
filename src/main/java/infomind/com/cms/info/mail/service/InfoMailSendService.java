package infomind.com.cms.info.mail.service;

import infomind.com.cms.info.mail.vo.InfoMailSendFormVO;
import infomind.com.cms.info.mail.vo.InfoMailSendVO;
import infomind.com.ext.vo.CmsSearchVO;

import java.util.List;

public interface InfoMailSendService {

    InfoMailSendVO select(InfoMailSendVO vo) throws Exception;

    Integer selectTotalCount(InfoMailSendVO vo) throws Exception;

    List<?> selectList(InfoMailSendVO vo) throws Exception;

    void insert(InfoMailSendVO vo) throws Exception;

    void update(InfoMailSendVO vo) throws Exception;

    void delete(InfoMailSendVO vo) throws Exception;

    List<?> selectComUserList(CmsSearchVO searchVO);

    void sendMail(InfoMailSendFormVO infoMailSendFormVO);
}
