package gsis.com.cms.datainfo.jgBtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgBtitle.dao.JgB02TitleDAO;
import gsis.com.cms.datainfo.jgBtitle.service.JgB02TitleService;
import gsis.com.cms.datainfo.jgBtitle.vo.JewB02TiileDataVO;
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

@Service("JgB02TitleService")
public class JgB02TitleServiceImpl extends EgovAbstractServiceImpl implements JgB02TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgB02TitleDAO")
    private JgB02TitleDAO jgB02TitleDAO;



    @Override
    public List<JewB02TiileDataVO> selectList(JewB02TiileDataVO vo) throws Exception {
        return jgB02TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewB02TiileDataVO vo) throws Exception {





        for(JewB02TiileDataVO tVo : vo.getListData()){

            jgB02TitleDAO.delete(tVo);
            jgB02TitleDAO.insert(tVo);

        }



    }

    @Override
    public void delete(JewB02TiileDataVO vo) throws Exception {


        jgB02TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewB02TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgBtitle/b02.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("2-4(제주형 돌봄 공동체 현황)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewB02TiileDataVO> getSelectList(JewB02TiileDataVO vo) throws Exception {
        return jgB02TitleDAO.selectList(vo);
    }
}
