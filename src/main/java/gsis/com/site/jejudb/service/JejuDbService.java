package gsis.com.site.jejudb.service;

import gsis.com.site.jejudb.vo.JejuDbVO;

import java.util.List;

public interface JejuDbService {

    List<JejuDbVO> getSelectJejuDbList(JejuDbVO vo)throws Exception;


    List<JejuDbVO> getSelectSearchJejuDbList(JejuDbVO vo)throws Exception;

}
