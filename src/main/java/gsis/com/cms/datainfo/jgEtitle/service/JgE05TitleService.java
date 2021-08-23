package gsis.com.cms.datainfo.jgEtitle.service;

import gsis.com.cms.datainfo.jgEtitle.vo.JewE05TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgE05TitleService {


     List<JewE05TiileDataVO> selectList(JewE05TiileDataVO vo)throws Exception;
     void insert(JewE05TiileDataVO vo)throws Exception;
     void delete(JewE05TiileDataVO vo)throws Exception;
     void excelDownload(JewE05TiileDataVO searchVO, HttpServletResponse response);
     List<JewE05TiileDataVO> getSelectList(JewE05TiileDataVO vo)throws Exception;
}
