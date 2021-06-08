package infomind.com.file.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.file.vo.InfoFileDetailVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoFileDAO")
public class InfoFileDAO extends EgovComAbstractDAO {


    public int getMaxFileSN(InfoFileDetailVO fvo) throws Exception {
        return (Integer) selectOne("InfoFileDAO.getMaxFileSN", fvo);
    }

    public int getOneFileSN(InfoFileDetailVO fvo) throws Exception {
        return (Integer) selectOne("InfoFileDAO.getOneFileSN", fvo);
    }



    public void insertInfoFileDetail(InfoFileDetailVO infoFileDetailVO) throws Exception{
        insert("InfoFileDAO.insertInfoFileDetail", infoFileDetailVO);
    }


    public List<InfoFileDetailVO> getInfoFileList(InfoFileDetailVO infoFileDetailVO) throws Exception{
        return (List<InfoFileDetailVO>) list("InfoFileDAO.selectInfoFileList", infoFileDetailVO);
    }


    public InfoFileDetailVO getFileInf(InfoFileDetailVO fvo) throws Exception {
        return (InfoFileDetailVO) selectOne("InfoFileDAO.getFileInf", fvo);
    }


    public void deleteInfoFileDetail(InfoFileDetailVO infoFileDetailVO) throws Exception{
        update("InfoFileDAO.deleteInfoFileDetail", infoFileDetailVO);
    }


    public void updateInfoFileDetail(InfoFileDetailVO infoFileDetailVO) throws Exception{
        update("InfoFileDAO.updateInfoFileDetail", infoFileDetailVO);
    }



    public void insertInfoFileThumbnail(InfoFileDetailVO infoFileDetailVO) throws Exception{
        insert("InfoFileDAO.insertInfoFileThumbnail", infoFileDetailVO);
    }


    public void deleteInfoFileThumbnail(InfoFileDetailVO infoFileDetailVO) throws Exception{
        delete("InfoFileDAO.deleteInfoFileThumbnail", infoFileDetailVO);
    }

    public List<InfoFileDetailVO> getInfoFileTempList(InfoFileDetailVO infoFileDetailVO) throws Exception{
        return (List<InfoFileDetailVO>) list("InfoFileDAO.selectInfoFileTempList", infoFileDetailVO);
    }

    public InfoFileDetailVO getFileImageId(InfoFileDetailVO fvo) throws Exception {
        return (InfoFileDetailVO) selectOne("InfoFileDAO.getFileImageId", fvo);
    }


    public InfoFileDetailVO getFileThumbnailImageId(InfoFileDetailVO fvo) throws Exception {
        return (InfoFileDetailVO) selectOne("InfoFileDAO.getFileThumbnailImageId", fvo);
    }

    public InfoFileDetailVO getFileThumbnailInf(InfoFileDetailVO fvo) throws Exception {
        return (InfoFileDetailVO) selectOne("InfoFileDAO.getFileThumbnailInf", fvo);
    }


    public void insertInfoFileByte(InfoFileDetailVO vo) throws Exception {
        insert("InfoFileDAO.insertInfoFileByte", vo);
    }


    public InfoFileDetailVO selectInfoFileByte(InfoFileDetailVO fvo) throws Exception {
        return (InfoFileDetailVO) selectOne("InfoFileDAO.selectInfoFileByte", fvo);

    }

}
