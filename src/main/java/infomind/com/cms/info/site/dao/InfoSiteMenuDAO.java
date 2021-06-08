package infomind.com.cms.info.site.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.site.vo.InfoSiteMenuVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoSiteMenuDAO")
public class InfoSiteMenuDAO extends EgovComAbstractDAO {


    public InfoSiteMenuVO selectSiteMenuInfo(InfoSiteMenuVO searchVO) throws Exception{
        return  selectOne("InfoSiteMenuDAO.selectSiteMenuInfo", searchVO);
    }


    public void insertSiteMenu(InfoSiteMenuVO vo) throws Exception {
        insert("InfoSiteMenuDAO.insertSiteMenu", vo);
    }

    public void updateSiteMenu(InfoSiteMenuVO vo) throws Exception {
        insert("InfoSiteMenuDAO.updateSiteMenu", vo);
    }


    public Integer selectSiteMemuIdCount(InfoSiteMenuVO vo) throws Exception {
        return selectOne("InfoSiteMenuDAO.selectSiteMemuIdCount", vo);
    }

    public Integer selectSiteSlugCount(InfoSiteMenuVO vo) throws Exception {
        return selectOne("InfoSiteMenuDAO.selectSiteSlugCount", vo);
    }




    public List<InfoSiteMenuVO> selectSiteAllMenuList(InfoSiteMenuVO searchVO) throws Exception{
        return  selectList("InfoSiteMenuDAO.selectSiteAllMenuList", searchVO);
    }


    public List<?> selectSiteParentMenuList(InfoSiteMenuVO searchVO) throws Exception{
        return  selectList("InfoSiteMenuDAO.selectSiteParentMenuList", searchVO);
    }






}






    //저장

    //상세

    //삭제

