package gsis.com.cms.datainfo.jgDtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC01TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.service.JgC01TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD01TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.service.JgD01TitleService;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD01TiileDataVO;
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

@Service("JgD01TitleService")
public class JgD01TitleServiceImpl extends EgovAbstractServiceImpl implements JgD01TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgD01TitleDAO")
    private JgD01TitleDAO jgD01TitleDAO;



    @Override
    public List<JewD01TiileDataVO> selectList(JewD01TiileDataVO vo) throws Exception {
        return jgD01TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewD01TiileDataVO vo) throws Exception {


        for(JewD01TiileDataVO tVo : vo.getListData()){
            jgD01TitleDAO.delete(tVo);
            jgD01TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewD01TiileDataVO vo) throws Exception {


        jgD01TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewD01TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgDtitle/d01.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("4-1(기대여명)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
