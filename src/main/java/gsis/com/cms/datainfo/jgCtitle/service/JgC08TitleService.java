package gsis.com.cms.datainfo.jgCtitle.service;

import gsis.com.cms.datainfo.jgCtitle.vo.JewC07TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC08TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgC08TitleService {


     List<JewC08TiileDataVO> selectList(JewC08TiileDataVO vo)throws Exception;
     void insert(JewC08TiileDataVO vo)throws Exception;
     void delete(JewC08TiileDataVO vo)throws Exception;
     void excelDownload(JewC08TiileDataVO searchVO, HttpServletResponse response);


     List<JewC08TiileDataVO> getSelectList(JewC08TiileDataVO vo)throws Exception;
}
