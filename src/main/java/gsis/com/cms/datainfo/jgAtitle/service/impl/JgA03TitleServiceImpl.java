package gsis.com.cms.datainfo.jgAtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA03TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.service.JgA03TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA03TiileDataVO;
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

@Service("JgA03TitleService")
public class JgA03TitleServiceImpl extends EgovAbstractServiceImpl implements JgA03TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgA03TitleDAO")
    private JgA03TitleDAO jgA03TitleDAO;



    @Override
    public List<JewA03TiileDataVO> selectList(JewA03TiileDataVO vo) throws Exception {
        return jgA03TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewA03TiileDataVO vo) throws Exception {





        for(JewA03TiileDataVO tVo : vo.getListData()){

            jgA03TitleDAO.delete(tVo);
            jgA03TitleDAO.insert(tVo);

        }



    }

    @Override
    public void delete(JewA03TiileDataVO vo) throws Exception {


        jgA03TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewA03TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgAtitle/a03.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("1-5(연령별 인구)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" +fileName +".xlsx");
            JxlsHelper.getInstance().processTemplate(templateStream, targetStream, context);
        }catch (IOException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
