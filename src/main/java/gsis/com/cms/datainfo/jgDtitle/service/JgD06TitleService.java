package gsis.com.cms.datainfo.jgDtitle.service;

import gsis.com.cms.datainfo.jgDtitle.vo.JewD05TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD06TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgD06TitleService {


     List<JewD06TiileDataVO> selectList(JewD06TiileDataVO vo)throws Exception;
     void insert(JewD06TiileDataVO vo)throws Exception;
     void delete(JewD06TiileDataVO vo)throws Exception;
     void excelDownload(JewD06TiileDataVO searchVO, HttpServletResponse response);
     List<JewD06TiileDataVO> getSelectList(JewD06TiileDataVO vo)throws Exception;
}
