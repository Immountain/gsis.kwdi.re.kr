package gsis.com.cms.datainfo.jgEtitle.service;

import gsis.com.cms.datainfo.jgEtitle.vo.JewE06TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgE06TitleService {


     List<JewE06TiileDataVO> selectList(JewE06TiileDataVO vo)throws Exception;
     void insert(JewE06TiileDataVO vo)throws Exception;
     void delete(JewE06TiileDataVO vo)throws Exception;
     void excelDownload(JewE06TiileDataVO searchVO, HttpServletResponse response);
     List<JewE06TiileDataVO> getSelectList(JewE06TiileDataVO vo)throws Exception;
}
