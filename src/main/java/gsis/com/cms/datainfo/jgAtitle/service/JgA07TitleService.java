package gsis.com.cms.datainfo.jgAtitle.service;

import gsis.com.cms.datainfo.jgAtitle.vo.JewA07TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgA07TitleService {


     List<JewA07TiileDataVO> selectList(JewA07TiileDataVO vo)throws Exception;
     void insert(JewA07TiileDataVO vo)throws Exception;
     void delete(JewA07TiileDataVO vo)throws Exception;
     void excelDownload(JewA07TiileDataVO searchVO, HttpServletResponse response);


     List<JewA07TiileDataVO> getSelectList(JewA07TiileDataVO vo)throws Exception;
}
