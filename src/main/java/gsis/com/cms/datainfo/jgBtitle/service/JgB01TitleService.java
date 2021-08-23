package gsis.com.cms.datainfo.jgBtitle.service;

import gsis.com.cms.datainfo.jgBtitle.vo.JewB01TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgB01TitleService {


     List<JewB01TiileDataVO> selectList(JewB01TiileDataVO vo)throws Exception;
     void insert(JewB01TiileDataVO vo)throws Exception;
     void delete(JewB01TiileDataVO vo)throws Exception;
     void excelDownload(JewB01TiileDataVO searchVO, HttpServletResponse response);


     List<JewB01TiileDataVO> getSelectList(JewB01TiileDataVO vo)throws Exception;
}
