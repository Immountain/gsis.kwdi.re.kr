package gsis.com.cms.datainfo.jgDtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD03TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD04TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.service.JgD03TitleService;
import gsis.com.cms.datainfo.jgDtitle.service.JgD04TitleService;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD03TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD04TiileDataVO;
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

@Service("JgD04TitleService")
public class JgD04TitleServiceImpl extends EgovAbstractServiceImpl implements JgD04TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgD04TitleDAO")
    private JgD04TitleDAO jgD04TitleDAO;



    @Override
    public List<JewD04TiileDataVO> selectList(JewD04TiileDataVO vo) throws Exception {
        return jgD04TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewD04TiileDataVO vo) throws Exception {


        for(JewD04TiileDataVO tVo : vo.getListData()){
            jgD04TitleDAO.delete(tVo);
            jgD04TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewD04TiileDataVO vo) throws Exception {


        jgD04TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewD04TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgDtitle/d04.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("4-12(고용보험 피보험자 추이)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
