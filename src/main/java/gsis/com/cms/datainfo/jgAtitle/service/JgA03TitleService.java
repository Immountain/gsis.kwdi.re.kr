package gsis.com.cms.datainfo.jgAtitle.service;

import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgA03TitleService {


     List<JewA03TiileDataVO> selectList(JewA03TiileDataVO vo)throws Exception;
     void insert(JewA03TiileDataVO vo)throws Exception;
     void delete(JewA03TiileDataVO vo)throws Exception;
     void excelDownload(JewA03TiileDataVO searchVO, HttpServletResponse response);
}
