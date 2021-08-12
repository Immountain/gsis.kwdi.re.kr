package gsis.com.cms.datainfo.jgEtitle.service;

import gsis.com.cms.datainfo.jgDtitle.vo.JewD06TiileDataVO;
import gsis.com.cms.datainfo.jgEtitle.dao.JgE03TitleDAO;
import gsis.com.cms.datainfo.jgEtitle.vo.JewE03TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgE03TitleService {


     List<JewE03TiileDataVO> selectList(JewE03TiileDataVO vo)throws Exception;
     void insert(JewE03TiileDataVO vo)throws Exception;
     void delete(JewE03TiileDataVO vo)throws Exception;
     void excelDownload(JewE03TiileDataVO searchVO, HttpServletResponse response);
     List<JewE03TiileDataVO> getSelectList(JewE03TiileDataVO vo)throws Exception;
}
