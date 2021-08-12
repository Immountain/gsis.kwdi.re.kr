package gsis.com.cms.datainfo.jgCtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC07TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC08TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.service.JgC07TitleService;
import gsis.com.cms.datainfo.jgCtitle.service.JgC08TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC07TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC08TiileDataVO;
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

@Service("JgC08TitleService")
public class JgC08TitleServiceImpl extends EgovAbstractServiceImpl implements JgC08TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgC08TitleDAO")
    private JgC08TitleDAO jgC08TitleDAO;



    @Override
    public List<JewC08TiileDataVO> selectList(JewC08TiileDataVO vo) throws Exception {
        return jgC08TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewC08TiileDataVO vo) throws Exception {


        for(JewC08TiileDataVO tVo : vo.getListData()){
            jgC08TitleDAO.delete(tVo);
            jgC08TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewC08TiileDataVO vo) throws Exception {


        jgC08TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewC08TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgCtitle/c08.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("3-8(사업체의 여성 대표자 및 여성 종사자 비율)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewC08TiileDataVO> getSelectList(JewC08TiileDataVO vo) throws Exception {
        return jgC08TitleDAO.selectList(vo);
    }
}
