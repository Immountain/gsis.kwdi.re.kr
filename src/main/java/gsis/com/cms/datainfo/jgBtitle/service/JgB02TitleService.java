package gsis.com.cms.datainfo.jgBtitle.service;

import gsis.com.cms.datainfo.jgBtitle.vo.JewB02TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgB02TitleService {


     List<JewB02TiileDataVO> selectList(JewB02TiileDataVO vo)throws Exception;
     void insert(JewB02TiileDataVO vo)throws Exception;
     void delete(JewB02TiileDataVO vo)throws Exception;
     void excelDownload(JewB02TiileDataVO searchVO, HttpServletResponse response);


     List<JewB02TiileDataVO> getSelectList(JewB02TiileDataVO vo)throws Exception;
}
