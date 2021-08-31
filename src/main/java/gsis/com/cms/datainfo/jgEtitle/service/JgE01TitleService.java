package gsis.com.cms.datainfo.jgEtitle.service;

import gsis.com.cms.datainfo.jgEtitle.vo.JewE01TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgE01TitleService {


     List<JewE01TiileDataVO> selectList(JewE01TiileDataVO vo)throws Exception;
     void insert(JewE01TiileDataVO vo)throws Exception;
     void delete(JewE01TiileDataVO vo)throws Exception;
     void excelDownload(JewE01TiileDataVO searchVO, HttpServletResponse response);
     List<JewE01TiileDataVO> getSelectList(JewE01TiileDataVO vo)throws Exception;
}
