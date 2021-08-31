package gsis.com.cms.datainfo.jgBtitle.service;

import gsis.com.cms.datainfo.jgBtitle.vo.JewB03TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgB03TitleService {


     List<JewB03TiileDataVO> selectList(JewB03TiileDataVO vo)throws Exception;
     void insert(JewB03TiileDataVO vo)throws Exception;
     void delete(JewB03TiileDataVO vo)throws Exception;
     void excelDownload(JewB03TiileDataVO searchVO, HttpServletResponse response);


     List<JewB03TiileDataVO> getSelectList(JewB03TiileDataVO vo)throws Exception;
}
