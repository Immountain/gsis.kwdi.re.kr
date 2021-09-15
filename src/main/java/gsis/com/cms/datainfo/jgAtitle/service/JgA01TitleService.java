package gsis.com.cms.datainfo.jgAtitle.service;

import gsis.com.cms.datainfo.jgAtitle.vo.JewA01TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgA01TitleService {


     List<JewA01TiileDataVO> selectList(JewA01TiileDataVO vo)throws Exception;
     void insert(JewA01TiileDataVO vo)throws Exception;
     void delete(JewA01TiileDataVO vo)throws Exception;
     void excelDownload(JewA01TiileDataVO searchVO, HttpServletResponse response);


     List<JewA01TiileDataVO> getSelectList(JewA01TiileDataVO vo)throws Exception;
}
