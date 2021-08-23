package gsis.com.cms.datainfo.jgFtitle.service;

import gsis.com.cms.datainfo.jgFtitle.vo.JewF01TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgF01TitleService {


     List<JewF01TiileDataVO> selectList(JewF01TiileDataVO vo)throws Exception;
     void insert(JewF01TiileDataVO vo)throws Exception;
     void delete(JewF01TiileDataVO vo)throws Exception;
     void excelDownload(JewF01TiileDataVO searchVO, HttpServletResponse response);
     List<JewF01TiileDataVO> getSelectList(JewF01TiileDataVO vo)throws Exception;
}
