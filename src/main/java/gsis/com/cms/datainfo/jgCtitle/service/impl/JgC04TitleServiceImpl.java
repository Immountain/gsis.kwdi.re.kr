package gsis.com.cms.datainfo.jgCtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC04TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.service.JgC04TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC04TiileDataVO;
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

@Service("JgC04TitleService")
public class JgC04TitleServiceImpl extends EgovAbstractServiceImpl implements JgC04TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgC04TitleDAO")
    private JgC04TitleDAO jgC04TitleDAO;



    @Override
    public List<JewC04TiileDataVO> selectList(JewC04TiileDataVO vo) throws Exception {
        return jgC04TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewC04TiileDataVO vo) throws Exception {


        for(JewC04TiileDataVO tVo : vo.getListData()){
            jgC04TitleDAO.delete(tVo);
            jgC04TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewC04TiileDataVO vo) throws Exception {


        jgC04TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewC04TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgCtitle/c04.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("3-4(여성의 경력단절 사유)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewC04TiileDataVO> getSelectList(JewC04TiileDataVO vo) throws Exception {
        return jgC04TitleDAO.selectList(vo);
    }
}
