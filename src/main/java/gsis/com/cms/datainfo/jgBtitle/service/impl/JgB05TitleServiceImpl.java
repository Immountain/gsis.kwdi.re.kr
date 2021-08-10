package gsis.com.cms.datainfo.jgBtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgAtitle.dao.JgA02TitleDAO;
import gsis.com.cms.datainfo.jgAtitle.service.JgA02TitleService;
import gsis.com.cms.datainfo.jgAtitle.vo.JewA02TiileDataVO;
import gsis.com.cms.datainfo.jgBtitle.dao.JgB05TitleDAO;
import gsis.com.cms.datainfo.jgBtitle.service.JgB05TitleService;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB05TiileDataVO;
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

@Service("JgB05TitleService")
public class JgB05TitleServiceImpl extends EgovAbstractServiceImpl implements JgB05TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgB05TitleDAO")
    private JgB05TitleDAO jgB05TitleDAO;



    @Override
    public List<JewB05TiileDataVO> selectList(JewB05TiileDataVO vo) throws Exception {
        return jgB05TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewB05TiileDataVO vo) throws Exception {





        for(JewB05TiileDataVO tVo : vo.getListData()){

            jgB05TitleDAO.delete(tVo);
            jgB05TitleDAO.insert(tVo);

        }



    }

    @Override
    public void delete(JewB05TiileDataVO vo) throws Exception {


        jgB05TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewB05TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgBtitle/b05.xlsx").getInputStream();
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
    public List<JewB05TiileDataVO> getSelectList(JewB05TiileDataVO vo) throws Exception {
        return jgB05TitleDAO.selectList(vo);
    }
}
