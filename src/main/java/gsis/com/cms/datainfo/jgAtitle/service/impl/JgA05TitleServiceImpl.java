package gsis.com.cms.datainfo.jgAtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA05TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.service.JgA05TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA05TiileDataVO;
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

@Service("JgA05TitleService")
public class JgA05TitleServiceImpl extends EgovAbstractServiceImpl implements JgA05TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgA05TitleDAO")
    private JgA05TitleDAO jgA05TitleDAO;



    @Override
    public List<JewA05TiileDataVO> selectList(JewA05TiileDataVO vo) throws Exception {
        return jgA05TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewA05TiileDataVO vo) throws Exception {


        for(JewA05TiileDataVO tVo : vo.getListData()){
            jgA05TitleDAO.delete(tVo);
            jgA05TitleDAO.insert(tVo);
        }



    }

    @Override
    public void delete(JewA05TiileDataVO vo) throws Exception {


        jgA05TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewA05TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgAtitle/a05.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("1-11(가구주 가구)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewA05TiileDataVO> getSelectList(JewA05TiileDataVO vo) throws Exception {
        return jgA05TitleDAO.selectList(vo);
    }
}
