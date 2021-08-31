package gsis.com.cms.datainfo.jgAtitle.service;

import gsis.com.cms.datainfo.jgAtitle.vo.JewA05TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgA05TitleService {


     List<JewA05TiileDataVO> selectList(JewA05TiileDataVO vo)throws Exception;
     void insert(JewA05TiileDataVO vo)throws Exception;
     void delete(JewA05TiileDataVO vo)throws Exception;
     void excelDownload(JewA05TiileDataVO searchVO, HttpServletResponse response);


     List<JewA05TiileDataVO> getSelectList(JewA05TiileDataVO vo)throws Exception;
}
