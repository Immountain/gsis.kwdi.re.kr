package gsis.com.cms.datainfo.jgBtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgBtitle.dao.JgB01TitleDAO;
import gsis.com.cms.datainfo.jgBtitle.service.JgB01TitleService;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB01TiileDataVO;
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

@Service("JgB01TitleService")
public class JgB01TitleServiceImpl extends EgovAbstractServiceImpl implements JgB01TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgB01TitleDAO")
    private JgB01TitleDAO jgB01TitleDAO;



    @Override
    public List<JewB01TiileDataVO> selectList(JewB01TiileDataVO vo) throws Exception {
        return jgB01TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewB01TiileDataVO vo) throws Exception {





        for(JewB01TiileDataVO tVo : vo.getListData()){

            jgB01TitleDAO.delete(tVo);
            jgB01TitleDAO.insert(tVo);

        }



    }

    @Override
    public void delete(JewB01TiileDataVO vo) throws Exception {


        jgB01TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewB01TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgBtitle/b01.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("2-9(여성 석‧박사과정 졸업자 현황)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewB01TiileDataVO> getSelectList(JewB01TiileDataVO vo) throws Exception {
        return jgB01TitleDAO.selectList(vo);
    }
}
