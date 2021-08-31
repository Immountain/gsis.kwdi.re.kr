package gsis.com.cms.datainfo.jgFtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgFtitle.dao.JgF03TitleDAO;
import gsis.com.cms.datainfo.jgFtitle.service.JgF03TitleService;
import gsis.com.cms.datainfo.jgFtitle.vo.JewF03TiileDataVO;
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

@Service("JgF03TitleService")
public class JgF03TitleServiceImpl extends EgovAbstractServiceImpl implements JgF03TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgF03TitleDAO")
    private JgF03TitleDAO jgF03TitleDAO;



    @Override
    public List<JewF03TiileDataVO> selectList(JewF03TiileDataVO vo) throws Exception {
        return jgF03TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewF03TiileDataVO vo) throws Exception {


        for(JewF03TiileDataVO tVo : vo.getListData()){
            jgF03TitleDAO.delete(tVo);
            jgF03TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewF03TiileDataVO vo) throws Exception {


        jgF03TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewF03TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgFtitle/f03.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("6-3(범죄발생 및 검거건수)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewF03TiileDataVO> getSelectList(JewF03TiileDataVO vo) throws Exception {
        return jgF03TitleDAO.selectList(vo);
    }
}
