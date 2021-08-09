package gsis.com.cms.datainfo.jgDtitle.service;

import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD01TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgD01TitleService {


     List<JewD01TiileDataVO> selectList(JewD01TiileDataVO vo)throws Exception;
     void insert(JewD01TiileDataVO vo)throws Exception;
     void delete(JewD01TiileDataVO vo)throws Exception;
     void excelDownload(JewD01TiileDataVO searchVO, HttpServletResponse response);

     List<JewD01TiileDataVO> getSelectList(JewD01TiileDataVO vo)throws Exception;
}
