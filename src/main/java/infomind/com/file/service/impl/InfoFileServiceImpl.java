package infomind.com.file.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import infomind.com.file.dao.InfoFileDAO;
import infomind.com.file.service.InfoFileService;
import infomind.com.file.vo.InfoFileDetailVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("InfoFileService")
public class InfoFileServiceImpl extends EgovAbstractServiceImpl implements InfoFileService {


    @Resource(name="InfoFileDAO")
    private InfoFileDAO infoFileDAO;

    @Override
    public int getMaxFileSN(InfoFileDetailVO fvo) throws Exception {
        return infoFileDAO.getMaxFileSN(fvo);
    }

    @Override
    public int getOneFileSN(InfoFileDetailVO fvo) throws Exception {
        return infoFileDAO.getOneFileSN(fvo);
    }

    @Override
    public void insertInfoFileDetail(InfoFileDetailVO infoFileDetailVO) throws Exception {
        infoFileDAO.insertInfoFileDetail(infoFileDetailVO);
    }

    @Override
    public List<InfoFileDetailVO> getInfoFileList(InfoFileDetailVO infoFileDetailVO) throws Exception {
        return infoFileDAO.getInfoFileList(infoFileDetailVO);
    }

    @Override
    public InfoFileDetailVO getFileInf(InfoFileDetailVO fvo) throws Exception {
        return infoFileDAO.getFileInf(fvo);
    }

    @Override
    public void deleteInfoFileDetail(InfoFileDetailVO infoFileDetailVO) throws Exception {

        //실제파일 삭제
        infoFileDAO.deleteInfoFileDetail(infoFileDetailVO);
        //썸네일 삭제
        infoFileDAO.deleteInfoFileThumbnail(infoFileDetailVO);
    }

    @Override
    public void updateInfoFileDetail(InfoFileDetailVO infoFileDetailVO) throws Exception {
        infoFileDAO.updateInfoFileDetail(infoFileDetailVO);
    }

    @Override
    public void insertInfoFileThumbnail( List<InfoFileDetailVO> fvoList) throws Exception {

        if (fvoList.size() != 0) {


            for (InfoFileDetailVO fVo : fvoList) {

                infoFileDAO.insertInfoFileThumbnail(fVo);
            }
         }
        }

    @Override
    public void deleteInfoFileThumbnail(InfoFileDetailVO infoFileDetailVO) throws Exception {
        infoFileDAO.deleteInfoFileThumbnail(infoFileDetailVO);
    }

    @Override
    public List<InfoFileDetailVO> getInfoFileTempList(InfoFileDetailVO infoFileDetailVO) throws Exception {
        return infoFileDAO.getInfoFileTempList(infoFileDetailVO);
    }

    @Override
    public InfoFileDetailVO getFileImageId(InfoFileDetailVO fvo) throws Exception {
        return infoFileDAO.getFileImageId(fvo);
    }

    @Override
    public InfoFileDetailVO getFileThumbnailImageId(InfoFileDetailVO fvo) throws Exception {
        return infoFileDAO.getFileThumbnailImageId(fvo);
    }

    @Override
    public InfoFileDetailVO getFileThumbnailInf(InfoFileDetailVO fvo) throws Exception {
        return infoFileDAO.getFileThumbnailInf(fvo);
    }

    @Override
    public void insertInfoFileByte(InfoFileDetailVO vo) throws Exception {

        infoFileDAO.insertInfoFileByte(vo);
    }


    @Override
    public InfoFileDetailVO selectInfoFileByte(InfoFileDetailVO fvo) throws Exception {
        return infoFileDAO.selectInfoFileByte(fvo);
    }
}
