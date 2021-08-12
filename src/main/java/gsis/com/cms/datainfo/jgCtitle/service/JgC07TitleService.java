package gsis.com.cms.datainfo.jgCtitle.service;

import gsis.com.cms.datainfo.jgCtitle.vo.JewC03TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC07TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgC07TitleService {


     List<JewC07TiileDataVO> selectList(JewC07TiileDataVO vo)throws Exception;
     void insert(JewC07TiileDataVO vo)throws Exception;
     void delete(JewC07TiileDataVO vo)throws Exception;
     void excelDownload(JewC07TiileDataVO searchVO, HttpServletResponse response);


     List<JewC07TiileDataVO> getSelectList(JewC07TiileDataVO vo)throws Exception;
}
