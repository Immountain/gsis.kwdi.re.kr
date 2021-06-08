package infomind.com.cms.info.layout.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.layout.vo.InfoLayoutInfoVO;
import infomind.com.cms.info.page.vo.InfoPageInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoLayoutInfoDAO")
public class InfoLayoutInfoDAO extends EgovComAbstractDAO {



    public int selectLayoutInfoListTotCnt(InfoLayoutInfoVO searchVO) throws Exception{
        return (Integer)selectOne("InfoLayoutInfoDAO.selectLayoutInfoListTotCnt", searchVO);
    }


    public List<?> selectLayoutInfoList(InfoLayoutInfoVO searchVO) throws Exception{
        return  list("InfoLayoutInfoDAO.selectLayoutInfoList", searchVO);
    }

    public void insertLayoutInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception{
        insert("InfoLayoutInfoDAO.insertLayoutInfo", infoLayoutInfoVO);
    }


    public InfoLayoutInfoVO selecLayoutInfoDetail(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception{
        return selectOne("InfoLayoutInfoDAO.selecLayoutInfoDetail", infoLayoutInfoVO);
    }

    public void updateLayoutInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception{
        update("InfoLayoutInfoDAO.updateLayoutInfo", infoLayoutInfoVO);
    }

    public void insertLayoutContent(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception{
        insert("InfoLayoutInfoDAO.insertLayoutContent", infoLayoutInfoVO);
    }


    public void updateLayoutContent(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception{
        update("InfoLayoutInfoDAO.updateLayoutContent", infoLayoutInfoVO);
    }

    public InfoLayoutInfoVO selecLayoutContentInfo(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception{
        return selectOne("InfoLayoutInfoDAO.selecLayoutContentInfo", infoLayoutInfoVO);
    }


    public void insertLayoutContentHis(InfoLayoutInfoVO infoLayoutInfoVO) throws Exception{
        insert("InfoLayoutInfoDAO.insertLayoutContentHis", infoLayoutInfoVO);
    }




}
