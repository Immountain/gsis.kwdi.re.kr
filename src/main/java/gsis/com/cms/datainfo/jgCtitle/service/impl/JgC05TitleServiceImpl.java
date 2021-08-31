package gsis.com.cms.datainfo.jgCtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC05TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.service.JgC05TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC05TiileDataVO;
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

@Service("JgC05TitleService")
public class JgC05TitleServiceImpl extends EgovAbstractServiceImpl implements JgC05TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgC05TitleDAO")
    private JgC05TitleDAO jgC05TitleDAO;



    @Override
    public List<JewC05TiileDataVO> selectList(JewC05TiileDataVO vo) throws Exception {
        return jgC05TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewC05TiileDataVO vo) throws Exception {


        for(JewC05TiileDataVO tVo : vo.getListData()){
            jgC05TitleDAO.delete(tVo);
            jgC05TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewC05TiileDataVO vo) throws Exception {


        jgC05TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewC05TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgCtitle/c05.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("3-5(연령별 취업자)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewC05TiileDataVO> getSelectList(JewC05TiileDataVO vo) throws Exception {
        return jgC05TitleDAO.selectList(vo);
    }
}
