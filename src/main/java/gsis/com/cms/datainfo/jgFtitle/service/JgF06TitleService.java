package gsis.com.cms.datainfo.jgFtitle.service;

import gsis.com.cms.datainfo.jgFtitle.vo.JewF06TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgF06TitleService {


     List<JewF06TiileDataVO> selectList(JewF06TiileDataVO vo)throws Exception;
     void insert(JewF06TiileDataVO vo)throws Exception;
     void delete(JewF06TiileDataVO vo)throws Exception;
     void excelDownload(JewF06TiileDataVO searchVO, HttpServletResponse response);
     List<JewF06TiileDataVO> getSelectList(JewF06TiileDataVO vo)throws Exception;
}
