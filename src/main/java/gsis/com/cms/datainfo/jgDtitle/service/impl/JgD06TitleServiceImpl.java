package gsis.com.cms.datainfo.jgDtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD05TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD06TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.service.JgD05TitleService;
import gsis.com.cms.datainfo.jgDtitle.service.JgD06TitleService;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD05TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD06TiileDataVO;
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

@Service("JgD06TitleService")
public class JgD06TitleServiceImpl extends EgovAbstractServiceImpl implements JgD06TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgD06TitleDAO")
    private JgD06TitleDAO jgD06TitleDAO;



    @Override
    public List<JewD06TiileDataVO> selectList(JewD06TiileDataVO vo) throws Exception {
        return jgD06TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewD06TiileDataVO vo) throws Exception {


        for(JewD06TiileDataVO tVo : vo.getListData()){
            jgD06TitleDAO.delete(tVo);
            jgD06TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewD06TiileDataVO vo) throws Exception {


        jgD06TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewD06TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgDtitle/d06.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("4-17(등록 장애인 수)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewD06TiileDataVO> getSelectList(JewD06TiileDataVO vo) throws Exception {
        return jgD06TitleDAO.selectList(vo);
    }
}
