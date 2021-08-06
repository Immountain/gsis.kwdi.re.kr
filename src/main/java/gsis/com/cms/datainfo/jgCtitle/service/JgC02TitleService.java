package gsis.com.cms.datainfo.jgCtitle.service;

import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC02TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgC02TitleService {


     List<JewC02TiileDataVO> selectList(JewC02TiileDataVO vo)throws Exception;
     void insert(JewC02TiileDataVO vo)throws Exception;
     void delete(JewC02TiileDataVO vo)throws Exception;
     void excelDownload(JewC02TiileDataVO searchVO, HttpServletResponse response);


     List<JewC02TiileDataVO> getSelectList(JewC02TiileDataVO vo)throws Exception;
}
