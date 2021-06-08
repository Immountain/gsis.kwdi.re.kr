package infomind.com.cms.sec.ram.service.impl;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.sec.ram.dao.InfoAuthorManageDAO;
import infomind.com.cms.sec.ram.service.InfoAuthorManageService;
import infomind.com.cms.sec.ram.vo.InfoAuthorManage;
import infomind.com.cms.sec.ram.vo.InfoAuthorManageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoAuthorManageService")
public class InfoAuthorManageServiceImpl extends EgovAbstractServiceImpl implements InfoAuthorManageService {

    @Resource(name="InfoAuthorManageDAO")
    private InfoAuthorManageDAO infoAuthorManageDAO;

    @Override
    public List<InfoAuthorManageVO> selectInfoAuthorList(InfoAuthorManageVO infoAuthorManageVO) throws Exception {
        return infoAuthorManageDAO.selectInfoAuthorList(infoAuthorManageVO);
    }

    @Override
    public int selectInfoAuthorListTotCnt(InfoAuthorManageVO infoAuthorManageVO) throws Exception {
        return infoAuthorManageDAO.selectInfoAuthorListTotCnt(infoAuthorManageVO);
    }

    @Override
    public List<InfoAuthorManageVO> selectInfoAuthorAllList(InfoAuthorManageVO infoAuthorManageVO) throws Exception {
        return infoAuthorManageDAO.selectInfoAuthorAllList(infoAuthorManageVO);
    }

    @Override
    public void insertInfoAuthor(InfoAuthorManage infoAuthorManage) throws Exception {
        infoAuthorManageDAO.insertInfoAuthor(infoAuthorManage);

    }

    @Override
    public void updateInfoAuthor(InfoAuthorManage infoAuthorManage) throws Exception {
        infoAuthorManageDAO.updateInfoAuthor(infoAuthorManage);

    }

    @Override
    public void deleteInfoAuthor(InfoAuthorManage infoAuthorManage) throws Exception {
        infoAuthorManageDAO.deleteInfoAuthor(infoAuthorManage);

    }

    @Override
    public InfoAuthorManageVO selectInfoAuthor(InfoAuthorManageVO infoAuthorManageVO) throws Exception {
        InfoAuthorManageVO resultVO = infoAuthorManageDAO.selectInfoAuthor(infoAuthorManageVO);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;    }
}
