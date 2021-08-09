package gsis.com.cms.datainfo.jgDtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD04TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD05TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.service.JgD04TitleService;
import gsis.com.cms.datainfo.jgDtitle.service.JgD05TitleService;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD04TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD05TiileDataVO;
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

@Service("JgD05TitleService")
public class JgD05TitleServiceImpl extends EgovAbstractServiceImpl implements JgD05TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgD05TitleDAO")
    private JgD05TitleDAO jgD05TitleDAO;



    @Override
    public List<JewD05TiileDataVO> selectList(JewD05TiileDataVO vo) throws Exception {
        return jgD05TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewD05TiileDataVO vo) throws Exception {


        for(JewD05TiileDataVO tVo : vo.getListData()){
            jgD05TitleDAO.delete(tVo);
            jgD05TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewD05TiileDataVO vo) throws Exception {


        jgD05TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewD05TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgDtitle/d05.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("4-13(출산전후휴가급여, 육아휴직급여, 육아기근로단축급여 초회 수급인원 및 지급액)_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewD05TiileDataVO> getSelectList(JewD05TiileDataVO vo) throws Exception {
        return jgD05TitleDAO.selectList(vo);
    }
}
