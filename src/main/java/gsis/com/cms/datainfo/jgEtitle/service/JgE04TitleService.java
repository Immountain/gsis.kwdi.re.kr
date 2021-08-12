package gsis.com.cms.datainfo.jgEtitle.service;

import gsis.com.cms.datainfo.jgEtitle.vo.JewE03TiileDataVO;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE04TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgE04TitleService {


     List<JewE04TiileDataVO> selectList(JewE04TiileDataVO vo)throws Exception;
     void insert(JewE04TiileDataVO vo)throws Exception;
     void delete(JewE04TiileDataVO vo)throws Exception;
     void excelDownload(JewE04TiileDataVO searchVO, HttpServletResponse response);
     List<JewE04TiileDataVO> getSelectList(JewE04TiileDataVO vo)throws Exception;
}
