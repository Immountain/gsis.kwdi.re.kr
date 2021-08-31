package gsis.com.cms.datainfo.jgFtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgFtitle.dao.JgF04TitleDAO;
import gsis.com.cms.datainfo.jgFtitle.service.JgF04TitleService;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF04TiileDataVO;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("JgF04TitleService")
public class JgF04TitleServiceImpl extends EgovAbstractServiceImpl implements JgF04TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgF04TitleDAO")
    private JgF04TitleDAO jgF04TitleDAO;



    @Override
    public List<JewF04TiileDataVO> selectList(JewF04TiileDataVO vo) throws Exception {
        return jgF04TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewF04TiileDataVO vo) throws Exception {


        for(JewF04TiileDataVO tVo : vo.getListData()){
            jgF04TitleDAO.delete(tVo);
            jgF04TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewF04TiileDataVO vo) throws Exception {


        jgF04TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewF04TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgFtitle/f04.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("6-4(강력범죄 피해자의 여성비율)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" +fileName +".xlsx");
            JxlsHelper.getInstance().processTemplate(templateStream, targetStream, context);
        }catch (IOException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<JewF04TiileDataVO> getSelectList(JewF04TiileDataVO vo) throws Exception {
        return jgF04TitleDAO.selectList(vo);
    }
}
