package gsis.com.cms.datainfo.jgCtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC02TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC03TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.service.JgC02TitleService;
import gsis.com.cms.datainfo.jgCtitle.service.JgC03TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC02TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC03TiileDataVO;
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

@Service("JgC03TitleService")
public class JgC03TitleServiceImpl extends EgovAbstractServiceImpl implements JgC03TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgC03TitleDAO")
    private JgC03TitleDAO jgC03TitleDAO;



    @Override
    public List<JewC03TiileDataVO> selectList(JewC03TiileDataVO vo) throws Exception {
        return jgC03TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewC03TiileDataVO vo) throws Exception {


        for(JewC03TiileDataVO tVo : vo.getListData()){
            jgC03TitleDAO.delete(tVo);
            jgC03TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewC03TiileDataVO vo) throws Exception {


        jgC03TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewC03TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgCtitle/c03.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("3-3(경력단절 여성 규모)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewC03TiileDataVO> getSelectList(JewC03TiileDataVO vo) throws Exception {
        return jgC03TitleDAO.selectList(vo);
    }
}
