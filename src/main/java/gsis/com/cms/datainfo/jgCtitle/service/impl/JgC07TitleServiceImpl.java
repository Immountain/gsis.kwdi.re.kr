package gsis.com.cms.datainfo.jgCtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC03TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC07TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.service.JgC03TitleService;
import gsis.com.cms.datainfo.jgCtitle.service.JgC07TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC03TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC07TiileDataVO;
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

@Service("JgC07TitleService")
public class JgC07TitleServiceImpl extends EgovAbstractServiceImpl implements JgC07TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgC07TitleDAO")
    private JgC07TitleDAO jgC07TitleDAO;



    @Override
    public List<JewC07TiileDataVO> selectList(JewC07TiileDataVO vo) throws Exception {
        return jgC07TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewC07TiileDataVO vo) throws Exception {


        for(JewC07TiileDataVO tVo : vo.getListData()){
            jgC07TitleDAO.delete(tVo);
            jgC07TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewC07TiileDataVO vo) throws Exception {


        jgC07TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewC07TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgCtitle/c07.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("3-7(임금근로자의 월평균 임금)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewC07TiileDataVO> getSelectList(JewC07TiileDataVO vo) throws Exception {
        return jgC07TitleDAO.selectList(vo);
    }
}
