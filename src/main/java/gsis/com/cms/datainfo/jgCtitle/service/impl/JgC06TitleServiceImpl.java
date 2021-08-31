package gsis.com.cms.datainfo.jgCtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC06TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.service.JgC06TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC06TiileDataVO;
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

@Service("JgC06TitleService")
public class JgC06TitleServiceImpl extends EgovAbstractServiceImpl implements JgC06TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgC06TitleDAO")
    private JgC06TitleDAO jgC06TitleDAO;



    @Override
    public List<JewC06TiileDataVO> selectList(JewC06TiileDataVO vo) throws Exception {
        return jgC06TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewC06TiileDataVO vo) throws Exception {


        for(JewC06TiileDataVO tVo : vo.getListData()){
            jgC06TitleDAO.delete(tVo);
            jgC06TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewC06TiileDataVO vo) throws Exception {


        jgC06TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewC06TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgCtitle/c06.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("3-6(취업자의 직업)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewC06TiileDataVO> getSelectList(JewC06TiileDataVO vo) throws Exception {
        return jgC06TitleDAO.selectList(vo);
    }
}
