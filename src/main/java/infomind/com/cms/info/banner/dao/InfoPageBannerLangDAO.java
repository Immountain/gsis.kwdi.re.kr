package infomind.com.cms.info.banner.dao;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import infomind.com.cms.info.banner.vo.InfoPageBannerLangVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InfoPageBannerLangDAO")
public class InfoPageBannerLangDAO extends EgovComAbstractDAO {

    public InfoPageBannerLangVO selectPageBannerLang(InfoPageBannerLangVO vo) throws Exception {
        return selectOne("InfoPageBannerLangDAO.selectPageBannerLang", vo);
    }

    public Integer selectPageBannerLangTotalCount(InfoPageBannerLangVO vo) throws Exception {
        return selectOne("InfoPageBannerLangDAO.selectPageBannerLangTotalCount", vo);
    }

    public List<?> selectPageBannerLangList(InfoPageBannerLangVO vo) throws Exception {
        return selectList("InfoPageBannerLangDAO.selectPageBannerLangList", vo);
    }

    public void insertPageBannerLang(InfoPageBannerLangVO vo) throws Exception {
        insert("InfoPageBannerLangDAO.insertPageBannerLang", vo);
    }

    public void updatePageBannerLang(InfoPageBannerLangVO vo) throws Exception {
        insert("InfoPageBannerLangDAO.updatePageBannerLang", vo);
    }

}
