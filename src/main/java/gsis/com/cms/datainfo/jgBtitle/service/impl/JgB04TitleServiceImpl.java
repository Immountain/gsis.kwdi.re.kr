package gsis.com.cms.datainfo.jgBtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgBtitle.dao.JgB04TitleDAO;
import gsis.com.cms.datainfo.jgBtitle.service.JgB04TitleService;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB04TiileDataVO;
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

@Service("JgB04TitleService")
public class JgB04TitleServiceImpl extends EgovAbstractServiceImpl implements JgB04TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgB04TitleDAO")
    private JgB04TitleDAO jgB04TitleDAO;



    @Override
    public List<JewB04TiileDataVO> selectList(JewB04TiileDataVO vo) throws Exception {
        return jgB04TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewB04TiileDataVO vo) throws Exception {





        for(JewB04TiileDataVO tVo : vo.getListData()){

            jgB04TitleDAO.delete(tVo);
            jgB04TitleDAO.insert(tVo);

        }



    }

    @Override
    public void delete(JewB04TiileDataVO vo) throws Exception {


        jgB04TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewB04TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgBtitle/b04.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("2-8(대학교 진학률)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewB04TiileDataVO> getSelectList(JewB04TiileDataVO vo) throws Exception {
        return jgB04TitleDAO.selectList(vo);
    }
}
