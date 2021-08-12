package gsis.com.cms.datainfo.jgCtitle.service;

import gsis.com.cms.datainfo.jgCtitle.vo.JewC02TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC03TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgC03TitleService {


     List<JewC03TiileDataVO> selectList(JewC03TiileDataVO vo)throws Exception;
     void insert(JewC03TiileDataVO vo)throws Exception;
     void delete(JewC03TiileDataVO vo)throws Exception;
     void excelDownload(JewC03TiileDataVO searchVO, HttpServletResponse response);


     List<JewC03TiileDataVO> getSelectList(JewC03TiileDataVO vo)throws Exception;
}
