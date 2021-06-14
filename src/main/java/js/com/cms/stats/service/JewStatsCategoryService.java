package js.com.cms.stats.service;

import js.com.cms.stats.vo.JewStatsCategoryVO;

import java.util.List;

public interface JewStatsCategoryService {

    public JewStatsCategoryVO selectStatsCategory(JewStatsCategoryVO vo)throws Exception;

    public List<?> selectStatsCategoryList(JewStatsCategoryVO vo)throws Exception;

    public List<?> selectStatsCategorySearchList(JewStatsCategoryVO vo)throws Exception;

    public void insertStatsCategory(JewStatsCategoryVO vo)throws Exception;

    public void updateStatsCategory(JewStatsCategoryVO vo)throws Exception;

    public void deleteStatsCategory(JewStatsCategoryVO vo)throws Exception;

}
