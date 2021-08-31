package gsis.com.cms.datainfo.jgCtitle.service;

import gsis.com.cms.datainfo.jgCtitle.vo.JewC04TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgC04TitleService {


     List<JewC04TiileDataVO> selectList(JewC04TiileDataVO vo)throws Exception;
     void insert(JewC04TiileDataVO vo)throws Exception;
     void delete(JewC04TiileDataVO vo)throws Exception;
     void excelDownload(JewC04TiileDataVO searchVO, HttpServletResponse response);


     List<JewC04TiileDataVO> getSelectList(JewC04TiileDataVO vo)throws Exception;
}
