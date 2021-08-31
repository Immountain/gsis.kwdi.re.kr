package gsis.com.cms.datainfo.jgAtitle.service;

import gsis.com.cms.datainfo.jgAtitle.vo.JewA06TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgA06TitleService {


     List<JewA06TiileDataVO> selectList(JewA06TiileDataVO vo)throws Exception;
     void insert(JewA06TiileDataVO vo)throws Exception;
     void delete(JewA06TiileDataVO vo)throws Exception;
     void excelDownload(JewA06TiileDataVO searchVO, HttpServletResponse response);


     List<JewA06TiileDataVO> getSelectList(JewA06TiileDataVO vo)throws Exception;
}
