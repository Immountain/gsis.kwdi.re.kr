package gsis.com.cms.data.service;

import gsis.com.cms.data.vo.JewThemaFileHisVO;
import gsis.com.cms.thema.vo.JewThemaGroupVO;

import java.util.List;

public interface JejuDataService {


    List<JewThemaFileHisVO> selectJewThemaFileHis(JewThemaFileHisVO vo)throws Exception;
    void insertJewThemaFileHis(JewThemaGroupVO vo)throws Exception;

}
