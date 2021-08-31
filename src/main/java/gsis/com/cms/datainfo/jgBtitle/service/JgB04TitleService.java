package gsis.com.cms.datainfo.jgBtitle.service;

import gsis.com.cms.datainfo.jgBtitle.vo.JewB04TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgB04TitleService {


     List<JewB04TiileDataVO> selectList(JewB04TiileDataVO vo)throws Exception;
     void insert(JewB04TiileDataVO vo)throws Exception;
     void delete(JewB04TiileDataVO vo)throws Exception;
     void excelDownload(JewB04TiileDataVO searchVO, HttpServletResponse response);


     List<JewB04TiileDataVO> getSelectList(JewB04TiileDataVO vo)throws Exception;
}
