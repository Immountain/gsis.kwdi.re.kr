package gsis.com.cms.datainfo.jgFtitle.service;

import gsis.com.cms.datainfo.jgFtitle.vo.JewF05TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgF05TitleService {


     List<JewF05TiileDataVO> selectList(JewF05TiileDataVO vo)throws Exception;
     void insert(JewF05TiileDataVO vo)throws Exception;
     void delete(JewF05TiileDataVO vo)throws Exception;
     void excelDownload(JewF05TiileDataVO searchVO, HttpServletResponse response);
     List<JewF05TiileDataVO> getSelectList(JewF05TiileDataVO vo)throws Exception;
}
