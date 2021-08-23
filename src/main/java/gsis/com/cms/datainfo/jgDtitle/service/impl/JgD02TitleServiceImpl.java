package gsis.com.cms.datainfo.jgDtitle.service.impl;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD01TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.dao.JgD02TitleDAO;
import gsis.com.cms.datainfo.jgDtitle.service.JgD01TitleService;
import gsis.com.cms.datainfo.jgDtitle.service.JgD02TitleService;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD01TiileDataVO;
import gsis.com.cms.datainfo.jgDtitle.vo.JewD02TiileDataVO;
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

@Service("JgD02TitleService")
public class JgD02TitleServiceImpl extends EgovAbstractServiceImpl implements JgD02TitleService {



    @Autowired
    private ResourceLoader resourceLoader;

    @Resource(name="JgD02TitleDAO")
    private JgD02TitleDAO jgD02TitleDAO;



    @Override
    public List<JewD02TiileDataVO> selectList(JewD02TiileDataVO vo) throws Exception {
        return jgD02TitleDAO.selectList(vo);
    }

    @Override
    public void insert(JewD02TiileDataVO vo) throws Exception {


        for(JewD02TiileDataVO tVo : vo.getListData()){
            jgD02TitleDAO.delete(tVo);
            jgD02TitleDAO.insert(tVo);
        }

    }

    @Override
    public void delete(JewD02TiileDataVO vo) throws Exception {


        jgD02TitleDAO.delete(vo);

    }

    @Override
    public void excelDownload(JewD02TiileDataVO searchVO, HttpServletResponse response) {

        try {


            InputStream templateStream = resourceLoader.getResource("classpath:/gsis/excel/jgDtitle/d02.xlsx").getInputStream();
            OutputStream targetStream = response.getOutputStream();

            Context context = new Context();
//            context.putVar("list", list);
//            context.putVar("equipMstList", equipMstList);



            SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss");
            String fileName = java.net.URLEncoder.encode("주관적 건강인지율_" +sdf.format(System.currentTimeMillis()), "UTF-8");
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
    public List<JewD02TiileDataVO> getSelectList(JewD02TiileDataVO vo) throws Exception {
        return jgD02TitleDAO.selectList(vo);
    }
}
