package gsis.com.cms.datainfo.jgBtitle.service;

import gsis.com.cms.datainfo.jgAtitle.vo.JewA02TiileDataVO;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB05TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgB05TitleService {


     List<JewB05TiileDataVO> selectList(JewB05TiileDataVO vo)throws Exception;
     void insert(JewB05TiileDataVO vo)throws Exception;
     void delete(JewB05TiileDataVO vo)throws Exception;
     void excelDownload(JewB05TiileDataVO searchVO, HttpServletResponse response);


     List<JewB05TiileDataVO> getSelectList(JewB05TiileDataVO vo)throws Exception;
}
