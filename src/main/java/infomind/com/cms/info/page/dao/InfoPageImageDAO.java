package infomind.com.cms.info.page.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.page.vo.InfoPageImageGroupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoPageImageDAO")
public class InfoPageImageDAO extends EgovComAbstractDAO {


    public int selectPageImageListTotCnt(InfoPageImageGroupVO searchVO) throws Exception{
        return (Integer)selectOne("InfoPageImageDAO.selectPageImageListTotCnt", searchVO);
    }


    public List<?> selectPageImageList(InfoPageImageGroupVO searchVO) throws Exception{
        return  list("InfoPageImageDAO.selectPageImageList", searchVO);
    }

    public void insertPageImage(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception{
        insert("InfoPageImageDAO.insertPageImage", infoPageImageGroupVO);
    }



    public InfoPageImageGroupVO selecPageImageDetail(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception{
        return selectOne("InfoPageImageDAO.selecPageImageDetail", infoPageImageGroupVO);
    }




    public void updatePageImage(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception{
        insert("InfoPageImageDAO.updatePageImage", infoPageImageGroupVO);
    }

    public InfoPageImageGroupVO selecPageImageThumbnail(InfoPageImageGroupVO infoPageImageGroupVO) throws Exception{
        return selectOne("InfoPageImageDAO.selecPageImageThumbnail", infoPageImageGroupVO);
    }





}
