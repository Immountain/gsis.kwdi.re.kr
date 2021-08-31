package gsis.com.cms.datainfo.jgAtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA08TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.service.JgA08TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA08TiileDataVO;
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

@Service("JgA08TitleService")
public class JgA08TitleServiceImpl extends EgovAbstractServiceImpl implements JgA08TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgA08TitleDAO")
    private JgA08TitleDAO jgA08TitleDAO;



    @Override
    public List<JewA08TiileDataVO> selectList(JewA08TiileDataVO vo) throws Exception {
        return jgA08TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewA08TiileDataVO vo) throws Exception {


        for(JewA08TiileDataVO tVo : vo.getListData()){
            jgA08TitleDAO.delete(tVo);
            jgA08TitleDAO.insert(tVo);
        }



    }

    @Override
    public void delete(JewA08TiileDataVO vo) throws Exception {


        jgA08TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewA08TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgAtitle/a08.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("1-19(전반적 가족관계 만족도)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewA08TiileDataVO> getSelectList(JewA08TiileDataVO vo) throws Exception {
        return jgA08TitleDAO.selectList(vo);
    }
}
