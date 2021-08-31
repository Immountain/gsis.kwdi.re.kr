package gsis.com.cms.datainfo.jgAtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA07TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.service.JgA07TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA07TiileDataVO;
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

@Service("JgA07TitleService")
public class JgA07TitleServiceImpl extends EgovAbstractServiceImpl implements JgA07TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgA07TitleDAO")
    private JgA07TitleDAO jgA07TitleDAO;



    @Override
    public List<JewA07TiileDataVO> selectList(JewA07TiileDataVO vo) throws Exception {
        return jgA07TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewA07TiileDataVO vo) throws Exception {


        for(JewA07TiileDataVO tVo : vo.getListData()){
            jgA07TitleDAO.delete(tVo);
            jgA07TitleDAO.insert(tVo);
        }



    }

    @Override
    public void delete(JewA07TiileDataVO vo) throws Exception {


        jgA07TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewA07TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgAtitle/a07.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("1-15(평균 초혼연령)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewA07TiileDataVO> getSelectList(JewA07TiileDataVO vo) throws Exception {
        return jgA07TitleDAO.selectList(vo);
    }
}
