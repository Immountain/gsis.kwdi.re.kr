package gsis.com.cms.datainfo.jgBtitle.service;

import gsis.com.cms.datainfo.jgBtitle.vo.JewB01TiileDataVO;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB06TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgB06TitleService {


     List<JewB06TiileDataVO> selectList(JewB06TiileDataVO vo)throws Exception;
     void insert(JewB06TiileDataVO vo)throws Exception;
     void delete(JewB06TiileDataVO vo)throws Exception;
     void excelDownload(JewB06TiileDataVO searchVO, HttpServletResponse response);


     List<JewB06TiileDataVO> getSelectList(JewB06TiileDataVO vo)throws Exception;
}
