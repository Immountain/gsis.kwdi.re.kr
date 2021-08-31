package gsis.com.cms.datainfo.jgEtitle.service;

import gsis.com.cms.datainfo.jgEtitle.vo.JewE02TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgE02TitleService {


     List<JewE02TiileDataVO> selectList(JewE02TiileDataVO vo)throws Exception;
     void insert(JewE02TiileDataVO vo)throws Exception;
     void delete(JewE02TiileDataVO vo)throws Exception;
     void excelDownload(JewE02TiileDataVO searchVO, HttpServletResponse response);
     List<JewE02TiileDataVO> getSelectList(JewE02TiileDataVO vo)throws Exception;
}
