package gsis.com.cms.datainfo.jgDtitle.service;

import gsis.com.cms.datainfo.jgDtitle.vo.JewD01TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD03TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgD03TitleService {


     List<JewD03TiileDataVO> selectList(JewD03TiileDataVO vo)throws Exception;
     void insert(JewD03TiileDataVO vo)throws Exception;
     void delete(JewD03TiileDataVO vo)throws Exception;
     void excelDownload(JewD03TiileDataVO searchVO, HttpServletResponse response);
}
