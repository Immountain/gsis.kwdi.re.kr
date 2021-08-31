package gsis.com.cms.datainfo.jgAtitle.service;

import gsis.com.cms.datainfo.jgAtitle.vo.JewA08TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgA08TitleService {


     List<JewA08TiileDataVO> selectList(JewA08TiileDataVO vo)throws Exception;
     void insert(JewA08TiileDataVO vo)throws Exception;
     void delete(JewA08TiileDataVO vo)throws Exception;
     void excelDownload(JewA08TiileDataVO searchVO, HttpServletResponse response);


     List<JewA08TiileDataVO> getSelectList(JewA08TiileDataVO vo)throws Exception;
}
