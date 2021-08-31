package gsis.com.cms.datainfo.jgFtitle.service;

import gsis.com.cms.datainfo.jgFtitle.vo.JewF04TiileDataVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JgF04TitleService {


     List<JewF04TiileDataVO> selectList(JewF04TiileDataVO vo)throws Exception;
     void insert(JewF04TiileDataVO vo)throws Exception;
     void delete(JewF04TiileDataVO vo)throws Exception;
     void excelDownload(JewF04TiileDataVO searchVO, HttpServletResponse response);
     List<JewF04TiileDataVO> getSelectList(JewF04TiileDataVO vo)throws Exception;
}
