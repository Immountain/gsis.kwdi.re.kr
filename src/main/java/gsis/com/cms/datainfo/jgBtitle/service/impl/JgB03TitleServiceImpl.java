package gsis.com.cms.datainfo.jgBtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgBtitle.dao.JgB03TitleDAO;
import gsis.com.cms.datainfo.jgBtitle.service.JgB03TitleService;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB03TiileDataVO;
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

@Service("JgB03TitleService")
public class JgB03TitleServiceImpl extends EgovAbstractServiceImpl implements JgB03TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgB03TitleDAO")
    private JgB03TitleDAO jgB03TitleDAO;



    @Override
    public List<JewB03TiileDataVO> selectList(JewB03TiileDataVO vo) throws Exception {
        return jgB03TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewB03TiileDataVO vo) throws Exception {





        for(JewB03TiileDataVO tVo : vo.getListData()){

            jgB03TitleDAO.delete(tVo);
            jgB03TitleDAO.insert(tVo);

        }



    }

    @Override
    public void delete(JewB03TiileDataVO vo) throws Exception {


        jgB03TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewB03TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgBtitle/b03.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("2-7(초중고등학교 학생 수 변화)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewB03TiileDataVO> getSelectList(JewB03TiileDataVO vo) throws Exception {
        return jgB03TitleDAO.selectList(vo);
    }
}
