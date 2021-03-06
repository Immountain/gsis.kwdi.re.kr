package gsis.com.cms.datainfo.jgAtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA01TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.service.JgA01TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA01TiileDataVO;
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

@Service("JgA01TitleService")
public class JgA01TitleServiceImpl extends EgovAbstractServiceImpl implements JgA01TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgA01TitleDAO")
    private JgA01TitleDAO jgA01TitleDAO;



    @Override
    public List<JewA01TiileDataVO> selectList(JewA01TiileDataVO vo) throws Exception {
        return jgA01TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewA01TiileDataVO vo) throws Exception {





        for(JewA01TiileDataVO tVo : vo.getListData()){

            jgA01TitleDAO.delete(tVo);
            jgA01TitleDAO.insert(tVo);

        }



    }

    @Override
    public void delete(JewA01TiileDataVO vo) throws Exception {


        jgA01TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewA01TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgAtitle/a01.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("1-1?????????_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewA01TiileDataVO> getSelectList(JewA01TiileDataVO vo) throws Exception {
        return jgA01TitleDAO.selectList(vo);
    }
}
