package gsis.com.cms.datainfo.jgFtitle.service;

import gsis.com.cms.datainfo.jgFtitle.vo.JewF02TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgF02TitleService {


     List<JewF02TiileDataVO> selectList(JewF02TiileDataVO vo)throws Exception;
     List<JewF02TiileDataVO> selectList2(JewF02TiileDataVO vo)throws Exception;
     void insert(JewF02TiileDataVO vo)throws Exception;
     void delete(JewF02TiileDataVO vo)throws Exception;
     void excelDownload(JewF02TiileDataVO searchVO, HttpServletResponse response);
     List<JewF02TiileDataVO> getSelectList(JewF02TiileDataVO vo)throws Exception;
     List<JewF02TiileDataVO> getSelectList2(JewF02TiileDataVO vo)throws Exception;
}
