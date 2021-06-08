package infomind.com.cms.info.page.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.ccm.cca.vo.InfoCmmnCodeVO;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoPageInfoDAO")
public class InfoPageInfoDAO extends EgovComAbstractDAO {



    public int selectPageListTotCnt(InfoPageInfoVO searchVO) throws Exception{
        return (Integer)selectOne("InfoPageInfoDAO.selectPageListTotCnt", searchVO);
    }


    public List<?> selectPageInfoList(InfoPageInfoVO searchVO) throws Exception{
        return  list("InfoPageInfoDAO.selectPageInfoList", searchVO);
    }

    public void insertPageInfo(InfoPageInfoVO infoPageInfoVO) throws Exception{
        insert("InfoPageInfoDAO.insertPageInfo", infoPageInfoVO);
    }

    public void insertPageContent(InfoPageInfoVO infoPageInfoVO) throws Exception{
        insert("InfoPageInfoDAO.insertPageContent", infoPageInfoVO);
    }


    public InfoPageInfoVO selecPageInfoDetail(InfoPageInfoVO infoPageInfoVO) throws Exception{
        return selectOne("InfoPageInfoDAO.selecPageInfoDetail", infoPageInfoVO);
    }




    public void updatePageInfo(InfoPageInfoVO infoPageInfoVO) throws Exception{
        insert("InfoPageInfoDAO.updatePageInfo", infoPageInfoVO);
    }

    public void updatePageInfoDetail(InfoPageInfoVO infoPageInfoVO) throws Exception{
        insert("InfoPageInfoDAO.updatePageInfoDetail", infoPageInfoVO);
    }


    public void insertPageInfoHis(InfoPageInfoVO infoPageInfoVO) throws Exception{
        insert("InfoPageInfoDAO.insertPageInfoHis", infoPageInfoVO);
    }

    public InfoPageInfoVO selectPageInfoHis(InfoPageInfoVO vo)throws Exception{
        return selectOne("InfoPageInfoDAO.selectPageInfoHis",vo);
    }

    public void updatePageInfoHis(InfoPageInfoVO vo)throws Exception{
        insert("InfoPageInfoDAO.updatePageInfoHis",vo);
    }

    public List<?> selectPageInfoHisList(InfoPageInfoVO searchVO) throws Exception{
        return  list("InfoPageInfoDAO.selectPageInfoHisList", searchVO);
    }
    public int selectPageListHisTotCnt(InfoPageInfoVO searchVO) throws Exception{
        return (Integer)selectOne("InfoPageInfoDAO.selectPageListHisTotCnt", searchVO);
    }


    public List<?> getSelectPageInfoAllList(InfoPageInfoVO searchVO) throws Exception{
        return  list("InfoPageInfoDAO.getSelectPageInfoAllList", searchVO);
    }





}
