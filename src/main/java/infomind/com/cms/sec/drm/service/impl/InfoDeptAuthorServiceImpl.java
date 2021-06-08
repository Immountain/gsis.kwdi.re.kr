package infomind.com.cms.sec.drm.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.cms.sec.drm.dao.InfoDeptAuthorDAO;
import infomind.com.cms.sec.drm.service.InfoDeptAuthorService;
import infomind.com.cms.sec.drm.vo.InfoDeptAuthor;
import infomind.com.cms.sec.drm.vo.InfoDeptAuthorVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoDeptAuthorService")
public class InfoDeptAuthorServiceImpl extends EgovAbstractServiceImpl implements InfoDeptAuthorService {

    @Resource(name="InfoDeptAuthorDAO")
    private InfoDeptAuthorDAO infoDeptAuthorDAO;

    @Override
    public List<InfoDeptAuthorVO> selectInfoDeptAuthorList(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception {
        return infoDeptAuthorDAO.selectInfoDeptAuthorList(infoDeptAuthorVO);
    }

    @Override
    public void insertInfoDeptAuthor(InfoDeptAuthor infoDeptAuthor) throws Exception {
        infoDeptAuthorDAO.insertInfoDeptAuthor(infoDeptAuthor);
    }

    @Override
    public void updateInfoDeptAuthor(InfoDeptAuthor infoDeptAuthor) throws Exception {
        infoDeptAuthorDAO.updateInfoDeptAuthor(infoDeptAuthor);
    }

    @Override
    public void deleteInfoDeptAuthor(InfoDeptAuthor infoDeptAuthor) throws Exception {
        infoDeptAuthorDAO.deleteInfoDeptAuthor(infoDeptAuthor);
    }

    @Override
    public int selectInfoDeptAuthorListTotCnt(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception {
        return infoDeptAuthorDAO.selectInfoDeptAuthorListTotCnt(infoDeptAuthorVO);
    }

    @Override
    public List<InfoDeptAuthorVO> selectInfoDeptList(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception {
        return infoDeptAuthorDAO.selectInfoDeptList(infoDeptAuthorVO);
    }

    @Override
    public int selectInfoDeptListTotCnt(InfoDeptAuthorVO infoDeptAuthorVO) throws Exception {
        return infoDeptAuthorDAO.selectInfoDeptListTotCnt(infoDeptAuthorVO);
    }

}
