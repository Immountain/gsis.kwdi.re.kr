package gsis.com.cms.datainfo.jgCtitle.service;

import gsis.com.cms.datainfo.jgCtitle.vo.JewC05TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgC05TitleService {


     List<JewC05TiileDataVO> selectList(JewC05TiileDataVO vo)throws Exception;
     void insert(JewC05TiileDataVO vo)throws Exception;
     void delete(JewC05TiileDataVO vo)throws Exception;
     void excelDownload(JewC05TiileDataVO searchVO, HttpServletResponse response);


     List<JewC05TiileDataVO> getSelectList(JewC05TiileDataVO vo)throws Exception;
}
