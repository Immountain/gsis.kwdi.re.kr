package gsis.com.cms.datainfo.jgAtitle.service;

import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA04TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgA04TitleService {


     List<JewA04TiileDataVO> selectList(JewA04TiileDataVO vo)throws Exception;
     void insert(JewA04TiileDataVO vo)throws Exception;
     void delete(JewA04TiileDataVO vo)throws Exception;
     void excelDownload(JewA04TiileDataVO searchVO, HttpServletResponse response);
}
