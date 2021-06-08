package infomind.com.file.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

import infomind.com.file.vo.InfoFileDownloadInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoFileDownloadInfoDAO")
public class InfoFileDownloadInfoDAO extends EgovComAbstractDAO {

    public InfoFileDownloadInfoVO selectInfoFileDownloadInfo(InfoFileDownloadInfoVO vo) throws Exception {
        return selectOne("InfoFileDownloadInfoDAO.selectInfoFileDownloadInfo", vo);
    }

    public Integer selectInfoFileDownloadInfoTotalCount(InfoFileDownloadInfoVO vo) throws Exception {
        return selectOne("InfoFileDownloadInfoDAO.selectInfoFileDownloadInfoTotalCount", vo);
    }

    public List<?> selectInfoFileDownloadInfoList(InfoFileDownloadInfoVO vo) throws Exception {
        return selectList("InfoFileDownloadInfoDAO.selectInfoFileDownloadInfoList", vo);
    }

    public void insertInfoFileDownloadInfo(InfoFileDownloadInfoVO vo) throws Exception {
        insert("InfoFileDownloadInfoDAO.insertInfoFileDownloadInfo", vo);
    }

}
