package gsis.com.cms.datainfo.jgDtitle.service;

import gsis.com.cms.datainfo.jgDtitle.vo.JewD04TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD05TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgD05TitleService {


     List<JewD05TiileDataVO> selectList(JewD05TiileDataVO vo)throws Exception;
     void insert(JewD05TiileDataVO vo)throws Exception;
     void delete(JewD05TiileDataVO vo)throws Exception;
     void excelDownload(JewD05TiileDataVO searchVO, HttpServletResponse response);
}
