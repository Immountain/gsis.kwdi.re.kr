package gsis.com.cms.datainfo.jgDtitle.service;

import gsis.com.cms.datainfo.jgDtitle.vo.JewD01TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD02TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgD02TitleService {


     List<JewD02TiileDataVO> selectList(JewD02TiileDataVO vo)throws Exception;
     void insert(JewD02TiileDataVO vo)throws Exception;
     void delete(JewD02TiileDataVO vo)throws Exception;
     void excelDownload(JewD02TiileDataVO searchVO, HttpServletResponse response);

     List<JewD02TiileDataVO> getSelectList(JewD02TiileDataVO vo)throws Exception;
}
