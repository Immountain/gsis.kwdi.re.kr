package gsis.com.cms.datainfo.jgCtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC01TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.dao.JgC02TitleDAO;
import gsis.com.cms.datainfo.jgCtitle.service.JgC01TitleService;
import gsis.com.cms.datainfo.jgCtitle.service.JgC02TitleService;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC01TiileDataVO;
import gsis.com.cms.datainfo.jgCtitle.vo.JewC02TiileDataVO;
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

@Service("JgC02TitleService")
public class JgC02TitleServiceImpl extends EgovAbstractServiceImpl implements JgC02TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgC02TitleDAO")
    private JgC02TitleDAO jgC02TitleDAO;



    @Override
    public List<JewC02TiileDataVO> selectList(JewC02TiileDataVO vo) throws Exception {
        return jgC02TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewC02TiileDataVO vo) throws Exception {


        for(JewC02TiileDataVO tVo : vo.getListData()){
            jgC02TitleDAO.delete(tVo);
            jgC02TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewC02TiileDataVO vo) throws Exception {


        jgC02TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewC02TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgCtitle/c02.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("3-2(연령별 경제활동인구 및 고용률)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
