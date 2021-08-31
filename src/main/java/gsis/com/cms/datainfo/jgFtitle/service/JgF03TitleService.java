package gsis.com.cms.datainfo.jgFtitle.service;

import gsis.com.cms.datainfo.jgFtitle.vo.JewF03TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgF03TitleService {


     List<JewF03TiileDataVO> selectList(JewF03TiileDataVO vo)throws Exception;
     void insert(JewF03TiileDataVO vo)throws Exception;
     void delete(JewF03TiileDataVO vo)throws Exception;
     void excelDownload(JewF03TiileDataVO searchVO, HttpServletResponse response);
     List<JewF03TiileDataVO> getSelectList(JewF03TiileDataVO vo)throws Exception;
}
