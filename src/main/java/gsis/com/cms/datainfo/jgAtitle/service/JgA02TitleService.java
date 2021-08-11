package gsis.com.cms.datainfo.jgAtitle.service;

import gsis.com.cms.datainfo.jgAtitle.vo.JewA02TiileDataVO;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgA02TitleService {


     List<JewA02TiileDataVO> selectList(JewA02TiileDataVO vo)throws Exception;
     void insert(JewA02TiileDataVO vo)throws Exception;
     void delete(JewA02TiileDataVO vo)throws Exception;
     void excelDownload(JewA02TiileDataVO searchVO, HttpServletResponse response);


     List<JewA02TiileDataVO> getSelectList(JewA02TiileDataVO vo)throws Exception;
}
