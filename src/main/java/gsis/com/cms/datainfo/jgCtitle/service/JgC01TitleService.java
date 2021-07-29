package gsis.com.cms.datainfo.jgCtitle.service;

import gsis.com.cms.datainfo.jgAtitle.vo.JewA04TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgC01TitleService {


     List<JewC01TiileDataVO> selectList(JewC01TiileDataVO vo)throws Exception;
     void insert(JewC01TiileDataVO vo)throws Exception;
     void delete(JewC01TiileDataVO vo)throws Exception;
     void excelDownload(JewC01TiileDataVO searchVO, HttpServletResponse response);
}
