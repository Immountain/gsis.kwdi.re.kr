package gsis.com.cms.data.service;

import gsis.com.cms.data.vo.JewThemaFileHisVO;
import gsis.com.cms.thema.vo.JewThemaGroupVO;

import java.util.List;

public interface JejuDataService {


    List<JewThemaFileHisVO> selectJewThemaFileHis(JewThemaFileHisVO vo)throws Exception;
    void insertJewThemaFileHis(JewThemaFileHisVO vo)throws Exception;


    JewThemaFileHisVO getSelectJewThemaFileHisView(JewThemaFileHisVO vo)throws Exception;

    void updateJewThemaFileHis(JewThemaFileHisVO vo)throws Exception;


    void getInsertJewUpdateKeep(JewThemaFileHisVO vo)throws Exception;


    void getInsertJewThemaVisit(JewThemaFileHisVO vo)throws Exception;


    List<JewThemaFileHisVO> getSelectMainUpdateList(JewThemaFileHisVO vo)throws Exception;

}
