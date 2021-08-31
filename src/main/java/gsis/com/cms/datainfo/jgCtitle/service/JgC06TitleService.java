package gsis.com.cms.datainfo.jgCtitle.service;

import gsis.com.cms.datainfo.jgCtitle.vo.JewC06TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgC06TitleService {


     List<JewC06TiileDataVO> selectList(JewC06TiileDataVO vo)throws Exception;
     void insert(JewC06TiileDataVO vo)throws Exception;
     void delete(JewC06TiileDataVO vo)throws Exception;
     void excelDownload(JewC06TiileDataVO searchVO, HttpServletResponse response);


     List<JewC06TiileDataVO> getSelectList(JewC06TiileDataVO vo)throws Exception;
}
