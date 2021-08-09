package gsis.com.cms.datainfo.jgDtitle.service;

import gsis.com.cms.datainfo.jgDtitle.vo.JewD03TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD04TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgD04TitleService {


     List<JewD04TiileDataVO> selectList(JewD04TiileDataVO vo)throws Exception;
     void insert(JewD04TiileDataVO vo)throws Exception;
     void delete(JewD04TiileDataVO vo)throws Exception;
     void excelDownload(JewD04TiileDataVO searchVO, HttpServletResponse response);
     List<JewD04TiileDataVO> getSelectList(JewD04TiileDataVO vo)throws Exception;
}
